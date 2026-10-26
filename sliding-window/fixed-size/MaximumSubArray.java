/* arr = [2, 1, 5, 1, 3, 2]
k = 3
# Maximum Sum Subarray of Size K

## Category

**Array → Sliding Window → Fixed Size Window**

## Problem

Given an array and integer `k`, find the maximum sum among all **consecutive subarrays of size k**.

Example:

```text
arr = [2, 1, 5, 1, 3, 2]
k = 3

Windows:

[2,1,5] → 8
[1,5,1] → 7
[5,1,3] → 9
[1,3,2] → 6

Answer = 9
```

---

## Brute Force

For every valid starting index, calculate the sum of the next `k` elements.

```java
for (int i = 0; i <= arr.length - k; i++) {

    int sum = 0;

    for (int j = i; j < i + k; j++) {
        sum += arr[j];
    }

    // update maximum
}
```

### Complexity

```text
Time  → O(n × k)
Space → O(1)
```

If `k` grows with `n`, worst case can approach `O(n²)`.

---

## Sliding Window Idea

Consecutive windows overlap.

Example:

```text
Old: [2, 1, 5] → 8
New:    [1, 5, 1]
```

Instead of calculating:

```text
1 + 5 + 1
```

again:

```text
8 - 2 + 1 = 7
```

So:

```text
new window sum = old window sum - outgoing element + incoming element
```

---

## Index Formula

When `i` represents the incoming element:

```text
Incoming element → arr[i]
Outgoing element → arr[i-k]
```

Therefore:

```java
windowSum = windowSum - arr[i-k] + arr[i];
```

---

## Important Variables

```text
windowSum → sum of CURRENT window
maxSum    → maximum sum found so far
```

**Very important:**

`windowSum` changes on **every slide**.

`maxSum` changes **only when we find a larger sum**.

Example:

```text
Window      windowSum    maxSum

[5,4]           9           9
[4,1]           5           9
[1,1]           2           9
[1,10]         11          11
```

Do not use one variable for both jobs.

---

## Optimized Solution

```java
public static int maxSumSubarray(int[] arr, int k) {

    int windowSum = 0;

    // First window
    for (int i = 0; i < k; i++) {
        windowSum += arr[i];
    }

    int maxSum = windowSum;

    // Slide the window
    for (int i = k; i < arr.length; i++) {

        windowSum =
                windowSum - arr[i-k] + arr[i];

        if (windowSum > maxSum) {
            maxSum = windowSum;
        }
    }

    return maxSum;
}
```

---

## Why maxSum Is Initialized After First Window

Do:

```java
int maxSum = windowSum;
```

**after** calculating the first window.

Don't initialize:

```java
int maxSum = 0;
```

because the array may contain negative numbers.

Example:

```text
[-5, -2, -3]
k = 2

Windows:
[-5,-2] → -7
[-2,-3] → -5

Answer = -5
```

Returning `0` would be wrong.

---

## Complexity

First window:

```text
O(k)
```

Remaining windows:

```text
O(n-k)
```

Total:

```text
O(k) + O(n-k)
= O(n)
```

Extra space:

```text
O(1)
```

because only fixed variables are used.

---

## Recognition Cue

When the problem says:

> Find maximum/minimum/sum/count among **k consecutive elements**

think:

**Fixed-Size Sliding Window**

Core formula:

```text
Remove outgoing
+
Add incoming
=
Next window
```
or:
java
windowSum =
    windowSum - arr[i-k] + arr[i];

*/
public class MaximumSubArray {
    public static int maxSumSubarray(int[] arr, int k) {
        int windowSum = 0;
        for(int i=0; i<k; i++){
            windowSum+=arr[i];
        }
        int maxSum = windowSum;
        for(int i=k; i<arr.length; i++){
            windowSum = windowSum - arr[i-k] + arr[i];
            if(windowSum > maxSum){
                maxSum = windowSum;
            }
        }
        return maxSum;
    }
}
