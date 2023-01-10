package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import application.connexionmysql;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class controllercompte implements Initializable {

    Connection cnx;
    public PreparedStatement stm;
    public ResultSet result;

    Parent fxml;


    @FXML
    private AnchorPane root;

    @FXML
    private RadioButton manager;

    @FXML
    private TextField txtnom;

    @FXML
    private TextField txtpassword;

    @FXML
    private TextField txtprenom;

    @FXML
    private TextField txtsalaire;

    @FXML
    private RadioButton vendeur;

    @FXML
    void annuler() {
        try {

            txtnom.setText("");
            txtprenom.setText("");
            txtpassword.setText("");
            txtsalaire.setText("");

            fxml= FXMLLoader.load(getClass().getResource("/FXML/listeemployeeadmin.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
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
        if(!nom.equals("")&&!prenom.equals("")&&!password.equals("")){
        try {
            stm=cnx.prepareStatement(sql);
            stm.setString(1, nom);
            stm.setString(2, prenom);
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
                fxml= FXMLLoader.load(getClass().getResource("/FXML/listeemployeeadmin.fxml"));
                root.getChildren().removeAll();
                root.getChildren().setAll(fxml);
            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }else{
        Alert alert = new Alert(AlertType.WARNING, "Veuillez remplir tous les champs!!", ButtonType.OK);
        alert.showAndWait();
    }
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        cnx=connexionmysql.connexionDB();
    }
    
}
