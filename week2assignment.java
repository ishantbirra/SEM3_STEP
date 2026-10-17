import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Week2AssignmentSubmission {

    // Problem 1: ATM PIN Length Validator
    public static void checkPinLength(String pin) {
        if (pin == null || pin.length() != 4) {
            System.out.println("Invalid PIN must be exactly 4 digits.");
        } else {
            System.out.println("PIN length OK.");
        }
    }

    // Problem 2: Word Reversal Encoder
    public static String reverseEachWord(String sentence) {
        if (sentence == null || sentence.isEmpty()) {
            return "";
        }
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            StringBuilder reversedWord = new StringBuilder(words[i]);
            result.append(reversedWord.reverse().toString());
            if (i < words.length - 1) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    // Problem 3: Product Inventory CSV Parser
    public static void parseInventoryRecord(String csvLine) {
        if (csvLine == null) {
            System.out.println("Invalid Record");
            return;
        }
        String[] fields = csvLine.split(",");
        for (int i = 0; i < fields.length; i++) {
            fields[i] = fields[i].trim();
        }

        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        System.out.printf("Product: %s | SKU: %s | Qty: %s\n", fields[0], fields[1], fields[2]);
    }

    // Problem 4: Library ISBN Normalizer & Validator
    public static String normalizeCode(String raw) {
        if (raw == null) return "";
        String trimmed = raw.trim();
        if (trimmed.length() < 3) {
            return trimmed.toUpperCase();
        }
        String pubCode = trimmed.substring(0, 3).toUpperCase();
        String rest = trimmed.substring(3);
        return pubCode + rest;
    }

    public static String validateAndFormat(String code) {
        if (code.length() != 13) {
            return "Invalid: wrong length";
        }

        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: non-digit body";
            }
        }

        String pubCode = code.substring(0, 3);
        String yearPart = code.substring(3, 7);     // 4 digits year
        String catalogPart = code.substring(7, 13); // 6 digits catalog

        StringBuilder sb = new StringBuilder();
        sb.append("[").append(pubCode).append("] YEAR: ")
          .append(yearPart).append(" | CATALOG: ").append(catalogPart);

        return sb.toString();
    }

    // Problem 5: Stop-Word-Filtered Word Frequency Report
    public static void printFilteredWordFrequency(String feedback) {
        if (feedback == null) return;

        // Define stop words list
        String[] stopWordsArray = {"the", "was", "and", "a", "is", "of", "in"};
        Map<String, Boolean> stopWords = new HashMap<>();
        for (String sw : stopWordsArray) {
            stopWords.put(sw, true);
        }

        // Normalize: lowercase, remove periods and commas
        String cleaned = feedback.toLowerCase().replace(".", "").replace(",", "").replace(":", "");
        String[] words = cleaned.split("\\s+");

        Map<String, Integer> freqMap = new HashMap<>();
        for (String word : words) {
            if (word.isEmpty()) continue;
            if (!stopWords.containsKey(word)) {
                freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
            }
        }

        // Sort by frequency descending
        List<Map.Entry<String, Integer>> list = new ArrayList<>(freqMap.entrySet());
        list.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Problem 1 Test ---");
        checkPinLength("482");
        checkPinLength("4820");

        System.out.println("\n--- Problem 2 Test ---");
        System.out.println(reverseEachWord("hello club"));

        System.out.println("\n--- Problem 3 Test ---");
        parseInventoryRecord("Wireless Mouse, WM-2201,150");
        parseInventoryRecord("Wireless Mouse, 150");

        System.out.println("\n--- Problem 4 Test ---");
        String norm1 = normalizeCode("pen2026004251");
        System.out.println(validateAndFormat(norm1));
        String norm2 = normalizeCode("12N2026004251");
        System.out.println(validateAndFormat(norm2));

        System.out.println("\n--- Problem 5 Test ---");
        printFilteredWordFrequency("The mentor was great, the session was great and clear.");
    }
}
