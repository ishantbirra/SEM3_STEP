import java.util.Arrays;

public class DutyRosterRotator {

    public static String[] rotateRoster(String[] names, int k) {
        int n = names.length;
        if (n == 0) return names;
        
        // Reduce k using modulo to remove redundant full rotations
        k = k % n;
        if (k == 0) return names;
        
        String[] rotated = new String[n];
        for (int i = 0; i < n; i++) {
            int newIndex = (i + k) % n;
            rotated[newIndex] = names[i];
        }
        
        return rotated;
    }

    public static void main(String[] args) {
        // Sample 1
        String[] names1 = {"A", "B", "C", "D", "E"};
        int k1 = 2;
        System.out.println("Sample 1 Output: " + Arrays.toString(rotateRoster(names1, k1))); 
        // Expected: [D, E, A, B, C]

        // Sample 2
        String[] names2 = {"A", "B", "C", "D", "E"};
        int k2 = 7;
        System.out.println("Sample 2 Output: " + Arrays.toString(rotateRoster(names2, k2))); 
        // Expected: [D, E, A, B, C]
    }
}
