/*
    arr = [2, 4, 6, 8, 10]
    target = 8
    Day 8 — Revision Notes: Floor in Sorted Array
Definition: Floor of target = largest element ≤ target.

Example:
arr = [2, 4, 6, 8, 10]
target = 7

floor = 6

Pattern: Binary Search + candidate answer.

The key interview thought is:
arr[mid] == target
→ exact target is the best possible floor
→ return it

arr[mid] < target
→ valid floor candidate
→ save arr[mid]
→ move RIGHT looking for something larger

arr[mid] > target
→ too large
→ move LEFT

Recognition trick
When the interviewer says something like:
"Find the largest value smaller than or equal to X"

think:

Floor → candidate + search RIGHT.

Compare that with normal Binary Search: normal search asks "Does target exist?" Floor asks "What is the best valid value even if target doesn't exist?"
GitHub organization
Binary Search
└── Boundary / Candidate Search
    └── Find Floor in Sorted Array ✅
*/

public class FindFloor{
    public static int findFloor(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int floor = -1;

        while(left <= right){
            int mid = left + (right - left) / 2;
            if(arr[mid] == target){
                return arr[mid];
            }else if(arr[mid] < target){
                floor = arr[mid];
                left = mid + 1;
            }else{
                right = mid-1;
            }
        }

        return floor;
    }
}