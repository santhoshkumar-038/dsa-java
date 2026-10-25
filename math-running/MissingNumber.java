/*
    ### Problem

Array contains `n` distinct numbers from `0` to `n`, with one number missing.

```text
Input:
[3, 0, 1]

Output:
2
```

### Thinking

Expected sum:

```text
0 + 1 + 2 + ... + n
```

Actual sum:

```text
sum of array
```

Missing:

```text
expected - actual
```

Expected sum formula:

```text
n * (n + 1) / 2
```
This:

```java
long result = n * (n + 1);
```

can overflow as an `int` **before** being assigned to `long`.

So cast before multiplication:

```java
(long) n * (n + 1)
```

### Complexity

```text
Time  : O(n)
Space : O(1)
```

*/

public class MissingNumber {

    public static int missingNumber(int[] arr) {
        int n = arr.length;
        long expectedSum = (long) n * (n + 1) / 2;
        long actualSum = 0;
        for (int value : arr) {
            actualSum += value;
        }
        return (int) (expectedSum - actualSum);
    }
}
