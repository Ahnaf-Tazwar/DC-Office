/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badc.S_H_Management;

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
public class ProductionDetailsController implements Initializable {

    ObservableList<String> SeedOrCroptype = FXCollections.observableArrayList("Crop", "Seed");
    ObservableList<String> Dname = FXCollections.observableArrayList("Barishal", "Chittagong", "Dhaka ", "Mymensingh ", "Khulna ", "Rajshahi ", "Rangpur ", "Sylhet ");
    @FXML
    private ComboBox type;
    @FXML
    private TextField name;
    @FXML
    private ComboBox diviaionname;
    @FXML
    private TextField amount;
    @FXML
    private DatePicker date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        type.setValue("");
        type.setItems(SeedOrCroptype);
        diviaionname.setValue("");
        diviaionname.setItems(Dname);
    }

    @FXML
    private void saveButtonOnAction(ActionEvent event) throws IOException {
        File f = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        DataOutputStream dos = null;

        try {
            f = new File("ProductionDetails.bin");
            if (f.exists()) {
                fos = new FileOutputStream(f, true);
            } else {
                fos = new FileOutputStream(f);
            }

            bos = new BufferedOutputStream(fos);
            dos = new DataOutputStream(bos);
            //dos = new DataOutputStream(fos);

            dos.writeUTF(type.getSelectionModel().getSelectedItem().toString());
            dos.writeUTF(name.getText());
            dos.writeUTF(diviaionname.getSelectionModel().getSelectedItem().toString());
            dos.writeInt(Integer.parseInt(amount.getText()));
            dos.writeUTF((date.getValue().toString()));

        } catch (IOException ex) {
            Logger.getLogger(PriceListController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (dos != null) {
                    dos.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(PriceListController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        type.setItems(SeedOrCroptype);
        name.setText("");
        diviaionname.setItems(Dname);
        amount.setText("");
        date.setValue(null);

    }

    @FXML
    private void backButtonOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SHPage.fxml"));
        Scene scene2 = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene2);
        window.show();
    }

    @FXML
    private void viewButtonOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ShowProD.fxml"));
        Scene scene2 = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene2);
        window.show();
    }
}
