package prefix;

/*
## 14. Equilibrium Index Using Prefix Sum

### Problem

Find an index where:

```text
left sum == right sum
```

Example:

```text
[1, 3, 5, 2, 2]
       ↑
     index 2
```

Left:

```text
1 + 3 = 4
```

Right:

```text
2 + 2 = 4
```

Answer:

```text
2
```

### Prefix Thinking

For index `i`:

```text
leftSum =
    i == 0 ? 0 : prefix[i - 1]
```

Right:

```text
rightSum =
    totalSum - prefix[i]

### Complexity

```text
Time  : O(n)
Space : O(n)
```

Two separate O(n) loops:

```text
O(n) + O(n)
= O(2n)
= O(n)
```

Not O(n²).

---

*/
public class EquilibriumIndex {

    public static int equilibriumIndex(int[] arr) {
        int[] prefix = new int[arr.length];
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            prefix[i] = sum;
        }

        int totalSum = prefix[prefix.length - 1];
        for (int i = 0; i < arr.length; i++) {
            int leftSum;

            if (i == 0) {
                leftSum = 0;
            } else {
                leftSum = prefix[i - 1];
            }

            int rightSum = totalSum - prefix[i];

            if (leftSum == rightSum) {
                return i;
            }
        }
        return -1;
    }
}
