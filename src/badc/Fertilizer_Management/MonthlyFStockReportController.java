/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badc.Fertilizer_Management;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author `Dipanker
 */
public class MonthlyFStockReportController implements Initializable {

    @FXML
    private TextField fsalescenter;
    @FXML
    private Label statusLabel;
    @FXML
    private DatePicker date;
    @FXML
    private ComboBox<?> report;
    @FXML
    private PieChart pieChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void generateButtonOnAction(ActionEvent event) {
        LocalDate ld = date.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy-dd");
        String dateString = formatter.format(ld);
        System.out.println("DATES:" + dateString);
        
        File f = null;
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        DataInputStream dis = null;
        String str = "";
        int counter = 0;
        try {
            f = new File("MonthlyFertiliserStock.bin");
            if (!f.exists()) {
                 System.out.println("MonthlyFertiliserStock.bin binary file does not exist...");
            } else {

                fis = new FileInputStream(f);
                //bis = new BufferedInputStream(fis);
                //dis = new DataInputStream(bis);
                dis = new DataInputStream(fis);
                //String str="";
                while (true) {
                    str += dis.readUTF()
                            + ":" + dis.readUTF()
                            + ":" + Integer.toString(dis.readInt())
                            + ":" + dis.readUTF() + "\n";
                    counter++;
                    //outputTextArea.setText(str);
                }//while
                //outputTextArea.setText(str);
            }//else
        } catch (IOException ex) {
            //System.out.println("START:");
            String []showList = new String[counter];
            String [] fileDateArr = new String [3];
            String [] searchDateArr = new String [3];
            searchDateArr = dateString.split("-");
            showList = str.split("\n");
            String fileDate = "";
            //String searchDate = "";
            String []individualChecker = new String [4];
            ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
            for(String s: showList){
                individualChecker = s.split(":");
                fileDate = individualChecker[3];
                //System.out.println("individualChecker" + fileDate);
                fileDateArr = fileDate.split("-");
                if (fsalescenter.getText().equals(individualChecker[0]) && fileDateArr[1].equals(searchDateArr[0])){
                    //System.out.println("File_Array:" + fileDateArr[1]);
                    //System.out.println("DatePicker_Array:" + searchDateArr[0]);
                    list.add(new PieChart.Data(individualChecker[1], Integer.valueOf(individualChecker[2])));
                }
            }
            pieChart.setData(list);
            
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
        Parent root = FXMLLoader.load(getClass().getResource("FertilizerMPage.fxml"));
        Scene scene2 = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene2);
        window.show();
    }

    @FXML
    private void reportButtonOnAction(ActionEvent event) {
    }
    
}
