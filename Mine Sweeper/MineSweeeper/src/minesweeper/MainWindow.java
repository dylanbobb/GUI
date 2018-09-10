/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class MainWindow extends JFrame{
 private JLabel displayMessage;
 private JButton startGame;
 private JLabel gamesWon;
 private JLabel gamesLost;
 private static MainWindow instance;
    
 public MainWindow(String title)
 {
     super(title);
     setSize(500,300);
     instance = this;
     gamesWon = new JLabel ("Games Won : 0 "); //must change when you win 
     gamesLost = new JLabel ("Games Lost : 0 "); //must change when you lost
     startGame = new JButton ("Start Game!"); //must be disabled when game is in progress
     displayMessage = new JLabel ("Press New Game To Start!");//must change to "game in progress when the game starts
     startGame.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                MineSweeper game = new MineSweeper();
                game.play();
               startGame.setEnabled(false);
            }
        });  
     
     Container pane = getContentPane();
     pane.setLayout( new BorderLayout());
     pane.add( displayMessage , BorderLayout.PAGE_END);
     pane.add( gamesWon , BorderLayout.WEST);
     pane.add ( gamesLost, BorderLayout.EAST);
     pane.add ( startGame, BorderLayout.CENTER);
     
     
 }
    public static void displayWonMessage (int message)
    {
        instance.gamesWon.setText("Games Won : " + message );
    }
    
    public static void displayLostMessage (int message)
    {
        instance.gamesLost.setText("Games Lost : "+ message);
    }
 
    public static void displayMessage ( String message)
    {
        instance.displayMessage.setText(message);
    }
    public static void main (String[]args)
    {
        MainWindow game = new MainWindow("Mine Sweeper Menu!");
        game.setVisible(true);
    }
}
