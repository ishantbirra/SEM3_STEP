import java.util.Scanner;

public class Week9AssignmentCategoryC {

    // =========================================================================
    // Problem 1: Movie Ticket Counter
    // =========================================================================
    public static abstract class MovieTicket {
        protected int count;
        protected static final double CONVENIENCE_FEE = 20.0; // Written in one place only

        public MovieTicket(int count) {
            this.count = count;
        }

        public abstract double getTicketPrice();
        
        public double calculateTotal() {
            return (getTicketPrice() + CONVENIENCE_FEE) * count;
        }

        public abstract String getSeatType();
    }

    public static class RegularTicket extends MovieTicket {
        public RegularTicket(int count) { super(count); }
        @Override public double getTicketPrice() { return 150.0; }
        @Override public String getSeatType() { return "REGULAR"; }
    }

    public static class PremiumTicket extends MovieTicket {
        public PremiumTicket(int count) { super(count); }
        @Override public double getTicketPrice() { return 250.0; }
        @Override public String getSeatType() { return "PREMIUM"; }
    }

    public static class ReclinerTicket extends MovieTicket {
        public ReclinerTicket(int count) { super(count); }
        @Override public double getTicketPrice() { return 400.0; }
        @Override public String getSeatType() { return "RECLINER"; }
    }

    public static void solveMovieTicketCounter(Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine().trim());
        MovieTicket[] tickets = new MovieTicket[n];

        for (int i = 0; i < n; i++) {
            String[] parts = scanner.nextLine().trim().split("\\s+");
            String seat = parts[0];
            int count = Integer.parseInt(parts[1]);

            if (seat.equals("REGULAR")) {
                tickets[i] = new RegularTicket(count);
            } else if (seat.equals("PREMIUM")) {
                tickets[i] = new PremiumTicket(count);
            } else if (seat.equals("RECLINER")) {
                tickets[i] = new ReclinerTicket(count);
            }
        }

        double total = 0;
        for (MovieTicket t : tickets) {
            double amt = t.calculateTotal();
            total += amt;
            System.out.printf("%s: %.2f\n", t.getSeatType(), amt);
        }
        System.out.printf("Total: %.2f\n", total);
    }


    // =========================================================================
    // Problem 2: Parcel Shipping Desk
    // =========================================================================
    public interface Insurable {
        double calculateInsurance(double declaredValue);
    }

    public static abstract class Parcel {
        protected double weightKg;
        protected double declaredValue;

        public Parcel(double weightKg, double declaredValue) {
            this.weightKg = weightKg;
            this.declaredValue = declaredValue;
        }

        public abstract double calculateCharge();
        public abstract String getTypeName();
    }

    public static class StandardParcel extends Parcel {
        public StandardParcel(double weightKg, double declaredValue) { super(weightKg, declaredValue); }
        @Override public double calculateCharge() { return 40.0 + (10.0 * weightKg); }
        @Override public String getTypeName() { return "STANDARD"; }
    }

    public static class ExpressParcel extends Parcel implements Insurable {
        public ExpressParcel(double weightKg, double declaredValue) { super(weightKg, declaredValue); }
        @Override public double calculateCharge() { return 80.0 + (15.0 * weightKg); }
        @Override public double calculateInsurance(double declaredValue) { return declaredValue * 0.02; }
        @Override public String getTypeName() { return "EXPRESS"; }
    }

    public static class FragileParcel extends Parcel implements Insurable {
        public FragileParcel(double weightKg, double declaredValue) { super(weightKg, declaredValue); }
        @Override public double calculateCharge() {
            // Standard charge (40 + 10 * weight) plus handling fee of 50
            return 40.0 + (10.0 * weightKg) + 50.0;
        }
        @Override public double calculateInsurance(double declaredValue) { return declaredValue * 0.02; }
        @Override public String getTypeName() { return "FRAGILE"; }
    }

    public static void solveParcelShipping(Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine().trim());
        Parcel[] parcels = new Parcel[n];

        for (int i = 0; i < n; i++) {
            String[] parts = scanner.nextLine().trim().split("\\s+");
            String type = parts[0];
            double weight = Double.parseDouble(parts[1]);
            double val = Double.parseDouble(parts[2]);

            if (type.equals("STANDARD")) {
                parcels[i] = new StandardParcel(weight, val);
            } else if (type.equals("EXPRESS")) {
                parcels[i] = new ExpressParcel(weight, val);
            } else if (type.equals("FRAGILE")) {
                parcels[i] = new FragileParcel(weight, val);
            }
        }

        double grandTotal = 0;
        for (Parcel p : parcels) {
            double charge = p.calculateCharge();
            double insurance = 0.0;
            if (p instanceof Insurable) {
                insurance = ((Insurable) p).calculateInsurance(p.declaredValue);
            }
            double total = charge + insurance;
            grandTotal += total;
            System.out.printf("%s: Charge=%.2f Insurance=%.2f Total=%.2f\n", p.getTypeName(), charge, insurance, total);
        }
        System.out.printf("Grand Total: %.2f\n", grandTotal);
    }


    // =========================================================================
    // Problem 3: College Fee Counter
    // =========================================================================
    public interface BusUser {
        double getTransportFee();
    }

    public static abstract class Student {
        protected String name;
        protected static final double TRANSPORT_FEE = 12000.0;

        public Student(String name) {
            this.name = name;
        }

        public abstract double calculateTuitionAndFees();
        public String getName() { return name; }
    }

    public static class DayScholar extends Student implements BusUser {
        public DayScholar(String name) { super(name); }
        @Override public double calculateTuitionAndFees() {
            return 40000.0 + getTransportFee();
        }
        @Override public double getTransportFee() { return TRANSPORT_FEE; }
    }

    public static class Hosteller extends Student {
        public Hosteller(String name) { super(name); }
        @Override public double calculateTuitionAndFees() {
            return 40000.0 + 60000.0; // tuition + hostel fee
        }
    }

    public static class ScholarStudent extends Student implements BusUser {
        public ScholarStudent(String name) { super(name); }
        @Override public double calculateTuitionAndFees() {
            return 20000.0 + getTransportFee(); // half of normal tuition (20000) + transport
        }
        @Override public double getTransportFee() { return TRANSPORT_FEE; }
    }

    public static void solveCollegeFeeCounter(Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine().trim());
        Student[] students = new Student[n];

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine().trim();
            int firstSpace = line.indexOf(' ');
            String type = line.substring(0, firstSpace);
            String name = line.substring(firstSpace + 1);

            if (type.equals("DAY_SCHOLAR")) {
                students[i] = new DayScholar(name);
            } else if (type.equals("HOSTELLER")) {
                students[i] = new Hosteller(name);
            } else if (type.equals("SCHOLAR")) {
                students[i] = new ScholarStudent(name);
            }
        }

        double totalCollected = 0;
        for (Student s : students) {
            double fee = s.calculateTuitionAndFees();
            totalCollected += fee;
            System.out.printf("%s: %.2f\n", s.getName(), fee);
        }
        System.out.printf("Total Collected: %.2f\n", totalCollected);
    }


    // =========================================================================
    // Problem 4: City Cab Fare Meter
    // =========================================================================
    public interface NightServiceable {
        boolean supportsNightService();
    }

    public static abstract class Cab {
        protected double distance;
        protected String time;

        public Cab(double distance, String time) {
            this.distance = distance;
            this.time = time;
        }

        public abstract double getRatePerKm();
        public abstract String getCabType();

        public double calculateFare() {
            double baseFare = distance * getRatePerKm();
            baseFare = Math.max(baseFare, 100.0); // Never less than 100
            if (time.equals("NIGHT")) {
                if (this instanceof NightServiceable && ((NightServiceable) this).supportsNightService()) {
                    baseFare *= 1.20; // Adds 20% to the fare after minimum is applied
                }
            }
            return baseFare;
        }
    }

    public static class MiniCab extends Cab {
        public MiniCab(double distance, String time) { super(distance, time); }
        @Override public double getRatePerKm() { return 10.0; }
        @Override public String getCabType() { return "MINI"; }
    }

    public static class SedanCab extends Cab implements NightServiceable {
        public SedanCab(double distance, String time) { super(distance, time); }
        @Override public double getRatePerKm() { return 14.0; }
        @Override public String getCabType() { return "SEDAN"; }
        @Override public boolean supportsNightService() { return true; }
    }

    public static class SuvCab extends Cab implements NightServiceable {
        public SuvCab(double distance, String time) { super(distance, time); }
        @Override public double getRatePerKm() { return 18.0; }
        @Override public String getCabType() { return "SUV"; }
        @Override public boolean supportsNightService() { return true; }
    }

    public static void solveCityCabMeter(Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine().trim());
        Cab[] cabs = new Cab[n];
        String[] rawInputs = new String[n];

        for (int i = 0; i < n; i++) {
            rawInputs[i] = scanner.nextLine().trim();
            String[] parts = rawInputs[i].split("\\s+");
            String type = parts[0];
            double dist = Double.parseDouble(parts[1]);
            String time = parts[2];

            if (type.equals("MINI")) {
                cabs[i] = new MiniCab(dist, time);
            } else if (type.equals("SEDAN")) {
                cabs[i] = new SedanCab(dist, time);
            } else if (type.equals("SUV")) {
                cabs[i] = new SuvCab(dist, time);
            }
        }

        double total = 0;
        for (int i = 0; i < n; i++) {
            Cab c = cabs[i];
            String[] parts = rawInputs[i].split("\\s+");
            String type = parts[0];
            String time = parts[2];

            if (type.equals("MINI") && time.equals("NIGHT")) {
                System.out.println(type + ": night service not available");
            } else {
                double fare = c.calculateFare();
                total += fare;
                System.out.printf("%s: %.2f\n", c.getCabType(), fare);
            }
        }
        System.out.printf("Total: %.2f\n", total);
    }


    // =========================================================================
    // Problem 5: Home Appliance Energy Report
    // =========================================================================
    public interface SaverModeSupport {
        boolean supportsSaver();
    }

    public static abstract class Appliance {
        protected double hours;
        protected boolean isSaver;

        public Appliance(double hours, boolean isSaver) {
            this.hours = hours;
            this.isSaver = isSaver;
        }

        public abstract double getPowerRatingWatts();
        public abstract String getApplianceName();

        public double calculateUnits() {
            double power = getPowerRatingWatts();
            double units = (power * hours) / 1000.0;
            if (isSaver) {
                units *= 0.75; // Reduced by 25%
            }
            return units;
        }

        public double calculateCost() {
            return calculateUnits() * 8.0;
        }
    }

    public static class Fridge extends Appliance {
        public Fridge(double hours, boolean isSaver) { super(hours, isSaver); }
        @Override public double getPowerRatingWatts() { return 150.0; }
        @Override public String getApplianceName() { return "FRIDGE"; }
    }

    public static class AC extends Appliance implements SaverModeSupport {
        public AC(double hours, boolean isSaver) { super(hours, isSaver); }
        @Override public double getPowerRatingWatts() { return 1500.0; }
        @Override public String getApplianceName() { return "AC"; }
        @Override public boolean supportsSaver() { return true; }
    }

    public static class TV extends Appliance {
        public TV(double hours, boolean isSaver) { super(hours, isSaver); }
        @Override public double getPowerRatingWatts() { return 100.0; }
        @Override public String getApplianceName() { return "TV"; }
    }

    public static class Washer extends Appliance implements SaverModeSupport {
        public Washer(double hours, boolean isSaver) { super(hours, isSaver); }
        @Override public double getPowerRatingWatts() { return 500.0; }
        @Override public String getApplianceName() { return "WASHER"; }
        @Override public boolean supportsSaver() { return true; }
    }

    public static void solveApplianceEnergy(Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine().trim());
        String[] rawInputs = new String[n];
        Appliance[] apps = new Appliance[n];

        for (int i = 0; i < n; i++) {
            rawInputs[i] = scanner.nextLine().trim();
            String[] parts = rawInputs[i].split("\\s+");
            String name = parts[0];
            double hours = Double.parseDouble(parts[1]);
            boolean saver = parts.length > 2 && parts[2].equals("SAVER");

            if (name.equals("FRIDGE")) {
                apps[i] = new Fridge(hours, saver);
            } else if (name.equals("AC")) {
                apps[i] = new AC(hours, saver);
            } else if (name.equals("TV")) {
                apps[i] = new TV(hours, saver);
            } else if (name.equals("WASHER")) {
                apps[i] = new Washer(hours, saver);
            }
        }

        double totalCost = 0;
        for (int i = 0; i < n; i++) {
            Appliance a = apps[i];
            String[] parts = rawInputs[i].split("\\s+");
            String name = parts[0];
            boolean saver = parts.length > 2 && parts[2].equals("SAVER");

            if (saver && !(a instanceof SaverModeSupport)) {
                System.out.println(name + ": saver mode not supported");
            } else {
                double units = a.calculateUnits();
                double cost = a.calculateCost();
                totalCost += cost;
                System.out.printf("%s: Units=%.2f Cost=%.2f\n", a.getApplianceName(), units, cost);
            }
        }
        System.out.printf("Total Cost: %.2f\n", totalCost);
    }

    // Main method to test any problem interactively
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Uncomment the specific problem solver you wish to execute:
        // solveMovieTicketCounter(scanner);
        // solveParcelShipping(scanner);
        // solveCollegeFeeCounter(scanner);
        // solveCityCabMeter(scanner);
        // solveApplianceEnergy(scanner);
        scanner.close();
    }
}
