import java.util.Scanner;

public class Week1Problems {

    // Problem 1: Rock-Paper-Scissors
    public static String playRound(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove)) {
            return "Draw";
        }
        if ((playerMove.equalsIgnoreCase("Rock") && computerMove.equalsIgnoreCase("Scissors")) ||
            (playerMove.equalsIgnoreCase("Paper") && computerMove.equalsIgnoreCase("Rock")) ||
            (playerMove.equalsIgnoreCase("Scissors") && computerMove.equalsIgnoreCase("Paper"))) {
            return "Player Wins";
        }
        return "Computer Wins";
    }

    // Problem 2: Palindrome Checker (Iterative)
    public static boolean isPalindromeIterative(String text) {
        int left = 0, right = text.length() - 1;
        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    // Problem 3: BMI Calculator Status
    public static String getBmiStatus(double bmi) {
        if (bmi < 18.5) return "Underweight";
        if (bmi <= 24.9) return "Normal";
        if (bmi <= 29.9) return "Overweight";
        return "Obese";
    }

    // Problem 4: First Non-Repeating Character
    public static char findFirstNonRepeatingChar(String text) {
        int[] freq = new int[256];
        for (char c : text.toCharArray()) {
            freq[c]++;
        }
        for (char c : text.toCharArray()) {
            if (freq[c] == 1) return c;
        }
        return '_'; // Indicates none found
    }

    // Problem 5: Reverse Customer Name
    public static String reverseCustomerName(String customerName) {
        StringBuilder sb = new StringBuilder(customerName);
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        // Test your methods here or add your interactive logic!
        System.out.println("STEP Week 1 Solutions Loaded Successfully.");
    }
}
