/*
    arr:   [1, 2, 2, 2, 3, 4, 5]
    # Last Occurrence in a Sorted Array

## Problem

Given a **sorted array containing duplicates**, find the index of the **last occurrence** of the target.

Example:

```text
index:  0  1  2  3  4  5  6
arr:   [1, 2, 2, 2, 3, 4, 5]

target = 2
```

Answer:

```text
3
```

because the last `2` occurs at index `3`.

---

# Why Normal Binary Search Is Not Enough

Normal Binary Search does:

```java
if (arr[mid] == target) {
    return mid;
}
```

But with duplicates, the `mid` we find may not be the **last occurrence**.

For example:

```text
[1, 2, 2, 2, 2, 4, 5]
       ↑
     found
```

Finding one `2` doesn't guarantee there isn't another `2` further right.

Therefore:

```text
Finding target ≠ search finished
```

We save the current index and continue searching toward the **right**.

---

# Core Idea

When:

```java
arr[mid] == target
```

save the current index:

```java
answer = mid;
```

Then continue searching on the right:

```java
left = mid + 1;
```

Why?

Because we want to know:

```text
"Does the same target exist at a larger index?"
```

---

# Three Cases

## Case 1 — Target Found

```java
if (arr[mid] == target) {
    answer = mid;
    left = mid + 1;
}
```

Do NOT immediately return.

Save the current answer and search further right.

---

## Case 2 — Mid Value Too Small

If:

```text
arr[mid] < target
```

we need a bigger value.

Because the array is sorted, search right:

```java
left = mid + 1;
```

---

## Case 3 — Mid Value Too Large

If:

```text
arr[mid] > target
```

we need a smaller value.

Search left:

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
mid = 0 + (6 - 0) / 2
    = 3

arr[3] = 2
```

Target found.

Save:

```text
answer = 3
```

But because we're looking for the **last occurrence**, continue right:

```text
left = mid + 1
     = 4
```

Now:

```text
left = 4
right = 6
answer = 3
```

---

### Iteration 2

```text
mid = 4 + (6 - 4) / 2
    = 5

arr[5] = 4
```

Since:

```text
4 > 2
```

we need something smaller.

```java
right = mid - 1;
```

Therefore:

```text
right = 4
```

---

### Iteration 3

```text
left = 4
right = 4

mid = 4

arr[4] = 3
```

Again:

```text
3 > 2
```

Search left:

```text
right = 3
```

Now:

```text
left = 4
right = 3
```

So:

```text
left <= right
```

is false.

Loop terminates.

Our saved answer is:

```text
answer = 3
```

Therefore:

```text
return 3
```

---

# Java Solution

```java
public class LastOccurrence {

    public static int lastOccurrence(int[] arr, int target) {

        int left = 0;
        int right = arr.length - 1;
        int answer = -1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {

                answer = mid;

                // We found target, but there may be
                // another occurrence further right.
                left = mid + 1;

            } else if (arr[mid] < target) {

                left = mid + 1;

            } else {

                right = mid - 1;
            }
        }

        return answer;
    }
}
```

---

# Why `answer = -1`?

Suppose:

```text
arr = [1, 2, 3, 4, 5]
target = 
*/
public class LastOccurrence {
    public static int lastOccurrence(int[] arr, int target){
        int left = 0;
        int right = arr.length - 1;
        int answer = -1;

        while(left <= right){
            int mid = left + (right - left) / 2;

            if(arr[mid] == target){
                answer = mid;
                left = mid + 1;
            }else if(arr[mid] < target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }

        return answer;
    }
}
