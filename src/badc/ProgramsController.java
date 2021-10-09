/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badc;

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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author `Dipanker
 */
public class ProgramsController implements Initializable {

    ObservableList<String> chooseProgram = FXCollections.observableArrayList("Fertilizer Sales Center Details", "Notice", "Seed & Crop Price List", "Upcoming Program Details");
    @FXML
    private TextArea programstxt;
    @FXML
    private ComboBox choosePCombo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        choosePCombo.setValue("");
        choosePCombo.setItems(chooseProgram);
    }

    @FXML
    private void backButtonOnAction(ActionEvent event) throws IOException {
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene2);
        window.show();
    }

    @FXML
    private void choosePButtonOnAction(ActionEvent event) {
        File f = null;
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        DataInputStream dis = null;
        String str = "";
        String fileNameSelector = choosePCombo.getSelectionModel().getSelectedItem().toString();
        String fileName = "";
        if (fileNameSelector == "Notice") {
            fileName = "Notice.bin";
        } else if (fileNameSelector == "Fertilizer Sales Center Details") {
            fileName = "FertilizerSalesCenter.bin";
        } else if (fileNameSelector == "Seed & Crop Price List") {
            fileName = "PriceList.bin";
        } else if (fileNameSelector == "Upcoming Program Details") {
            fileName = "UpcomingProDetails.bin";
        }
        StringBuffer sb = new StringBuffer();
        try {
            f = new File(fileName);
            if (!f.exists()) {
                System.out.println("Notice.bin binary file does not exis...");
            } else {

                fis = new FileInputStream(f);

                dis = new DataInputStream(fis);

                //Object ob = null;
                //StringBuffer sb = new StringBuffer();
                /*String s = "";
                while ((s = dis.readLine()) != null) {
                    //ob = dis.read();
                    sb.append(s);
                    //System.out.println("SB: " + sb);
                    str += sb.toString() + "\n";
                    //System.out.println("STR: " + str);
                    
                    
                }*/
                if (fileNameSelector == "Notice") {
                    System.out.println("This is STR: " + str);
                    str = printNotice(dis);
                } else if (fileNameSelector == "Fertilizer Sales Center Details") {
                    fileName = "FertilizerSalesCenter.bin";
                } else if (fileNameSelector == "Seed & Crop Price List") {
                    fileName = "PriceList.bin";
                } else if (fileNameSelector == "Upcoming Program Details") {
                    fileName = "UpcomingProDetails.bin";
                }

                System.out.println("SB: " + sb);
                System.out.println("STR: " + str);
            }
        } catch (IOException ex) {
            programstxt.setText(str);
            //programstxt.setText(sb);
        } finally {
            try {
                if (dis != null) {
                    dis.close();
                }
            } catch (IOException ex) {
                System.out.println("IOException: " + ex);
            }
        }
    }

    private String printNotice(DataInputStream dis) throws IOException {
        String str = "";
        String s = "";
        while ((s = dis.readLine()) != null) {
            str += "Date: " + dis.readUTF() + " " + dis.readUTF() + "\n";
        }
        return str;
    }

    /* private String printFertilizer(DataInputStream dis)throws IOException{
        String str = "";
        String s = "";
        while ((s = dis.readLine()) != null) {
                    
                }
    }
    
    private String printPriceList(DataInputStream dis){
        String str = "";
        String s = "";
        while ((s = dis.readLine()) != null) {
                    
                }
    }
    
    private String printProgramDetails(DataInputStream dis){
        String str = "";
        String s = "";
        while ((s = dis.readLine()) != null) {
                    
                }
    }*/
}
