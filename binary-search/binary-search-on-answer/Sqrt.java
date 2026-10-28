/*
    Revision Notes — Integer Square Root
Problem: Find the largest integer x such that:
x * x <= n

Pattern: Binary Search on Answer

Unlike normal binary search, left, mid, and right are possible answer values, not array indices.

The decision is:
mid² <= n
    → mid is a valid candidate
    → save answer = mid
    → search RIGHT for a larger valid candidate

mid² > n
    → mid is too large
    → search LEFT

And in Java, remember:
long square = (long) mid * mid;

to prevent int overflow.
Pattern recognition
When an interviewer asks for something like:
minimum/maximum value that satisfies a condition

and the possible answers have a monotonic nature such as:
valid valid valid valid | invalid invalid invalid

think:

Can I binary-search the answer space?

That's the new pattern we're building.

GitHub:
Binary Search
└── Binary Search on Answer
    └── Integer Square Root ✅

Good first foundation problem for this pattern. Next we'll do one more Search-on-Answer problem, 
where the condition is less obvious than mid² <= n. That will show whether you've actually picked 
up the pattern rather than memorized square root.
*/

public class Sqrt {
    public static int sqrt(int n) {
        int left = 1;
        int right = n;
        int answer = 0;
        while(left <= right){
            int mid = left + (right - left) / 2;
            long square = (long) mid * mid;

            if(square <= n){
                answer = mid;
                left = mid + 1;
            }else{
                right = mid - 1;
            }  
        }
        return answer;
    }
}
