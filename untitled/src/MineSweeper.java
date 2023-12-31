// import Scanner and Random
import java.util.Scanner;
import java.util.Random;

public class MineSweeper {
    // Variables defined
    Scanner scan = new Scanner(System.in);
    Random rand = new Random();
    int row, col;
    char[][] playerBoard;
    char[][] mineBoard;
    // Constructor defined
    MineSweeper(int row, int col) {
        this.row = Math.max(2, row);
        this.col = Math.max(col, 2);
        playerBoard = new char[this.row][this.col];
        mineBoard = new char[this.row][this.col];
    }

    public void run() {
        System.out.println("Welcome to MineSweeper Game !!!!");
        getBoardSizeFromUser();
        createBoards();
        printMineBoard(); // İlk olarak mayın haritasını basar
        System.out.println("---------");

        initializePlayerBoard();
        printPlayerBoard(); // Daha sonra oyuncu haritasını basar

        do { // Play until Game Over with do/while
            playTurn();
            printPlayerBoard();
        } while (!gameOver());

        System.out.println("Game Over !!!");
    }

    public void getBoardSizeFromUser() {
        System.out.println("Enter the number of rows:");
        this.row = scan.nextInt();

        System.out.println("Enter the number of columns:");
        this.col = scan.nextInt();

        this.row = Math.max(2, this.row);
        this.col = Math.max(2, this.col);

        playerBoard = new char[this.row][this.col];
        mineBoard = new char[this.row][this.col];
    }


    public void createBoards() {
        initializeMineBoard();
        initializePlayerBoard();
    }

    public void initializeMineBoard() {
        for (int r = 0; r < this.row; r++) {
            for (int c = 0; c < this.col; c++) {
                mineBoard[r][c] = '0';
            }
        }
        placeMines();
    }

    public void printMineBoard() {
        System.out.println("Mine Board:");
        for (int r = 0; r < this.row; r++) {
            for (int c = 0; c < this.col; c++) {
                System.out.print(mineBoard[r][c] + " ");
            }
            System.out.println();
        }
    }

    public void placeMines() {
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
        for (int r = 0; r < this.row; r++) {
            for (int c = 0; c < this.col; c++) {
                playerBoard[r][c] = '-';
            }
        }
    }

    public void printPlayerBoard() {
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

    public int countMinesAround(int r, int c) {
        int count = 0;

        if (r - 1 >= 0 && mineBoard[r - 1][c] == 'M') {
            count++; // Üst
        }
        if (r + 1 < row && mineBoard[r + 1][c] == 'M') {
            count++; // Alt
        }
        if (c - 1 >= 0 && mineBoard[r][c - 1] == 'M') {
            count++; // Sol
        }
        if (c + 1 < col && mineBoard[r][c + 1] == 'M') {
            count++; // Sağ
        }
        if (r - 1 >= 0 && c - 1 >= 0 && mineBoard[r - 1][c - 1] == 'M') {
            count++; // Sol Üst
        }
        if (r - 1 >= 0 && c + 1 < col && mineBoard[r - 1][c + 1] == 'M') {
            count++; // Sağ Üst
        }
        if (r + 1 < row && c - 1 >= 0 && mineBoard[r + 1][c - 1] == 'M') {
            count++; // Sol Alt
        }
        if (r + 1 < row && c + 1 < col && mineBoard[r + 1][c + 1] == 'M') {
            count++; // Sağ Alt
        }

        return count;
    }

    public void revealMines() {
        for (int r = 0; r < this.row; r++) {
            for (int c = 0; c < this.col; c++) {
                if (mineBoard[r][c] == 'M') {
                    playerBoard[r][c] = 'M';
                }
            }
        }
    }

    public boolean gameOver() {
        for (int r = 0; r < this.row; r++) {
            for (int c = 0; c < this.col; c++) {
                if (playerBoard[r][c] == '-' && mineBoard[r][c] != 'M') {
                    return false;
                }
            }
        }
        return true;
    }


}

