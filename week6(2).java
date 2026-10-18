public class Week6Assignment {

    // ==========================================
    // M1. Library Inventory Management
    // ==========================================
    public static class BookInventory {
        String title;
        String author;
        int copiesAvailable;

        public BookInventory(String title, String author, int copiesAvailable) {
            this.title = title;
            this.author = author;
            this.copiesAvailable = copiesAvailable;
        }

        public void printEntry() {
            System.out.println(title + " by " + author + " - " + copiesAvailable + " copies available");
        }
    }

    // ==========================================
    // M2. Payroll Salary Management
    // ==========================================
    public static class PayrollAccount {
        private double basicSalary;
        private double bonus;

        public PayrollAccount(double openingBasicSalary) {
            if (openingBasicSalary < 0) {
                System.out.println("Warning: Basic salary cannot be negative. Setting to 0.");
                this.basicSalary = 0;
            } else {
                this.basicSalary = openingBasicSalary;
            }
            this.bonus = 0;
        }

        public void creditBonus(double amount) {
            if (amount <= 0) {
                System.out.println("Bonus credit rejected: amount must be greater than zero.");
            } else {
                this.bonus += amount;
                System.out.println("Bonus credited: Rs " + amount);
            }
        }

        public void deductTax(double percent) {
            if (percent < 0 || percent > 100) {
                System.out.println("Tax deduction rejected: percent must be between 0 and 100.");
            } else {
                this.basicSalary -= (this.basicSalary * percent / 100.0);
                System.out.println("Tax deducted: " + percent + "%");
            }
        }

        public double getNetSalary() {
            return this.basicSalary + this.bonus;
        }
    }

    // ==========================================
    // M3. Employee Profile Creation
    // ==========================================
    public static class EmployeeProfile {
        String empId;
        String empName;
        double salary;
        boolean isIntern;

        // Constructor for permanent employees
        public EmployeeProfile(String empId, String empName, double salary) {
            this.empId = empId;
            this.empName = empName;
            this.salary = salary;
            this.isIntern = false;
        }

        // Constructor for interns chaining via this(...)
        public EmployeeProfile(String empId, String empName) {
            this(empId, empName, 0.0);
            this.isIntern = true;
        }

        public void printProfile() {
            System.out.println(empId + " | " + empName + " | Rs " + salary + " | Intern: " + isIntern);
        }
    }

    // ==========================================
    // M4. Exam Hall Ticket Reference Management
    // ==========================================
    public static class HallTicket {
        String studentName;
        int seatNumber;

        public HallTicket(String studentName, int seatNumber) {
            this.studentName = studentName;
            this.seatNumber = seatNumber;
        }
    }

    // ==========================================
    // M5. Employee and Company Information Management
    // ==========================================
    public static class CompanyEmployee {
        String empName;
        double salary;
        static String companyName = "Bright Horizon Technologies";
        static int employeeCount = 0;

        public CompanyEmployee(String empName, double salary) {
            this.empName = empName;
            this.salary = salary;
            employeeCount++;
        }

        public static void printCompanyInfo() {
            System.out.println(companyName);
            System.out.println("Employees on record: " + employeeCount);
        }
    }

    // ==========================================
    // MAIN METHOD: Test All Examples
    // ==========================================
    public static void main(String[] args) {
        System.out.println("--- M1. Library Inventory Test ---");
        BookInventory[] books = {
            new BookInventory("Clean Code", "Robert C. Martin", 3),
            new BookInventory("Effective Java", "Joshua Bloch", 5),
            new BookInventory("Refactoring", "Martin Fowler", 0),
            new BookInventory("Design Patterns", "GoF", 2)
        };
        for (BookInventory b : books) {
            b.printEntry();
        }

        System.out.println("\n--- M2. Payroll Salary Test ---");
        PayrollAccount account = new PayrollAccount(50000);
        account.creditBonus(5000);
        account.deductTax(10);
        System.out.println("Net salary: Rs " + account.getNetSalary());

        System.out.println("\n--- M3. Employee Profile Test ---");
        EmployeeProfile permanent = new EmployeeProfile("E-101", "Divya", 65000);
        EmployeeProfile intern = new EmployeeProfile("E-102", "Arjun");
        permanent.printProfile();
        intern.printProfile();

        System.out.println("\n--- M4. Hall Ticket Reference Test ---");
        HallTicket priya = new HallTicket("Priya", 0);
        HallTicket copyPriya = priya;
        copyPriya.seatNumber = 45;
        System.out.println("Priya's seatNumber (via first variable): " + priya.seatNumber);
        System.out.println("copy == priya: " + (copyPriya == priya));

        HallTicket separate = new HallTicket("Priya", 45);
        System.out.println("separate == priya: " + (separate == priya));

        System.out.println("\n--- M5. Company Employee Info Test ---");
        CompanyEmployee e1 = new CompanyEmployee("Alice", 45000);
        CompanyEmployee e2 = new CompanyEmployee("Bob", 50000);
        CompanyEmployee e3 = new CompanyEmployee("Charlie", 55000);
        System.out.println("3 Employee objects created");
        CompanyEmployee.printCompanyInfo();
    }
}
