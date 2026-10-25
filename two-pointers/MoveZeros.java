// Move all zeroes to the end while preserving the order of non-zero elements.

// ```text
// Input:
// [0, 1, 0, 3, 12]

// Output:
// [1, 3, 12, 0, 0]
// ```

// ### Thinking

// Use:

// ```text
// i         → scans array
// insertPos → position where next non-zero belongs
// ```

// Whenever a non-zero is found, swap it with `insertPos`.

// ### Complexity

// ```text
// Time  : O(n)
// Space : O(1)
// ```

// ### Pattern

// **Same-direction Two Pointers / Write Pointer**

// ### Remember

// `insertPos` means:

// > Where should the next valid/non-zero element go?

// ---


public class MoveZeros {
    public static void moveZeroes(int[] arr) {

        int insertPos = 0;

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] != 0) {

                int temp = arr[i];
                arr[i] = arr[insertPos];
                arr[insertPos] = temp;

                insertPos++;
            }
        }
    }
}
