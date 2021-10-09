/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badc.Fertilizer_Management;

import badc.Admin.CreateUserAccountController;
import java.io.File;
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
public class FMonthlyRevenueController implements Initializable {
    
    @FXML
    private TextField fsalescenter;
    @FXML
    private TextField ftype;
    @FXML
    private TextField amount;
    @FXML
    private DatePicker date;
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
           f = new File("FertilizerRevenue.txt");
            //fw = new FileWriter(f);
            if(f.exists()) fw = new FileWriter(f,true);
            else fw = new FileWriter(f);
           
            fw.write(
                fsalescenter.getText()+","	
                +ftype.getText()+","	
                +amount.getText()+","
                +date.getValue().toString()+"\n"	
            );           
  
        } catch (IOException ex) {
           
        } finally {
            try {
                if(fw != null) fw.close();
            } catch (IOException ex) {
             
            }
        }
      
        fsalescenter.setText("");
        date.setValue(null);
        ftype.setText("");
        amount.setText("");
       
        
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
    private void viewButtonOnAction(ActionEvent event) {
        show.setText("");
        File f = null;
        //FileReader fw = null;
        Scanner sc; String str; String[] tokens;
        try {
            f = new File("FertilizerRevenue.txt");
            sc = new Scanner(f);
            if(f.exists()){
                while(sc.hasNextLine()){
                    str=sc.nextLine();
                    tokens = str.split(",");
                    show.appendText(
                            "Fertilizer Sales Center Name="+tokens[0]
                            +",Fertilizers Type="+tokens[1]
                            +",Amount="+tokens[2]
                            +",Date="+tokens[3]
                            +"\n"
                                    );
                }
            }
            else 
                show.setText("FertilizerRevenue.txt does not exist...");
        } 
        catch (IOException ex) {
            Logger.getLogger(FMonthlyRevenueController.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
}

    
    

