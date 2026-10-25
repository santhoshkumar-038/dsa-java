// Remove duplicates in-place from a **sorted** array.

// ```text
// Input:
// [1, 1, 2, 2, 2, 3, 3]

// Unique portion:
// [1, 2, 3]

// Return:
// 3
// ```

// ### Thinking

// Because the array is sorted, duplicates are adjacent.

// Use a write pointer representing the last unique position.

// ### Complexity

// ```text
// Time  : O(n)
// Space : O(1)
// ```

// ### Recognition

// When you hear:

// ```text
// sorted array + remove duplicates + in-place
// ```

// think **Two Pointers / Write Pointer**.

public class RemoveDuplicates {
    public static int removeDuplicates(int[] arr) {

        if (arr.length == 0) {
            return 0;
        }

        int writeUnique = 0;

        for (int i = 1; i < arr.length; i++) {

            if (arr[i] != arr[i - 1]) {
                writeUnique++;
                arr[writeUnique] = arr[i];
            }
        }
        return writeUnique + 1;
    }
}
