# MineSweeper Game:
This Java program provides a simple text-based interface for the classic MineSweeper game. The game involves the player clearing a minefield by logically deducing the locations of mines.
### Game Rules:
* The game begins with the player entering the number of rows and columns.
* Mines are randomly placed(size/4), and the game board is generated.
* The player takes turns selecting and opening a cell.
* If the selected cell contains a mine, the game ends.
* If the selected cell is mine-free, the number of mines around that cell is displayed.
* The player wins the game by opening all safe cells.
## Game Interface:
Two main boards are displayed during the game:

* Mine Board: Shows the locations of mines.
* Current Board: Displays the cells opened by the player.

## Developer Notes:
* User input determines the size of the game board.
* Mines are randomly placed, and the game board is initialized.
* The game continues or ends based on user inputs and the state of the board.
* When the game ends, the user is informed whether they won or lost. 

    
This simple MineSweeper game demonstrates basic game development skills using the Java programming language. The game can be expanded by adding different strategies and features.