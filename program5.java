import java.util.Scanner;
/*
  Program Statement:
Write a program that defines a class TIME, which stores and processes time in hours, minutes, and seconds. The program should:

Accept the start time and end time of an online test from the user.
Validate the input to ensure correct time values.
Calculate the duration taken to complete the test by subtracting the start time from the end time.
Handle exceptions for invalid data (e.g., negative values, exceeding 23 hours, 59 minutes, or 59 seconds) and cases where the start time is greater than the end time.
Display the total test duration in hours, minutes, and seconds.

 
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
        if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59 || seconds < 0 || seconds > 59) {
            throw new InvalidTimeException("Invalid time! Hours must be 0-23, minutes and seconds 0-59.");
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

public class program5 {
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


*/



//  Exceptions
class InvalidPassengerException extends Exception {
    public InvalidPassengerException(String message) {
        super(message);
    }
}

class InvalidDestinationException extends Exception {
    public InvalidDestinationException(String message) {
        super(message);
    }
}

class InvalidPaymentException extends Exception {
    public InvalidPaymentException(String message) {
        super(message);
    }
}


class FlightBooking {

    private String passengerName;
    private String destination;
    private double ticketPrice;
    private boolean isPaid;

    // Available Destinations
    private static final String[] VALID_DESTINATIONS = {"Mumbai", "Delhi", "Bangalore", "Pune", "Chennai"};

    public FlightBooking(String passengerName, String destination, double ticketPrice) throws InvalidPassengerException, InvalidDestinationException {
        if (passengerName == null || passengerName.trim().isEmpty()) {
            throw new InvalidPassengerException("Passenger name cannot be empty.");
        }
        if (!isValidDestination(destination)) {
            throw new InvalidDestinationException("Invalid destination! Choose from Mumbai, Delhi, Bangalore, Pune, or Chennai.");
        }
        if (ticketPrice <= 0) {
            throw new IllegalArgumentException("Ticket price must be greater than zero.");
        }
        
        this.passengerName = passengerName;
        this.destination = destination;
        this.ticketPrice = ticketPrice;
        this.isPaid = false;
    }

    // Validate Destination
    private static boolean isValidDestination(String destination) {
        for (String city : VALID_DESTINATIONS) {
            if (city.equalsIgnoreCase(destination)) {
                return true;
            }
        }
        return false;
    }

    // Payment Method
    public void makePayment(double amount) throws InvalidPaymentException {
        if (amount < ticketPrice) {
            throw new InvalidPaymentException("Insufficient amount! Ticket price is $" + ticketPrice);
        } else if (amount > ticketPrice) {
            System.out.println("Extra $" + (amount - ticketPrice) + " refunded.");
        }
        this.isPaid = true;
        System.out.println("Payment successful! Ticket booked for " + passengerName);
    }

    
    public void displayBooking() {
        System.out.println("\n--- Flight Ticket Details ---");
        System.out.println("Passenger Name: " + passengerName);
        System.out.println("Destination: " + destination);
        System.out.println("Ticket Price: $" + ticketPrice);
        System.out.println("Payment Status: " + (isPaid ? "Paid" : "Pending"));
    }
}

// Main Class
public class program5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Input Passenger Details
            System.out.print("Enter Passenger Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Destination (Mumbai, Delhi, Bangalore, Pune, Chennai): ");
            String destination = scanner.nextLine();

            System.out.print("Enter Ticket Price: $");
            double ticketPrice = scanner.nextDouble();

            // Create Booking
            FlightBooking booking = new FlightBooking(name, destination, ticketPrice);
            booking.displayBooking();

            // Payment Process
            System.out.print("\nEnter Payment Amount: $");
            double payment = scanner.nextDouble();
            booking.makePayment(payment);
            booking.displayBooking();

        } catch (InvalidPassengerException e) {
            System.out.println("Booking Error: " + e.getMessage());
        } catch (InvalidDestinationException e) {
            System.out.println("Booking Error: " + e.getMessage());
        } catch (InvalidPaymentException e) {
            System.out.println("Payment Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Input Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected Error: Please enter valid inputs.");
        } finally {
            scanner.close();
            System.out.println("\nThank you for using the Flight Booking System!");
        }
    }
}

