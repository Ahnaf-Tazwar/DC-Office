/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badc.Admin;

import badc.Account_Management.YIncomeExpenseInfoController;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author `Dipanker
 */
public class UpdateNoticeController implements Initializable {

    @FXML
    private DatePicker date;
    @FXML
    private TextArea noticetxt;
    @FXML
    private TextArea show;

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
            f = new File("Notice.bin");
            if(f.exists()) fos = new FileOutputStream(f,true);
            else fos = new FileOutputStream(f);
            
            bos = new BufferedOutputStream(fos);
            dos = new DataOutputStream(bos);
            //dos = new DataOutputStream(fos);
            
            
            dos.writeUTF((date.getValue().toString()));
            dos.writeUTF(noticetxt.getText());
        
        } catch (IOException ex) {
            Logger.getLogger(UpdateNoticeController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(dos != null) dos.close();
            } catch (IOException ex) {
                Logger.getLogger(UpdateNoticeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
   
        date.setValue(null);
        noticetxt.setText("");

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
        show.setText("");
        File f = null;
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        DataInputStream dis = null;
        String str="";
        try {
            f = new File("Notice.bin");
            if(!f.exists()){
                show.setText("Notice.bin binary file does not exist...");
            }
            else{
                
                fis = new FileInputStream(f);
                //bis = new BufferedInputStream(fis);
                //dis = new DataInputStream(bis);
                dis = new DataInputStream(fis);
                //String str="";
                while(true){
                    str+= "; Date:"+dis.readUTF()
                        +"BADC Notice:"+dis.readUTF()
                        +"\n";
                    //outputTextArea.setText(str);
                }//while
                //outputTextArea.setText(str);
            }//else
        } catch (IOException ex) {
            show.setText(str);
            Logger.getLogger(UpdateNoticeController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(dis != null) dis.close();
            } catch (IOException ex) {
                Logger.getLogger(UpdateNoticeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }          
}
}
    
    

