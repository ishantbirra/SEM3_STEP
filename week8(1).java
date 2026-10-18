import java.util.Scanner;

public class Week8PracticeAssignment {

    // =========================================================================
    // PROBLEM 1: Payment System Fee Calculation
    // =========================================================================
    public static abstract class Payment {
        protected double amount;

        public Payment(double amount) {
            this.amount = amount;
        }

        public abstract double calculateAdjustedAmount();
        public abstract String getTypeName();
    }

    public static class CardPayment extends Payment {
        public CardPayment(double amount) { super(amount); }
        @Override public double calculateAdjustedAmount() { return amount * 1.02; }
        @Override public String getTypeName() { return "CARD"; }
    }

    public static class WalletPayment extends Payment {
        public WalletPayment(double amount) { super(amount); }
        @Override public double calculateAdjustedAmount() { return amount * 1.01; }
        @Override public String getTypeName() { return "WALLET"; }
    }

    public static class BankTransferPayment extends Payment {
        public BankTransferPayment(double amount) { super(amount); }
        @Override public double calculateAdjustedAmount() { return amount; }
        @Override public String getTypeName() { return "BANKTRANSFER"; }
    }

    public static void solvePaymentSystem(Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine().trim());
        Payment[] payments = new Payment[n];
        
        for (int i = 0; i < n; i++) {
            String[] parts = scanner.nextLine().trim().split("\\s+");
            String type = parts[0];
            double amount = Double.parseDouble(parts[1]);
            
            if (type.equals("CARD")) {
                payments[i] = new CardPayment(amount);
            } else if (type.equals("WALLET")) {
                payments[i] = new WalletPayment(amount);
            } else if (type.equals("BANKTRANSFER")) {
                payments[i] = new BankTransferPayment(amount);
            }
        }
        
        double grandTotal = 0;
        for (Payment p : payments) {
            double adjusted = p.calculateAdjustedAmount();
            grandTotal += adjusted;
            System.out.printf("%s: %.2f\n", p.getTypeName(), adjusted);
        }
        System.out.printf("Total: %.2f\n", grandTotal);
    }


    // =========================================================================
    // PROBLEM 2: Library Item Due Date Calculator
    // =========================================================================
    public static abstract class LibraryItem {
        protected String title;

        public LibraryItem(String title) {
            this.title = title;
        }

        public abstract int getBorrowingDays();
        public String getTitle() { return title; }
    }

    public static class Book extends LibraryItem {
        public Book(String title) { super(title); }
        @Override public int getBorrowingDays() { return 14; }
    }

    public static class DVD extends LibraryItem {
        public DVD(String title) { super(title); }
        @Override public int getBorrowingDays() { return 7; }
    }

    public static class Magazine extends LibraryItem {
        public Magazine(String title) { super(title); }
        @Override public int getBorrowingDays() { return 3; }
    }

    public static void solveLibraryCalculator(Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine().trim());
        LibraryItem[] items = new LibraryItem[n];
        
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine().trim();
            int firstSpace = line.indexOf(' ');
            String type = line.substring(0, firstSpace);
            String title = line.substring(firstSpace + 1).replace("\"", "");
            
            if (type.equals("BOOK")) {
                items[i] = new Book(title);
            } else if (type.equals("DVD")) {
                items[i] = new DVD(title);
            } else if (type.equals("MAGAZINE")) {
                items[i] = new Magazine(title);
            }
        }

        // Base date provided: 2023-10-26
        java.time.LocalDate currentDate = java.time.LocalDate.of(2023, 10, 26);
        for (LibraryItem item : items) {
            java.time.LocalDate dueDate = currentDate.plusDays(item.getBorrowingDays());
            System.out.println(item.getTitle() + ": " + dueDate.toString());
        }
    }


    // =========================================================================
    // PROBLEM 3: Delivery Fee Calculator
    // =========================================================================
    public static abstract class Delivery {
        protected double weight;
        protected double distance;

        public Delivery(double weight, double distance) {
            this.weight = weight;
            this.distance = distance;
        }

        public abstract double calculateFee();
        public abstract String getTypeName();
    }

    public static class StandardDelivery extends Delivery {
        public StandardDelivery(double weight, double distance) { super(weight, distance); }
        @Override public double calculateFee() { return 5.0 + (0.50 * weight) + (0.10 * distance); }
        @Override public String getTypeName() { return "STANDARD"; }
    }

    public static class ExpressDelivery extends Delivery {
        public ExpressDelivery(double weight, double distance) { super(weight, distance); }
        @Override public double calculateFee() { return 15.0 + (1.00 * weight) + (0.20 * distance); }
        @Override public String getTypeName() { return "EXPRESS"; }
    }

    public static class InternationalDelivery extends Delivery {
        private double customsFee;
        public InternationalDelivery(double weight, double distance, double customsFee) {
            super(weight, distance);
            this.customsFee = customsFee;
        }
        @Override public double calculateFee() { return 25.0 + (2.00 * weight) + (0.50 * distance) + customsFee; }
        @Override public String getTypeName() { return "INTERNATIONAL"; }
    }

    public static void solveDeliveryFee(Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine().trim());
        Delivery[] deliveries = new Delivery[n];

        for (int i = 0; i < n; i++) {
            String[] parts = scanner.nextLine().trim().split("\\s+");
            String type = parts[0];
            double weight = Double.parseDouble(parts[1]);
            double distance = Double.parseDouble(parts[2]);

            if (type.equals("STANDARD")) {
                deliveries[i] = new StandardDelivery(weight, distance);
            } else if (type.equals("EXPRESS")) {
                deliveries[i] = new ExpressDelivery(weight, distance);
            } else if (type.equals("INTERNATIONAL")) {
                double customs = Double.parseDouble(parts[3]);
                deliveries[i] = new InternationalDelivery(weight, distance, customs);
            }
        }

        double grandTotal = 0;
        for (Delivery d : deliveries) {
            double fee = d.calculateFee();
            grandTotal += fee;
            System.out.printf("%s: %.2f\n", d.getTypeName(), fee);
        }
        System.out.printf("Total: %.2f\n", grandTotal);
    }


    // =========================================================================
    // PROBLEM 4: Examination Question Grader
    // =========================================================================
    public static abstract class Question {
        protected String text;
        protected String correctAnswer;
        protected String studentAnswer;
        protected int points;

        public Question(String text, String correctAnswer, String studentAnswer, int points) {
            this.text = text;
            this.correctAnswer = correctAnswer;
            this.studentAnswer = studentAnswer;
            this.points = points;
        }

        public abstract double grade();
        public abstract String getTypeName();
    }

    public static class MCQQuestion extends Question {
        public MCQQuestion(String text, String correctAnswer, String studentAnswer, int points) {
            super(text, correctAnswer, studentAnswer, points);
        }
        @Override public double grade() {
            return studentAnswer.trim().equalsIgnoreCase(correctAnswer.trim()) ? points : 0.0;
        }
        @Override public String getTypeName() { return "MCQ"; }
    }

    public static class TFQuestion extends Question {
        public TFQuestion(String text, String correctAnswer, String studentAnswer, int points) {
            super(text, correctAnswer, studentAnswer, points);
        }
        @Override public double grade() {
            return studentAnswer.trim().equalsIgnoreCase(correctAnswer.trim()) ? points : 0.0;
        }
        @Override public String getTypeName() { return "TF"; }
    }

    public static class EssayQuestion extends Question {
        public EssayQuestion(String text, String correctAnswer, String studentAnswer, int points) {
            super(text, correctAnswer, studentAnswer, points);
        }
        @Override public double grade() {
            String[] keywords = correctAnswer.split(",");
            int matchCount = 0;
            String studentLower = studentAnswer.toLowerCase();
            for (String kw : keywords) {
                String trimmedKw = kw.trim().toLowerCase();
                if (!trimmedKw.isEmpty() && studentLower.contains(trimmedKw)) {
                    matchCount++;
                }
            }
            if (matchCount >= 2) return points * 0.75;
            else if (matchCount == 1) return points * 0.50;
            else return 0.0;
        }
        @Override public String getTypeName() { return "ESSAY"; }
    }

    public static void solveExamGrader(Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine().trim());
        Question[] questions = new Question[n];

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine().trim();
            int firstSpace = line.indexOf(' ');
            String type = line.substring(0, firstSpace);
            
            // Parse quoted or unquoted segments safely
            java.util.regex.Matcher m = java.util.regex.Pattern.compile("\"([^\"]*)\"|([^\s]+)").matcher(line.substring(firstSpace + 1));
            java.util.List<String> tokens = new java.util.ArrayList<>();
            while (m.find()) {
                tokens.add(m.group(1) != null ? m.group(1) : m.group(2));
            }

            String qText = tokens.get(0);
            String correct = tokens.get(1);
            String student = tokens.get(2);
            int pts = Integer.parseInt(tokens.get(3));

            if (type.equals("MCQ")) {
                questions[i] = new MCQQuestion(qText, correct, student, pts);
            } else if (type.equals("TF")) {
                questions[i] = new TFQuestion(qText, correct, student, pts);
            } else if (type.equals("ESSAY")) {
                questions[i] = new EssayQuestion(qText, correct, student, pts);
            }
        }

        double totalScore = 0;
        for (Question q : questions) {
            double score = q.grade();
            totalScore += score;
            System.out.printf("%s: %.2f\n", q.getTypeName(), score);
        }
        System.out.printf("Total Score: %.2f\n", totalScore);
    }


    // =========================================================================
    // PROBLEM 5: Public Transport Fare Calculator
    // =========================================================================
    public static abstract class Journey {
        protected double distance;

        public Journey(double distance) {
            this.distance = distance;
        }

        public abstract double calculateFare();
        public abstract String getTypeName();
    }

    public static class BusJourney extends Journey {
        public BusJourney(double distance) { super(distance); }
        @Override public double calculateFare() {
            double fare = 2.0 + (0.10 * distance);
            return Math.min(fare, 10.0);
        }
        @Override public String getTypeName() { return "BUS"; }
    }

    public static class TrainJourney extends Journey {
        public TrainJourney(double distance) { super(distance); }
        @Override public double calculateFare() { return 3.0 + (0.15 * distance); }
        @Override public String getTypeName() { return "TRAIN"; }
    }

    public static class MetroJourney extends Journey {
        private double peakHourFactor;
        public MetroJourney(double distance, double peakHourFactor) {
            super(distance);
            this.peakHourFactor = peakHourFactor;
        }
        @Override public double calculateFare() {
            return (1.50 + (0.20 * distance)) * peakHourFactor;
        }
        @Override public String getTypeName() { return "METRO"; }
    }

    public static void solveTransportFare(Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine().trim());
        Journey[] journeys = new Journey[n];

        for (int i = 0; i < n; i++) {
            String[] parts = scanner.nextLine().trim().split("\\s+");
            String type = parts[0];
            double distance = Double.parseDouble(parts[1]);

            if (type.equals("BUS")) {
                journeys[i] = new BusJourney(distance);
            } else if (type.equals("TRAIN")) {
                journeys[i] = new TrainJourney(distance);
            } else if (type.equals("METRO")) {
                double factor = Double.parseDouble(parts[2]);
                journeys[i] = new MetroJourney(distance, factor);
            }
        }

        double grandTotal = 0;
        for (Journey j : journeys) {
            double fare = j.calculateFare();
            grandTotal += fare;
            System.out.printf("%s: %.2f\n", j.getTypeName(), fare);
        }
        System.out.printf("Total: %.2f\n", grandTotal);
    }

    // Main method to test any problem interactively
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Example: Run Problem 1
        // solvePaymentSystem(scanner);
        scanner.close();
    }
}
