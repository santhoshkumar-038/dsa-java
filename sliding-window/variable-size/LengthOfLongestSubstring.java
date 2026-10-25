/*
    s = "abcabcbb"
    # Longest Substring Without Repeating Characters

## Problem

Given a String, find the length of the **longest substring containing no duplicate characters**.

Example:

```text
s = "abcabcbb"

answer = 3
```

Because:

```text
"abc" → length 3
```

---

## Pattern

**Variable-Size Sliding Window + HashSet**

We maintain:

```text
left      → start of current window
right     → expands the window
HashSet   → characters currently inside the window
maxLength → longest valid window found
```

---

# Core Idea

The condition for a valid window is:

```text
Every character inside the window must be unique.
```

As `right` moves forward, check the incoming character.

### If character is NOT already in the Set

The window remains valid.

```text
Add character
Calculate window length
Update maxLength
```

### If character IS already in the Set

The window would contain a duplicate.

Therefore:

```text
SHRINK from left
```

Keep shrinking until the incoming character no longer exists in the current window.

Then add it.

---

# Important Algorithm

```text
for every right:

    while incoming character already exists:

        remove s[left] from HashSet
        left++

    add incoming character to HashSet

    windowLength = right - left + 1

    update maxLength
```

---

# Why WHILE Instead of IF?

Suppose:

```text
current window = "abcd"
incoming = 'c'
```

Set:

```text
{a, b, c, d}
```

`c` already exists.

Remove `a`:

```text
window = "bcd"

c still exists ❌
```

Remove `b`:

```text
window = "cd"

c still exists ❌
```

Remove old `c`:

```text
window = "d"

c no longer exists ✅
```

Now add incoming `c`:

```text
window = "dc"
```

Therefore one shrink may not be enough.

That's why:

```java
while (seen.contains(s.charAt(right))) {
    seen.remove(s.charAt(left));
    left++;
}
```

---

# Important: We Don't Modify the String

We never do:

```java
s.replace(...)
```

The original String remains unchanged.

For:

```text
s = "abcabcbb"
```

we only change:

```text
left
right
HashSet
```

The current window is logically:

```text
s[left ... right]
```

When `left` moves, the character is removed only from the HashSet:

```java
seen.remove(s.charAt(left));
left++;
```

---

# Java Solution

```java
public static int lengthOfLongestSubstring(String s) {
    int left = 0;
    int maxLength = 0;

    Set<Character> seen = new HashSet<>();

    for (int right = 0; right < s.length(); right++) {

        while (seen.contains(s.charAt(right))) {
            seen.remove(s.charAt(left));
            left++;
        }

        seen.add(s.charAt(right));

        int windowLength = right - left + 1;

        if (windowLength > maxLength) {
            maxLength = windowLength;
        }
    }

    return maxLength;
}
```

---

# Dry Run

For:

```text
s = "abcabcbb"
```

Start:

```text
left = 0
set = {}
maxLength = 0
```

Read `a`:

```text
{a}
window = "a"
maxLength = 1
```

Read `b`:

```text
{a,b}
window = "ab"
maxLength = 2
```

Read `c`:

```text
{a,b,c}
window = "abc"
maxLength = 3
```

Next character = `a`.

But:

```text
set contains a ❌
```

Shrink:

```text
remove old a

window = "bc"
set = {b,c}
```

Now incoming `a` can be added:

```text
window = "bca"
set = {b,c,a}
```

Length remains `3`.

The same process continues.

Final answer:

```text
3
```

---

# Complexity

## Time — O(n)

At first this can look like:

```text
for + while → O(n²)?
```

No.

`right` only moves forward.

```text
right → maximum n movements
```

`left` also only moves forward.

```text
left → maximum n movements
```

Therefore total pointer movement is proportional to:

```text
n + n = 2n
```

Ignore constants:

```text
O(n)
```

## Extra Space — O(n)

In the worst case all characters are unique:

```text
"abcdefgh..."
```

The HashSet may store all `n` characters.

Therefore:

```text
O(n)
```

For a fixed-size character alphabet such as ASCII, the practical bound can be constant, but **O(n) is the safe general complexity answer**.

---

# Recognition Cue

If you see:

```text
Longest substring/subarray
+
consecutive elements
+
condition must remain valid
```

think:

```text
Variable Sliding Window
```

If validity depends on duplicates/characters:

```text
Sliding Window + HashSet / HashMap
```

---

# Connection to Previous Problems

### Minimum Size Subarray Sum

```text
sum >= target

VALID → shrink
because we want MINIMUM length
```

### Longest Subarray With Sum ≤ K

```text
sum > k

INVALID → shrink
because we need to restore validity
```

### Longest Substring Without Repeating Characters

```text
duplicate exists

INVALID → shrink
until duplicate disappears
```

The common idea is:

```text
RIGHT expands the window
LEFT shrinks the window
A condition determines when LEFT moves
```

---

# One-Line Revision

**Expand with `right`; if the incoming character already exists, keep removing from `left` until the duplicate disappears, 
then add the character and update the maximum window length.**
*/


import java.util.HashSet;
import java.util.Set;

public class LengthOfLongestSubstring {
    public static int lengthOfLongestSubstring(String s) {
        int left=0;
        int maxLength = 0;
        Set<Character> seen = new HashSet<>();
        for(int right=0; right<s.length(); right++){
            while(seen.contains(s.charAt(right))){
                seen.remove(s.charAt(left));
                left++;
            }
            seen.add(s.charAt(right));

            int windowLength = right - left + 1;
            if(windowLength > maxLength){
                maxLength = windowLength;
            }
        }
        return maxLength;
    }   
}