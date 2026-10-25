package prefix;

/*
    ## 16. Pivot Index — O(1) Extra Space

### Problem

Find an index where:

```text
leftSum == rightSum
```

Example:

```text
[1, 7, 3, 6, 5, 6]
          ↑
        index 3
```

Left:

```text
1 + 7 + 3 = 11
```

Right:

```text
5 + 6 = 11
```

### Better Thinking

Instead of creating a prefix array, first calculate:

```text
totalSum
```

Then maintain:

```text
leftSum
```

At index `i`:

```text
rightSum =
totalSum - leftSum - arr[i]
```

### Very Important

At the beginning of iteration `i`:

```text
leftSum = sum of elements BEFORE i
```

Conceptually:

```text
leftSum = arr[0] + ... + arr[i-1]
```

After checking index `i`:

```java
leftSum += arr[i];
```

Why?

Because the current element becomes part of the **left side for the next iteration**.

### Complexity

```text
Time  : O(n)
Space : O(1)
```

Again:

```text
first loop  = O(n)
second loop = O(n)

O(n) + O(n)
= O(n)
```

---
*/

public class PivotIndex {

    public static int pivotIndex(int[] arr) {
        int totalSum = 0;
        for (int value : arr) {
            totalSum += value;
        }
        int leftSum = 0;
        for (int i = 0; i < arr.length; i++) {
            int rightSum = totalSum - leftSum - arr[i];
            if (leftSum == rightSum) {
                return i;
            }
            leftSum += arr[i];
        }
        return -1;
    }
}
