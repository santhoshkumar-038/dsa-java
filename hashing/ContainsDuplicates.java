package hashing;

import java.util.HashSet;
import java.util.Set;

/*
    Determine whether an array contains a duplicate.

    ```text

    Input:  [1, 2, 3, 1]
    Output: true

    ```
    ### Pattern

Need to answer:

> “Have I already seen this value?”

Think **HashSet**.

### Brute Force

Compare every pair

*/
public class ContainsDuplicates {
    public static boolean containsDuplicateBrute(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean containsDuplicate(int[] arr) {

        Set<Integer> seen = new HashSet<>();

        for (int value : arr) {
            if (seen.contains(value)) {
                return true;
            }
            seen.add(value);
        }
        return false;
    }
}
