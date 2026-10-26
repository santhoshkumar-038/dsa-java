/*
    arr:   [2, 5, 8, 12, 16, 23, 38, 56, 72]
    # Binary Search Fundamentals

## When Do We Use Binary Search?

Basic Binary Search requires the search space to be **sorted/ordered**.

Example:

```text
arr = [2, 5, 8, 12, 16, 23, 38, 56, 72]
target = 23
```

Instead of checking every element one by one, Binary Search checks the middle and eliminates roughly **half of the remaining search space**.

---

# Core Variables

```text
left  → beginning of current search area
right → end of current search area
mid   → middle of current search area
```

Initially:

```java
int left = 0;
int right = arr.length - 1;
```

Everything between `left` and `right` is still a possible answer.

---

# Calculating Mid

Use:

```java
int mid = left + (right - left) / 2;
```

Think of it as:

```text
right - left
→ distance between boundaries

(right - left) / 2
→ half that distance

left + half-distance
→ actual middle index
```

Example:

```text
left = 4
right = 8

right - left = 4

half = 4 / 2 = 2

mid = left + 2
    = 6
```

So `6` is the actual index.

---

# Why Not Just `(right - left) / 2`?

Suppose:

```text
left = 4
right = 8
```

Then:

```text
(right - left) / 2
= 2
```

But index `2` isn't inside our current search range `[4...8]`.

`2` represents the **distance from left**, not the actual index.

Therefore:

```text
left + 2 = 6
```

---

# Why Not `(left + right) / 2`?

Mathematically it works.

But:

```java
(left + right) / 2
```

can theoretically overflow when both indices are very large Java `int` values.

Preferred interview version:

```java
left + (right - left) / 2
```

---

# Three Binary Search Cases

After calculating `mid`, compare:

```java
arr[mid]
```

with:

```java
target
```

## Case 1 — Found

```text
arr[mid] == target
```

Return the index:

```java
return mid;
```

---

## Case 2 — Mid Value Is Too Small

```text
arr[mid] < target
```

Example:

```text
arr[mid] = 16
target = 23
```

We need a **bigger value**.

Because the array is sorted, everything at `mid` and to its left can be discarded.

```text
TOO SMALL
→ go RIGHT
→ move LEFT boundary
```

Code:

```java
left = mid + 1;
```

Important memory:

```text
mid value too SMALL
→ target is RIGHT
→ left = mid + 1
```

---

## Case 3 — Mid Value Is Too Large

```text
arr[mid] > target
```

Example:

```text
arr[mid] = 38
target = 23
```

We need a **smaller value**.

Discard `mid` and everything to its right.

```text
TOO BIG
→ go LEFT
→ move RIGHT boundary
```

Code:

```java
right = mid - 1;
```

Important memory:

```text
mid value too BIG
→ target is LEFT
→ right = mid - 1
```

---

# Why `mid + 1` and `mid - 1`?

Because `mid` has already been checked.

If:

```text
arr[mid] != target
```

there is no reason to include `mid` in the next search.

So:

```text
Search right → left = mid + 1

Search left  → right = mid - 1
```

---

# Why `while (left <= right)`?

Use:

```java
while (left <= right)
```

because when:

```text
left == right
```

there is still **one element remaining to check**.

Example:

```text
left = 5
right = 5

window = [23]
```

That element could be our target.

Therefore the search should continue.

When:

```text
left > right
```

the search space is empty.

Then the target does not exist.

---

# Correct Java Solution

```java
public static int binarySearch(int[] arr, int target) {

    int left = 0;
    int right = arr.length - 1;

    while (left <= right) {

        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {

            return mid;

        } else if (arr[mid] < target) {

            left = mid + 1;

        } else {

            right = mid - 1;
        }
    }

    return -1;
}
```

---

# Important Mistake

Wrong:

```java
if (mid == target)
```

Why?

`mid` is an **index**.

Example:

```text
mid = 5
arr[mid] = 23
target = 23
```

We don't compare:

```text
5 == 23
```

We compare:

```text
arr[5] == 23
23 == 23
```

Therefore:

```java
if (arr[mid] == target)
```

---

# Target Not Found

If Binary Search finishes because:

```text
left > right
```

there are no possible positions remaining.

Return:

```java
return -1;
```

Do not return `mid`, because the last calculated `mid` doesn't mean the target was found.

---

# Time Complexity — O(log n)

Binary Search repeatedly eliminates roughly half of the remaining search space.

Example:

```text
16 elements
↓
8
↓
4
↓
2
↓
1
```

Only about 4 reductions.

For a very large input:

```text
1,000,000
500,000
250,000
125,000
...
```

we need only around 20 halvings.

Therefore:

```text
Time = O(log n)
```

---

# Extra Space — O(1)

We only maintain:

```text
left
right
mid
```

No additional array, HashMap, HashSet, etc.

Therefore:

```text
Extra Space = O(1)
```

This applies to the **iterative Binary Search** implementation.

---

# Recognition Cue

When you see:

```text
sorted array
+
find/search for a value
```

Binary Search should immediately come to mind.

Later we'll learn that Binary Search is broader than simply:

```text
"find target in sorted array"
```

It can also be used for:

```text
first occurrence
last occurrence
insertion position
rotated arrays
binary search on answer
```

But the fundamental engine remains:

```text
Choose middle
→ determine which side can contain answer
→ eliminate the other side
→ repeat
```

---

# One-Line Revision

```text
arr[mid] == target → FOUND

arr[mid] < target
→ need BIGGER
→ go RIGHT
→ left = mid + 1

arr[mid] > target
→ need SMALLER
→ go LEFT
→ right = mid - 1
```

**Time: O(log n)**  
**Extra Space: O(1)**
*/

public class BinarySearch {
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int mid = 0;
        while(left <= right){
            mid = left + (right - left) / 2;

            if(arr[mid] == target){
                return mid;
            }else if(arr[mid] < target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }

        return -1;
    }
}
