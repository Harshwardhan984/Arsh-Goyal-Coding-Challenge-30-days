import java.util.Scanner;

class InvalidTimeException extends Exception {
    public InvalidTimeException(String message) {
        super(message);
    }
}

class TIME {
    private int hours, minutes, seconds;

    // Constructor to initialize TIME object
    public TIME(int hours, int minutes, int seconds) throws InvalidTimeException {
        if (hours < 0 || hours > 23) {
            throw new InvalidTimeException("Hours must be between 0 and 23");
          }
          if (minutes < 0 || minutes > 59) {
            throw new InvalidTimeException("Minutes must be between 0 and 59");
          }
          if (seconds < 0 || seconds > 59) {
            throw new InvalidTimeException("Seconds must be between 0 and 59");
          }
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    // Convert time to total seconds
    public int toSeconds() {
        return (hours * 3600) + (minutes * 60) + seconds;
    }

    // Calculate duration between two TIME objects
    public static TIME duration(TIME start, TIME end) throws InvalidTimeException {
        int startSeconds = start.toSeconds();
        int endSeconds = end.toSeconds();

        if (startSeconds > endSeconds) {
            throw new InvalidTimeException("Start time cannot be greater than end time!");
        }

        int diffSeconds = endSeconds - startSeconds;
        int hours = diffSeconds / 3600;
        int minutes = (diffSeconds % 3600) / 60;
        int seconds = diffSeconds % 60;

        return new TIME(hours, minutes, seconds);
    }

    // Display time in HH:MM:SS format
    public String display() {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}

public class TestDuration {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Get start time input
            System.out.print("Enter start time (hours 0-23): ");
            int startH = scanner.nextInt();
            System.out.print("Enter start time (minutes 0-59): ");
            int startM = scanner.nextInt();
            System.out.print("Enter start time (seconds 0-59): ");
            int startS = scanner.nextInt();

            // Get end time input
            System.out.print("Enter end time (hours 0-23): ");
            int endH = scanner.nextInt();
            System.out.print("Enter end time (minutes 0-59): ");
            int endM = scanner.nextInt();
            System.out.print("Enter end time (seconds 0-59): ");
            int endS = scanner.nextInt();

            // Create TIME objects
            TIME startTime = new TIME(startH, startM, startS);
            TIME endTime = new TIME(endH, endM, endS);

            // Calculate duration
            TIME duration = TIME.duration(startTime, endTime);

            // Display result
            System.out.println("\nTest Duration: " + duration.display());

        } catch (InvalidTimeException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Invalid input! Please enter integer values only.");
        } finally {
            scanner.close();
        }
    }
}