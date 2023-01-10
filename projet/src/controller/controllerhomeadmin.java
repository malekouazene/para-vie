package controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;


public class controllerhomeadmin implements Initializable {
    Parent fxml;
    @FXML
    private Pane root;

    @FXML
    void listeemployer() {
        try {
            fxml= FXMLLoader.load(getClass().getResource("/FXML/listeemployeeadmin.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void listemedadmin() {
        try {
            fxml= FXMLLoader.load(getClass().getResource("/FXML/listemedadmin.fxml"));
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
