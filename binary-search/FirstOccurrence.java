/*
    arr = [1, 2, 2, 2, 3, 4, 5]
    # First Occurrence in a Sorted Array

## Problem

Given a sorted array containing duplicates, find the **first index** where the target occurs.

Example:

```text
index:  0  1  2  3  4  5  6
arr:   [1, 2, 2, 2, 3, 4, 5]

target = 2
```

Answer:

```text
1
```

---

## Why Normal Binary Search Is Not Enough

Normal Binary Search stops immediately when:

```java
arr[mid] == target
```

But here that may not be the **first occurrence**.

For example, Binary Search might first find:

```text
[1, 2, 2, 2, 3, 4, 5]
          ↑
        mid = 3
```

Index `3` is a valid occurrence, but index `1` is the first occurrence.

Therefore:

```text
Finding target ≠ search finished
```

We save the current answer and continue searching toward the left.

---

# Core Idea

When:

```java
arr[mid] == target
```

save it:

```java
answer = mid;
```

Then search for an earlier occurrence:

```java
right = mid - 1;
```

So:

```text
Found target
     ↓
Save current index
     ↓
Search further LEFT
```

---

# Three Cases

### Case 1: Target Found

```java
if (arr[mid] == target) {
    answer = mid;
    right = mid - 1;
}
```

We don't return immediately.

We remember `mid` and continue searching left.

---

### Case 2: Mid Value Too Small

```text
arr[mid] < target
```

Need a bigger value → search right.

```java
left = mid + 1;
```

---

### Case 3: Mid Value Too Large

```text
arr[mid] > target
```

Need a smaller value → search left.

```java
right = mid - 1;
```

---

# Dry Run

```text
arr = [1, 2, 2, 2, 3, 4, 5]
target = 2
```

Initially:

```text
left = 0
right = 6
answer = -1
```

### Iteration 1

```text
mid = 3
arr[3] = 2
```

Found target:

```text
answer = 3
right = 2
```

Don't return yet.

---

### Iteration 2

```text
left = 0
right = 2

mid = 1
arr[1] = 2
```

Found an earlier occurrence:

```text
answer = 1
right = 0
```

---

### Iteration 3

```text
left = 0
right = 0

mid = 0
arr[0] = 1
```

Since:

```text
1 < 2
```

search right:

```text
left = 1
```

Now:

```text
left = 1
right = 0
```

`left <= right` is false.

Return:

```text
answer = 1
```

---

# Why Initialize `answer = -1`?

Correct:

```java
int answer = -1;
```

Suppose:

```text
arr = [1, 2, 3, 4, 5]
target = 10
```

Target is never found.

Therefore `answer` remains:

```text
-1
```

which means:

```text
Target not found
```

Using:

```java
int answer = 0;
```

would be incorrect because index `0` is a legitimate array index.

---

# Java Solution

```java
public static int firstOccurrence(int[] arr, int target) {

    int left = 0;
    int right = arr.length - 1;
    int answer = -1;

    while (left <= right) {

        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {

            answer = mid;
            right = mid - 1;

        } else if (arr[mid] < target) {

            left = mid + 1;

        } else {

            right = mid - 1;
        }
    }

    return answer;
}
```

---

# Complexity

### Time — O(log n)

Even after finding the target, we continue using Binary Search.

Every iteration eliminates roughly half of the remaining search space.

Therefore:

```text
O(log n)
```

### Extra Space — O(1)

We only maintain:

```text
left
right
mid
answer
```

No additional data structure grows with input size.

Therefore:

```text
O(1)
```

---

# Normal Binary Search vs First Occurrence

Normal Binary Search:

```text
arr[mid] == target
        ↓
     return mid
```

First Occurrence:

```text
arr[mid] == target
        ↓
   answer = mid
        ↓
right = mid - 1
        ↓
continue searching left
```

---

# Interview Recognition

If the question says:

```text
sorted array
+
duplicates
+
first occurrence / first position / leftmost occurrence
```

Think:

```text
Modified Binary Search
```

The key modification is:

```java
if (arr[mid] == target) {
    answer = mid;
    right = mid - 1;
}
```

## One-Line Memory Trick

```text
FIRST occurrence
→ found target
→ save it
→ keep going LEFT
```

**Time: O(log n)**  
**Extra Space: O(1)**
*/
public class FirstOccurrence {
    public static int firstOccurrence(int[] arr, int target){
        int left=0;
        int right=arr.length - 1;
        int answer = -1;

        while(left <= right){
            int mid = left + (right - left) / 2;

            if(arr[mid] == target){
                answer = mid;
                right = mid-1;
            }else if(arr[mid] < target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }

        return answer;
    }
}
