/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badc.Account_Management;

import java.io.File;
import java.io.FileWriter;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author `Dipanker
 */
public class BankAccInfoController implements Initializable {

    @FXML
    private TextField bankname;
    @FXML
    private TextField branchname;
    @FXML
    private TextField accountnumber;
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
        FileWriter fw = null;
        try {
           f = new File("BankAccountInfo.txt");
            //fw = new FileWriter(f);
            if(f.exists()) fw = new FileWriter(f,true);
            else fw = new FileWriter(f);
           
            fw.write(
                bankname.getText()+","	
                +branchname.getText()+","	
                +accountnumber.getText()+"\n"	
            );           
  
        } catch (IOException ex) {
           
        } finally {
            try {
                if(fw != null) fw.close();
            } catch (IOException ex) {
             
            }
        }
      
        bankname.setText("");
        branchname.setText("");
        accountnumber.setText("");
       
        
    }


    @FXML
    private void backButtonOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AccountMPage.fxml"));
        Scene scene2 = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene2);
        window.show();
    }

    @FXML
    private void viewButtonOnAction(ActionEvent event) {
        show.setText("");
        File f = null;
        //FileReader fw = null;
        Scanner sc; String str; String[] tokens;
        try {
            f = new File("BankAccountInfo.txt");
            sc = new Scanner(f);
            if(f.exists()){
                while(sc.hasNextLine()){
                    str=sc.nextLine();
                    tokens = str.split(",");
                    show.appendText(
                            "Bank Name="+tokens[0]
                            +",Branch Name="+tokens[1]
                            +",Account Number="+tokens[2]
                            +"\n"
                                    );
                }
            }
            else 
                show.setText("BankAccountInfo.txt does not exist...");
        } 
        catch (IOException ex) {
            Logger.getLogger(BankAccInfoController.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
}


