/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badc.Fertilizer_Management;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author `Dipanker
 */
public class MonthlyFStockUpdateController implements Initializable {

    ObservableList<String> diffterentTypefertilizer = FXCollections.observableArrayList("TSP", "Urea", "Foliar", "Phosphate");
    @FXML
    private TextField fsalescenter;
    @FXML
    private ComboBox ftype;
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

        ftype.setValue("");
        ftype.setItems(diffterentTypefertilizer);
    }

    @FXML
    private void saveButtonOnAction(ActionEvent event) throws IOException {
        File f = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        DataOutputStream dos = null;

        try {
            f = new File("MonthlyFertiliserStock.bin");
            if (f.exists()) {
                fos = new FileOutputStream(f, true);
            } else {
                fos = new FileOutputStream(f);
            }

            bos = new BufferedOutputStream(fos);
            dos = new DataOutputStream(bos);
            //dos = new DataOutputStream(fos);

            dos.writeUTF(fsalescenter.getText());
            dos.writeUTF((ftype.getValue().toString()));
            dos.writeInt(Integer.parseInt(amount.getText()));
            dos.writeUTF((date.getValue().toString()));

        } catch (IOException ex) {
            Logger.getLogger(MonthlyFStockUpdateController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (dos != null) {
                    dos.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(MonthlyFStockUpdateController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        fsalescenter.setText("");
        ftype.setValue(null);
        amount.setText("");
        date.setValue(null);

    }

    @FXML
    private void backButtonOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FertilizerMPage.fxml"));
        Scene scene2 = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene2);
        window.show();
    }

    @FXML
    private void viewButtonOnAction(ActionEvent event) {
        show.setText("");
        File f = null;
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        DataInputStream dis = null;
        String str = "";
        try {
            f = new File("MonthlyFertiliserStock.bin");
            if (!f.exists()) {
                show.setText("MonthlyFertiliserStock.bin binary file does not exist...");
            } else {

                fis = new FileInputStream(f);
                //bis = new BufferedInputStream(fis);
                //dis = new DataInputStream(bis);
                dis = new DataInputStream(fis);
                //String str="";
                while (true) {
                    str += "Fertilizer Sales Center Name:" + dis.readUTF()
                            + "; Fertilizer Type:" + dis.readUTF()
                            + "; Amount:" + Integer.toString(dis.readInt())
                            + "; Date:" + dis.readUTF() + "\n";
                    //outputTextArea.setText(str);
                }//while
                //outputTextArea.setText(str);
            }//else
        } catch (IOException ex) {
            show.setText(str);
            //System.out.println(str);
            Logger.getLogger(MonthlyFStockUpdateController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (dis != null) {
                    dis.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(MonthlyFStockUpdateController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
