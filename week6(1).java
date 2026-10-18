public class Week6PracticeAssignment {

    // ==========================================
    // M1. Student Placement Record Management
    // ==========================================
    public static class PlacementRecord {
        String studentName;
        String company;
        double packageLpa;

        public PlacementRecord(String studentName, String company, double packageLpa) {
            this.studentName = studentName;
            this.company = company;
            this.packageLpa = packageLpa;
        }

        public void printRecord() {
            System.out.println(studentName + " -> " + company + " @ " + packageLpa + " LPA");
        }
    }

    // ==========================================
    // M2. Hostel Mess Wallet Management
    // ==========================================
    public static class MessWallet {
        private double balance;

        public MessWallet(double openingBalance) {
            if (openingBalance < 0) {
                System.out.println("Warning: Opening balance cannot be negative. Setting balance to 0.");
                this.balance = 0;
            } else {
                this.balance = openingBalance;
            }
        }

        public void topUp(double amount) {
            if (amount <= 0) {
                System.out.println("Top-up rejected: amount must be greater than zero.");
            } else {
                this.balance += amount;
                System.out.println("Balance after top-up: " + this.balance);
            }
        }

        public void deduct(double amount) {
            if (amount > this.balance) {
                System.out.println("Deduct rejected: insufficient balance");
            } else {
                this.balance -= amount;
            }
        }

        public double getBalance() {
            return this.balance;
        }
    }

    // ==========================================
    // M3. Course Credit Management
    // ==========================================
    public static class Course {
        String code;
        String title;
        int credits;
        int labCredits;

        // Four-argument constructor
        public Course(String code, String title, int credits, int labCredits) {
            this.code = code;
            this.title = title;
            this.credits = credits;
            this.labCredits = labCredits;
        }

        // Theory-only constructor chaining via this(...)
        public Course(String code, String title, int credits) {
            this(code, title, credits, 0);
        }

        public int totalCredits() {
            return this.credits + this.labCredits;
        }
    }

    // ==========================================
    // M4. Library ID Card Management
    // ==========================================
    public static class IdCard {
        String name;
        int booksIssued;

        public IdCard(String name, int booksIssued) {
            this.name = name;
            this.booksIssued = booksIssued;
        }
    }

    // ==========================================
    // M5. Student and College Information Management
    // ==========================================
    public static class Student {
        String name;
        double attendance;
        static String collegeName = "SRM Institute of Science and Technology";
        static int studentCount = 0;

        public Student(String name, double attendance) {
            this.name = name;
            this.attendance = attendance;
            studentCount++;
        }

        public static void printCollegeInfo() {
            System.out.println(collegeName + " Students created: " + studentCount);
        }
    }

    // ==========================================
    // MAIN METHOD: Test All Examples
    // ==========================================
    public static void main(String[] args) {
        System.out.println("--- M1. Placement Record Test ---[cite: 45]");
        PlacementRecord[] records = {
            new PlacementRecord("Ravi", "TCS", 4.5),
            new PlacementRecord("Anitha", "Zoho", 6.2),
            new PlacementRecord("Karthik", "Infosys", 4.0)
        };
        for (PlacementRecord r : records) {
            r.printRecord();
        }

        System.out.println("\n--- M2. Mess Wallet Test ---[cite: 46]");
        MessWallet wallet = new MessWallet(500);
        wallet.topUp(200);
        wallet.deduct(1000);
        System.out.println("Final balance: " + wallet.getBalance());

        System.out.println("\n--- M3. Course Credit Test ---[cite: 47]");
        Course theoryCourse = new Course("21CSC201J", "Data Structures", 4);
        Course labCourse = new Course("21CSC205L", "DSA Lab", 3, 1);
        System.out.println(theoryCourse.code + " total credits: " + theoryCourse.totalCredits());
        System.out.println(labCourse.code + " total credits: " + labCourse.totalCredits());

        System.out.println("\n--- M4. Library ID Card Test ---");
        IdCard ravi = new IdCard("Ravi", 0);
        IdCard duplicateRavi = ravi;
        duplicateRavi.booksIssued = 3;
        System.out.println("Ravi's books Issued (via first variable): " + ravi.booksIssued);
        System.out.println("duplicate == ravi: " + (duplicateRavi == ravi));
        
        IdCard separate = new IdCard("Ravi", 3);
        System.out.println("separate == ravi: " + (separate == ravi));

        System.out.println("\n--- M5. Student and College Info Test ---[cite: 48]");
        Student s1 = new Student("Rahul", 85.5);
        Student s2 = new Student("Priya", 90.0);
        System.out.println("2 Student objects created");
        Student.printCollegeInfo();
    }
}
