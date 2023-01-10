package controller;



import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import application.ConnexionMlysql;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class controllercompte implements Initializable {

    Connection cnx;
    public PreparedStatement stm;
    public ResultSet result;

    Parent fxml;
    Parent fxml1;

    @FXML
    private AnchorPane root1;
    
    

    @FXML
    private RadioButton manager;

    @FXML
    private TextField txtnom;

    @FXML
    private TextField txtpassword;

    @FXML
    private TextField txtprenom;

    @FXML
    private RadioButton vendeur;

    @FXML
    private TextField txtsalaire;

    private Pane root;

    @FXML
    void ajouter(MouseEvent event) {
        try {
            fxml1= FXMLLoader.load(getClass().getResource("/fxml/creercompte.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void enregistrer() {
        String nom =txtnom.getText();
        String prenom =txtprenom.getText();
        String password =txtpassword.getText();
        String salaire =txtsalaire.getText();
        String metier = "manager";
        if(vendeur.isSelected()){
            metier ="vendeur"; 
        }
        String sql ="insert into employees(nom, prenom, metier, password, salaire) values(?,?,?,?,?)";
        try {
            stm=cnx.prepareStatement(sql);
            stm.setString(1, nom);
            stm.setString(1, prenom);
            stm.setString(3, metier);
            stm.setString(4, password);
            stm.setString(5, salaire); 
            stm.execute();
            txtnom.setText("");
            txtprenom.setText("");
            txtpassword.setText("");
            txtsalaire.setText("");
            Alert alert = new Alert(AlertType.CONFIRMATION, "le compte est enregistrer avec succés", ButtonType.OK);
            alert.showAndWait();

            try {
                fxml= FXMLLoader.load(getClass().getResource("/fxml/listeemployeeadmin.fxml"));
                root1.getChildren().removeAll();
                root1.getChildren().setAll(fxml);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) { 
     cnx=ConnexionMlysql.connexionBD();
    }
    
}
