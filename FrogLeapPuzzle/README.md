# Frog Leap Puzzle

Frog Leap Puzzle task is an example for implementing uninformed search. Examples for uninformed search algorithms: Depth-First Search (DFS), Breadth-First Search (BFS), Uniform Cost Search (UCS), Depth-Limited Search (DLS), Iterative Deepening Search (IDS). For this task, DFS is utilized to solve the puzzle. 


## Description
The game consist of 2N + 1 fields. Initially, the rightmost N fields are occupied by frogs facing left, while the leftmost N fields are occupied by frogs facing right. The objective is to achieve the opposite configuration at the end of the game. Each frog can move only in the direction it is facing and has two possible moves:  it can either jump into an adjacent vacant space or leap over one frog to land on the next vacant space. The input to the game is the number of frogs facing the same direction, and the output is  a sequence of all configurations leading from the initial state to the goal state.


### Example: 
Input: 2

Output:<br>
    >>\_<< <br>
    >\_><< <br>
    ><>\_< <br>
    ><><\_ <br>
    ><\_<> <br>
    \_<><> <br>
    <\_><> <br>
    <<>\_> <br>
    <<_>>
