import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Week5Assignment {

    // ==========================================
    // PROBLEM 1: Fantasy Team Score Multiplier
    // ==========================================
    public static void applyMultipliers(double[] playerScores, int captainIndex, int viceCaptainIndex) {
        if (playerScores != null) {
            if (captainIndex >= 0 && captainIndex < playerScores.length) {
                playerScores[captainIndex] *= 2.0;
            }
            if (viceCaptainIndex >= 0 && viceCaptainIndex < playerScores.length) {
                playerScores[viceCaptainIndex] *= 1.5;
            }
        }
    }

    // ==========================================
    // PROBLEM 2: Duplicate Player Pick Checker
    // ==========================================
    public static String findDuplicatePick(String[] playerNames) {
        if (playerNames == null) return "No Duplicates Found";
        for (int i = 0; i < playerNames.length; i++) {
            for (int j = i + 1; j < playerNames.length; j++) {
                if (playerNames[i].equals(playerNames[j])) {
                    return "Duplicate Found: " + playerNames[i];
                }
            }
        }
        return "No Duplicates Found";
    }

    // ==========================================
    // PROBLEM 3: Top Performer Tracker
    // ==========================================
    public static String findMinMaxSpread(int[] scores) {
        if (scores == null || scores.length == 0) return "";
        int min = scores[0];
        int max = scores[0];
        
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] < min) {
                min = scores[i];
            }
            if (scores[i] > max) {
                max = scores[i];
            }
        }
        int spread = max - min;
        return "Min: " + min + " | Max: " + max + " | Spread: " + spread;
    }

    // ==========================================
    // PROBLEM 4: Match Day Grid Analyzer
    // ==========================================
    public static double rowAverage(int[] row) {
        if (row == null || row.length == 0) return 0.0;
        double sum = 0;
        for (int runs : row) {
            sum += runs;
        }
        return sum / row.length;
    }

    public static String classifyMatches(int[][] runsPerOver, int threshold) {
        if (runsPerOver == null || runsPerOver.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < runsPerOver.length; i++) {
            double avg = rowAverage(runsPerOver[i]);
            String status = (avg >= threshold) ? "Power Surge" : "Normal";
            sb.append("Match ").append(i).append(": ").append(status);
            if (i < runsPerOver.length - 1) {
                sb.append(" | ");
            }
        }
        return sb.toString();
    }

    // ==========================================
    // PROBLEM 5: Fantasy League Auto-Draft Ranking Engine
    // ==========================================
    public static class Player implements Comparable<Player> {
        String name;
        int matchesPlayed;
        double battingAverage; // Acts as fantasy points / rating
        boolean injured;

        public Player(String name, int matchesPlayed, double battingAverage, boolean injured) {
            this.name = name;
            this.matchesPlayed = matchesPlayed;
            this.battingAverage = battingAverage;
            this.injured = injured;
        }

        @Override
        public int compareTo(Player other) {
            // Sort descending by fantasy points / batting average
            return Double.compare(other.battingAverage, this.battingAverage);
        }
    }

    public static boolean isDraftable(int matchesPlayed) {
        // Experience-only rule: e.g., matches played >= 10
        return matchesPlayed >= 10;
    }

    public static boolean isDraftable(int matchesPlayed, boolean injured) {
        // Combined matches-and-fitness rule: e.g., matches played >= 5 and not injured
        return matchesPlayed >= 5 && !injured;
    }

    public static String draftAndRank(Player[] players) {
        List<Player> draftableList = new ArrayList<>();
        
        for (Player p : players) {
            // Evaluates using overloaded methods
            if (isDraftable(p.matchesPlayed) || isDraftable(p.matchesPlayed, p.injured)) {
                draftableList.add(p);
            }
        }

        // Sorts using the Comparable implementation
        Player[] draftableArray = draftableList.toArray(new Player[0]);
        Arrays.sort(draftableArray);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < draftableArray.length; i++) {
            sb.append((i + 1)).append(". ").append(draftableArray[i].name);
            if (i < draftableArray.length - 1) {
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
        double[] scores = {40, 55, 30, 62};
        applyMultipliers(scores, 1, 3);
        System.out.println(Arrays.toString(scores));

        System.out.println("\n--- Problem 2 Test ---");
        System.out.println(findDuplicatePick(new String[]{"Kohli", "Bumrah", "Kohli", "Rohit"}));
        System.out.println(findDuplicatePick(new String[]{"Kohli", "Bumrah", "Rohit"}));

        System.out.println("\n--- Problem 3 Test ---");
        System.out.println(findMinMaxSpread(new int[]{45, 82, 79, 90, 33, 90, 61}));

        System.out.println("\n--- Problem 4 Test ---");
        int[][] runs = {
            {4, 6, 8},
            {10, 12, 14},
            {2, 3, 1}
        };
        System.out.println(classifyMatches(runs, 8));

        System.out.println("\n--- Problem 5 Test ---");
        Player[] players = {
            new Player("Virat", 15, 48.0, false),
            new Player("Rahul", 7, 55.0, false),
            new Player("Sameer", 3, 60.0, false),
            new Player("Dev", 12, 20.0, true)
        };
        System.out.println(draftAndRank(players));
    }
}
