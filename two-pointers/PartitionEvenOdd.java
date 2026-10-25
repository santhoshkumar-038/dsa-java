// Rearrange the array so that even numbers appear on the left and odd numbers on the right.

// Order does not matter.

// ```text
// Input:
// [1, 2, 3, 4, 6, 5]

// Possible output:
// [6, 2, 4, 3, 1, 5]
// ```

// ### Thinking

// Use:

// ```text
// left
// right
// ```

// Rules:

// ```text
// left is even  → left++
// right is odd  → right--

// left is odd AND right is even
// → swap
// → left++
// → right--
// ```

// ### Complexity

// ```text
// Time  : O(n)
// Space : O(1)
// ```

public class PartitionEvenOdd {
    public static void partitionEvenOdd(int[] arr) {

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {

            if (arr[left] % 2 == 0) {
                left++;

            } else if (arr[right] % 2 != 0) {
                right--;

            } else {

                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;

                left++;
                right--;
            }
        }
    }   
}
