// Find the maximum number of consecutive `1`s.

// ```text
// Input:  [1, 1, 0, 1, 1, 1]
// Output: 3
// ```

// ### Thinking

// Maintain:

// ```text
// count   → current streak
// maxCons → best streak
// ```

// When we see `1`:

// ```text
// count++
// ```

// When we see `0`:

// ```text
// save maximum
// reset count
// ```

public class MaxConsecutiveOnes {
    public static int maxConsecutiveOnes(int[] arr) {
        int maxCons = 0;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                count++;
            } else {
                if (count > maxCons) {
                    maxCons = count;
                }
                count = 0;
            }
        }

        if (count > maxCons) {
            maxCons = count;
        }
        return maxCons;
    }
}
