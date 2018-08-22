package tictactoegame;

public class TicTacToeDriver
{
    public static void main(String[] args)
    {
        TicTacToe t = new TicTacToeGameAI();
       
        while(t.isGameFinished() == false)
        {
            t.displayBoard();
            t.playTurn();
        }
        
        t.displayBoard();
        if(t.isTie)
        {
            System.out.println("The game is a tie");
        }
        else if (t.isNextMovePlayer1)
        {
            System.out.println(t.namePlayer2 + " has won the game!");
        }
        else
        {
            System.out.println(t.namePlayer1 + " has won the game!");
        }
    }
}