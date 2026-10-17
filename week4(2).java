import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Week4CategoryCAssignment {

    // A1. Product of Array Except Self
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];
        
        answer[0] = 1;
        for (int i = 1; i < n; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }
        
        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            answer[i] = answer[i] * rightProduct;
            rightProduct *= nums[i];
        }
        
        return answer;
    }

    // A2. Maximum Subarray
    public static int maxSubArray(int[] nums) {
        int maxSoFar = nums[0];
        int currentMax = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            currentMax = Math.max(nums[i], currentMax + nums[i]);
            maxSoFar = Math.max(maxSoFar, currentMax);
        }
        
        return maxSoFar;
    }

    // A3. 3Sum
    public static int[][] threeSum(int[] nums) {
        Arrays.sort(nums);
        List<int[]> resultList = new ArrayList<>();
        
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // Skip duplicates for i
            
            int left = i + 1;
            int right = nums.length - 1;
            
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                
                if (sum == 0) {
                    resultList.add(new int[] { nums[i], nums[left], nums[right] });
                    
                    while (left < right && nums[left] == nums[left + 1]) left++; // Skip duplicates for left
                    while (left < right && nums[right] == nums[right - 1]) right--; // Skip duplicates for right
                    
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        
        return resultList.toArray(new int[0][0]);
    }

    // A4. Subarray Sum Equals K
    public static int subarraySum(int[] nums, int k) {
        int count = 0;
        int currentSum = 0;
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, 1); // Base case for prefix sum equal to k
        
        for (int num : nums) {
            currentSum += num;
            if (prefixSumMap.containsKey(currentSum - k)) {
                count += prefixSumMap.get(currentSum - k);
            }
            prefixSumMap.put(currentSum, prefixSumMap.getOrDefault(currentSum, 0) + 1);
        }
        
        return count;
    }

    // A5. Find Minimum in Rotated Sorted Array
    public static int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return nums[left];
    }

    public static void main(String[] args) {
        System.out.println("--- A1. Product Except Self Test ---");
        System.out.println(Arrays.toString(productExceptSelf(new int[]{1, 2, 3, 4})));

        System.out.println("\n--- A2. Maximum Subarray Test ---");
        System.out.println(maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));

        System.out.println("\n--- A3. 3Sum Test ---");
        int[][] triplets = threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        for (int[] t : triplets) {
            System.out.println(Arrays.toString(t));
        }

        System.out.println("\n--- A4. Subarray Sum Equals K Test ---");
        System.out.println(subarraySum(new int[]{1, 1, 1}, 2));

        System.out.println("\n--- A5. Find Min in Rotated Sorted Array Test ---");
        System.out.println(findMin(new int[]{3, 4, 5, 1, 2}));
    }
}
