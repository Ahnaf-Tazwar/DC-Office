/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badc;

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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author `Dipanker
 */
public class SignInController implements Initializable {
    ObservableList<String> typeUser = FXCollections.observableArrayList("Admin" , "Account Management" , "Fertilizer Management" , "Seed & Horticulter Management");
    @FXML
    private TextField userid;
    @FXML
    private TextField userpass;
    @FXML
    private ComboBox usertype;

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
    private void loginButtonOnAction(ActionEvent event) throws IOException {

        
         if (userid.getText().toString().equals("1234")) // admin
        {
            if(userpass.getText().equals("1111") && usertype.getSelectionModel().getSelectedItem().equals("Admin")) {
                Parent scene2Parent = FXMLLoader.load(getClass().getResource("/badc/Admin/AdminPage.fxml"));
                Scene scene2 = new Scene(scene2Parent);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene2);
                window.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Invalid Type or Password!");
                alert.showAndWait();
            }
        }
         if (userid.getText().toString().equals("2345")) // Account Management
        {
            if (userpass.getText().equals("2222") && usertype.getSelectionModel().getSelectedItem().equals("Account Management")) {
                Parent scene2Parent = FXMLLoader.load(getClass().getResource("/badc/Account_Management/AccountMPage.fxml"));
                Scene scene2 = new Scene(scene2Parent);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene2);
                window.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Invalid Type or Password!");
                alert.showAndWait();
            }
        }if (userid.getText().toString().equals("3456")) // Fertilizer_Management
        {
            if (userpass.getText().equals("3333") && usertype.getSelectionModel().getSelectedItem().equals("Fertilizer Management")) {
                Parent scene2Parent = FXMLLoader.load(getClass().getResource("/badc/Fertilizer_Management/FertilizerMPage.fxml"));
                Scene scene2 = new Scene(scene2Parent);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene2);
                window.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Invalid Type or Password!");
                alert.showAndWait();
            }
        }if (userid.getText().toString().equals("4567")) // Seed & Horticulter Management
        {
            if (userpass.getText().equals("4444") && usertype.getSelectionModel().getSelectedItem().equals("Seed & Horticulter Management")) {
                Parent scene2Parent = FXMLLoader.load(getClass().getResource("/badc/S_H_Management/SHPage.fxml"));
                Scene scene2 = new Scene(scene2Parent);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene2);
                window.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Invalid Type or Password!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void backButtonOnAction(ActionEvent event) throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene2 = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene2);
        window.show();
    }

    @FXML
    private void forgotButtonOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ForgotPassword.fxml"));
        Scene scene2 = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene2);
        window.show();
    }

}
