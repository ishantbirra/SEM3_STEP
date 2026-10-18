public class QuizScoreAnalyzer {
    
    public static int secondHighest(int[] scores) {
        int highest = -1;
        int second = -1;
        
        for (int score : scores) {
            if (score > highest) {
                second = highest;
                highest = score;
            } else if (score > second && score < highest) {
                second = score;
            }
        }
        
        // If second remains -1 or equals highest, it means no valid second-highest distinct score exists
        return (second == -1 || second == highest) ? -1 : second;
    }

    public static void main(String[] args) {
        int[] sample1 = {45, 78, 92, 78, 60};
        int[] sample2 = {50, 50, 50};
        
        System.out.println("Sample 1 Output: " + secondHighest(sample1)); // Expected: 78
        System.out.println("Sample 2 Output: " + secondHighest(sample2)); // Expected: -1
    }
}
