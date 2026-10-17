public class Week1Assignment {

    // Problem 1: The Exam Hall Seat Duplication Checker
    public static void checkDuplicateSeats(int[] seatNumbers) {
        boolean found = false;
        System.out.print("Seat Check Result: ");
        for (int i = 0; i < seatNumbers.length; i++) {
            for (int j = i + 1; j < seatNumbers.length; j++) {
                if (seatNumbers[i] == seatNumbers[j]) {
                    System.out.println("Duplicate Seat Number Found: " + seatNumbers[i]);
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("No Duplicate Seats Found");
        }
    }

    // Problem 2: The Typing Speed Test Accuracy Checker
    public static void checkTypingAccuracy(String original, String typed) {
        int matched = 0;
        int total = original.length();
        int firstMismatchPos = -1;
        char origChar = ' ';
        char typedChar = ' ';

        for (int i = 0; i < total; i++) {
            if (i < typed.length() && original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else {
                if (firstMismatchPos == -1) {
                    firstMismatchPos = i + 1; // 1-based index position
                    origChar = original.charAt(i);
                    typedChar = (i < typed.length()) ? typed.charAt(i) : ' ';
                }
            }
        }

        double accuracy = ((double) matched / total) * 100.0;
        System.out.printf("Matched: %d/%d | Accuracy: %.2f%%", matched, total, accuracy);
        if (firstMismatchPos != -1) {
            System.out.printf(" | First Mismatch at position %d ('%c' vs '%c')\n", firstMismatchPos, origChar, typedChar);
        } else {
            System.out.println(" | No Mismatches");
        }
    }

    // Problem 3: The Traffic Signal Streak Analyzer
    public static void findLongestStreak(String signalLog) {
        if (signalLog == null || signalLog.length() == 0) return;

        char longestChar = signalLog.charAt(0);
        int maxStreak = 1;

        char currentChar = signalLog.charAt(0);
        int currentStreak = 1;

        for (int i = 1; i < signalLog.length(); i++) {
            if (signalLog.charAt(i) == currentChar) {
                currentStreak++;
            } else {
                currentChar = signalLog.charAt(i);
                currentStreak = 1;
            }

            if (currentStreak > maxStreak) {
                maxStreak = currentStreak;
                longestChar = currentChar;
            }
        }

        System.out.println("Longest Streak: '" + longestChar + "' repeated " + maxStreak + " times");
    }

    // Problem 4: The Warehouse Inventory Balancer
    public static void analyzeInventory(int[] sectionA, int[] sectionB) {
        int totalA = 0;
        int totalB = 0;

        for (int val : sectionA) totalA += val;
        for (int val : sectionB) totalB += val;

        String status = (totalA == totalB) ? "Balanced" : "Not Balanced";

        int maxVal = sectionA[0];
        String maxSection = "Section A";
        int maxIndex = 1;

        for (int i = 0; i < sectionA.length; i++) {
            if (sectionA[i] > maxVal) {
                maxVal = sectionA[i];
                maxSection = "Section A";
                maxIndex = i + 1;
            }
        }

        for (int i = 0; i < sectionB.length; i++) {
            if (sectionB[i] > maxVal) {
                maxVal = sectionB[i];
                maxSection = "Section B";
                maxIndex = i + 1;
            }
        }

        System.out.printf("Section A Total: %d | Section B Total: %d | Status: %s | Highest Quantity: %d (%s, Item %d)\n",
                totalA, totalB, status, maxVal, maxSection, maxIndex);
    }

    // Problem 5: The Movie Review Word Length Profiler
    public static void classifyWordLengths(String review) {
        String[] words = review.split("\\s+");
        int shortCount = 0;
        int mediumCount = 0;
        int longCount = 0;

        for (String word : words) {
            // Remove basic punctuation if attached
            word = word.replaceAll("[^a-zA-Z]", "");
            int len = word.length();
            if (len >= 1 && len <= 4) {
                shortCount++;
            } else if (len >= 5 && len <= 8) {
                mediumCount++;
            } else if (len >= 9) {
                longCount++;
            }
        }

        System.out.printf("Short: %d | Medium: %d | Long: %d\n", shortCount, mediumCount, longCount);
    }

    public static void main(String[] args) {
        // Test examples from the assignment pdf
        System.out.println("--- Problem 1 Test ---");
        checkDuplicateSeats(new int[]{101, 102, 103, 102, 105});

        System.out.println("\n--- Problem 2 Test ---");
        checkTypingAccuracy("hello world", "hello worlt");

        System.out.println("\n--- Problem 3 Test ---");
        findLongestStreak("RRGGGYRR");

        System.out.println("\n--- Problem 4 Test ---");
        analyzeInventory(new int[]{20, 15, 30}, new int[]{25, 10, 30});

        System.out.println("\n--- Problem 5 Test ---");
        classifyWordLengths("This movie was absolutely fantastic and thrilling");
    }
}
