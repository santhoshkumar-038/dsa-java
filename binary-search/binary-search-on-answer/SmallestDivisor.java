/*
    arr = [1,2,5,9]
    threshold = 6
    Day 10 Revision Notes — Smallest Divisor
Problem
Find the smallest positive divisor such that after dividing every number and rounding upward:
total <= threshold

Example:
arr = [1,2,5,9]
threshold = 6

answer = 5

Brute Force
Try every divisor:
1 → maxElement

For each divisor, traverse the entire array.
Time:  O(n × m)
Space: O(1)

Optimized — Binary Search on Answer
Search space:
left = 1
right = maxElement

Here left, mid, right represent possible divisor values, not indices.

The validity pattern looks like:
1   2   3   4   5   6   7 ...
❌  ❌  ❌  ❌  ✅  ✅  ✅ ...
                 ↑
           smallest valid

Decision:
total <= threshold
    → mid is valid
    → answer = mid
    → search LEFT

total > threshold
    → mid is too small
    → search RIGHT

Ceiling division
You understood this version better, so keep using it:
total += num / mid;

if (num % mid != 0) {
    total++;
}

Meaning:
Divide normally. If anything remains, one extra unit is required.

Later you can shorten it to:
(num + mid - 1) / mid

but there's no need to force the shortcut right now.
Pattern Recognition
You should now start noticing this interview wording:
Find the minimum value such that a condition becomes valid.

Ask yourself:
Can I search possible answers?

Does validity look like:

❌ ❌ ❌ ❌ ✅ ✅ ✅
            ↑
       first valid?

If yes → strong signal for Binary Search on Answer.

You've now solved this pattern three times:
Binary Search on Answer
├── Integer Square Root ✅
│   └── largest valid answer
│
├── Koko Eating Bananas ✅
│   └── smallest valid speed
│
└── Smallest Divisor ✅
    └── smallest valid divisor
*/

public class SmallestDivisor {
    public static int smallestDivisorBruteForce(int[] arr, int threshold){
        int max = 0;
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }

        for(int divisor=1; divisor<= max; divisor++){
            int sum = 0;
            for(int num: arr){
                sum += num / divisor;

                if(num % divisor != 0){
                    sum++; 
                }
            }

            if(sum <= threshold){
                return divisor;
            }
        }

        return -1;
    }

    public static int smallestDivisorBinarySearch(int[] arr, int threshold) {
        int max = 0;
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }

        int left=1;
        int right = max;
        int answer = -1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            int total = 0;
            for(int num: arr){
                total += num / mid;
                if(num % mid != 0){
                    total++;
                }
            }

            if(total <= threshold){
                answer = mid;
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return  answer;
    }
}
