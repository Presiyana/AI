# Sliding puzzle game
The game begins with a square board containing numbered tiles from 1 to N, along with an empty tile represented by 0. The aim is to rearange the tiles so that they are in the correct numerical order by the end of the game. Tiles adjacent to the empty tile—above, below, to the left, or to the right—can be moved into its position. 

The problem is solved with IDA* algorithm and "manhattan distance" heuristic.

## Input:
The input requires:
- N - The number of tiles with numerical values (without 0, i.e the empty tile) (8, 15, 24, etc.);
- I - The index of the empty tile (represented by 0) in the final solution, indicating its position at the end of the game. If I = -1, the deaulf index is used, which places the empty tile in the rightmost position of the last row;
- Board order.

## Output:
- length of the "optimal" path from the start to the target state;
- corresponding steps that are performeed to get to the final state (left, right, up, down).

## Example input and output
### Input:
8 <br>
-1 <br>
1 2 3 <br>
4 5 6 <br>
0 7 8 <br>

### Output:
2 <br>
left <br>
left <br>

