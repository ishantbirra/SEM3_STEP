public class BusRowAnalyzer {

    public static int busiestRow(int[][] grid) {
        int maxTotal = -1;
        int busiestRowIndex = 0;

        for (int i = 0; i < grid.length; i++) {
            int currentRowTotal = 0;
            for (int j = 0; j < grid[i].length; j++) {
                currentRowTotal += grid[i][j];
            }

            // Update only if strictly greater to handle ties by keeping the smaller index
            if (currentRowTotal > maxTotal) {
                maxTotal = currentRowTotal;
                busiestRowIndex = i;
            }
        }

        return busiestRowIndex;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {2, 0, 1},
            {3, 3, 1},
            {1, 1, 1}
        };

        int result = busiestRow(grid);
        System.out.println("Expected Output: Row 1, Total 7"); // Row index 1 has sum 3+3+1 = 7
        System.out.println("Actual Code Output: Row " + result);
    }
}
