/*
Write a program that has an abstract class Polygon. Derive two classes Rectangle and Triangle 
from Polygon and write methods to get details of their dimensions hence calculate   the   area.

*/

//package java_Examples;

import java.util.Scanner;

abstract class Polygon {
    abstract void getDimensions();
    abstract double calculateArea();
    abstract double calculatePerimeter();
}
abstract class curvedshape {
    abstract void getDimensions();
    abstract double calculateArea();

    abstract double calculateCurvedPath();
}

class Rectangle extends Polygon {
    private double length, breadth;

    @Override
    void getDimensions() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the length of the rectangle: ");
        length = scanner.nextDouble();
        System.out.print("Enter the breadth of the rectangle: ");
        breadth = scanner.nextDouble();
    }

    @Override
    double calculateArea() {
        return length * breadth;
    }
    
    @Override
    double calculatePerimeter() {
        return 2 * (length + breadth);
    }
}

class pentagon extends Polygon{
    private double side;
    private double sideLength;
    
    @Override
    void getDimensions() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the side length of the pentagon: ");
        sideLength = scanner.nextDouble();
    }
    
    @Override
    double calculateArea() {
        return (5 * Math.pow(sideLength, 2)) / (4 * Math.tan(Math.PI / 5));
    }
    
    @Override
    double calculatePerimeter() {
        return 5 * sideLength;
    }
}

class RegularPolygon extends Polygon {
    private double side;
    private int numberOfSides;
    
    @Override
    void getDimensions() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the side length of the regular polygon: ");
        side = scanner.nextDouble();
        System.out.print("Enter the number of sides: ");
        numberOfSides = scanner.nextInt();
    }
    
    @Override
    double calculateArea() {
        return (numberOfSides * Math.pow(side, 2)) / (4 * Math.tan(Math.PI / numberOfSides));
    }
    
    @Override
    double calculatePerimeter() {
        return numberOfSides * side;
    }
}
class Circle extends curvedshape {
    private double radius;
    private  double PI = 3.14159;

    @Override
    void getDimensions() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the radius of the circle: ");
        radius = scanner.nextDouble();
    }
    
    @Override
    double calculateArea() {
        return PI * Math.pow(radius, 2);
    }

    @Override
    double calculateCurvedPath() {
        return PI * radius * (360 / 360);
    }
}

class Arc extends curvedshape{
    private double radius;
    private double angle;
    private double PI = 3.14159;
    
    @Override
    void getDimensions() {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the radius of the circle: ");
            radius = scanner.nextDouble();
            System.out.print("Enter the angle in degrees: ");
            angle = scanner.nextDouble();
        
    }
    
    @Override
    double calculateArea() {
        return (PI * Math.pow(radius, 2)) * (angle / 360);
    }

    @Override
    double calculateCurvedPath() {
        return (PI * radius * angle) / 360;
    }
    
}
class Square extends Rectangle {
    private double side;
    
    @Override
    void getDimensions() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the side length of the square: ");
        side = scanner.nextDouble();
    }

    
    @Override
    double calculateArea() {
        return Math.pow(side, 2);
    }
    
    @Override
    double calculatePerimeter() {
        return 4 * side;
    }
}

class Triangle extends Polygon {
    private double base, height;

    @Override
    void getDimensions() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the base of the triangle: ");
        base = scanner.nextDouble();
        System.out.print("Enter the height of the triangle: ");
        height = scanner.nextDouble();
    }

    @Override
    double calculateArea() {
        return 0.5 * base * height;
    }
    
    @Override
    double calculatePerimeter() {
        return Math.sqrt(Math.pow(base, 2) + Math.pow(height, 2));
    }
}

public  class program3{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        while(true){
        System.out.println("Enter 0 for exit. Choose a shape:\n 1. Rectangle\n 2. Triangle\n 3.Circle\n 4.Square\n 5.Pentagon\n 6.Any regularly n side polygon\n 7.Arc");
        int choice = scanner.nextInt();
        Polygon polygon;
        curvedshape  arc;
        if (choice == 0) {
            break;
        }
        else if (choice == 1) {
            polygon = new Rectangle();
        } else if (choice == 2) {
            polygon = new Triangle();
        }
        else if (choice == 3) {
            arc = new Circle();
            arc.getDimensions();
            System.out.println("Area: " + arc.calculateArea());
            System.out.println("lenth of curved path: " + arc.calculateCurvedPath());
            continue;
        }
        else if (choice == 4) {
            polygon = new Square();
        }
        else if (choice == 5) {
            polygon = new pentagon();
        }
        else if (choice == 6) {
            polygon = new RegularPolygon();
        } 
        else if (choice == 7) {
            arc = new Arc();
            arc.getDimensions();
            System.out.println("Area: " + arc.calculateArea());
            System.out.println("lenth of curved path: " + arc.calculateCurvedPath());
            continue;
        }
        else {
            System.out.println("Invalid choice! Please enter 1 t0 4.");
            continue;
        }

        polygon.getDimensions();
        System.out.println("Area: " + polygon.calculateArea());
        System.out.println("Perimeter: " + polygon.calculatePerimeter());
    }
    }
}
