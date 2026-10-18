public class ExamScoreAnalyzer {

    // Helper method to find the first index where score >= low
    private static int findLowerBound(int[] scores, int low) {
        int left = 0, right = scores.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (scores[mid] >= low) {
                right = mid; // Try to find an earlier occurrence on the left
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // Helper method to find the first index where score > high
    private static int findUpperBound(int[] scores, int high) {
        int left = 0, right = scores.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (scores[mid] > high) {
                right = mid; // Try to find an earlier occurrence on the left
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // Main function to count scores within [low, high]
    public static int countInBand(int[] scores, int low, int high) {
        int lowerIdx = findLowerBound(scores, low);
        int upperIdx = findUpperBound(scores, high);
        return upperIdx - lowerIdx;
    }

    public static void main(String[] args) {
        int[] scores = {35, 42, 42, 50, 58, 58, 58, 63, 71, 88};
        
        // Sample 1
        int low1 = 42, high1 = 58;
        System.out.println("Sample 1 Output: " + countInBand(scores, low1, high1)); // Expected: 6

        // Sample 2
        int low2 = 90, high2 = 100;
        System.out.println("Sample 2 Output: " + countInBand(scores, low2, high2)); // Expected: 0
    }
}
