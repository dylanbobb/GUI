package tictactoegame;

import java.util.Scanner;

public class TicTacToe
{
    public char[][] board = {{'-', '-', '-'}, {'-', '-', '-'}, {'-', '-', '-'}};
    public Scanner kb = new Scanner(System.in);
    public String namePlayer1 = "";
    public String namePlayer2 = "";
    public boolean isNextMovePlayer1 = true;
    public boolean isTie = false;
    
    public TicTacToe()
    {
        System.out.print("Enter name for player 1: ");
        this.namePlayer1 = kb.next();
        System.out.print("Enter name for player 2: ");
        this.namePlayer2 = kb.next();    
    }
    
    public void displayBoard()
    {
        System.out.println();
        
        for (int i=0; i<3; ++i)
        {
            for (int j=0; j<3; ++j)
            {
                System.out.print(this.board[i][j] + " ");
            }
            System.out.println();
        }
        
        System.out.println("\n");
    }
    
    public boolean isGameFinished()
    {
        // Test diagonals
        if (this.board[1][1] != '-')
        {
            if (this.board[0][0] == this.board[1][1] && this.board[1][1] == this.board[2][2])
                return true;

            if (this.board[0][2] == this.board[1][1] && this.board[1][1] == this.board[2][0])
                return true;
        }
            
        // Test rows
        for (int i=0; i<3; ++i)
        {
            if (this.board[i][0] != '-' && this.board[i][0] == this.board[i][1] && this.board[i][1] == this.board[i][2])
                return true;
        }
        
        // Test columns
        for (int j=0; j<3; ++j)
        {
            if (this.board[0][j] != '-' && this.board[0][j] == this.board[1][j] && this.board[1][j] == this.board[2][j])
                return true;
        }
        
        // Test if board is completly full
        for (int i=0; i<3; i++)
        {
            for (int j=0; j<3; j++)
            {
                if(this.board[i][j] == '-')
                {
                    return false;
                }
            }
        }
        
        isTie = true;
        return true;
    }
    
    public void playTurn()
    {
        // Initialize data for this turn
        // - Get player name
        // - Get player symbol
        // - Set next turn to other player
        String playerName;
        char playerSymbol;
        
        if (this.isNextMovePlayer1)
        {
            playerName = this.namePlayer1;
            playerSymbol = 'X';
        }
        else
        {
            playerName = this.namePlayer2;
            playerSymbol = 'O';
        }
        
        this.isNextMovePlayer1 = !(this.isNextMovePlayer1);
         
        
        // Interactive prompt to ask player for coordinate to play
        System.out.println(playerName + ", enter the coordinates of your next move (eg: 1 2 is middle-right): ");

        int i = kb.nextInt();
        int j = kb.nextInt();

        // Validate that the requested coordinate is valid
        while(i < 0 || i > 2 || j < 0 || j > 2 || this.board[i][j] != '-')
        {
            System.out.println("Invalid coordinate, enter the coordinates of your next move (eg: 1 2 is middle-right): ");

            i = kb.nextInt();
            j = kb.nextInt();                
        }
        
        setBoard(playerSymbol, i, j);
    }
    
    public void setBoard(char playerSymbol, int row, int column)
    {
        // Assumptions on the coordinate
        assert(row >= 0 && row < 3 && column >= 0 && column < 3);
        
        // Assumptions on the player symbol
        assert(playerSymbol == 'X' || playerSymbol == 'O');
        
        this.board[row][column] = playerSymbol;        
    }
}