/* ----------------------------------------------------------------------------
- This is a skeleton of a tic tac toe game using the console.
- This current project is not really object oriented. 
- The project has bugs to fix!
- Next week, we will make a user interface to make it easier to play!!

To get started with coding, you can look at the TODO list below...

TODO:
1- Bugs in the code - Fix them!
 - The game doesn't detect tie games
 - The game doesn't handle the "/" diagonal
 - The winner is the wrong name

2- Create a class TicTacToe that will manage the board for a single game
 - The constructor will take the name of the 2 players
 - When the game ends, give the option to the player to play another game

3- To play against the computer, create a class TicTacToeAI extending TicTacToe
 - The computer plays "dumb" and chooses a random available cell each turn

For each exercise, try to write the minimal amount of code, and reuse as much
as possible from the current project.

Author: Nicolas Bergeron
Date:   22/08/2017
---------------------------------------------------------------------------- */

package tictactoegame;

import java.util.Scanner;

public class TicTacToeGame 
{
    protected static char[][] board = {{'-', '-', '-'}, {'-', '-', '-'}, {'-', '-', '-'}};
    protected static Scanner kb = new Scanner(System.in);
    protected static String namePlayer1 = "";
    protected static String namePlayer2 = "";
    protected static boolean isNextMovePlayer1 = true;
    protected static boolean isTie = false;
    
    public static void displayBoard()
    {
        System.out.println();
        
        for (int i=0; i<3; ++i)
        {
            for (int j=0; j<3; ++j)
            {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        
        System.out.println("\n");
    }
    
    public static void initializeGame()
    {
        System.out.print("Enter name for player 1: ");
        namePlayer1 = kb.next();
        System.out.print("Enter name for player 2: ");
        namePlayer2 = kb.next();              
    }
    
    public static boolean isGameFinished()
    {
        // Test diagonals
        if (board[1][1] != '-')
        {
            if (board[0][0] == board[1][1] && board[1][1] == board[2][2])
                return true;

            if (board[0][2] == board[1][1] && board[1][1] == board[2][0])
                return true;
        }
            
        // Test rows
        for (int i=0; i<3; ++i)
        {
            if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2])
                return true;
        }
        
        // Test columns
        for (int j=0; j<3; ++j)
        {
            if (board[0][j] != '-' && board[0][j] == board[1][j] && board[1][j] == board[2][j])
                return true;
        }
        
        // Test if board is completly full
        for (int i=0; i<3; i++)
        {
            for (int j=0; j<3; j++)
            {
                if(board[i][j] == '-')
                {
                    return false;
                }
            }
        }
        
        isTie = true;
        return true;
    }
    
    public static void playTurn()
    {
        // Initialize data for this turn
        // - Get player name
        // - Get player symbol
        // - Set next turn to other player
        String playerName;
        char playerSymbol;
        
        if (isNextMovePlayer1)
        {
            playerName = namePlayer1;
            playerSymbol = 'X';
        }
        else
        {
            playerName = namePlayer2;
            playerSymbol = 'O';
        }
        
        isNextMovePlayer1 = !isNextMovePlayer1;
         
        
        // Interactive prompt to ask player for coordinate to play
        System.out.println(playerName + ", enter the coordinates of your next move (eg: 1 2 is middle-right): ");

        int i = kb.nextInt();
        int j = kb.nextInt();

        // Validate that the requested coordinate is valid
        while(i < 0 || i > 2 || j < 0 || j > 2 || board[i][j] != '-')
        {
            System.out.println("Invalid coordinate, enter the coordinates of your next move (eg: 1 2 is middle-right): ");

            i = kb.nextInt();
            j = kb.nextInt();                
        }
        
        setBoard(playerSymbol, i, j);
    }
    
    public static void setBoard(char playerSymbol, int row, int column)
    {
        // Assumptions on the coordinate
        assert(row >= 0 && row < 3 && column >= 0 && column < 3);
        
        // Assumptions on the player symbol
        assert(playerSymbol == 'X' || playerSymbol == 'O');
        
        board[row][column] = playerSymbol;        
    }
    
    
    
    public static void main(String[] args) {
        initializeGame();
        
        while(isGameFinished() == false)
        {
            displayBoard();
            playTurn();
        }
        
        displayBoard();
        if(isTie)
        {
            System.out.println("The game is a tie");
        }
        else if (isNextMovePlayer1)
        {
            System.out.println(namePlayer2 + " has won the game!");
        }
        else
        {
            System.out.println(namePlayer1 + " has won the game!");
        }
    }
}
