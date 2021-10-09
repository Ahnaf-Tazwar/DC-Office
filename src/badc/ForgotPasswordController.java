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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author `Dipanker
 */
public class ForgotPasswordController implements Initializable {
    
    ObservableList<String> typeUser = FXCollections.observableArrayList("Admin" , "Account Management" , "Fertilizer Management" , "Seed & Horticulter Management");
    @FXML
    private TextField userid;
    @FXML
    private ComboBox usertype;
    @FXML
    private Label msg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        usertype.setValue("");
        usertype.setItems(typeUser);
    }    

    @FXML
    private void backButtonOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        Scene scene2 = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene2);
        window.show();
    }

    @FXML
    private void ReqForNewPassButtonOnAction(ActionEvent event) throws IOException {
        File f = null;
        FileWriter fw = null;
        try { 
           f = new File("NewPasswordReq.txt");
            //fw = new FileWriter(f);
            if(f.exists()) fw = new FileWriter(f,true);
            else fw = new FileWriter(f);
           
            fw.write(
                userid.getText()+","	
                +usertype.getValue().toString()+"\n"	
            );  
            msg.setText("Admin will Contact with you very soon");
  
        } catch (IOException ex) {
           
        } finally {
            try {
                if(fw != null) fw.close();
            } catch (IOException ex) {
             
            }
        }
      
        userid.setText("");
        usertype.setValue("");
           
    }
        
 }
   
