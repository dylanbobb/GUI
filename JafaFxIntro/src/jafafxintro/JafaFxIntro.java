/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jafafxintro;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author dylan
 */
public class JafaFxIntro extends Application 
{
    Label result;
    Scene scene1;
    Scene scene2;
    
    @Override
    public void start(Stage primaryStage) 
    {
        // Scene 1 Components
        Label prompt = new Label();
        prompt.setText("Enter a temperature in C");
                
        TextField input = new TextField();
        
        Button convertBtn = new Button();
        convertBtn.setText("Convert");
        convertBtn.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                double c = Double.parseDouble(input.getText());
                double f = (9f/5f) * c + 32;
                result.setText(f+" F");
                primaryStage.setScene(scene2);
            }
        });
        
        // Scene 2 Components
        result = new Label();
        Button backBtn = new Button();
        backBtn.setText("Back");
        backBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                result.setText("");
                primaryStage.setScene(scene1);
            }
        });
        
        // Scene 1 Layout
        VBox root = new VBox();
        root.getChildren().add(prompt);
        root.getChildren().add(input);
        root.getChildren().add(convertBtn);
        scene1 = new Scene(root, 200, 150);
        
        
        // Scene 2 Layout
        VBox root2 = new VBox();
        root2.getChildren().add(result);
        root2.getChildren().add(backBtn);
        scene2 = new Scene(root2, 200, 150);
        
        primaryStage.setTitle("Temperature Converter");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
    
}
