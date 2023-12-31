import java.util.Scanner;
import java.util.Random;

public class MineSweeper {
    // Variables defined
    Scanner scan = new Scanner(System.in);
    Random rand = new Random();
    int row, col;
    char[][] playerBoard;
    char[][] mineBoard;

    MineSweeper(int row, int col) {
        // Constructor method created
        this.row = Math.max(2, row);
        this.col = Math.max(col, 2);

        playerBoard = new char[this.row][this.col];
        mineBoard = new char[this.row][this.col];
    }

    public void run() {
        // For Running to game this method was created.
        System.out.println("Welcome to MineSweeper Game !!!!");
        getBoardSizeFromUser();
        createBoards();
        printMineBoard();
        System.out.println("---------");

        initializePlayerBoard();
        printPlayerBoard();

        do { // Playing Until Game Over
            playTurn();
            printPlayerBoard();
        } while (!gameOver());

        if (allSafeCellsOpened()) { // Condition if all safe cell opened
            System.out.println("You're a Winner !!");
        }

        System.out.println("Game Over !!!");
    }

    public void getBoardSizeFromUser() {
        do {
            System.out.println("Enter the number of rows:");
            this.row = scan.nextInt();

            System.out.println("Enter the number of columns:");
            this.col = scan.nextInt();

            if (this.row < 2 || this.col < 2) {
                System.out.println("Invalid dimensions. Please enter numbers greater than or equal to 2.");
            }

        } while (this.row < 2 || this.col < 2);

        playerBoard = new char[this.row][this.col];
        mineBoard = new char[this.row][this.col];
    }

    public void createBoards() { // Method to initialize both mine and player board
        initializeMineBoard();
        initializePlayerBoard();
    }

    public void initializeMineBoard() {
        // Method that filling mine board and place mines
        for (int r = 0; r < this.row; r++) {
            for (int c = 0; c < this.col; c++) {
                mineBoard[r][c] = '0';
            }
        }
        placeMines();
    }

    public void printMineBoard() { // Printing Mine board
        System.out.println("Mine Board:");
        for (int r = 0; r < this.row; r++) {
            for (int c = 0; c < this.col; c++) {
                System.out.print(mineBoard[r][c] + " ");
            }
            System.out.println();
        }
    }

    public void placeMines() { // mines distributed randomly with random method size / 4
        int numberOfMines = (this.row * this.col) / 4;
        for (int i = 0; i < numberOfMines; i++) {
            int randomRow = rand.nextInt(this.row);
            int randomCol = rand.nextInt(this.col);
            while (mineBoard[randomRow][randomCol] == 'M') {
                randomRow = rand.nextInt(this.row);
                randomCol = rand.nextInt(this.col);
            }
            mineBoard[randomRow][randomCol] = 'M';
        }
    }

    public void initializePlayerBoard() {
        // filling player board with -
        for (int r = 0; r < this.row; r++) {
            for (int c = 0; c < this.col; c++) {
                playerBoard[r][c] = '-';
            }
        }
    }

    public void printPlayerBoard() {
        // Printing current board
        System.out.println("Current Board:");
        for (int r = 0; r < this.row; r++) {
            for (int c = 0; c < this.col; c++) {
                System.out.print(playerBoard[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println("---------");
    }

    public void playTurn() {
        // Players turn was determined with these condition that caused error prevented.
        System.out.println("Please Select a Row (0-" + (row - 1) + "):");
        int selectedRow = scan.nextInt();
        System.out.println("Please Select a Column (0-" + (col - 1) + "):");
        int selectedCol = scan.nextInt();

        if (selectedRow < 0 || selectedRow >= row || selectedCol < 0 || selectedCol >= col) {
            System.out.println("Invalid selection. Please choose valid row and column.");
        } else {
            if (mineBoard[selectedRow][selectedCol] == 'M') {
                System.out.println("Game Over !!!");
                revealMines();
                System.exit(0);
            } else {
                int minesAround = countMinesAround(selectedRow, selectedCol);
                playerBoard[selectedRow][selectedCol] = (char) (minesAround + '0');
            }
        }
    }

    public int countMinesAround(int r, int c) { // controls all around a cell to count methods
        int count = 0;

        if (r - 1 >= 0 && mineBoard[r - 1][c] == 'M') {
            count++;
        }
        if (r + 1 < row && mineBoard[r + 1][c] == 'M') {
            count++;
        }
        if (c - 1 >= 0 && mineBoard[r][c - 1] == 'M') {
            count++;
        }
        if (c + 1 < col && mineBoard[r][c + 1] == 'M') {
            count++;
        }
        if (r - 1 >= 0 && c - 1 >= 0 && mineBoard[r - 1][c - 1] == 'M') {
            count++;
        }
        if (r - 1 >= 0 && c + 1 < col && mineBoard[r - 1][c + 1] == 'M') {
            count++;
        }
        if (r + 1 < row && c - 1 >= 0 && mineBoard[r + 1][c - 1] == 'M') {
            count++;
        }
        if (r + 1 < row && c + 1 < col && mineBoard[r + 1][c + 1] == 'M') {
            count++;
        }

        return count;
    }

    public void revealMines() {
        // revealing all mines
        for (int r = 0; r < this.row; r++) {
            for (int c = 0; c < this.col; c++) {
                if (mineBoard[r][c] == 'M') {
                    playerBoard[r][c] = 'M';
                }
            }
        }
    }

    public boolean gameOver() {
        // method that game is over or not
        for (int r = 0; r < this.row; r++) {
            for (int c = 0; c < this.col; c++) {
                if (playerBoard[r][c] == '-' && mineBoard[r][c] != 'M') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean allSafeCellsOpened() { // conditions for winning
        for (int r = 0; r < this.row; r++) {
            for (int c = 0; c < this.col; c++) {
                if (playerBoard[r][c] == '-' && mineBoard[r][c] == '0') {
                    return false;
                }
            }
        }
        return true;
    }
}
