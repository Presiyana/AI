package course.ai.sliding.puzzle;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class SlidingPuzzle {
    int size;
    int blankIndex;
    int[][] initialState;
    int[][] goalState;

    public void readSize(Scanner scanner) {
        System.out.println("Enter number of tiles with numbers: ");
        int inputNumber = scanner.nextInt();

        size = ((int) Math.sqrt(inputNumber + 1));
    }

    public void readBlankIndex(Scanner scanner) {
        System.out.println("Enter index of the position of zero (the empty tile): ");
        int readIndex = scanner.nextInt();

        blankIndex = readIndex == -1 ? (size) * (size) - 1 : readIndex;
    }

    public void readBoard(Scanner scanner) {
        System.out.println("Enter the matrix(the board): ");
        initialState = new int[size][size];

        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                initialState[i][j] = scanner.nextInt();
            }
        }
    }

    public void goalState() {
        goalState = new int[size][size];
        int index;
        int counter = 0;
        boolean isBlankBefore = false;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                index = (counter * size) + j;
                if (index == blankIndex) {
                    goalState[i][j] = 0;
                    isBlankBefore = true;
                } else {
                    goalState[i][j] = isBlankBefore ? index : index + 1;
                }
            }
            counter++;
        }
    }

    public BoardNode idaStar() {
        BoardNode start = new BoardNode(initialState, size, goalState, 0, null, null);
        int bound = start.h;
        Deque<BoardNode> path = new ArrayDeque<>();
        path.push(start);

        while (true) {
            int result = search(path, bound);
            if (result == -1) return path.peek();
            if (result == Integer.MAX_VALUE) return null;
            bound = result;
        }
    }

    public int search(Deque<BoardNode> path, int bound) {
        BoardNode node = path.peek();

        assert node != null;
        if (node.f > bound) return node.f;
        if (node.isGoal()) return -1;

        int min = Integer.MAX_VALUE;
        for (BoardNode neighbour : node.neighbours()) {
            if (!path.contains(neighbour)) {
                path.push(neighbour);
                int result = search(path, bound);
                if (result == -1) return -1;
                if (result < min) min = result;
                path.pop();
            }
        }

        return min;
    }

    public int[] toArray() {
        int[] array = new int[(size * size) - 1];
        int index = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (initialState[i][j] != 0) {
                    array[index] = initialState[i][j];
                    index++;
                }
            }
        }

        return array;
    }

    public int countInversions() {
        int count = 0;
        int[] arr = toArray();
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j]) {
                    count++;
                }
            }
        }

        return count;
    }

    public int findZeroRow() {
        int number = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (initialState[i][j] == 0) {
                    number = i;
                    break;
                }
            }
        }

        return number;
    }

    public boolean isSolvable() {
        return (blankIndex != ((size * size) - 1)) || (blankIndex == ((size * size) - 1)
                && ((size % 2 != 0 && countInversions() % 2 == 0) ||
                (size % 2 == 0 && (countInversions() + findZeroRow()) % 2 != 0)));
    }

    public void printSolution(BoardNode solutionNode) {
        if (solutionNode == null) {
            System.out.println("No solution found.");
            return;
        }
        System.out.println(solutionNode.g);

        Deque<BoardNode> path = new ArrayDeque<>();
        while (solutionNode != null) {
            path.push(solutionNode);
            solutionNode = solutionNode.parent;
        }

        while (!path.isEmpty()) {
            BoardNode step = path.pop();
            if (step.direction != null) {
                System.out.println(step.direction);
            }
        }
    }
}