
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;

 class Ball extends JPanel{
    private int x = getWidth()/2;
    private int y = getHeight()/2;
    private int speedx = 2;
    private int speedy = 2;
    private int ballsize = 10;

    public Ball() {
        
    }

    void updatestate() {
       // System.out.println("X : " + x + " Y : " + y + " size : " + ballsize);
       if(x < getWidth() && y < getHeight()){
        x += speedx;
        y += speedy;
       }

        if (x <= 0 || x + ballsize >= getWidth()) {
           
            speedx = -speedx;
            
           // playSound();
        }
        if (y <= 0 || y + ballsize >= getHeight()) {
           
            speedy = -speedy;
            
            //playSound();
        }
    }
private void playSound() {
        try {
            File soundFile = new File("beep.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error playing sound: " + e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_DEFAULT);
        g2d.fillOval(x, y, ballsize, ballsize);
    }

    

    
}


class gameloop implements Runnable {
    private boolean running;
    private final double updateRate = 1.0d/60.0d;
    private long nextStartTime = System.currentTimeMillis() + 1000;
    private int fps, ups;

    Ball ball = new Ball();

    public gameloop(){
        
        JFrame frame = new JFrame("Bouncing Ball");
        
        ball.setBackground(Color.BLACK);
        frame.add(ball);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // center the window
        frame.setVisible(true);

    }

    @Override
    public void run() {
        running = true;
        double accumilator = 0;
        long currentTime;
        long lastUpdateTime = System.currentTimeMillis();
        while(running) {
            currentTime = System.currentTimeMillis();
            accumilator += (currentTime - lastUpdateTime) / 1000.0d;
            lastUpdateTime = currentTime;
        
            while(accumilator >= updateRate) {
                update();
                accumilator -= updateRate;
            }
            
            printStats();
            render();
            
        }
    }
    
    private void printStats() {
        //System.out.println("hello ..... FPS: "+ fps + " UPS: "+ ups);
        if(System.currentTimeMillis() > nextStartTime) {
            System.out.println("FPS: "+ fps + " UPS: "+ ups);
        
        fps = 0;
        ups = 0;
        nextStartTime += 1000; // Reset stats every second
        }
    }
    private void update() {
        // Update game logic here
        
        ball.updatestate();
        ups++;
    }
    
    private void render() {
        // Render game state here
       ball.repaint();
        fps++;
    }

}

public class Game{
    public static void main(String[] args) {
        
        gameloop gameLoop = new gameloop();
        Thread gameLoopThread = new Thread(gameLoop);
        gameLoopThread.start();
    }
}




