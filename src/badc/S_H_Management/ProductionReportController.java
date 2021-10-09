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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author `Dipanker
 */
public class ProductionReportController implements Initializable {

    ObservableList<String> Dname = FXCollections.observableArrayList("Barishal", "Chittagong", "Dhaka ", "Mymensingh ", "Khulna ", "Rajshahi ", "Rangpur ", "Sylhet ");

    @FXML
    private ComboBox divisionname;
    @FXML
    private PieChart pieChart;
    @FXML
    private Label statusLabel;
    @FXML
    private RadioButton cropId;
    @FXML
    private ToggleGroup Type;
    @FXML
    private RadioButton seedid;
    @FXML
    private ToggleGroup CropGroup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        divisionname.setValue("");
        divisionname.setItems(Dname);
        
    }

    @FXML
    private void generateButtonOnAction(ActionEvent event) {
        File f = null;
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        DataInputStream dis = null;
        String str = "";
        String selectDivision = divisionname.getSelectionModel().getSelectedItem().toString();

        String searchType = "";
        if (cropId.isSelected()) {
            searchType = "Crop";
        } else if (seedid.isSelected()) {
            searchType = "Seed";
        }
        ArrayList<String> typeList = new ArrayList<String>();
        int counter = 0;

        try {
            f = new File("ProductionDetails.bin");
            if (!f.exists()) {
                System.out.println("ProductionDetails.bin binary file does not exis...");
            } else {

                fis = new FileInputStream(f);

                dis = new DataInputStream(fis);

                while (true) {
                    str += dis.readUTF()
                            + ":" + dis.readUTF()
                            + ":" + dis.readUTF()
                            + ":" + Integer.toString(dis.readInt())
                            + ":" + dis.readUTF() + "\n";
                    counter++;
                }
            }
        } catch (IOException ex) {
            String[] productionList = new String[counter];
            productionList = str.split("\n");
            String[] listChart = new String[5];
            ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
            for (String i : productionList) {
                listChart = i.split(":");
                if (searchType.equals(listChart[0]) && selectDivision.equals(listChart[2])) {
                    list.add(new PieChart.Data(listChart[1], Integer.valueOf(listChart[3])));
                }
            }
            pieChart.setData(list);
        } finally {
            try {
                if (dis != null) {
                    dis.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ShowProDController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        for (PieChart.Data data : pieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    statusLabel.setText(String.valueOf(data.getPieValue()));
                }
            }
            );
        }
    }

    @FXML
    private void backButtonOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SHPage.fxml"));
        Scene scene2 = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene2);
        window.show();
    }

}
