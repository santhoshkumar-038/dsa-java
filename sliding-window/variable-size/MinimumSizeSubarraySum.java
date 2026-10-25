/*
    arr = [2, 1, 5, 2, 3, 2]
    target = 7

    # Minimum Size Subarray Sum

**Category:** Array → Sliding Window → Variable Size

## Problem

Given an array of positive integers and a `target`, find the **minimum length of a consecutive subarray** whose sum is greater than or equal to `target`.

Example:

```text
arr = [2, 1, 5, 2, 3, 2]
target = 7
```

Valid windows include:

```text
[2, 1, 5] → sum = 8 → length = 3
[5, 2]    → sum = 7 → length = 2
[2, 3, 2] → sum = 7 → length = 3
```

Answer:

```text
2
```

because `[5, 2]` is the smallest valid window.

---

## Why Variable-Size Sliding Window?

There is no fixed `k`.

We don't know whether the answer will contain:

```text
1 element
2 elements
3 elements
...
```

The size of the window changes depending on whether the condition is satisfied.

Condition:

```text
sum >= target
```

---

## Two Pointers

Maintain:

```text
left  → start of current window
right → end of current window
```

`right` is used to **expand** the window.

`left` is used to **shrink** the window.

---

## Core Mental Model

When the sum is too small:

```text
sum < target

→ need more elements
→ expand RIGHT
```

When the condition is satisfied:

```text
sum >= target

→ current window is valid
→ record its length
→ try to find a smaller window
→ shrink LEFT
```

So remember:

```text
NOT ENOUGH → RIGHT expands

ENOUGH → LEFT shrinks
```

---

## Algorithm

Initialize:

```text
left = 0
sum = 0
minLength = Integer.MAX_VALUE
```

Move `right` through the array.

For every `right`:

```text
sum += arr[right]
```

This **expands** the window.

If:

```text
sum >= target
```

we have a valid window.

Calculate:

```text
windowSize = right - left + 1
```

Update:

```text
minLength = minimum of minLength and windowSize
```

Then shrink:

```text
sum -= arr[left]
left++
```

Continue shrinking **while**:

```text
sum >= target
```

---

## Why `while` Instead of `if`?

One expansion may allow us to shrink multiple times.

Example:

```text
window = [1, 5, 2]
sum = 8
target = 7
```

Valid:

```text
[1, 5, 2] → sum = 8
```

Shrink once:

```text
[5, 2] → sum = 7
```

Still valid.

So we must shrink again.

Therefore:

```java
while (sum >= target)
```

not:

```java
if (sum >= target)
```

### Important Rule

```text
WHILE condition is valid
    record answer
    shrink left
```

---

## Why Record Length Before Shrinking?

Suppose:

```text
window = [5, 2]
sum = 7
target = 7
```

This is a valid window of length `2`.

We must first record:

```text
minLength = 2
```

Then remove `5`.

If we remove first:

```text
[5, 2] → [2]

sum = 2
```

we destroy the valid window before recording its answer.

So:

```text
VALID WINDOW
     ↓
Record answer
     ↓
Shrink
     ↓
Check condition again
```

---

## Important Edge Case

Suppose:

```text
arr = [1, 1, 1]
target = 10
```

No valid window exists.

`minLength` remains:

```text
Integer.MAX_VALUE
```

Therefore return:

```java
return minLength == Integer.MAX_VALUE ? 0 : minLength;
```

---

## Time Complexity — Important

The code contains:

```java
for (...) {
    while (...) {
    }
}
```

but this is **NOT O(n²)**.

Why?

`right` only moves forward:

```text
0 → 1 → 2 → 3 → ... → n-1
```

At most `n` movements.

`left` also only moves forward:

```text
0 → 1 → 2 → 3 → ... → n-1
```

At most `n` movements across the **entire algorithm**.

Therefore:

```text
right movements ≤ n
left movements  ≤ n

total ≤ 2n

O(2n) → O(n)
```

### Time Complexity

**O(n)**

### Extra Space Complexity

Only fixed variables are used:

```text
left
right
sum
windowSize
minLength
```

Therefore:

**O(1)**

---

# Fixed vs Variable Sliding Window

### Fixed Size

Problem gives a fixed `k`.

```text
exactly k consecutive elements
```

Pattern:

```text
Build first k elements
        ↓
Remove outgoing
        ↓
Add incoming
        ↓
Update answer
```

Window size always remains `k`.

### Variable Size

No fixed window size.

Window changes according to a condition.

Pattern:

```text
Expand RIGHT
     ↓
Check condition
     ↓
Condition valid
     ↓
Record answer
     ↓
Shrink LEFT
     ↓
Still valid?
     ↓
Shrink again
```

---

# Recognition Cue

Think **Variable-Size Sliding Window** when you see questions like:

> Smallest/longest consecutive subarray or substring satisfying some condition.

Typical clues:

```text
minimum length
maximum length
at least target
at most something
consecutive / contiguous
substring / subarray
```

Then think:

```text
right → EXPAND
left  → SHRINK
```

based on the condition.

---

## One-Line Revision

**Variable-size sliding window = expand `right` until the condition is satisfied, 
then shrink `left` while the condition remains satisfied, updating the answer along the way.**
*/

public class MinimumSizeSubarraySum {
    public static int minSubArrayLen(int target, int[] arr) {
        int left = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;

        for(int right=0; right<arr.length; right++){
            sum+=arr[right];
            while(sum >= target){
                int windowSize = right - left + 1;
                if(windowSize < minLength){
                    minLength = windowSize;
                }

                sum -= arr[left];
                left++;
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
