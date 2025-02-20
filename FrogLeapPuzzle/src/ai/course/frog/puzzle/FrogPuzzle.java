package ai.course.frog.puzzle;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Deque;
import java.util.ArrayDeque;

public class FrogPuzzle {

    public static String findZeroState(int n) {
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < n; i++) {
            s.append('>');
        }
        s.append('_');
        for (int i = 0; i < n; i++) {
            s.append('<');
        }

        return s.toString();
    }

    public static String findGoalState(int n) {
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < n; i++) {
            s.append('<');
        }
        s.append('_');
        for (int i = 0; i < n; i++) {
            s.append('>');
        }

        return s.toString();
    }

    public static String jump(String state, int i, int j) {
        char[] stateArray = state.toCharArray();
        char temp = stateArray[i];
        stateArray[i] = stateArray[j];
        stateArray[j] = temp;

        return new String(stateArray);
    }

    public static List<String> getMoves(String currentState) {
        int blankIndex = currentState.indexOf('_');
        List<String> nextStates = new ArrayList<>();

        if (blankIndex > 0 && currentState.charAt(blankIndex - 1) == '>') {
            nextStates.add(jump(currentState, blankIndex, blankIndex - 1));
        }
        if (blankIndex < currentState.length() - 1 && currentState.charAt(blankIndex + 1) == '<') {
            nextStates.add(jump(currentState, blankIndex, blankIndex + 1));
        }
        if (blankIndex > 1 && currentState.charAt(blankIndex - 2) == '>') {
            nextStates.add(jump(currentState, blankIndex, blankIndex - 2));
        }
        if (blankIndex < currentState.length() - 2 && currentState.charAt(blankIndex + 2) == '<') {
            nextStates.add(jump(currentState, blankIndex, blankIndex + 2));
        }

        return nextStates;
    }

    public static TreeNode dfs(String zeroState, String goalState) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        TreeNode root = new TreeNode(zeroState, null);
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode currentNode = stack.pop();
            String currentState = currentNode.state;

            if (currentState.equals(goalState)) {
                return currentNode;
            }

            if (!visited.contains(currentState)) {
                visited.add(currentState);

                List<String> nextStates = getMoves(currentState);
                for (String nextState : nextStates) {
                    if (!visited.contains(nextState)) {
                        stack.push(new TreeNode(nextState, currentNode));
                    }
                }
            }
        }

        return null;
    }

    public static void printSolution(TreeNode treeNode) {
        List<String> path = new ArrayList<>();
        while (treeNode != null) {
            path.add(0, treeNode.state);
            treeNode = treeNode.parent;
        }

        for (String state : path) {
            System.out.println(state);
        }
    }

    public static void main(String[] args) {
        System.out.println("Type number of frogs watching in the same direction: ");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();

        String zeroState = findZeroState(n);
        String goalState = findGoalState(n);

        long startTime = System.currentTimeMillis();

        TreeNode solution = dfs(zeroState, goalState);

        long endTime = System.currentTimeMillis();
        long executionTime = (endTime - startTime);

        double precise = executionTime / 1000.0;
        DecimalFormat df = new DecimalFormat("#0.000");
        String formatInSeconds = df.format(precise);

        if (solution != null) {
            printSolution(solution);
        } else {
            System.out.println("No solution.");
        }

        System.out.println("Execution Time in seconds: " + formatInSeconds);
    }
}