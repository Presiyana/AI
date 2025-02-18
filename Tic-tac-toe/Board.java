public class Board {

    int BOARD_SIZE = 3;
    char[][] board = new char[BOARD_SIZE][BOARD_SIZE];

    Board() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = ' ';
            }
        }
    }

    boolean checkAlreadyBusy(int row, int col){
        return board[row - 1][col - 1] != ' ';
    }

    void modifyBoard(int row, int col) {
        row = row - 1;
        col = col - 1;

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (row == i && col == j) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    boolean isFullBoard() {
        boolean isFull = true;

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == ' ') {
                    isFull = false;
                    break;
                }
            }
        }

        return isFull;
    }

    int countBlankSpaces(){
        int counter = 0;

        for(int i = 0; i < BOARD_SIZE; i++){
            for(int j = 0; j < BOARD_SIZE; j++){
                if(board[i][j] == ' '){
                    counter++;
                }
            }
        }

        return counter;
    }

    void printBoard() {
        System.out.print("_______");
        System.out.println();

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print("|" + board[i][j]);
            }

            System.out.print("|");
            System.out.println();
            System.out.print("–––––––");
            System.out.println();
        }
    }
}
