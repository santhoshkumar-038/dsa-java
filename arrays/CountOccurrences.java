// Count how many times a target occurs.
// arr    = [1, 2, 2, 3, 2]
// target = 2
// Output = 3

class CountOccurrences{
    public static int countOccurrences(int[] arr, int target) {
        int count = 0;
        for (int value : arr) {
            if (value == target) {
                count++;
            }
        }
        return count;
    }
}