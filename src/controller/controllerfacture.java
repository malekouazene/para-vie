package controller;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class controllerfacture  implements Initializable{

    @FXML
    private DatePicker date;

    @FXML
    private Button enregister;

    @FXML
    private TableColumn<?, ?> nom;

    @FXML
    private TableColumn<?, ?> prix;

    @FXML
    private TextField prixtotal;

    @FXML
    private Button selctmed;

    @FXML
    private TableColumn<?, ?> type;
 
    private Pane root;
    Parent fxml;
    @FXML
    void selectmed(){
        





        
        try {
            fxml= FXMLLoader.load(getClass().getResource("/fxml/listemedicament.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();


        }
    }











    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    }








}
