// ```text
// Input:  [1, 2, 3, 4, 5]
// Output: [5, 4, 3, 2, 1]
// ```

// ### Thinking

// Use two pointers:

// ```text
// left  → beginning
// right → end
// ```

// Swap them and move inward.


// ### Complexity

// ```text
// Time  : O(n)
// Space : O(1)
// ```

// ### Pattern

// **Opposite-direction Two Pointers**

// ---

public class ReverseArray {
    public static void reverseArray(int[] arr) {

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {

            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
    }
}
