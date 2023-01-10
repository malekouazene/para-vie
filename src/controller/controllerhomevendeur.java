package controller;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public class controllerhomevendeur implements Initializable {
    Parent fxml;
    @FXML
    private Pane root;

    @FXML
    void facture(MouseEvent event) {
        try {
            fxml= FXMLLoader.load(getClass().getResource("/fxml/facture.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void listemed(MouseEvent event) {
        try {
            fxml= FXMLLoader.load(getClass().getResource("/fxml/listmedvendeur.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    
}


  
    

