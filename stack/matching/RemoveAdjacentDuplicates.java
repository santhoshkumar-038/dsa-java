/*
    s = "abbaca"

    Remove Adjacent Duplicates
    Recognition clue: We process characters left → right, and the current character needs to interact with the most recently surviving character.
    current == stack.peek() → pop
    current != stack.peek() → push

    Stack represents processed characters that have survived so far.

    Complexity remains O(n) time and O(n) extra space. reverse() is also O(n), so overall is still O(n).
*/

package stack.matching;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveAdjacentDuplicates {
    public static String removeDuplicates(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for(int i=0; i<s.length(); i++){
            if(stack.isEmpty()){
                stack.push(s.charAt(i));
            }else if(stack.peek() == s.charAt(i)){
                stack.pop();
            }else{
                stack.push(s.charAt(i));
            }
        }

        StringBuilder result = new StringBuilder();
        while(!stack.isEmpty()){
            Character removed = stack.pop();
            result.append(removed);
        }

        return result.reverse().toString();
    }
}
