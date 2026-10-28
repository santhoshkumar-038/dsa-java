/*
    arr = [1, 3, 5, 6]
    # Search Insert Position

## Problem

Given a **sorted array of distinct integers** and a target:

- If target exists → return its index.
- If target doesn't exist → return the index where it should be inserted to maintain sorted order.

Example:

```text
arr = [1, 3, 5, 6]
target = 5

answer = 2
```

Because `5` already exists at index `2`.

---

## Target Doesn't Exist

```text
arr = [1, 3, 5, 6]
target = 2
```

To maintain sorted order:

```text
[1, 2, 3, 5, 6]
    ↑
  index 1
```

Therefore:

```text
answer = 1
```

---

# Core Idea

Run normal Binary Search.

If:

```text
arr[mid] == target
```

return:

```java
return mid;
```

Otherwise continue narrowing the search space normally.

The important new idea is:

> If the target is not found, after Binary Search finishes, `left` points to the correct insertion position.

Therefore:

```java
return left;
```

---

# Binary Search Rules

### Target Found

```java
if (arr[mid] == target) {
    return mid;
}
```

### Mid Value Too Small

```text
arr[mid] < target
```

Need something bigger → search right:

```java
left = mid + 1;
```

### Mid Value Too Large

```text
arr[mid] > target
```

Need something smaller → search left:

```java
right = mid - 1;
```

---

# Example — Insert in Middle

```text
arr = [1, 3, 5, 6]
target = 2
```

Start:

```text
left = 0
right = 3

mid = 1
arr[mid] = 3
```

Since:

```text
3 > 2
```

search left:

```text
right = 0
```

Next:

```text
left = 0
right = 0

mid = 0
arr[mid] = 1
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

Loop terminates.

Return:

```java
return left;
```

Therefore:

```text
answer = 1
```

---

# Example — Target Bigger Than Everything

```text
arr = [1, 3, 5, 6]
target = 7
```

Binary Search eventually reaches:

```text
left = 3
right = 3

mid = 3
arr[mid] = 6
```

Since:

```text
6 < 7
```

move left:

```text
left = 4
```

Now:

```text
left = 4
right = 3
```

Loop ends.

Return:

```text
4
```

Which is correct:

```text
[1, 3, 5, 6, 7]
             ↑
           index 4
```

---

# Example — Target Smaller Than Everything

```text
arr = [1, 3, 5, 6]
target = 0
```

Binary Search keeps moving `right` toward the left.

Eventually:

```text
left = 0
right = -1
```

Loop terminates.

Return:

```text
left = 0
```

Correct insertion:

```text
[0, 1, 3, 5, 6]
 ↑
index 0
```

---

# Why Does `left` Give the Insertion Position?

During Binary Search:

```text
arr[mid] < target
→ left moves past values that are too small

arr[mid] > target
→ right moves before values that are too large
```

Eventually the boundaries cross:

```text
right < left
```

At that point, `left` sits exactly at the boundary where the target belongs.

Think:

```text
smaller values | TARGET BELONGS HERE | larger values
                         ↑
                        left
```

---

# Java Solution

```java
public class SearchInsert {

    public static int searchInsert(int[] arr, int target) {

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

        return left;
    }
}
```

---

# Complexity

## Time Complexity

```text
O(log n)
```

Each iteration removes roughly half of the remaining search space.

## Extra Space Complexity

```text
O(1)
```

Only `left`, `right`, and `mid` are maintained.

---

# Difference From Basic Binary Search

Basic Binary Search:

```text
target found     → return mid
target not found → return -1
```

Search Insert Position:

```text
target found     → return mid
target not found → return left
```

That final line is the key difference.

---

# Interview Recognition

When you see:

```text
sorted array
+
find target OR position where target belongs
```

think:

```text
Binary Search → Search Insert Position
```

---

# One-Line Revision

```text
Found target → return mid

Too small → left = mid + 1

Too big → right = mid - 1

Not found → return left
```

**Time: O(log n)**  
**Extra Space: O(1)**
*/
public class SearchInsert {
    public static int searchInsert(int[] arr, int target){
        int left = 0;
        int right = arr.length - 1;
        
        while(left <= right){
            int mid = left + (right - left) / 2;

            if(arr[mid] == target){
                return mid;
            }else if(arr[mid] < target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return left;
    }
}
