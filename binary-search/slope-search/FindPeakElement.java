/*

arr = [1, 3, 5, 7, 6, 4, 2]

Revision Notes — Find Peak Element
Goal: Find an index whose value is greater than its neighbors.

The array does not need to be sorted.

We use the slope/property to eliminate half:
arr[mid] < arr[mid + 1]

      /
     /
   mid → mid+1

Going UP
→ a peak exists toward the RIGHT
→ left = mid + 1

Otherwise:
arr[mid] > arr[mid + 1]

   \
    \
   mid → mid+1

Going DOWN
→ mid itself or something LEFT can be a peak
→ right = mid

We use:
while (left < right)

because we're shrinking the search space until:
left == right

That remaining index is a peak.
Interview recognition
This problem teaches an important broader lesson:
Binary Search does not always require a fully sorted array.

What you really need is a property that allows you to safely discard half of the search space.

That's why Peak Element can be solved in O(log n).
*/

public class FindPeakElement {
    public static int findPeakBruteForce(int[] arr) {

        for (int i = 0; i < arr.length; i++) {

            boolean greaterThanLeft =
                    (i == 0) || (arr[i] > arr[i - 1]);

            boolean greaterThanRight =
                    (i == arr.length - 1) || (arr[i] > arr[i + 1]);

            if (greaterThanLeft && greaterThanRight) {
                return i;
            }
        }

        return -1;
    }

    public static int findPeak(int[] arr){
        int left = 0;
        int right = arr.length - 1;
        while(left < right){
            int mid = left + (right - left) / 2;

            if(arr[mid] < arr[mid + 1]){
                left = mid + 1;
            }else{
                right = mid;
            }
        }

        return left;
    }
}