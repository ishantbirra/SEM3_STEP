import java.util.Arrays;

public class Week4CategoryCPractice {

    // L1. Two Sum
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] { i, j };
                }
            }
        }
        return new int[] {};
    }

    // L2. Best Time to Buy and Sell Stock
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int minPrice = prices[0];
        int maxPr = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                int profit = prices[i] - minPrice;
                if (profit > maxPr) {
                    maxPr = profit;
                }
            }
        }
        return maxPr;
    }

    // L3. Contains Duplicate
    public static boolean containsDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    // L4. Merge Two Sorted Arrays
    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                result[k++] = arr1[i++];
            } else {
                result[k++] = arr2[j++];
            }
        }
        while (i < arr1.length) {
            result[k++] = arr1[i++];
        }
        while (j < arr2.length) {
            result[k++] = arr2[j++];
        }
        return result;
    }

    // L5. Rotate Array
    public static int[] rotateArray(int[] nums, int k) {
        if (nums == null || nums.length == 0) return nums;
        k = k % nums.length;
        int[] newArray = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            newArray[(i + k) % nums.length] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = newArray[i];
        }
        return nums;
    }

    public static void main(String[] args) {
        System.out.println("--- L1. Two Sum Test ---");
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));

        System.out.println("\n--- L2. Stock Profit Test ---");
        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));

        System.out.println("\n--- L3. Contains Duplicate Test ---");
        System.out.println(containsDuplicate(new int[]{1, 2, 3, 1}));

        System.out.println("\n--- L4. Merge Sorted Arrays Test ---");
        System.out.println(Arrays.toString(mergeSortedArrays(new int[]{1, 3, 5}, new int[]{2, 4, 6})));

        System.out.println("\n--- L5. Rotate Array Test ---");
        System.out.println(Arrays.toString(rotateArray(new int[]{1, 2, 3, 4, 5, 6, 7}, 3)));
    }
}
