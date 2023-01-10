package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.connexionmysql;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.time.ZoneId;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

public class controllerajoutermed implements Initializable{

    Connection cnx;
    public PreparedStatement stm;
    public ResultSet result;

    Parent fxml;

    @FXML
    private DatePicker txtdatefab;

    @FXML
    private DatePicker txtdatexp;

    @FXML
    private TextField txtdosage;

    @FXML
    private TextField txtnom;

    @FXML
    private TextField txttype;

    @FXML
    private TextField txtprix;

    @FXML
    private Pane root;

    @FXML
    void annuler() {
        try {

            txtnom.setText("");
            txttype.setText("");
            txtdosage.setText("");
            txtdatexp.setValue(null);
            txtdatefab.setValue(null);
            txtprix.setText("");

            fxml= FXMLLoader.load(getClass().getResource("/FXML/listemedadmin.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void enregistrer() {
        String nom =txtnom.getText();
        String type =txttype.getText();
        String dosage =txtdosage.getText();
        String prix =txtprix.getText();
        String sql="insert into medicament(nom,type,dosage,dateexpiration,datefabrication,prix) values(?,?,?,?,?,?)";
        if(!nom.equals("")&&!type.equals("")&&!dosage.equals("")&&!prix.equals("")&&!txtdatefab.getValue().equals(null)&&!txtdatexp.getValue().equals(null)){
        try {
            stm=cnx.prepareStatement(sql);
            stm.setString(1, nom);
            stm.setString(2, type);
            stm.setString(3, dosage);
            java.util.Date dateexp=java.util.Date.from(txtdatexp.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date sdateexp =new Date(dateexp.getTime());
            stm.setDate(4,sdateexp);
            java.util.Date datefab=java.util.Date.from(txtdatefab.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date sdatefab =new Date(datefab.getTime());
            stm.setDate(5,sdatefab);
            stm.setString(6, prix);
            stm.execute();
            txtnom.setText("");
            txttype.setText("");
            txtdosage.setText("");
            txtdatexp.setValue(null);
            txtdatefab.setValue(null);
            txtprix.setText("");
            Alert alert = new Alert(AlertType.CONFIRMATION, "le médicament est enregistrer avec succés", ButtonType.OK);
            alert.showAndWait();

            try {
                fxml= FXMLLoader.load(getClass().getResource("/FXML/listemedadmin.fxml"));
                root.getChildren().removeAll();
                root.getChildren().setAll(fxml);
            } catch (IOException e) {
                e.printStackTrace();
            }
     

        } catch (SQLException e) {
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
