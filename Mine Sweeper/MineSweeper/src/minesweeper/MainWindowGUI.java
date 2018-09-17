// Programmed by: Dylan Bobb & Veronica Crijavetchi
// This class will serve as a Main Window where information about previous games can be seen, a game can be started, and options can be toggled.
package minesweeper;

import java.awt.Color;

public class MainWindowGUI extends javax.swing.JFrame {
    // Making use of the singleton technique to pass information from the MineSweeperGUI to the MainWindowGUI.
    public static MainWindowGUI instance = null;
    
    // Setting the counters to be 0 by default.
    protected int lossCounter = 0;
    protected int winCounter = 0;
    protected int gamesPlayedCounter = 0;
    
    // This variable will hold the GUI of the current minesweeper game.
    MineSweeperGUI sweep;
    
    // At the beginning no previous game will have been played. This is used to close a completed board when a new game is started.
    protected boolean previousGame = false;
    
    // By default, debug mode is disabled.
    protected boolean DEBUG_MODE = false;
    
    // The default color of the buttons is set to be white (null).
    Color color = null;
    
    public MainWindowGUI() {
        initComponents();
        instance = this;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gamesWonLabel = new javax.swing.JLabel();
        gamesLostLabel = new javax.swing.JLabel();
        gamesPlayedLabel = new javax.swing.JLabel();
        startButton = new javax.swing.JButton();
        statusLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        File = new javax.swing.JMenu();
        quitButton = new javax.swing.JMenuItem();
        debugMode = new javax.swing.JCheckBoxMenuItem();
        Edit = new javax.swing.JMenu();
        colorChange = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        gamesWonLabel.setText("Games Won: 0 ");

        gamesLostLabel.setText("Games Lost: 0");

        gamesPlayedLabel.setText("Total Games Played: 0");

        startButton.setText("New Game");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statusLabel.setText("Press \"New Game\" to Start");

        File.setText("File");

        quitButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        quitButton.setText("Quit");
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonActionPerformed(evt);
            }
        });
        File.add(quitButton);

        debugMode.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        debugMode.setText("Debug Mode");
        debugMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                debugModeActionPerformed(evt);
            }
        });
        File.add(debugMode);

        jMenuBar1.add(File);

        Edit.setText("Edit");

        colorChange.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        colorChange.setText("Change Color");
        colorChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorChangeActionPerformed(evt);
            }
        });
        Edit.add(colorChange);

        jMenuBar1.add(Edit);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(startButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(gamesWonLabel)
                                    .addComponent(gamesLostLabel)
                                    .addComponent(gamesPlayedLabel))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gamesWonLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gamesLostLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gamesPlayedLabel)
                .addGap(18, 18, 18)
                .addComponent(startButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(statusLabel)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // When the quit button is pressed, the program shall terminate.
    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_quitButtonActionPerformed

    // When the start button is pressed a new MineSweeperGUI window will be opened, and if another game is already completed, that window shall be closed.
    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        startButton.setEnabled(false);
        if(previousGame)
        {
            sweep.frame.setVisible(false);
        }
        sweep = new MineSweeperGUI(DEBUG_MODE, color);
    }//GEN-LAST:event_startButtonActionPerformed

    // When the debug option is selected it will toggle debug mode on/off.
    private void debugModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_debugModeActionPerformed
        DEBUG_MODE = !DEBUG_MODE;
    }//GEN-LAST:event_debugModeActionPerformed

    // When the color change option is selected, a ColorChangerGUI window will open.
    private void colorChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorChangeActionPerformed
        ColorChangerGUI color = new ColorChangerGUI();
    }//GEN-LAST:event_colorChangeActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindowGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindowGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindowGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindowGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindowGUI().setVisible(true);
            }
        });
    }
    
    // Method to update MainWindowGUI data after a loss.
    public void lose()
    {
        // First, the gamesPlayedCounter and the lossCounter are incremented by 1.
        gamesPlayedCounter++;   
        lossCounter++;
        
        // Next, the game data labels are updated.
        gamesLostLabel.setText("Games lost: " + lossCounter);
        gamesPlayedLabel.setText("Total Games Played: " + gamesPlayedCounter);
        
        // Indicating that a game has already been completed (this helps close the completed game window when a new game is started).
        previousGame = true;
        
        // Updating the statusLabel to let the user know that they lost the game.
        statusLabel.setText("You Lost... Click to play again");
        
        // Re-enabling the startButton.
        startButton.setEnabled(true);
    }
    
    // Method to update MainWindowGUI data after a win.
    public void win()
    {
        // First, the gamesPlayedCounter and the lossCounter are incremented by 1.
        gamesPlayedCounter++;
        winCounter++;
        
        // Next, the game data labels are updated.
        gamesWonLabel.setText("Games won: " + winCounter);
        gamesPlayedLabel.setText("Total Games Played: " + gamesPlayedCounter);
        
        // Indicating that a game has already been completed (this helps close the completed game window when a new game is started).
        previousGame = true;
        
        // Updating the statusLabel to let the user know that they won the game.
        statusLabel.setText("You won! Click to play again!");
        
        // Re-enabling the startButton.
        startButton.setEnabled(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Edit;
    private javax.swing.JMenu File;
    private javax.swing.JMenuItem colorChange;
    private javax.swing.JCheckBoxMenuItem debugMode;
    private javax.swing.JLabel gamesLostLabel;
    private javax.swing.JLabel gamesPlayedLabel;
    private javax.swing.JLabel gamesWonLabel;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem quitButton;
    private javax.swing.JButton startButton;
    private javax.swing.JLabel statusLabel;
    // End of variables declaration//GEN-END:variables

    // This method is used to change the text of the statusLabel. This is mainly used to let the user know that a game is in progress.
    public void changeStatusText(String text)
    {
        statusLabel.setText(text);
    }
}
