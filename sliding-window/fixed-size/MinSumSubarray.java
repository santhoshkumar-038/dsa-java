/*
    arr = [4, 2, 1, 7, 8, 1, 2, 8]  
    k = 3
    # Minimum Sum Subarray of Size K

**Category:** Array → Sliding Window → Fixed Size

### Problem

Find the minimum sum among all subarrays containing exactly `k` consecutive elements.

Example:

```text
arr = [4, 2, 1, 7, 8, 1, 2, 8]
k = 3
```

### Variables

```text
windowSum → sum of CURRENT window
minSum    → smallest window sum seen so far
```

### Step 1 — Build First Window

Calculate the sum of the first `k` elements.

```text
[4, 2, 1] → windowSum = 7
```

Initialize:

```text
minSum = windowSum
```

Important: initialize `minSum` using the **first valid window**, not `0`.

This also works correctly when the array contains negative numbers.

### Step 2 — Slide the Window

Start from:

```text
i = k
```

For every new window:

```text
outgoing index = i - k
incoming index = i
```

Update:

```text
windowSum = windowSum - arr[i-k] + arr[i]
```

Then:

```text
if windowSum < minSum
    minSum = windowSum
```

### Core Pattern

```text
Build first window
        ↓
minSum = first window
        ↓
Remove outgoing
        ↓
Add incoming
        ↓
Compare current window with minSum
        ↓
Repeat
```

### Complexity

First window:

```text
O(k)
```

Remaining elements:

```text
O(n-k)
```

Total:

```text
O(k) + O(n-k)
= O(n)
```

**Time Complexity: O(n)**

Only fixed variables are used:

```text
windowSum
minSum
i
```

**Extra Space: O(1)**

### Recognition Cue

When you see:

> Find the minimum/maximum/sum/count among exactly `k` consecutive elements

think:

**Fixed-Size Sliding Window**

### Key Formula

```text
new window
= old window
- outgoing element
+ incoming element
```

*/

class MinSumSubArray{
    public static int minSumSubarray(int[] arr, int k) {
        int windowSum = 0;
        for(int i=0; i<k; i++){
            windowSum+=arr[i];
        }
        int minSum = windowSum;

        for(int i=k; i<arr.length; i++){
            windowSum = windowSum - arr[i-k] + arr[i];
            if(windowSum < minSum){
                minSum = windowSum;
            }
        }

        return minSum;
    }
}