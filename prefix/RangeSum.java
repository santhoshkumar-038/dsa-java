package prefix;

/*
## 13. Range Sum Query

### Problem

Given:

```text
arr = [2, 4, 1, 7, 3, 6]
```

Find sum from index `L` to `R`.

For:

```text
L = 1
R = 4
```

Answer:

```text
4 + 1 + 7 + 3 = 15
```

### Brute Force

Traverse `L → R` for every query.

Worst case:

```text
O(n) per query
```

### Prefix Sum

Build:

```text
arr:     [2, 4, 1, 7, 3, 6]
prefix:  [2, 6, 7,14,17,23]
```

Meaning:

```text
prefix[i] = sum from index 0 through i
```

Range formula:

```text
if L == 0:
    prefix[R]

otherwise:
    prefix[R] - prefix[L - 1]
```

Mental model:

> Sum till R − Sum before L

### Solution

```java

```

### Complexity

Building prefix:

```text
Time  : O(n)
Space : O(n)
```

Each range query after prefix is built:

```text
O(1)
```

For `q` queries:

```text
O(n + q)
```

instead of approximately:

```text
O(q × n)
```

### Recognition

```text
Many range-sum queries
```

→ think **Prefix Sum**.

---
*/

class RangeSum{
    public static int rangeSum(int[] arr, int l, int r) {
        int[] prefix = new int[arr.length];
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            prefix[i] = sum;
        }
        if (l == 0) {
            return prefix[r];
        }
        return prefix[r] - prefix[l - 1];
    }
}