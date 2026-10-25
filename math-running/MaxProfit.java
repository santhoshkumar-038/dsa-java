/*
    ## 12. Best Time to Buy and Sell Stock

### Problem

Buy once and sell once later. Find maximum profit.

```text
Input:
[7, 1, 5, 3, 6, 4]

Output:
5
```

Buy at `1`, sell at `6`.

### Brute Force

Try every buy day with every later sell day.

```text
Time: O(n²)
```

### Optimized Thinking

While scanning maintain:

```text
minPrice  → cheapest price seen so far
maxProfit → best profit found so far
```

For each price:

```text
profit = currentPrice - minPrice
```

### Complexity

```text
Time  : O(n)
Space : O(1)
```

### Important Insight

Don't simply find the global minimum first.

Example:

```text
[2, 8, 1, 3]
```

Global minimum is `1`, giving profit `2`.

But the best transaction is:

```text
buy 2
sell 8
profit 6
```

The order of days matters.

---
*/
public class MaxProfit {
    public static int maxProfit(int[] prices) {

        int minPrice = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }
            int currentProfit = prices[i] - minPrice;
            if (currentProfit > maxProfit) {
                maxProfit = currentProfit;
            }
        }
        return maxProfit;
    }
}
