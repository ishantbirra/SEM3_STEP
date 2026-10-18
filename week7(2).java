public class Week7PracticeAssignment {

    // ==========================================
    // Problem 1: The Piggy Bank
    // ==========================================
    public static class PiggyBank {
        private final String id;
        private double savings;

        public PiggyBank(String id) {
            this.id = id;
            this.savings = 0.0; // starts at 0 savings
        }

        public void deposit(double amount) {
            if (amount > 0) {
                this.savings += amount;
            }
        }

        public void withdraw(double amount) {
            if (amount > 0 && amount <= this.savings) {
                this.savings -= amount;
            } else {
                System.out.println("Withdrawal of " + amount + " rejected: insufficient funds or invalid amount.");
            }
        }

        public double getSavings() {
            return this.savings;
        }

        public String getId() {
            return this.id;
        }
    }

    // ==========================================
    // Problem 2: The Quiz Scorecard
    // ==========================================
    public static class Scorecard {
        private boolean[] results;
        private int count;

        public Scorecard(int totalQuestions) {
            this.results = new boolean[totalQuestions];
            this.count = 0;
        }

        public void recordAnswer(boolean isCorrect) {
            if (count < results.length) {
                results[count++] = isCorrect;
            } else {
                System.out.println("Cannot record more answers than the fixed question count.");
            }
        }

        public int getScore() {
            int score = 0;
            for (int i = 0; i < count; i++) {
                if (results[i]) {
                    score++;
                }
            }
            return score;
        }
    }

    // ==========================================
    // Problem 3: The Nickname Tag
    // ==========================================
    public static class NameTag {
        private final String firstName;
        private final String lastNameInitial;

        public NameTag(String fullName) {
            String[] parts = fullName.split(" ");
            this.firstName = parts[0];
            // Take the first character of the last name
            this.lastNameInitial = (parts.length > 1) ? parts[1].substring(0, 1) : "";
        }

        public String getNickname() {
            return firstName + " " + lastNameInitial + ".";
        }
    }

    // ==========================================
    // Problem 4: The Locker Code
    // ==========================================
    public static class Locker {
        private final int lockerNumber;
        private String combination;

        public Locker(int lockerNumber, String initialCode) {
            this.lockerNumber = lockerNumber;
            this.combination = initialCode;
        }

        public void changeCode(String currentCode, String newCode) {
            if (this.combination.equals(currentCode)) {
                this.combination = newCode;
                System.out.println("Code changed successfully.");
            } else {
                System.out.println("Change rejected: incorrect current code.");
            }
        }

        public int getLockerNumber() {
            return this.lockerNumber;
        }
    }

    // ==========================================
    // Problem 5: The Attendance Sheet
    // ==========================================
    public static class AttendanceSheet {
        private String[] presentStudents;
        private int count;

        public AttendanceSheet(int capacity) {
            this.presentStudents = new String[capacity];
            this.count = 0;
        }

        public void markPresent(String name) {
            if (isPresent(name)) {
                return; // Avoid duplicates
            }
            if (count < presentStudents.length) {
                presentStudents[count++] = name;
            }
        }

        public boolean isPresent(String name) {
            for (int i = 0; i < count; i++) {
                if (presentStudents[i].equals(name)) {
                    return true;
                }
            }
            return false;
        }

        public int getPresentCount() {
            return count;
        }
    }

    // ==========================================
    // MAIN METHOD: Test All Examples
    // ==========================================
    public static void main(String[] args) {
        System.out.println("--- Problem 1 Test: Piggy Bank ---");
        PiggyBank pb = new PiggyBank("PB-1");
        pb.deposit(100);
        System.out.println("After depositing 100 -> savings = " + pb.getSavings());
        pb.withdraw(30);
        System.out.println("After withdrawing 30 -> savings = " + pb.getSavings());
        pb.withdraw(500);
        System.out.println("After invalid withdrawal -> savings stays = " + pb.getSavings());

        System.out.println("\n--- Problem 2 Test: Quiz Scorecard ---");
        Scorecard sc = new Scorecard(4);
        sc.recordAnswer(true);
        sc.recordAnswer(true);
        sc.recordAnswer(false);
        sc.recordAnswer(true);
        System.out.println("Total score -> " + sc.getScore());

        System.out.println("\n--- Problem 3 Test: Nickname Tag ---");
        NameTag tag = new NameTag("Maria Gomez");
        System.out.println("Nickname -> " + tag.getNickname());

        System.out.println("\n--- Problem 4 Test: Locker Code ---");
        Locker l = new Locker(101, "1234");
        l.changeCode("1234", "5678");
        l.changeCode("0000", "9999");

        System.out.println("\n--- Problem 5 Test: Attendance Sheet ---");
        AttendanceSheet sheet = new AttendanceSheet(30);
        sheet.markPresent("Ana");
        sheet.markPresent("Ben");
        sheet.markPresent("Ana"); // Duplicate, shouldn't increase count
        System.out.println("Present count -> " + sheet.getPresentCount());
        System.out.println("Is Ben present? -> " + sheet.isPresent("Ben"));
        System.out.println("Is Chen present? -> " + sheet.isPresent("Chen"));
    }
}
