public class Week2Assignment {

    // Problem 1: Vowel & Consonant Counter
    public static void countVowelsAndConsonants(String text) {
        int vowels = 0;
        int consonants = 0;
        String lower = text.toLowerCase();
        
        for (int i = 0; i < lower.length(); i++) {
            char ch = lower.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    vowels++;
                } else {
                    consonants++;
                }
            }
        }
        System.out.printf("Vowels: %d | Consonants: %d\n", vowels, consonants);
    }

    // Problem 2: CSV Student Record Parser
    public static void parseStudentRecord(String csvLine) {
        String[] fields = csvLine.split(",");
        for (int i = 0; i < fields.length; i++) {
            fields[i] = fields[i].trim();
        }
        
        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }
        
        System.out.printf("Name: %s | Roll No: %s | Dept: %s\n", fields[0], fields[1], fields[2]);
    }

    // Problem 3: File Extension Validator
    public static String validateFileExtension(String filename) {
        int lastDot = filename.lastIndexOf('.');
        if (lastDot == -1 || lastDot == filename.length() - 1) {
            return "Rejected - invalid file type";
        }
        String ext = filename.substring(lastDot + 1).toLowerCase();
        if (ext.equals("pdf") || ext.equals("docx") || ext.equals("zip")) {
            return "Accepted";
        } else {
            return "Rejected - invalid file type";
        }
    }

    // Problem 4: Masked Phone Number Formatter
    public static String maskPhoneNumber(String phone) {
        if (phone == null || phone.length() != 10) {
            return "Invalid phone number";
        }
        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return "Invalid phone number";
            }
        }
        String last4 = phone.substring(6);
        StringBuilder sb = new StringBuilder();
        sb.append("XXXXXX-").append(last4);
        return sb.toString();
    }

    // Problem 5: Bank Transaction Reference Generator & Validator
    public static String normalizeReference(String raw) {
        if (raw == null) return "";
        String trimmed = raw.trim();
        if (trimmed.length() < 3) {
            return trimmed.toUpperCase();
        }
        String bankCode = trimmed.substring(0, 3).toUpperCase();
        String rest = trimmed.substring(3);
        return bankCode + rest;
    }

    public static String validateAndFormat(String reference) {
        if (reference.length() != 14) {
            return "Invalid: wrong length";
        }
        
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(reference.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }
        
        for (int i = 3; i < 14; i++) {
            if (!Character.isDigit(reference.charAt(i))) {
                return "Invalid: non-digit body";
            }
        }
        
        String bankCode = reference.substring(0, 3);
        String datePart = reference.substring(3, 9); // ddMMyy
        String seqPart = reference.substring(9, 14); // 5 digits
        
        String dd = datePart.substring(0, 2);
        String mm = datePart.substring(2, 4);
        String yy = datePart.substring(4, 6);
        
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(bankCode).append("] DATE: ")
          .append(dd).append("/").append(mm).append("/").append(yy)
          .append(" | SEQ: ").append(seqPart);
          
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("--- Problem 1 Test ---");
        countVowelsAndConsonants("Java Programming");

        System.out.println("\n--- Problem 2 Test ---");
        parseStudentRecord("Ananya Verma, RA2211003010123,CSE");
        parseStudentRecord("Ananya Verma, CSE");

        System.out.println("\n--- Problem 3 Test ---");
        System.out.println(validateFileExtension("Assignment1.PDF"));
        System.out.println(validateFileExtension("notes.txt"));

        System.out.println("\n--- Problem 4 Test ---");
        System.out.println(maskPhoneNumber("9876543210"));
        System.out.println(maskPhoneNumber("98765"));

        System.out.println("\n--- Problem 5 Test ---");
        String norm1 = normalizeReference("hdf03022600042");
        System.out.println(validateAndFormat(norm1));
        
        String norm2 = normalizeReference("12F03022600042");
        System.out.println(validateAndFormat(norm2));
    }
}
