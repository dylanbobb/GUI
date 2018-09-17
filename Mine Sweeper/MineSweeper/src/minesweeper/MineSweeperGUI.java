// Programmed by: Dylan Bobb & Veronica Crijavetchi
// This class will serve as the game window where the mine sweeper game will take place. It contains a 8x8 grid of buttons with 10 randomly placed bombs.
package minesweeper;

// Needed imports.
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MineSweeperGUI implements KeyListener
{
    // A 2d int array will hold the actual game information, and a 2d array of buttons will be used to model the game and to allow input from the user.
    protected int[][] board = new int[8][8];
    protected JButton[][] buttons = new JButton[8][8];
    
    // By default, DEBUG_MODE is set to false.
    protected boolean DEBUG_MODE = false;
    
    // This boolean will track if the control key is pressed (as seen in the methods below) using a KeyListener.
    private boolean ctrlKeyPressed = false;
    JFrame frame;
    
    // By default, a game is not in progress.
    boolean isGameInProgress = false;
    
    // This boolen will track if a user has lost a game or not.
    boolean isLost;
    
    
    // Constructor for the MineSweeperGUI.
    public MineSweeperGUI(boolean DEBUG_MODE, Color color)
    {
        // When the constructor is called, a game is started. So the isGameInProgress boolean is updated to reflect this.
        isGameInProgress = true;
        
        // Setting the DEBUG_MODE boolean to whatever was decided in the MainWindowGUI.
        this.DEBUG_MODE = DEBUG_MODE;
        
        // Changed the statusLabel text on the MainWindowGUI to let the user know that a game is currently in progress.
        MainWindowGUI.instance.changeStatusText("Game in progress...");
        
        // Initializing the frame and the GridLayout
        frame = new JFrame("Minesweeper");
        frame.setSize(900,900);
        Container container = frame.getContentPane();
        container.setLayout(new GridLayout(8,8));
        
        
        // WindowListener for when the window is closed.
        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                // If a game is in progress, the user will be asked if they are sure if they want to close the game. 
                // If they are, the game will be lost by calling the close() method. 
                // If they change their mind, the game will resume.
                if(isGameInProgress)
                {
                    int i = JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?\nThis will cause you to lose this game.");
                    if(i==0)
                    {
                        close();
                    }
                }
                else
                {
                    frame.setVisible(false);
                }
            }
        });
        
        
        // First we set the entire board to be all 0's
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                board[i][j] = 0;
            }
        }
        
        
        // Next, we randomly assign 10 squares to hold a bomb by changing their value to -1.
        for(int i=0;i<10;i++)
        {
            int iPos = (int)(Math.random() * 8);
            int jPos = (int)(Math.random() * 8);
            
            while(board[iPos][jPos] == -1)
            {
                iPos = (int)(Math.random() * 8);
                jPos = (int)(Math.random() * 8);
            }
            
            board[iPos][jPos] = -1;
        }
        
        
        // Now, we loop through the board and appropriatly assign each square a number. This number indicates how many bombs the square is touching.
        // First, we loop through each square of the board.
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                // Next we loop through every square that the button is touching.
                for(int a=i-1;a<i+2;a++)
                {
                    for(int b=j-1;b<j+2;b++)
                    {
                        // Try catch block is used to avoid an ArrayOutOfBoundsException 
                        // (i.e. we cannot check squares  to the left of the square in the left corners, because one does not exist)
                        try
                        {
                            // If the square we are on is a bomb, we do not want to update it's value, no matter how many other bombs it is touching.
                            if(board[i][j] == -1) {}
                            
                            // This is used to skip the square in the middle of the 3x3 grid, because we only want to check the squares AROUND the square in the middle.
                            else if(i == a && j == b) {}
                            
                            // If a square around the current square is a bomb, we will increase the current squares value by 1.
                            else if(board[a][b] == -1)
                            {
                                board[i][j]++;
                            }
                        }
                        catch(Exception e){}
                    }
                }
            }
        }
        
        // Now, we loop through each square and create it's associated button.
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                final int iIndex = i;
                final int jIndex = j;
                buttons[i][j] = new JButton();
                
                // Set the background to the color decided by the user in the ColorChangerGUI window.
                buttons[i][j].setBackground(color);
                
                
                // If the debug mode is currently active, the bombs shall be revealed by setting their associated button's text to -1.
                if(DEBUG_MODE)
                {
                    if(board[i][j] == -1)
                    {
                        buttons[i][j].setText("-1");
                    }
                }
                
                
                // Creating a key and action listener for each button.
                buttons[i][j].addKeyListener(this);
                buttons[i][j].addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {   
                        // If the control key is pressed when a button is clicked, it will be flagged as a Mine. If it is already flagged, it will be unflagged.
                        if(ctrlKeyPressed)
                        {
                            if(buttons[iIndex][jIndex].getText().equals("Mine"))
                            {
                                buttons[iIndex][jIndex].setText("");
                            }
                            else
                            {
                                buttons[iIndex][jIndex].setText("Mine");
                            }
                        }
                        // Otherwise, we go through the procedure of clearing the square.
                        else
                        {
                            // First, we disable the button so that is may not be clicked again.
                            buttons[iIndex][jIndex].setEnabled(false);
                            
                            // If the button that was clicked is a mine, the game shall be lost by calling the lose() function.
                            if(board[iIndex][jIndex] == -1)
                            {
                                lose();
                            }
                            
                            // If the button clicked is a 0, we clear all the squares around it by simulating a click.
                            else if(board[iIndex][jIndex] == 0)
                            {
                                int aMax = (iIndex-1) + 3;
                                int bMax = (jIndex-1) + 3;
                                for(int a=iIndex-1;a<aMax;a++)
                                {
                                    for(int b=jIndex-1;b<bMax;b++)
                                    {
                                        try
                                        {
                                                buttons[a][b].doClick(0);
                                        }
                                        catch(Exception exception){}
                                    }
                                }
                            }
                            
                            // If the button is not a 0, we will set it's text to the corresponding number in the 2d int array (board[][]).
                            if(board[iIndex][jIndex] != 0)
                            {
                                buttons[iIndex][jIndex].setText(Integer.toString(board[iIndex][jIndex]));
                            }
                        }
                        
                        // Next, we check if the game is won by using the isGameWon function, if it is, the game is won and we call the win() function.
                        if(isGameWon())
                        {
                            win();
                        }
                    }
                });
                // Add the buttons to the container.
                container.add(buttons[i][j]);
            }
        }
        // set the default location of the MineSweeperGUI window to be in the center of the screen so that it does not overlap with the MainWindowGUI window.
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    // This method will check if the game is won.
    public boolean isGameWon()
    {
        // If it has already been indicated that the game is lost, obviously the game has not been won.
        if(isLost)
        {
            return false;
        }
        
        // Otherwise, if every button except for a bomb has been pressed, the game is won.
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                if(buttons[i][j].isEnabled() && board[i][j] != -1)
                {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    // This method will be called whenever the game is won.
    public void win()
    {
        // First we reveal all the data and disable all the buttons.
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                buttons[i][j].setText(Integer.toString(board[i][j]));
                buttons[i][j].setEnabled(false);
            }
        }
        
        
        // Next, we play the win sound.
        try
        {
            File winSound = new File("win.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(winSound);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        }
        catch(Exception e){}
        
        // Once a game is completed, the isGameInProgress boolean is set to false.
        isGameInProgress = false;
        
        // Lastly, we call the win method from the MainWindowGUI so that the game information labels can be updated.
        MainWindowGUI.instance.win();
    }
    
    
    // This method will be called whenever the game is lost.
    public void lose()
    {
        // First we reveal all the data and disable all the buttons.
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                buttons[i][j].setText(Integer.toString(board[i][j]));
                buttons[i][j].setEnabled(false);
            }
        }
        
        // Next, we play the loss/explosion sound.
        try
        {
            File loseSound = new File("lose.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(loseSound);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        }
        catch(Exception e){}
        
        // Once a game is completed, the isGameInProgress boolean is set to false.
        isGameInProgress = false;
        
        // We call the lose() method from the MainWindowGUI so that the game information labels can be updated.
        MainWindowGUI.instance.lose();
        
        // updating the isLost variable so that the the game is not falsely declared to be won because all the buttons are pressed.
        isLost = true;
    }
    
    public void close()
    {
        // When the game is closed, we do not go through the process of playing the loss sound and revealing the location of the bombs, but it is still counted as a loss.
        // To refelct this the lose() method from MainWindowGUI is called to reflect this in the game information labels.
        MainWindowGUI.instance.lose();
    }

    
    // These methods update the ctrlKeyPressed boolean when the control key is pressed.
    // This allows for the "flagging of bombs".
    @Override
    public void keyTyped(KeyEvent e) 
    {
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        ctrlKeyPressed = e.isControlDown();
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        ctrlKeyPressed = e.isControlDown();
    }
}