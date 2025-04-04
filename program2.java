import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Locale;

class Person {
    String name;
    String code;

    public Person(String name, String code) {
        this.name = name;
        this.code = code;
    }
}

class Account extends Person {
    double pay;

    public Account(String name, String code, double pay) {
        super(name, code);
        this.pay = pay;
    }
}

class Admin extends Person {
    int experience;

    public Admin(String name, String code, int experience) {
        super(name, code);
        this.experience = experience;
    }
}

class Employee extends Account {
    int experience;

    public Employee(String name, String code, double pay, int experience) {
        super(name, code, pay);
        this.experience = experience;
    }

    public void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("Code: " + code);
        System.out.println("Pay: " + pay);
        System.out.println("Experience: " + experience + " years");
    }
}

class Manager extends Admin {
    String department;
    public Manager(String name, String code, int experience, String department) {
        super(name, code, experience);
        this.department = department;
    }
    public void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("Code: " + code);
        System.out.println("Experience: " + experience + " years");
        System.out.println("Department: " + department);
    }
    public void displayDepartmentDetails() {
        System.out.println("Department: " + department);
        System.out.println("Manager Details:");
        displayDetails();
    }
}

public class program2 {
    public static void main(String[] args) {
        // Create Employee object
        Employee employee = new Employee("John Doe", "E123", 5000.00, 5);

        // Display employee details
        employee.displayDetails();

        Manager manager = new Manager("harsh", "HR123", 3, "java devlop");
        // Display manager details
        manager.displayDetails();
        manager.displayDepartmentDetails();

        // Display date and time information
        displayDateInfo();
    }

    public static void displayDateInfo() {
        LocalDateTime now = LocalDateTime.now();

        // Format the current date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("\nDate and Time Information:");
        System.out.println("Current Date and Time: " + now.format(formatter));

        // Display current year
        System.out.println("Current Year: " + now.getYear());

        // Display month of year
        System.out.println("Month of Year: " + now.getMonth());

        // Get the week number of the year
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int weekNumber = now.get(weekFields.weekOfWeekBasedYear());
        System.out.println("Week Number of the Year: " + weekNumber);

        // Display the day of the week (Weekend of the week)
        System.out.println("Weekend of the Week: " + now.getDayOfWeek());

        // Display day of the year
        System.out.println("Day of the Year: " + now.getDayOfYear());

        // Display day of the month
        System.out.println("Day of the Month: " + now.getDayOfMonth());

        // Display the day of the week
        System.out.println("Day of the Week: " + now.getDayOfWeek());
    }
}


    

