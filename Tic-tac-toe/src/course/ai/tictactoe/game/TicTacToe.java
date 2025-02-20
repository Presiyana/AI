package course.ai.tictactoe.game;

import java.util.Scanner;

public class TicTacToe {

    void player(Board board) {
        Scanner scanner = new Scanner(System.in);
        int row, col;

        do {
            System.out.println("Enter your next move (number of the row and number of the column you want to mark with X " +
                    "(both numbers can be 1, 2 or 3 and you can not mark board spaces, which are already busy): ");
            row = scanner.nextInt();
            col = scanner.nextInt();
        } while (row < 1 || col < 1 || row > 3 || col > 3 || board.checkAlreadyBusy(row, col));

        board.modifyBoard(row, col);
        System.out.println("Your move: ");
        board.printBoard();
    }

    boolean computer(Board board) {
        if (earlyGameTermination(board)) {
            System.out.println("No more winning strategies...no need to proceed. Nobody wins!");
            return false;
        }

        int bestScore = Integer.MIN_VALUE;
        int row = -1, col = -1;

        for (int i = 0; i < board.BOARD_SIZE; i++) {
            for (int j = 0; j < board.BOARD_SIZE; j++) {
                if (board.board[i][j] == ' ') {
                    board.board[i][j] = 'O';
                    int score = minimax(board, 0, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    board.board[i][j] = ' ';
                    if (score > bestScore) {
                        bestScore = score;
                        row = i;
                        col = j;
                    }
                }
            }
        }

        board.board[row][col] = 'O';
        System.out.println("Computer's move: ");
        board.printBoard();

        return true;
    }

    boolean earlyGameTermination(Board board) {
        return board.countBlankSpaces() <= board.BOARD_SIZE && !checkWinningMove(board);
    }

    boolean checkWinningMove(Board board) {
        for (int i = 0; i < board.BOARD_SIZE; i++) {
            for (int j = 0; j < board.BOARD_SIZE; j++) {
                if (board.board[i][j] == ' ') {
                    board.board[i][j] = 'X';
                    if (isPlayerWinner(board)) {
                        board.board[i][j] = ' ';
                        return true;
                    }
                    board.board[i][j] = 'O';
                    if (isComputerWinner(board)) {
                        board.board[i][j] = ' ';
                        return true;
                    }
                    board.board[i][j] = ' ';
                }
            }
        }

        return false;
    }

    boolean isPlayerWinner(Board board) {
        return isWinner(board, 'X');
    }

    boolean isComputerWinner(Board board) {
        return isWinner(board, 'O');
    }

    boolean isWinner(Board board, char mark) {
        boolean isWinner = false;

        //horizontals
        for (int i = 0; i < board.BOARD_SIZE; i++) {
            if (board.board[i][0] == mark && board.board[i][1] == mark && board.board[i][2] == mark) {
                isWinner = true;
                break;
            }
        }

        //verticals
        for (int j = 0; j < board.BOARD_SIZE; j++) {
            if (board.board[0][j] == mark && board.board[1][j] == mark && board.board[2][j] == mark) {
                isWinner = true;
                break;
            }
        }

        //diagonals:
        //diagonal 1
        if (board.board[0][0] == mark && board.board[1][1] == mark && board.board[2][2] == mark) {
            isWinner = true;
        }

        //diagonal 2
        if (board.board[0][2] == mark && board.board[1][1] == mark && board.board[2][0] == mark) {
            isWinner = true;
        }

        return isWinner;
    }

    int minimax(Board board, int depth, boolean maximizingPlayer, int alpha, int beta) {
        if (isPlayerWinner(board)) return depth - 10;
        if (isComputerWinner(board)) return 10 - depth;
        if (board.isFullBoard()) return 0;

        int bestValue;
        if (maximizingPlayer) {
            bestValue = Integer.MIN_VALUE;
            for (int i = 0; i < board.BOARD_SIZE; i++) {
                for (int j = 0; j < board.BOARD_SIZE; j++) {
                    if (board.board[i][j] == ' ') {
                        board.board[i][j] = 'O';
                        int compute = minimax(board, depth + 1, false, alpha, beta);
                        board.board[i][j] = ' ';
                        bestValue = Math.max(bestValue, compute);
                        alpha = Math.max(alpha, compute);
                        if (beta <= alpha) return bestValue;
                    }
                }
            }
        } else {
            bestValue = Integer.MAX_VALUE;
            for (int i = 0; i < board.BOARD_SIZE; i++) {
                for (int j = 0; j < board.BOARD_SIZE; j++) {
                    if (board.board[i][j] == ' ') {
                        board.board[i][j] = 'X';
                        int compute = minimax(board, depth + 1, true, alpha, beta);
                        board.board[i][j] = ' ';
                        bestValue = Math.min(bestValue, compute);
                        beta = Math.min(beta, compute);
                        if (beta <= alpha) return bestValue;
                    }
                }
            }
        }

        return bestValue;
    }

    void play(int firstMove) {
        System.out.println("Initial state(blank board): ");
        Board board = new Board();
        board.printBoard();
        int nextMove = firstMove;

        while (true) {
            if (nextMove % 2 != 0) {
                player(board);
            } else {
                if (!computer(board)) {
                    break;
                }
            }
            nextMove++;
            if (isComputerWinner(board)) {
                System.out.println("You lose!!!Think better next time!");
                break;
            } else if (isPlayerWinner(board)) {
                System.out.println("Congratulations! You are better! You win!");
                break;
            } else if (board.isFullBoard()) {
                System.out.println("Nobody wins!");
                break;
            }
        }
    }

    public static void main(String[] args) {
        int firstMove;

        do {
            System.out.println("Choose weather the computer or the player owns the first move" +
                    " (enter 0 for the computer and 1 for the player): ");
            Scanner scanner = new Scanner(System.in);
            firstMove = scanner.nextInt();
        } while (!(firstMove == 0 || firstMove == 1));

        TicTacToe game = new TicTacToe();
        game.play(firstMove);
    }
}