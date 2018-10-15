/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userparsing;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author cstuser
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label firstNameLabel;
    
    @FXML
    private Button retrieveBtn;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println(readJsonFile("./user.json"));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private String readJsonFile(String fileName)
    {
        try
        {
            FileInputStream fileIn = new FileInputStream(fileName);
            BufferedReader in = new BufferedReader(new InputStreamReader(fileIn));
            
            String jsonString = "";
            String inputLine = "";
            
            while((inputLine = in.readLine()) != null)
            {
                jsonString += inputLine;
            }
            in.close();
            fileIn.close();
            
            return jsonString;
        }
        catch(Exception e)
        {
            System.out.println("Error");
        }
        return "Error";
    }
}
