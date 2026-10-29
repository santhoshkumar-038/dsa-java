/*
    piles = [3, 6, 7, 11]
    h = 8
    Revision Notes — Koko Eating Bananas
Goal: Find the minimum eating speed that finishes all piles within h hours.

Example:
piles = [3,6,7,11]
h = 8

answer = 4 bananas/hour

1. Search space
We're not Binary Searching array indices.

We're Binary Searching possible answers:
left  = 1
right = maxPile

For the example:
1  2  3  4  5  6 ... 11
❌ ❌ ❌ ✅ ✅ ✅ ... ✅
         ↑
    first valid

2. Check whether a speed works
For every pile:
int hours = pile / speed;

if (pile % speed != 0) {
    hours++;
}

Accumulate hours across all piles.
3. Binary Search decision
totalHours <= h
    → speed is VALID
    → save answer = mid
    → search LEFT
    → maybe a smaller speed also works

totalHours > h
    → speed is TOO SLOW
    → search RIGHT
    → need a faster speed

4. Complexity
Let:
n = number of piles
m = maximum pile size

Brute force:
O(n × m)

Optimized:
O(n × log m)

Extra space:
O(1)

5. Interview pattern recognition
Think Binary Search on Answer when you see:
Find the minimum/maximum value that satisfies some condition.

and the answers have a monotonic pattern:
❌ ❌ ❌ ❌ ✅ ✅ ✅ ✅

or:
✅ ✅ ✅ ✅ ❌ ❌ ❌ ❌

Then ask:
Can I write a function/check that tells me whether mid is valid?

That's the heart of Binary Search on Answer.
GitHub
Binary Search
└── Binary Search on Answer
    ├── Integer Square Root ✅
    └── Koko Eating Bananas ✅
        ├── Brute Force
        └── Binary Search
*/

public class KokoEatingBananas {
    public static int kokoEatingBananasBruteForce(int[] piles, int k){
        int answer = -1;
        int maxPile = 0;
        for(int i=0; i<piles.length; i++){
            if(piles[i] > maxPile){
                maxPile = piles[i];
            }
        }
        for(int speed = 1; speed <= maxPile; speed++){
            int totalHours = 0;
            for(int pile: piles){
                totalHours = pile / speed;

                if (pile % speed != 0) {
                    totalHours++;
                }
            }

            if(totalHours <= k){
                return speed;
            }
        }
        return answer;
    }

    public static int kokoEatingBananasBinarySearch(int[] piles, int h){
        int maxPile = -1;
        int answer = -1;
        for(int pile: piles){
            if(pile > maxPile){
                maxPile = pile;
            }
        }

        int left = 1;
        int right = maxPile;

        while(left <= right){
            int mid = left + (right - left) / 2;
            int totalHours = 0;
            for(int pile: piles){
                totalHours += pile / mid;
                if(pile % mid != 0){
                    totalHours++;
                }
            }

            if(totalHours <= h){
                answer = mid;
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return answer;
    }
}
