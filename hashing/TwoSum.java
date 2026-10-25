package hashing;

import java.util.HashMap;
import java.util.Map;

/*

Find two indices whose values add up to `target`.

```text
arr    = [2, 7, 11, 15]
target = 9

Output = [0, 1]
```

### Brute Force

Try every pair.

```text
Time  : O(n²)
Space : O(1)
```

### Optimized Thinking

For current value:

```text
needed = target - current
```

Ask:

> Have I already seen `needed`?

Because we need the **index**, store:

```text
value → index
```

### Complexity

```text
Average Time : O(n)
Space        : O(n)
```

### Important

**Check before inserting current element.**

Otherwise the same element could incorrectly be used twice.

### Recognition

```text
pair + target + unsorted array
```

Think:

```text
needed = target - current
```

then HashMap.

*/
public class TwoSum {

    public static int[] twoSumBrute(int[] arr, int target) {   
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    public static int[] twoSum(int[] arr, int target) {

        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int needed = target - arr[i];
            if (seen.containsKey(needed)) {
                return new int[]{seen.get(needed), i};
            }
            seen.put(arr[i], i);
        }

        return new int[]{-1, -1};
    }
}
