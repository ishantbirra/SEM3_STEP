import java.util.Scanner;
import java.time.LocalDate;

public class Week8Assignment {

    // =========================================================================
    // PROBLEM 1: The Canteen Billing Counter
    // =========================================================================
    public static abstract class CustomerBill {
        protected double amount;

        public CustomerBill(double amount) {
            this.amount = amount;
        }

        public abstract double calculateFinalAmount();
        public abstract String getCustomerType();
    }

    public static class StudentBill extends CustomerBill {
        public StudentBill(double amount) { super(amount); }
        @Override public double calculateFinalAmount() { return amount * 0.90; } // 10% discount
        @Override public String getCustomerType() { return "STUDENT"; }
    }

    public static class StaffBill extends CustomerBill {
        public StaffBill(double amount) { super(amount); }
        @Override public double calculateFinalAmount() { return amount * 0.95; } // 5% discount
        @Override public String getCustomerType() { return "STAFF"; }
    }

    public static class GuestBill extends CustomerBill {
        public GuestBill(double amount) { super(amount); }
        @Override public double calculateFinalAmount() { return amount + 10.0; } // Full amount plus 10 service charge
        @Override public String getCustomerType() { return "GUEST"; }
    }

    public static void solveCanteenBilling(Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine().trim());
        CustomerBill[] bills = new CustomerBill[n];

        for (int i = 0; i < n; i++) {
            String[] parts = scanner.nextLine().trim().split("\\s+");
            String type = parts[0];
            double amount = Double.parseDouble(parts[1]);

            if (type.equals("STUDENT")) {
                bills[i] = new StudentBill(amount);
            } else if (type.equals("STAFF")) {
                bills[i] = new StaffBill(amount);
            } else if (type.equals("GUEST")) {
                bills[i] = new GuestBill(amount);
            }
        }

        double grandTotal = 0;
        for (CustomerBill cb : bills) {
            double finalAmt = cb.calculateFinalAmount();
            grandTotal += finalAmt;
            System.out.printf("%s: %.2f\n", cb.getCustomerType(), finalAmt);
        }
        System.out.printf("Total: %.2f\n", grandTotal);
    }


    // =========================================================================
    // PROBLEM 2: The Campus Parking Charge Calculator
    // =========================================================================
    public static abstract class Vehicle {
        protected int hours;

        public Vehicle(int hours) {
            this.hours = hours;
        }

        public abstract double calculateCharge();
        public abstract String getVehicleType();
    }

    public static class Bike extends Vehicle {
        public Bike(int hours) { super(hours); }
        @Override public double calculateCharge() { return hours * 10.0; }
        @Override public String getVehicleType() { return "BIKE"; }
    }

    public static class Car extends Vehicle {
        public Car(int hours) { super(hours); }
        @Override public double calculateCharge() {
            if (hours <= 1) return 30.0;
            return 30.0 + (hours - 1) * 20.0;
        }
        @Override public String getVehicleType() { return "CAR"; }
    }

    public static class Truck extends Vehicle {
        public Truck(int hours) { super(hours); }
        @Override public double calculateCharge() {
            double charge = hours * 50.0;
            return Math.max(charge, 100.0); // minimum charge of 100
        }
        @Override public String getVehicleType() { return "TRUCK"; }
    }

    public static void solveParkingCalculator(Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine().trim());
        Vehicle[] vehicles = new Vehicle[n];

        for (int i = 0; i < n; i++) {
            String[] parts = scanner.nextLine().trim().split("\\s+");
            String type = parts[0];
            int hours = Integer.parseInt(parts[1]);

            if (type.equals("BIKE")) {
                vehicles[i] = new Bike(hours);
            } else if (type.equals("CAR")) {
                vehicles[i] = new Car(hours);
            } else if (type.equals("TRUCK")) {
                vehicles[i] = new Truck(hours);
            }
        }

        double grandTotal = 0;
        for (Vehicle v : vehicles) {
            double charge = v.calculateCharge();
            grandTotal += charge;
            System.out.printf("%s: %.2f\n", v.getVehicleType(), charge);
        }
        System.out.printf("Total: %.2f\n", grandTotal);
    }


    // =========================================================================
    // PROBLEM 3: The Hostel Electricity Bill
    // =========================================================================
    public static abstract class Room {
        protected double units;

        public Room(double units) {
            this.units = units;
        }

        public abstract double calculateBill();
        public abstract String getRoomType();
    }

    public static class SingleRoom extends Room {
        public SingleRoom(double units) { super(units); }
        @Override public double calculateBill() { return units * 8.0; }
        @Override public String getRoomType() { return "SINGLE"; }
    }

    public static class SharedRoom extends Room {
        private int occupants;
        public SharedRoom(double units, int occupants) {
            super(units);
            this.occupants = occupants;
        }
        @Override public double calculateBill() { return (units * 6.0) / occupants; }
        @Override public String getRoomType() { return "SHARED"; }
    }

    public static class ACRoom extends Room {
        public ACRoom(double units) { super(units); }
        @Override public double calculateBill() { return (units * 10.0) + 200.0; }
        @Override public String getRoomType() { return "AC"; }
    }

    public static void solveHostelElectricity(Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine().trim());
        Room[] rooms = new Room[n];

        for (int i = 0; i < n; i++) {
            String[] parts = scanner.nextLine().trim().split("\\s+");
            String type = parts[0];
            double units = Double.parseDouble(parts[1]);

            if (type.equals("SINGLE")) {
                rooms[i] = new SingleRoom(units);
            } else if (type.equals("SHARED")) {
                int occupants = Integer.parseInt(parts[2]);
                rooms[i] = new SharedRoom(units, occupants);
            } else if (type.equals("AC")) {
                rooms[i] = new ACRoom(units);
            }
        }

        double grandTotal = 0;
        for (Room r : rooms) {
            double bill = r.calculateBill();
            grandTotal += bill;
            System.out.printf("%s: %.2f\n", r.getRoomType(), bill);
        }
        System.out.printf("Total: %.2f\n", grandTotal);
    }


    // =========================================================================
    // PROBLEM 4: The Festival Bonus Calculator
    // =========================================================================
    public static abstract class Employee {
        protected String name;
        protected double monthlySalary;

        public Employee(String name, double monthlySalary) {
            this.name = name;
            this.monthlySalary = monthlySalary;
        }

        public abstract double calculateBonus();
        public String getName() { return name; }
    }

    public static class FullTimeEmployee extends Employee {
        public FullTimeEmployee(String name, double monthlySalary) { super(name, monthlySalary); }
        @Override public double calculateBonus() { return monthlySalary * 0.10; }
    }

    public static class PartTimeEmployee extends Employee {
        public PartTimeEmployee(String name, double monthlySalary) { super(name, monthlySalary); }
        @Override public double calculateBonus() { return monthlySalary * 0.05; }
    }

    public static class InternEmployee extends Employee {
        public InternEmployee(String name, double monthlySalary) { super(name, monthlySalary); }
        @Override public double calculateBonus() { return 2000.0; }
    }

    public static void solveFestivalBonus(Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine().trim());
        Employee[] employees = new Employee[n];

        for (int i = 0; i < n; i++) {
            String[] parts = scanner.nextLine().trim().split("\\s+");
            String type = parts[0];
            String name = parts[1];
            double salary = Double.parseDouble(parts[2]);

            if (type.equals("FULLTIME")) {
                employees[i] = new FullTimeEmployee(name, salary);
            } else if (type.equals("PARTTIME")) {
                employees[i] = new PartTimeEmployee(name, salary);
            } else if (type.equals("INTERN")) {
                employees[i] = new InternEmployee(name, salary);
            }
        }

        double grandTotalBonus = 0;
        for (Employee e : employees) {
            double bonus = e.calculateBonus();
            grandTotalBonus += bonus;
            System.out.printf("%s: %.2f\n", e.getName(), bonus);
        }
        System.out.printf("Total Bonus: %.2f\n", grandTotalBonus);
    }


    // =========================================================================
    // PROBLEM 5: The Streaming Plan Renewal Reminder
    // =========================================================================
    public static abstract class SubscriptionPlan {
        protected String name;
        protected LocalDate startDate;

        public SubscriptionPlan(String name, LocalDate startDate) {
            this.name = name;
            this.startDate = startDate;
        }

        public abstract int getValidityDays();
        public String getName() { return name; }
        public LocalDate getStartDate() { return startDate; }
    }

    public static class BasicPlan extends SubscriptionPlan {
        public BasicPlan(String name, LocalDate startDate) { super(name, startDate); }
        @Override public int getValidityDays() { return 30; }
    }

    public static class StandardPlan extends SubscriptionPlan {
        public StandardPlan(String name, LocalDate startDate) { super(name, startDate); }
        @Override public int getValidityDays() { return 90; }
    }

    public static class PremiumPlan extends SubscriptionPlan {
        public PremiumPlan(String name, LocalDate startDate) { super(name, startDate); }
        @Override public int getValidityDays() { return 365; }
    }

    public static void solveStreamingRenewal(Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine().trim());
        SubscriptionPlan[] plans = new SubscriptionPlan[n];

        for (int i = 0; i < n; i++) {
            String[] parts = scanner.nextLine().trim().split("\\s+");
            String type = parts[0];
            String name = parts[1];
            LocalDate startDate = LocalDate.parse(parts[2]);

            if (type.equals("BASIC")) {
                plans[i] = new BasicPlan(name, startDate);
            } else if (type.equals("STANDARD")) {
                plans[i] = new StandardPlan(name, startDate);
            } else if (type.equals("PREMIUM")) {
                plans[i] = new PremiumPlan(name, startDate);
            }
        }

        for (SubscriptionPlan plan : plans) {
            LocalDate renewalDate = plan.getStartDate().plusDays(plan.getValidityDays());
            System.out.println(plan.getName() + ": " + renewalDate.toString());
        }
    }

    // Main method to execute any solver interactively
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Example: Uncomment the function you want to run
        // solveCanteenBilling(scanner);
        // solveParkingCalculator(scanner);
        // solveHostelElectricity(scanner);
        // solveFestivalBonus(scanner);
        // solveStreamingRenewal(scanner);
        scanner.close();
    }
}
