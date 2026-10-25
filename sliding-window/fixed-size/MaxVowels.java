/*

s = "abciiidef"
k = 3

# Maximum Vowels in Substring of Size K

**Category:** String → Sliding Window → Fixed Size

### Problem

Find the maximum number of vowels in any substring containing exactly `k` consecutive characters.

Example:

```text
s = "abciiidef"
k = 3

"abc" → 1
"bci" → 1
"cii" → 2
"iii" → 3 ⭐
"iid" → 2
"ide" → 2
"def" → 1

Answer = 3
```

### State to Maintain

```text
windowVowels → number of vowels in CURRENT window
maxVowels    → maximum number of vowels seen so far
```

Important:

```text
windowVowels changes every time the window slides.

maxVowels changes only when we find a better window.
```

### First Window

Loop through the first `k` characters.

If current character is:

```text
a / e / i / o / u
```

increment `windowVowels`.

Then:

```text
maxVowels = windowVowels
```

### Sliding the Window

Start the second loop from:

```text
i = k
```

For every slide:

```text
outgoing index = i - k
incoming index = i
```

If outgoing character is a vowel:

```text
windowVowels--
```

If incoming character is a vowel:

```text
windowVowels++
```

Then compare:

```text
maxVowels = max(maxVowels, windowVowels)
```

### Core Mental Model

For sums:

```text
remove outgoing value
add incoming value
```

For vowel counts:

```text
outgoing is vowel → count--
incoming is vowel → count++
```

This shows that Sliding Window is **not only for sums**.

We maintain whatever information the current window needs.

### Complexity

First `k` characters:

```text
O(k)
```

Remaining characters:

```text
O(n-k)
```

Therefore:

```text
O(k) + O(n-k) = O(n)
```

**Time: O(n)**

For the sliding-window state itself:

```text
windowVowels
maxVowels
outgoingElement
incomingElement
```

Fixed number of variables → **O(1)** auxiliary space.

However, with:

```java
s.toLowerCase();
s.toCharArray();
```

new data proportional to `n` can be created, so the actual implementation uses **O(n) extra space**.

If input is already lowercase and we use:

```java
s.charAt(i)
```

directly, extra space can be **O(1)**.

### Recognition Cue

If the problem says:

> Find maximum/minimum/count/sum among exactly `k` consecutive characters/elements.

Think:

**Fixed-Size Sliding Window**

Pattern:

```text
Build first window
      ↓
Remove outgoing
      ↓
Add incoming
      ↓
Update best answer
      ↓
Repeat
```

*/

public class MaxVowels {
    public static int maxVowels(String s, int k) {
        int maxWindow = 0;
        for(int i=0; i<k; i++){
            if(s.charAt(i) == 'a' || s.charAt(i) == 'e' || s.charAt(i) == 'i' || s.charAt(i) == 'o' || s.charAt(i) == 'u'){
                maxWindow++;
            }
        }

        int maxVowels = maxWindow;

        for(int i=k; i<s.length(); i++){
            char outgoingElement = s.charAt(i-k);
            if(outgoingElement == 'a' || outgoingElement == 'e' || outgoingElement == 'i' || outgoingElement == 'o' || outgoingElement == 'u'){
                maxWindow -= 1;
            }

            char incomingElement = s.charAt(i);
            if(incomingElement == 'a' || incomingElement == 'e' || incomingElement == 'i' || incomingElement == 'o' || incomingElement == 'u'){
                maxWindow += 1;
            }

            if(maxWindow > maxVowels){
                maxVowels = maxWindow;
            }
        }
        return maxVowels; 
    }
}
