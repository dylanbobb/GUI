/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webapinobelprize;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 *
 * @author bergeron
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label1;
    
    @FXML
    private Label label2;
    
    @FXML
    private Label label3;
    
    @FXML
    private ComboBox yearCombo;
    
    @FXML
    private ComboBox categoryCombo;
    
    @FXML
    private Button searchButton;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        NobelPrizeLaureate[] lArray = getNobelPrizeLaureates(categoryCombo.getValue().toString(),Integer.parseInt(yearCombo.getValue()+""));
        label1.setText(lArray[0].getFirstname() + " " + lArray[0].getSurname());
        label2.setText(lArray[1].getFirstname() + " " + lArray[1].getSurname());
        label3.setText(lArray[2].getFirstname() + " " + lArray[2].getSurname());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categoryCombo.getItems().addAll("chemistry","economics","medicine","peace","physics");
        categoryCombo.setValue("chemistry");
        
        ArrayList<String> yearList = new ArrayList<String>();
        for(int i=2018;i>=1901;i--)
        {
            yearList.add(i+"");
        }
        
        yearCombo.getItems().addAll(yearList);
        yearCombo.setValue("2018");
    }    
    
    private String server = "http://api.nobelprize.org/v1/";
    public String nobelPrizeRequest(String category, int year)
    {
        String requestUrlStr = server + "prize.json?category="+category+"&year="+year;

        try
        {
            URL requestURL = new URL(requestUrlStr);
            URLConnection connection = requestURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String inputLine = "";
            String jsonOutput = "";

            while ((inputLine = in.readLine()) != null)
            {
                jsonOutput += inputLine;
            }

            in.close();

            return jsonOutput;            
        }
        catch(Exception e)
        {
            System.out.println();
            return null;
        } 
    }

    public NobelPrizeLaureate[] getNobelPrizeLaureates(String category, int year)
    {
        NobelPrizeLaureate[] nobelPrizeLaureate = new NobelPrizeLaureate[3];
        String json = nobelPrizeRequest(category, year);

        JsonElement rootElement = new JsonParser().parse(json);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonArray prizeArray = rootObject.get("prizes").getAsJsonArray();
        JsonArray lArray = prizeArray.get(0).getAsJsonObject().get("laureates").getAsJsonArray();

        for(int i=0;i<3;i++)
        {
                String firstName = lArray.get(i).getAsJsonObject().get("firstname").getAsString();
                String surname = lArray.get(i).getAsJsonObject().get("surname").getAsString();
                nobelPrizeLaureate[i] = new NobelPrizeLaureate(firstName,surname,category,year);
        }

        return nobelPrizeLaureate;
    }
}
