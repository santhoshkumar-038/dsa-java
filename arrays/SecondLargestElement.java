// Find the second-largest distinct element.
// Input:  [10, 5, 8, 10, 7]
// Output: 8
// Thinking
// Maintain:
// largest
// secondLargest
// If a new largest appears:
// old largest → secondLargest
// new value   → largest
// Duplicates of largest should not count.

public class SecondLargestElement {
    public static int findSecondLargest(int[] arr) {

        if (arr == null || arr.length < 2) {
            throw new IllegalArgumentException(
                    "Array must contain at least two elements"
            );
        }
    
        int largest = arr[0];
        int secondLargest = Integer.MIN_VALUE;
        boolean secondLargestFound = false;
    
        for (int i = 1; i < arr.length; i++) {
    
            if (arr[i] > largest) {
    
                secondLargest = largest;
                largest = arr[i];
                secondLargestFound = true;
    
            } else if (arr[i] < largest && arr[i] > secondLargest) {
    
                secondLargest = arr[i];
                secondLargestFound = true;
            }
        }
    
        if (!secondLargestFound) {
            throw new IllegalArgumentException(
                    "Second largest distinct element not found"
            );
        }
    
        return secondLargest;
    }
}
