package tictactoegame;

import java.util.Random;

public class TicTacToeGameAI extends TicTacToe
{
    Random random = new Random();
    
    @Override
    public void playTurn()
    {
        String playerName;
        char playerSymbol;
        int i;
        int j;
        
        if (this.isNextMovePlayer1)
        {
            playerName = this.namePlayer1;
            playerSymbol = 'X';
            
            // Interactive prompt to ask player for coordinate to play
            System.out.println(namePlayer1 + ", enter the coordinates of your next move (eg: 1 2 is middle-right): ");

            i = kb.nextInt();
            j = kb.nextInt();
            
            // Validate that the requested coordinate is valid
            while(i < 0 || i > 2 || j < 0 || j > 2 || this.board[i][j] != '-')
            {
                System.out.println("Invalid coordinate, enter the coordinates of your next move (eg: 1 2 is middle-right): ");

                i = kb.nextInt();
                j = kb.nextInt();                
            }
        }
        
        else
        {
            playerName = this.namePlayer2;
            playerSymbol = 'O';
            
            i = random.nextInt(3);
            j = random.nextInt(3);
            
            while(i < 0 || i > 2 || j < 0 || j > 2 || this.board[i][j] != '-')
            {
                System.out.println("Invalid coordinate, enter the coordinates of your next move (eg: 1 2 is middle-right): ");

                i = random.nextInt(3);
                j = random.nextInt(3);                
            }
        }
        
        isNextMovePlayer1 = !isNextMovePlayer1;
        setBoard(playerSymbol, i, j);
    }
    
}