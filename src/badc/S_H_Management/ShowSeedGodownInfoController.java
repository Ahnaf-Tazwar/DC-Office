/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badc.S_H_Management;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author `Dipanker
 */
public class ShowSeedGodownInfoController implements Initializable {

    @FXML
    private TextArea showseedgodown;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void viewwButtonOnAction(ActionEvent event) {
        showseedgodown.setText("");
        File f = null;
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        DataInputStream dis = null;
        String str="";
        try {
            f = new File("SeedGodown.bin");
            if(!f.exists()){
                showseedgodown.setText("Oops! PriceList.bin binary file does not exist...");
            }
            else{
                
                fis = new FileInputStream(f);
                //bis = new BufferedInputStream(fis);
                //dis = new DataInputStream(bis);
                dis = new DataInputStream(fis);
                //String str="";
                while(true){
                    str+= "seedname:"+dis.readUTF()
                        +"; warehouse:"+dis.readUTF()
                        +"; division:"+dis.readUTF()
                        +"; district:"+dis.readUTF()
                            +"; upazila:"+dis.readUTF()
                            +"; location:"+dis.readUTF()
                            +"; phnnum:"+Integer.toString(dis.readInt())
                            +"; capacity:"+Integer.toString(dis.readInt())+"\n";
                    //outputTextArea.setText(str);
                }//while
                //outputTextArea.setText(str);
            }//else
        } catch (IOException ex) {
            showseedgodown.setText(str);
            Logger.getLogger(ShowSeedGodownInfoController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(dis != null) dis.close();
            } catch (IOException ex) {
                Logger.getLogger(ShowSeedGodownInfoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }          
    }

    @FXML
    private void backButtonOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SeedGodownInfo.fxml"));
        Scene scene2 = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene2);
        window.show();
    }
    }
    

