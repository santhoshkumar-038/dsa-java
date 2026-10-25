/*
    arr = [2, 1, 3, 2, 1, 1, 1]
    k = 5

    # Longest Subarray With Sum ≤ K

**Category:** Array → Sliding Window → Variable Size

> Assumption: array contains positive/non-negative numbers.

## Problem

Find the **maximum length of a consecutive subarray** whose sum is `<= k`.

Example:

```text
arr = [2, 1, 3, 2, 1, 1, 1]
k = 5
```

One valid longest window:

```text
[2, 1, 1, 1]

sum = 5
length = 4
```

Answer:

```text
4
```

---

## Variables

```text
left      → start of current window
right     → end of current window
sum       → sum of current window
maxLength → longest valid window found
```

---

## Core Idea

`right` expands the window:

```text
sum += arr[right]
```

If:

```text
sum > k
```

the window becomes invalid.

Since the elements are positive/non-negative, adding more elements cannot fix an oversized sum.

Therefore:

```text
INVALID → shrink LEFT
```

We keep shrinking:

```text
while sum > k
```

until:

```text
sum <= k
```

Now the window is valid, so calculate its length:

```text
windowLength = right - left + 1
```

and update `maxLength`.

---

## Main Mental Model

```text
Expand RIGHT
     ↓
sum > k ?
     ↓ YES
Shrink LEFT
     ↓
Still sum > k ?
     ↓ YES
Shrink again
     ↓
sum <= k
     ↓
Window is VALID
     ↓
Calculate length
     ↓
Update maxLength
```

Remember:

```text
INVALID → SHRINK
VALID   → RECORD LENGTH + KEEP EXPANDING
```

---

## Why Don't We Recalculate the Sum?

Suppose:

```text
window = [2, 1, 3]
sum = 6
k = 5
```

The window is invalid.

Remove `2`:

```text
sum = 6 - 2
    = 4

window = [1, 3]
```

We do **not** need to calculate:

```text
1 + 3
```

again.

The existing `sum` already represents the whole window.

So:

```text
EXPAND:
sum += arr[right]

SHRINK:
sum -= arr[left]
```

This is one of the main advantages of Sliding Window.

---

## Why `while`?

Suppose removing one element is not enough.

```text
sum > k
```

may still be true after one shrink.

Therefore:

```java
while (sum > k) {
    sum -= arr[left];
    left++;
}
```

We keep shrinking until the window becomes valid.

---

## Important Property

Once an element leaves from the left:

```text
left++
```

it **never comes back**.

Both pointers move only forward:

```text
right → → → → →

left  → → → → →
```

This is important for understanding the `O(n)` complexity.

---

# Difference From Problem 1

## Problem 1

**Minimum length where:**

```text
sum >= target
```

Once valid:

```text
VALID
 ↓
record length
 ↓
SHRINK while valid
```

Why?

Because we want the **smallest** valid window.

---

## Problem 2

**Maximum length where:**

```text
sum <= k
```

When invalid:

```text
INVALID
 ↓
SHRINK until valid
 ↓
record length
 ↓
continue expanding
```

Why?

Because we want the **largest** valid window.

### Easy Comparison

```text
Minimum valid window:
VALID → try shrinking

Maximum valid window:
INVALID → must shrink
```

---

# Complexity

Even though the code looks like:

```java
for (...) {
    while (...) {
    }
}
```

it is **not O(n²)**.

`right` moves through the array at most `n` times.

`left` also moves through the array at most `n` times total.

```text
right movements ≤ n
left movements  ≤ n

total ≤ 2n

O(2n) → O(n)
```

### Time Complexity

**O(n)**

### Extra Space

Only fixed variables are maintained:

```text
left
right
sum
maxLength
```

Therefore:

**O(1)**

---

# Recognition Cue

If the question says something like:

> Find the **longest consecutive subarray** satisfying a limit such as `sum <= k`

think:

**Variable-Size Sliding Window**

For positive/non-negative numbers:

```text
Expand right
      ↓
Invalid?
      ↓
Shrink left until valid
      ↓
Record maximum length
```

---

# One-Line Revision

**For maximum valid window problems: expand `right`, shrink `left` only when the window becomes invalid, 
then update the maximum length once the window is valid again.**
*/

public class LongestSubarray {
    public static int longestSubarray(int[] arr, int k) {
        int left = 0;
        int sum = 0;
        int maxLength = 0;

        for(int right = 0; right < arr.length; right++){
            sum += arr[right];

            while(sum > k){
                sum -= arr[left];
                left++;
            }
            int windowLength = right - left + 1;

            if (windowLength > maxLength) {
                maxLength = windowLength;
            }
        }

        return maxLength;
    }    
}
