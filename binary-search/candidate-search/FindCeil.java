/*
    arr = [2, 4, 6, 7, 8, 10]
    target = 7
    Revision Notes — Ceil in Sorted Array
Definition:
Ceil = smallest element ≥ target

Example:
arr = [2, 4, 6, 8, 10]
target = 7

ceil = 8

Binary Search rule
arr[mid] == target
→ exact match is the best ceil
→ return arr[mid]

arr[mid] < target
→ too small
→ search RIGHT →

arr[mid] > target
→ valid ceil candidate
→ save arr[mid]
→ search LEFT ← for a smaller valid candidate

Floor vs Ceil — remember the meaning, not the code
FLOOR
largest value <= target
valid candidate → search RIGHT →

CEIL
smallest value >= target
valid candidate → search LEFT ←

Pattern: Binary Search → Candidate / Boundary Search

Recognition: If an interviewer asks for the smallest value greater than or equal to X, think Ceil.

Complexity: O(log n) time, O(1) extra space.
*/

public class FindCeil {
    public static int findCeil(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int ceil = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if(arr[mid] == target){
                return arr[mid];
            }else if(arr[mid] < target){
                left = mid + 1;
            }else{
                ceil = arr[mid];
                right = mid - 1;
            }
        }

        return ceil;
    }
}
