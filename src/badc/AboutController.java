/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badc;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author `Dipanker
 */
public class AboutController implements Initializable {

    @FXML
    private TextArea about;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        about.setText("The company traces its origins to East Pakistan Agricultural Development Corporation which was established in 1961. It was changed to Bangladesh Agricultural Development Corporation after the independence of Bangladesh. The corporation has offices nationwide reaching down to all Upazila and sometimes lower administrative unit. The primary purpose is increase agricultural output. Its under the Ministry of Agriculture. The corporation distributes rice and other seeds developed at government institutes to farmers. \n" +
"BADC mission and vision: Quality agricultural inputs supply and efficient irrigation management. Production of high yielding seeds of different crops, preservation and increasing supply, irrigation technology development, the best use of surface water, irrigation efficiency by reducing logging and increasing irrigated areas and farmers to supply quality fertilizer. The authority recruit manpower through Tele talk. Online application, admit card and others. Also they advertise on National Newspaper. ");
    }    

    @FXML
    private void BackButtonOnAction(ActionEvent event) throws IOException {
       Parent scene2Parent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene2);
        window.show();
    }
    
}
