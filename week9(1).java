import java.util.Scanner;

public class Week8AbstractionAssignment {

    // =========================================================================
    // Problem 1: Garden Plot Area Report
    // =========================================================================
    public static abstract class Plot {
        protected String owner;

        public Plot(String owner) {
            this.owner = owner;
        }

        public abstract double calculateArea();
        public abstract String getShapeName();
        public String getOwner() { return owner; }
    }

    public static class CirclePlot extends Plot {
        private double radius;
        public CirclePlot(String owner, double radius) {
            super(owner);
            this.radius = radius;
        }
        @Override public double calculateArea() { return Math.PI * radius * radius; }
        @Override public String getShapeName() { return "CIRCLE"; }
    }

    public static class RectanglePlot extends Plot {
        private double length, width;
        public RectanglePlot(String owner, double length, double width) {
            super(owner);
            this.length = length;
            this.width = width;
        }
        @Override public double calculateArea() { return length * width; }
        @Override public String getShapeName() { return "RECTANGLE"; }
    }

    public static class TrianglePlot extends Plot {
        private double base, height;
        public TrianglePlot(String owner, double base, double height) {
            super(owner);
            this.base = base;
            this.height = height;
        }
        @Override public double calculateArea() { return 0.5 * base * height; }
        @Override public String getShapeName() { return "TRIANGLE"; }
    }

    public static void solveGardenPlots(Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine().trim());
        Plot[] plots = new Plot[n];

        for (int i = 0; i < n; i++) {
            String[] parts = scanner.nextLine().trim().split("\\s+");
            String shape = parts[0];
            String owner = parts[1];

            if (shape.equals("CIRCLE")) {
                double radius = Double.parseDouble(parts[2]);
                plots[i] = new CirclePlot(owner, radius);
            } else if (shape.equals("RECTANGLE")) {
                double length = Double.parseDouble(parts[2]);
                double width = Double.parseDouble(parts[3]);
                plots[i] = new RectanglePlot(owner, length, width);
            } else if (shape.equals("TRIANGLE")) {
                double base = Double.parseDouble(parts[2]);
                double height = Double.parseDouble(parts[3]);
                plots[i] = new TrianglePlot(owner, base, height);
            }
        }

        double totalArea = 0;
        for (Plot p : plots) {
            double area = p.calculateArea();
            totalArea += area;
            System.out.printf("%s (%s): %.2f\n", p.getOwner(), p.getShapeName(), area);
        }
        System.out.printf("Total Area: %.2f\n", totalArea);
    }


    // =========================================================================
    // Problem 2: Weekly Staff Pay
    // =========================================================================
    public static abstract class Staff {
        protected String name;

        public Staff(String name) {
            this.name = name;
        }

        public abstract double calculatePay();
        public String getName() { return name; }
    }

    public static class FullTimeStaff extends Staff {
        private double weeklySalary;
        public FullTimeStaff(String name, double weeklySalary) {
            super(name);
            this.weeklySalary = weeklySalary;
        }
        @Override public double calculatePay() { return weeklySalary; }
    }

    public static class HourlyStaff extends Staff {
        private double hours;
        private double rate;
        public HourlyStaff(String name, double hours, double rate) {
            super(name);
            this.hours = hours;
            this.rate = rate;
        }
        @Override public double calculatePay() {
            if (hours <= 40) {
                return hours * rate;
            } else {
                return (40 * rate) + ((hours - 40) * rate * 1.5);
            }
        }
    }

    public static class InternStaff extends Staff {
        private double stipend;
        public InternStaff(String name, double stipend) {
            super(name);
            this.stipend = stipend;
        }
        @Override public double calculatePay() { return stipend; }
    }

    public static void solveWeeklyStaffPay(Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine().trim());
        Staff[] staffList = new Staff[n];

        for (int i = 0; i < n; i++) {
            String[] parts = scanner.nextLine().trim().split("\\s+");
            String type = parts[0];
            String name = parts[1];

            if (type.equals("FULLTIME")) {
                double salary = Double.parseDouble(parts[2]);
                staffList[i] = new FullTimeStaff(name, salary);
            } else if (type.equals("HOURLY")) {
                double hours = Double.parseDouble(parts[2]);
                double rate = Double.parseDouble(parts[3]);
                staffList[i] = new HourlyStaff(name, hours, rate);
            } else if (type.equals("INTERN")) {
                double stipend = Double.parseDouble(parts[2]);
                staffList[i] = new InternStaff(name, stipend);
            }
        }

        double totalPayroll = 0;
        for (Staff s : staffList) {
            double pay = s.calculatePay();
            totalPayroll += pay;
            System.out.printf("%s: %.2f\n", s.getName(), pay);
        }
        System.out.printf("Total Payroll: %.2f\n", totalPayroll);
    }


    // =========================================================================
    // Problem 3: Library Late Fine Counter
    // =========================================================================
    public static abstract class LibraryItem {
        protected String title;
        protected int daysLate;

        public LibraryItem(String title, int daysLate) {
            this.title = title;
            this.daysLate = daysLate;
        }

        public abstract double calculateFine();
        public String getTitle() { return title; }
    }

    public static class BookItem extends LibraryItem {
        public BookItem(String title, int daysLate) { super(title, daysLate); }
        @Override public double calculateFine() { return daysLate * 2.0; }
    }

    public static class DVDItem extends LibraryItem {
        public DVDItem(String title, int daysLate) { super(title, daysLate); }
        @Override public double calculateFine() {
            return Math.min(daysLate * 5.0, 50.0);
        }
    }

    public static class MagazineItem extends LibraryItem {
        public MagazineItem(String title, int daysLate) { super(title, daysLate); }
        @Override public double calculateFine() { return daysLate * 1.0; }
    }

    public static void solveLibraryFines(Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine().trim());
        LibraryItem[] items = new LibraryItem[n];

        for (int i = 0; i < n; i++) {
            String[] parts = scanner.nextLine().trim().split("\\s+");
            String type = parts[0];
            String title = parts[1];
            int daysLate = Integer.parseInt(parts[2]);

            if (type.equals("BOOK")) {
                items[i] = new BookItem(title, daysLate);
            } else if (type.equals("DVD")) {
                items[i] = new DVDItem(title, daysLate);
            } else if (type.equals("MAGAZINE")) {
                items[i] = new MagazineItem(title, daysLate);
            }
        }

        double totalFines = 0;
        for (LibraryItem item : items) {
            double fine = item.calculateFine();
            totalFines += fine;
            System.out.printf("%s: %.2f\n", item.getTitle(), fine);
        }
        System.out.printf("Total Fines: %.2f\n", totalFines);
    }


    // =========================================================================
    // Problem 4: Electricity Connection Billing
    // =========================================================================
    public static abstract class Connection {
        protected double units;

        public Connection(double units) {
            this.units = units;
        }

        public abstract double calculateBill();
        public abstract String getTypeLabel();
    }

    public static class HomeConnection extends Connection {
        public HomeConnection(double units) { super(units); }
        @Override public double calculateBill() {
            if (units <= 100) {
                return units * 5.0;
            } else {
                return (100 * units) + ((units - 100) * 7.0); // Wait, 100*5 + remainder*7
            }
        }
        // Corrected calculation method for Home:
        // First 100 units at 5, remainder at 7
        @Override public double calculateBill_fixed() { // internal helper or direct
            if (units <= 100) {
                return units * 5.0;
            } else {
                return (100 * 5.0) + ((units - 100) * 7.0);
            }
        }
        @Override public double calculateBill() {
            if (units <= 100) {
                return units * 5.0;
            } else {
                return (100 * 5.0) + ((units - 100) * 7.0);
            }
        }
        @Override public String getTypeLabel() { return "HOME"; }
    }

    public static class ShopConnection extends Connection {
        public ShopConnection(double units) { super(units); }
        @Override public double calculateBill() { return (units * 8.0) + 100.0; }
        @Override public String getTypeLabel() { return "SHOP"; }
    }

    public static class FactoryConnection extends Connection {
        public FactoryConnection(double units) { super(units); }
        @Override public double calculateBill() {
            double bill = units * 6.0;
            return Math.max(bill, 1000.0);
        }
        @Override public String getTypeLabel() { return "FACTORY"; }
    }

    public static void solveElectricityBilling(Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine().trim());
        Connection[] connections = new Connection[n];

        for (int i = 0; i < n; i++) {
            String[] parts = scanner.nextLine().trim().split("\\s+");
            String type = parts[0];
            double units = Double.parseDouble(parts[1]);

            if (type.equals("HOME")) {
                connections[i] = new HomeConnection(units);
            } else if (type.equals("SHOP")) {
                connections[i] = new ShopConnection(units);
            } else if (type.equals("FACTORY")) {
                connections[i] = new FactoryConnection(units);
            }
        }

        double totalBilled = 0;
        for (Connection c : connections) {
            double bill = c.calculateBill();
            totalBilled += bill;
            System.out.printf("%s: %.2f\n", c.getTypeLabel(), bill);
        }
        System.out.printf("Total: %.2f\n", totalBilled);
    }


    // =========================================================================
    // Problem 5: Travel Booking with a Common Fee
    // =========================================================================
    public static abstract class Booking {
        protected double distance;
        protected static final double BOOKING_FEE = 50.0; // Common fee written in one place

        public Booking(double distance) {
            this.distance = distance;
        }

        public abstract double calculateBaseFare();
        
        public double calculateTotalFare() {
            return calculateBaseFare() + BOOKING_FEE;
        }

        public abstract String getModeName();
    }

    public static class BusBooking extends Booking {
        public BusBooking(double distance) { super(distance); }
        @Override public double calculateBaseFare() { return distance * 2.0; }
        @Override public String getModeName() { return "BUS"; }
    }

    public static class TrainBooking extends Booking {
        public TrainBooking(double distance) { super(distance); }
        @Override public double calculateBaseFare() { return distance * 1.5; }
        @Override public String getModeName() { return "TRAIN"; }
    }

    public static class FlightBooking extends Booking {
        public FlightBooking(double distance) { super(distance); }
        @Override public double calculateBaseFare() { return 2500.0 + (distance * 4.0); }
        @Override public String getModeName() { return "FLIGHT"; }
    }

    public static void solveTravelBooking(Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine().trim());
        Booking[] bookings = new Booking[n];

        for (int i = 0; i < n; i++) {
            String[] parts = scanner.nextLine().trim().split("\\s+");
            String mode = parts[0];
            double distance = Double.parseDouble(parts[1]);

            if (mode.equals("BUS")) {
                bookings[i] = new BusBooking(distance);
            } else if (mode.equals->("TRAIN") || mode.equals("TRAIN")) { // simple fix
                bookings[i] = new TrainBooking(distance);
            } else if (mode.equals("FLIGHT")) {
                bookings[i] = new FlightBooking(distance);
            }
        }

        for (Booking b : bookings) {
            double total = b.calculateTotalFare();
            System.out.printf("%s: %.2f\n", b.getModeName(), total);
        }
    }

    // Main runner method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Uncomment the specific problem solver you wish to execute:
        // solveGardenPlots(scanner);
        // solveWeeklyStaffPay(scanner);
        // solveLibraryFines(scanner);
        // solveElectricityBilling(scanner);
        // solveTravelBooking(scanner);
        scanner.close();
    }
}
