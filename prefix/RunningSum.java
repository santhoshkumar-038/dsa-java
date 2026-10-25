package prefix;

/*
    ### Problem

```text
Input:
[3, 1, 2, 10, 1]

Output:
[3, 4, 6, 16, 17]
```

Each position contains the sum from index `0` through that index.

### Thinking

Maintain:

```text
sum
```

It must be declared **outside the loop**, otherwise it resets every iteration.

### Complexity

```text
Time  : O(n)
Space : O(n)
```

Why O(n) space?

Because:

```java
new int[arr.length]
```

grows with the input size.

---
*/

public class RunningSum {
    public static int[] runningSum(int[] arr) {
        int[] result = new int[arr.length];
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            result[i] = sum;
        }
        return result;
    }
}
