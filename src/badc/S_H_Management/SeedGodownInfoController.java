/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badc.S_H_Management;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author `Dipanker
 */
public class SeedGodownInfoController implements Initializable {

    @FXML
    private TextField seedname;
    @FXML
    private TextField warehouse;
    @FXML
    private TextField division;
    @FXML
    private TextField district;
    @FXML
    private TextField upazila;
    @FXML
    private TextField location;
    @FXML
    private TextField phnnum;
    @FXML
    private TextField capacity;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveButtonOnAction(ActionEvent event) {
        File f = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        DataOutputStream dos = null;
        
        try {
            f = new File("SeedGodown.bin");
            if(f.exists()) fos = new FileOutputStream(f,true);
            else fos = new FileOutputStream(f);
            
            bos = new BufferedOutputStream(fos);
            dos = new DataOutputStream(bos);
            //dos = new DataOutputStream(fos);
            
            
            dos.writeUTF(seedname.getText());
            dos.writeUTF(warehouse.getText());
            dos.writeUTF(division.getText());
            dos.writeUTF(district.getText());
            dos.writeUTF(upazila.getText());
            dos.writeUTF(location.getText());
            dos.writeInt(Integer.parseInt(phnnum.getText()));
            dos.writeInt(Integer.parseInt(capacity.getText()));

        } catch (IOException ex) {
            Logger.getLogger(SeedGodownInfoController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(dos != null) dos.close();
            } catch (IOException ex) {
                Logger.getLogger(SeedGodownInfoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        seedname.setText("");
        warehouse.setText("");
        division.setText("");
        district.setText("");
        upazila.setText("");
        location.setText("");
        phnnum.setText(null);
        capacity.setText(null);
  
    }


    @FXML
    private void backButtonOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SHPage.fxml"));
        Scene scene2 = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene2);
        window.show();
    }

    @FXML
    private void viewwButtonOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("showSeedGodownInfo.fxml"));
        Scene scene2 = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene2);
        window.show();
    }
    
}
