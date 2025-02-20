package course.ai.sliding.puzzle;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SlidingPuzzle puzzle = new SlidingPuzzle();

        puzzle.readSize(scanner);
        puzzle.readBlankIndex(scanner);
        puzzle.readBoard(scanner);
        puzzle.goalState();

        if (puzzle.isSolvable()) {
            long startTime = System.currentTimeMillis();
            BoardNode solution = puzzle.idaStar();

            long endTime = System.currentTimeMillis();
            long executionTime = (endTime - startTime);

            double precise = executionTime / 1000.0;
            DecimalFormat df = new DecimalFormat("#0.000");
            String formatInSeconds = df.format(precise);

            puzzle.printSolution(solution);
            System.out.println("Execution Time in seconds: " + formatInSeconds);
        } else {
            System.out.println("-1");
        }

        scanner.close();
    }
}
