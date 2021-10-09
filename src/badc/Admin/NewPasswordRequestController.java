/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badc.Admin;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author `Dipanker
 */
public class NewPasswordRequestController implements Initializable {

    @FXML
    private TextArea passwordList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void backButtonOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminPage.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    private void viewButtonOnAction(ActionEvent event) {
        passwordList.setText("");
        File f = null;
        //FileReader fw = null;
        Scanner sc; String str; String[] tokens;
        try {
            f = new File("NewPasswordReq.txt");
            sc = new Scanner(f);
            if(f.exists()){
                while(sc.hasNextLine()){
                    str=sc.nextLine();
                    tokens = str.split(",");
                    passwordList.appendText(
                            "User ID="+tokens[0]
                            +"-  User Type="+tokens[1]
                            +"\n"
                                    );
                }
            }
            else 
                passwordList.setText("NewPasswordReq.txt does not exist...");
        } 
        catch (IOException ex) {
            Logger.getLogger(NewPasswordRequestController.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
}

    
