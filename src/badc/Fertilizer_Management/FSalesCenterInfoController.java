/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badc.Fertilizer_Management;

import badc.S_H_Management.PriceListController;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
public class FSalesCenterInfoController implements Initializable {

    @FXML
    private TextField fname;
    @FXML
    private TextField fwarehousename;
    @FXML
    private TextField division;
    @FXML
    private TextField distric;
    @FXML
    private TextField upazila;
    @FXML
    private TextField dlocation;
    @FXML
    private TextField phnnumber;
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
            f = new File("FertilizerSalesCenter.bin");
            if(f.exists()) fos = new FileOutputStream(f,true);
            else fos = new FileOutputStream(f);
            
            bos = new BufferedOutputStream(fos);
            dos = new DataOutputStream(bos);
            //dos = new DataOutputStream(fos);
            
            dos.writeUTF(fname.getText());
            dos.writeUTF(fwarehousename.getText());
            dos.writeUTF(division.getText());
            dos.writeUTF(distric.getText());
            dos.writeUTF(upazila.getText());
            dos.writeUTF(dlocation.getText());
            dos.writeInt(Integer.parseInt(phnnumber.getText()));
            dos.writeInt(Integer.parseInt(capacity.getText()));
        

        } catch (IOException ex) {
            Logger.getLogger(ShowFSalesCenterController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(dos != null) dos.close();
            } catch (IOException ex) {
                Logger.getLogger(ShowFSalesCenterController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
     
        fname.setText("");
        fwarehousename.setText("");
        division.setText("");
        distric.setText("");       
        upazila.setText("");
        dlocation.setText("");
        phnnumber.setText("");
        capacity.setText("");
        
        
  
    }

    @FXML
    private void backButtonOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FertilizerMPage.fxml"));
        Scene scene2 = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene2);
        window.show();
    }

    @FXML
    private void viewButtonOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ShowFSalesCenter.fxml"));
        Scene scene2 = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene2);
        window.show();
    }
    
}
