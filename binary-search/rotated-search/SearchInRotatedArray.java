/*
    arr = [6, 7, 1, 2, 3, 4, 5]
    target = 7
    Day 8 — Revision Notes: Search in Rotated Sorted Array
Problem
A sorted array has been rotated:
Normal:
[1, 2, 3, 4, 5, 6, 7]

Rotated:
[4, 5, 6, 7, 1, 2, 3]

Find the index of a target in O(log n).

For this version, assume distinct elements.
Core observation ⭐
At every Binary Search iteration:
At least one half is sorted.

Don't try to decide where the target is immediately.

Ask two questions:
1. Which half is sorted?

2. Is target inside that sorted half?

Step 1 — Is left half sorted?
Check:
arr[left] <= arr[mid]

If true:
LEFT HALF IS SORTED

Then check whether target belongs to its range:
arr[left] <= target && target < arr[mid]

If yes:
right = mid - 1;

Otherwise:
left = mid + 1;

Step 2 — Otherwise, right half is sorted
Check whether target belongs to the right sorted range:
arr[mid] < target && target <= arr[right]

If yes:
left = mid + 1;

Otherwise:
right = mid - 1;

Interview mental model
Don't memorize the code. Remember:
              Calculate mid
                    |
           arr[mid] == target?
              /           \
           YES             NO
           return          |
                    Which half sorted?
                       /        \
                    LEFT        RIGHT
                      |           |
                target in     target in
                its range?    its range?
                  /  \          /  \
                YES  NO       YES  NO
                 ←    →        →    ←

The sorted half gives us a reliable range. If the target falls inside that range, search it. Otherwise, discard it and search the other half.
Important mistake from today
Keep indices and values separate:
left, mid, right
→ indices

arr[left], arr[mid], arr[right], target
→ values

So this is wrong:
target < mid       // ❌ value compared with index

This is correct:
target < arr[mid]  // ✅ value compared with value

Complexity
Time: O(log n) ✅ — we discard roughly half the search space each iteration.

Extra Space: O(1) ✅ — only a few variables.
Pattern recognition
If you hear:
"Sorted array was rotated at some pivot; find target in O(log n)."

Think:

Modified Binary Search → identify sorted half → range check → discard half.
GitHub
Binary Search
├── Basic Search
├── Boundary Search
│   ├── First Occurrence ✅
│   ├── Last Occurrence ✅
│   └── Search Insert Position ✅
├── Candidate Search
│   ├── Floor ✅
│   └── Ceil ✅
└── Rotated Array
    └── Search in Rotated Sorted Array ✅
*/

public class SearchInRotatedArray {
    public static int search(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(arr[mid] == target){
                return mid;
            }else if(arr[left] <= arr[mid]){
               if(arr[left] <= target && target < arr[mid]){
                    right = mid - 1;
               }else{
                    left = mid + 1;
               }
            }else{
                if(arr[mid] < target && target <= arr[right]){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
