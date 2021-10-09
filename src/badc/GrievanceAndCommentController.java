/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badc;

import java.io.File;
import java.io.FileWriter;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author `Dipanker
 */
public class GrievanceAndCommentController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField subject;
    @FXML
    private TextField mNumber;
    @FXML
    private TextField email;
    @FXML
    private TextArea comment;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void sendButtonOnAction(ActionEvent event) {
        File f = null;
        FileWriter fw = null;
        try {
           f = new File("Comment.txt");
            //fw = new FileWriter(f);
            if(f.exists()) fw = new FileWriter(f,true);
            else fw = new FileWriter(f);
           
            fw.write(
                name.getText()+","	
                +subject.getText()+","	
                +mNumber.getText()+","
                +email.getText()+","
                +comment.getText()+"\n"	
            );           
  
        } catch (IOException ex) {
           
        } finally {
            try {
                if(fw != null) fw.close();
            } catch (IOException ex) {
             
            }
        }
      
        name.setText("");
        subject.setText("");
        mNumber.setText("");
        email.setText("");
        comment.setText("");
           
    }

    @FXML
    private void backButtonOnAction(ActionEvent event) throws IOException {
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }
    
}
