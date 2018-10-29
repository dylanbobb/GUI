/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spotifyproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 *
 * @author cstuser
 */
public class FXMLDocumentController implements Initializable {  
    @FXML
    private void play(ActionEvent event)
    {
        System.out.println(SpotifyController.getAlbumDataFromArtist("2ye2Wgw4gimLv2eAKyk1NB"));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }     
}
