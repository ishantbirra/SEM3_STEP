import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Week5PracticeAssignment {

    // ==========================================
    // PROBLEM 1: Hackathon Score Curve Booster
    // ==========================================
    public static void curveScores(int[] scores, int bonus) {
        if (scores != null) {
            for (int i = 0; i < scores.length; i++) {
                scores[i] += bonus;
            }
        }
    }

    // ==========================================
    // PROBLEM 2: Duplicate Team Name Finder
    // ==========================================
    public static String findDuplicateTeam(String[] teamNames) {
        if (teamNames == null) return "No Duplicates Found";
        for (int i = 0; i < teamNames.length; i++) {
            for (int j = i + 1; j < teamNames.length; j++) {
                if (teamNames[i].equals(teamNames[j])) {
                    return "Duplicate Found: " + teamNames[i];
                }
            }
        }
        return "No Duplicates Found";
    }

    // ==========================================
    // PROBLEM 3: Top-3 Podium Finder
    // ==========================================
    public static int[] findTopThreeScores(int[] scores) {
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        int third = Integer.MIN_VALUE;

        for (int score : scores) {
            if (score > first) {
                third = second;
                second = first;
                first = score;
            } else if (score > second && score != first) {
                third = second;
                second = score;
            } else if (score > third && score != second && score != first) {
                third = score;
            }
        }
        return new int[]{first, second, third};
    }

    // ==========================================
    // PROBLEM 4: Hackathon Seating Grid Optimizer
    // ==========================================
    public static double rowAverage(int[] row) {
        if (row == null || row.length == 0) return 0.0;
        double sum = 0;
        for (int val : row) {
            sum += val;
        }
        return sum / row.length;
    }

    public static String classifyRows(int[][] seatingScores, int threshold) {
        if (seatingScores == null || seatingScores.length == 0) return "";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < seatingScores.length; i++) {
            double avg = rowAverage(seatingScores[i]);
            String zone = (avg < threshold) ? "Quiet Zone" : "Buzzing Zone";
            sb.append("Row ").append(i).append(": ").append(zone);
            if (i < seatingScores.length - 1) {
                sb.append(" | ");
            }
        }
        return sb.toString();
    }

    // ==========================================
    // PROBLEM 5: Placement Drive Shortlisting & Ranking Engine
    // ==========================================
    public static class Candidate implements Comparable<Candidate> {
        String name;
        double cgpa;
        int codingScore;
        double compositeScore;

        public Candidate(String name, double cgpa, int codingScore) {
            this.name = name;
            this.cgpa = cgpa;
            this.codingScore = codingScore;
            // Example composite score formula for ranking
            this.compositeScore = (cgpa * 10.0) + codingScore;
        }

        @Override
        public int compareTo(Candidate other) {
            // Sort descending by composite score
            return Double.compare(other.compositeScore, this.compositeScore);
        }
    }

    public static boolean isEligible(double cgpa) {
        // CGPA-only quick filter rule (e.g., CGPA >= 7.5)
        return cgpa >= 7.5;
    }

    public static boolean isEligible(double cgpa, int codingScore) {
        // Combined CGPA-and-coding-score filter for borderline cases (e.g., CGPA >= 6.0 and codingScore >= 60)
        return cgpa >= 6.0 && codingScore >= 60;
    }

    public static String shortlistAndRank(Candidate[] candidates) {
        List<Candidate> shortlistedList = new ArrayList<>();

        for (Candidate c : candidates) {
            if (isEligible(c.cgpa) || isEligible(c.cgpa, c.codingScore)) {
                shortlistedList.add(c);
            }
        }

        Candidate[] shortlistedArray = shortlistedList.toArray(new Candidate[0]);
        Arrays.sort(shortlistedArray); // Uses Comparable implementation

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < shortlistedArray.length; i++) {
            Candidate c = shortlistedArray[i];
            sb.append((i + 1)).append(". ").append(c.name).append(" (").append(c.compositeScore).append(")");
            if (i < shortlistedArray.length - 1) {
                sb.append(" | ");
            }
        }
        return sb.toString();
    }

    // ==========================================
    // MAIN METHOD: Test All Examples
    // ==========================================
    public static void main(String[] args) {
        System.out.println("--- Problem 1 Test ---");
        int[] scores = {70, 85, 60};
        curveScores(scores, 10);
        System.out.println(Arrays.toString(scores));

        System.out.println("\n--- Problem 2 Test ---");
        System.out.println(findDuplicateTeam(new String[]{"ByteForce", "CodeCrafters", "ByteForce"}));
        System.out.println(findDuplicateTeam(new String[]{"ByteForce", "CodeCrafters", "NullPointers"}));

        System.out.println("\n--- Problem 3 Test ---");
        int[] podiumScores = {45, 82, 79, 90, 33, 90, 61};
        System.out.println(Arrays.toString(findTopThreeScores(podiumScores)));

        System.out.println("\n--- Problem 4 Test ---");
        int[][] grid = {
            {40, 50, 45},
            {85, 90, 95},
            {30, 20, 25}
        };
        System.out.println(classifyRows(grid, 60));

        System.out.println("\n--- Problem 5 Test ---");
        Candidate[] candidates = {
            new Candidate("Aisha", 8.2, 40),
            new Candidate("Rohit", 6.8, 65),
            new Candidate("Meena", 6.0, 90),
            new Candidate("Karan", 7.5, 20)
        };
        System.out.println(shortlistAndRank(candidates));
    }
}
