/*
Stack
Stack = LIFO (Last In, First Out)
Push: 10, 20, 30

TOP
 ↓
30
20
10

pop() → 30

Java
Prefer Deque + ArrayDeque:
Deque<Integer> stack = new ArrayDeque<>();

stack.push(10);    // add to top
stack.pop();       // remove + return top
stack.peek();      // return top without removing
stack.isEmpty();
stack.size();

push(), pop(), peek() → O(1).
Recognition
Think Stack when the current item needs to interact with the most recently seen/unresolved item.

    string = "{[()]}"
    Revision Notes — Valid Parentheses
Pattern: Stack / matching most-recent unresolved item.
Opening bracket
→ push

Closing bracket
→ stack empty? INVALID
→ top doesn't match? INVALID
→ matches? pop

End
→ stack empty? VALID
→ otherwise INVALID

Why Stack? Closing brackets must match the most recently encountered unmatched opening bracket → LIFO.

Complexity: O(n) time, O(n) extra space
*/
package stack.matching;

import java.util.ArrayDeque;
import java.util.Deque;

public class ValidParantesis {
    public static boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == '{' || s.charAt(i) == '[' || s.charAt(i) == '('){
                stack.push(s.charAt(i));
            }else if(s.charAt(i) == '}' || s.charAt(i) == ']' || s.charAt(i) == ')'){
                if(stack.isEmpty()){
                    return false;
                }else if(stack.peek() == '[' && s.charAt(i) == ']' || 
                         stack.peek() == '{' && s.charAt(i) == '}' ||
                         stack.peek() == '(' && s.charAt(i) == ')')
                {
                    stack.pop();
                }else{
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
