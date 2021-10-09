/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badc.Admin;

import Classes.Account_Management;
import Classes.User;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author `Dipanker
 */
public class CreateUserAccountController implements Initializable {
   
    ObservableList<String> typeUser = FXCollections.observableArrayList("Admin" , "Account Management" , "Fertilizer Management" , "Seed & Horticulter Management");
    @FXML
    private TextField newuserid;
    @FXML
    private TextField newpass;
    @FXML
    private DatePicker date;
    @FXML
    private TextField neweducation;
    @FXML
    private TextField newexperience;
    @FXML
    private TextField newemail;
    @FXML
    private ComboBox userType;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        userType.setValue("");
        userType.setItems(typeUser);
    }    

    @FXML
    private void saveButtonOnAction(ActionEvent event) throws IOException {
     if(userType.getSelectionModel().getSelectedItem().toString().equals("Account Management"))
     {
         User u = new Account_Management(userType.getSelectionModel().getSelectedItem().toString(), Integer.parseInt(newuserid.getText()), newpass.getText(), date.getValue().toString(), neweducation.getText(), newexperience.getText(), newemail.getText());
     }
     File f = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        DataOutputStream dos = null;
        
        try {
            f = new File("UserAccountList.bin");
            if(f.exists()) fos = new FileOutputStream(f,true);
            else fos = new FileOutputStream(f);
            
            bos = new BufferedOutputStream(fos);
            dos = new DataOutputStream(bos);
            //dos = new DataOutputStream(fos);
            
            
            dos.writeUTF(userType.getSelectionModel().getSelectedItem().toString());
            dos.writeUTF(newuserid.getText());
            dos.writeUTF(newpass.getText());
            dos.writeUTF(date.getValue().toString());
            dos.writeUTF(neweducation.getText());
            dos.writeUTF(newexperience.getText());
            dos.writeUTF(newemail.getText());
            
        
        } catch (IOException ex) {
            Logger.getLogger(CreateUserAccountController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(dos != null) dos.close();
            } catch (IOException ex) {
                Logger.getLogger(CreateUserAccountController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
   
        userType.setItems(null);
        newuserid.setText("");  
        newpass.setText("");
        date.setValue(null); 
        neweducation.setText("");
        newexperience.setText("");
        newemail.setText("");

    }
 

    @FXML
    private void backButtonOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminPage.fxml"));
        Scene scene2 = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene2);
        window.show();
    }

    @FXML
    private void viewwButtonOnAction(ActionEvent event) throws IOException {
         
        Parent root = FXMLLoader.load(getClass().getResource("ShowUserAccList.fxml"));
        Scene scene2 = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene2);
        window.show();
}
}
