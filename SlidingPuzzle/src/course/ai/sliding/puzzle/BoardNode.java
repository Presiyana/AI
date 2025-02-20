package course.ai.sliding.puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardNode {

    public enum Directions {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    int[][] board;
    int size;
    int[][] goalState;
    int g;
    int h;
    int f;
    Directions direction;
    BoardNode parent;
    int zeroRow, zeroCol;

    BoardNode(int[][] board, int size, int[][] goalState, int g, Directions dir, BoardNode parent) {
        this.board = board;
        this.size = size;
        this.goalState = goalState;
        this.g = g;
        direction = dir;
        this.parent = parent;
        h = manhattan();
        f = this.g + h;
        findZeros();
    }

    public void findZeros() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 0) {
                    zeroRow = i;
                    zeroCol = j;
                }
            }
        }
    }

    public boolean isGoal() {
        return Arrays.deepEquals(board, goalState);
    }

    public int getRowPosition(int n) {
        int goalStateRow = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (this.goalState[i][j] == n) {
                    goalStateRow = i;
                    break;
                }
            }
        }
        return goalStateRow;
    }

    public int getColPosition(int n) {
        int goalStateCol = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (goalState[i][j] == n) {
                    goalStateCol = j;
                    break;
                }
            }
        }

        return goalStateCol;
    }

    public int manhattan() {
        int manhattanSum = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int value = board[i][j];
                if (value != 0) {
                    int goalStateRow = getRowPosition(value);
                    int goalStateCol = getColPosition(value);
                    manhattanSum += Math.abs(goalStateRow - i) + Math.abs(goalStateCol - j);
                }
            }
        }

        return manhattanSum;
    }

    public int[][] copyBoard() {
        int[][] newBoard = new int[size][size];

        for (int i = 0; i < size; i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, size);
        }

        return newBoard;
    }

    public List<BoardNode> neighbours() {
        List<BoardNode> neighbours = new ArrayList<>();
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int counter = 0;

        for (int[] dir : directions) {
            int newRow = zeroRow + dir[0];
            int newCol = zeroCol + dir[1];
            if (newRow >= 0 && newRow < size && newCol >= 0 && newCol < size) {
                int[][] newBoard = copyBoard();
                newBoard[zeroRow][zeroCol] = newBoard[newRow][newCol];
                newBoard[newRow][newCol] = 0;
                neighbours.add(new BoardNode(newBoard, size, goalState, (g + 1), Directions.values()[counter], this));
            }
            counter++;
        }

        return neighbours;
    }
}
