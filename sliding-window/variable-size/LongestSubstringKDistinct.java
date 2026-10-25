/*
    s = "eceba"
    # Longest Substring with At Most K Distinct Characters

## Problem

Given a string `s` and integer `k`, find the length of the **longest consecutive substring containing at most `k` distinct characters**.

Example:

```text
s = "eceba"
k = 2
```

Longest valid substring:

```text
"ece"

characters = e, c, e
distinct characters = {e, c}
length = 3
```

Answer:

```text
3
```

---

# Pattern

**Variable-Size Sliding Window + HashMap**

We need:

```text
left      → start of current window
right     → expands current window
freq      → frequency of each character inside window
maxLength → longest valid window found
```

---

# Why HashMap Instead of HashSet?

Previous problem:

```text
Longest Substring Without Repeating Characters
```

We only needed to know:

```text
Is this character already present?
```

So `HashSet` was enough.

Here duplicates are allowed:

```text
"ece"

e → 2
c → 1
```

But we need to know **how many distinct characters** currently exist.

Therefore:

```java
Map<Character, Integer> freq = new HashMap<>();
```

The map represents:

```text
character → frequency inside current window
```

Example:

```text
window = "ece"

map:
e → 2
c → 1

map.size() = 2 distinct characters
```

---

# Validity Condition

Window is valid when:

```text
freq.size() <= k
```

Window is invalid when:

```text
freq.size() > k
```

So the main rule is:

```text
EXPAND right

If distinct characters > k:
    SHRINK left until valid

Once valid:
    calculate length
    update maxLength
```

---

# Expanding the Window

When `right` enters a new character, increase its frequency:

```java
char rightChar = s.charAt(right);

freq.put(
    rightChar,
    freq.getOrDefault(rightChar, 0) + 1
);
```

Example:

```text
Before:
{e=2, c=1}

incoming = b

After:
{e=2, c=1, b=1}
```

Now:

```text
freq.size() = 3
```

If:

```text
k = 2
```

then:

```text
3 > 2 → INVALID
```

We must shrink.

---

# Shrinking the Window

Suppose:

```text
window = "eceb"

map:
e → 2
c → 1
b → 1
```

Remove the character at `left`.

First `e` leaves:

```text
e → 2 - 1
e → 1
```

But `e` still exists in the window.

Therefore we **do not remove `e` from the HashMap yet**.

Current window:

```text
"ceb"

map:
e → 1
c → 1
b → 1
```

Still 3 distinct characters, so continue shrinking.

Remove `c`:

```text
c → 1 - 1
c → 0
```

Now `c` no longer exists inside the window.

Therefore:

```java
freq
*/
import java.util.Map;
import java.util.HashMap;

public class LongestSubstringKDistinct {
    public static int longestSubstringKDistinct(String s, int k) {
        int left = 0;
        int maxLength = 0;

        Map<Character, Integer> freq = new HashMap<>();

        for(int right=0; right<s.length(); right++){
            freq.put(s.charAt(right), freq.getOrDefault(s.charAt(right),0) + 1);

            while(freq.size() > k){
               int freqCount = freq.get(s.charAt(left));
               freq.put(s.charAt(left), freqCount-1);

               if(freq.get(s.charAt(left)) == 0){
                    freq.remove(s.charAt(left));
               }

               left++;
            }

            int windowLength = right - left + 1;
            if(windowLength > maxLength){
                maxLength = windowLength;
            }
        }

        return maxLength;
    }
}
