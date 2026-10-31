/*
    Queue

    Queue = FIFO (First In, First Out)
    FRONT              REAR
     ↓                   ↓
    10 → 20 → 30 → 40
    
10 entered first → 10 leaves first.
Java
Queue<Integer> queue = new ArrayDeque<>();

queue.offer(10);   // add at rear
queue.poll();      // remove + return front
queue.peek();      // see front without removing
queue.isEmpty();
queue.size();

Stack vs Queue
STACK                     QUEUE

LIFO                      FIFO
push → top                offer → rear
pop  → top                poll  → front
peek → top                peek  → front

Recognition
Think Queue when elements need to be processed in arrival/order sequence.

Queues become especially important later for BFS in Trees and Graphs.

    students   = [1, 1, 0]
    sandwiches = [0, 1, 1]
    Students Unable to Eat Lunch
We used this problem to practice Queue simulation.

Students:
FRONT
 ↓
[1, 0, 1, 0]

Sandwiches are processed using an index:
[0, 1, 0, 1]
 ↑
currentSandwich

Match
queue.poll();
currentSandwich++;
rejectedCount = 0;

Student eats → leaves queue → next sandwich.
No match
Move student:
FRONT → REAR

Using:
int student = queue.poll();
queue.offer(student);

rejectedCount++;

When do we stop?
If:
rejectedCount == queue.size()

every remaining student has rejected the current sandwich once.

Nobody can eat it → stop.

Answer:
return queue.size();

Complexity of simulation
Time:  O(n²) worst case
Space: O(n)

Why O(n²)?

Students can be rotated and processed multiple times:
~n + ~(n-1) + ~(n-2) + ...
→ O(n²)

Important interview lesson:
One loop does NOT automatically mean O(n).

Ask:
How many times can each element be processed?
*/

package queue.simulation;

import java.util.ArrayDeque;
import java.util.Queue;

public class StudentsUnableToEatLunch {

    public static int countStudents(int[] students, int[] sandwiches) {
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i: students){
            queue.add(i);
        }
        
        int currentSandwitch = 0;
        int rejectedSandwitch = 0;

        while(!queue.isEmpty()){
            if(sandwiches[currentSandwitch] == queue.peek()){
                queue.poll();
                currentSandwitch++;
                rejectedSandwitch = 0;
            }else if(sandwiches[currentSandwitch] != queue.peek()){
                int student = queue.poll();
                queue.add(student);
                rejectedSandwitch++;
            }

            if(rejectedSandwitch == queue.size()){
                break;
            }
        }

        return queue.size();
    }
}
