/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userparsing;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
    String jsonString = readJsonFile("./user.json");
    JsonElement rootElement = new JsonParser().parse(jsonString);
    JsonObject rootObject = rootElement.getAsJsonObject();
    
    @FXML
    private Label firstNameLabel;
    
    @FXML
    private Label lastNameLabel;
    
    @FXML
    private Label ageLabel;
    
    @FXML
    private Label addressLabel;
    
    @FXML
    private Label cityLabel;
    
    @FXML
    private Label stateLabel;
    
    @FXML
    private Label postalLabel;
    
    @FXML
    private Label homeLabel;
    
    @FXML
    private Label faxLabel;
    
    @FXML
    private Label genderLabel;
    
    @FXML
    private Button retrieveBtn;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        String firstName = rootObject.get("firstName").getAsString();
        firstNameLabel.setText(firstName);
        
        String lastName = rootObject.get("lastName").getAsString();
        lastNameLabel.setText(lastName);
        
        int age = rootObject.get("age").getAsInt();
        ageLabel.setText(age+"");
        
        JsonObject addressObject = rootObject.get("address").getAsJsonObject();
        
        String address = addressObject.get("streetAddress").getAsString();
        addressLabel.setText(address);
        
        String city = addressObject.get("city").getAsString();
        cityLabel.setText(city);
        
        String state = addressObject.get("state").getAsString();
        stateLabel.setText(state);
        
        String postal = addressObject.get("postalCode").getAsString();
        postalLabel.setText(postal);
        
        JsonArray phoneArray = rootObject.get("phoneNumber").getAsJsonArray();
        
        JsonObject homeObject = phoneArray.get(0).getAsJsonObject();
        String home = homeObject.get("number").getAsString();
        homeLabel.setText(home);
        
        JsonObject faxObject = phoneArray.get(1).getAsJsonObject();
        String fax = faxObject.get("number").getAsString();
        faxLabel.setText(fax);
        
        JsonObject genderObject = rootObject.get("gender").getAsJsonObject();
        String gender = genderObject.get("type").getAsString();
        genderLabel.setText(gender);
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
            return "Error";
        }
    }
}
