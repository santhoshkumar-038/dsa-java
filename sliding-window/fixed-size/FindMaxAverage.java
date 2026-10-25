/*

arr = [1, 12, -5, -6, 50, 3]
k = 4
# Maximum Average Subarray of Size K

**Category:** Array → Sliding Window → Fixed Size

### Problem

Find the maximum average among all consecutive subarrays of size `k`.

Example:

```text
arr = [1, 12, -5, -6, 50, 3]
k = 4

Windows:

[1, 12, -5, -6]  → sum = 2  → avg = 0.5
[12, -5, -6, 50] → sum = 51 → avg = 12.75
[-5, -6, 50, 3]  → sum = 42 → avg = 10.5

Answer = 12.75
```

### Sliding Window Idea

Calculate the first `k` elements once.

For every next window:

```text
new window
=
old window
- outgoing element
+ incoming element
```

Index-wise:

```text
Outgoing → arr[i-k]
Incoming → arr[i]
```

So:

```text
windowSum = windowSum - arr[i-k] + arr[i]
```

### Important Insight

Since every window has the **same size `k`**:

```text
largest sum
    ↓
largest average
```

Therefore, we don't need to calculate average for every window.

Find `maxSum` first, then:

```text
maxAverage = maxSum / k
```

### Java Important Point

Avoid integer division:

```java
(double) maxSum / k
```

Example:

```text
51 / 4         → 12      ❌ integer division
(double)51 / 4 → 12.75   ✅
```

### Complexity

```text
First window      → O(k)
Remaining windows → O(n-k)

Total:
O(k) + O(n-k)
= O(n)

Extra Space → O(1)
```

### Recognition Cue

When you see:

> maximum/minimum/average/sum over exactly **k consecutive elements**

think:

**Fixed-Size Sliding Window**

Core mental model:

```text
Remove outgoing → Add incoming → Update answer
```

*/

public class FindMaxAverage {
    public static double findMaxAverage(int[] arr, int k) {
        int windowSum = 0;
        for(int i=0; i<k; i++){
            windowSum += arr[i];
        }
        int maxSum = windowSum;

        for(int i=k; i<arr.length; i++){
            windowSum = windowSum - arr[i-k] + arr[i];
            if(windowSum > maxSum){
                maxSum = windowSum;
            }
        }

        double maxAverage = (double) maxSum / k;
        return maxAverage;
    }
}
