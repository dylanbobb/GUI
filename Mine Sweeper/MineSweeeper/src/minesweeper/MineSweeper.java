
package minesweeper;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class MineSweeper implements KeyListener {
 //private final int [] board = new int [8][8];
 private Scanner kb = new Scanner(System.in);
 private final int numberOfMines = 10;
 private boolean DEBUG_MODE = false;
 private int minesSurrounding;
 private int numberGamesWon = 0;
 private int numberGamesLost = 0;
private final int numOfButtons = 64;
private boolean ctrlKeyPressed = false;
 JButton[] buttons;

 
 
 
 public void play()
 {
    JFrame frame = new JFrame("Mine Sweeper Game!");
    frame.setSize(600,600);
    Container pane = frame.getContentPane();
    pane.setLayout(new GridLayout(8,8));
    buttons = new JButton[numOfButtons];
    
    
        for ( int j = 0 ; j < numOfButtons ; j++)
        {
            //final int row = i % 3;
            //final int column = i / 3;
            final int buttonIndex = j;
            
            
            
            buttons[j] = new JButton();            
            buttons[j].setFont(new Font("Lucida", Font.BOLD, 45));
            buttons[j].addKeyListener(this);
            buttons[j].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if (ctrlKeyPressed)
                    {
                        buttons[buttonIndex].setText("Mine!");
                    }
                    
            
        }});
            frame.add(buttons[j]);
        }
 frame.setVisible(true);
 }
    
 
 public MineSweeper()
 {
     //System.out.println("hehe");
 }
 
 
 
    public static void main(String[] args) {
      
    }
    


    @Override
    public void keyTyped(KeyEvent e) {
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

   
