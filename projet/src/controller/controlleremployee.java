package controller;


import java.io.IOException;
import java.net.URL;
import java.sql.* ;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.sql.SQLException;

import application.connexionmysql;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.modellisteemplyeeadmin;

public class controlleremployee implements Initializable {

    Connection cnx;
    public PreparedStatement stm;
    public ResultSet result;

    Parent fxml;

    @FXML
    private TableColumn<modellisteemplyeeadmin, String> clnmetier;

    @FXML
    private TableColumn<modellisteemplyeeadmin, String> clnnom;

    @FXML
    private TableColumn<modellisteemplyeeadmin, String> clnprenom;

    @FXML
    private TableColumn<modellisteemplyeeadmin, Integer> clnsalaire;

    @FXML
    private TableColumn<modellisteemplyeeadmin, Integer> clnnum;

    @FXML
    private TableView<modellisteemplyeeadmin> tableemployee;

    @FXML    
    private Pane root;

    @FXML
    private TextField txtsearch;

    @FXML
    void ajouter() {
        try {
            fxml= FXMLLoader.load(getClass().getResource("/FXML/creercompte.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void tableevenet() {
        modellisteemplyeeadmin employee =tableemployee.getSelectionModel().getSelectedItem();
        String sql = "delete from employees where id=?";
        try {
            stm=cnx.prepareStatement(sql);
            stm.setInt(1, employee.getId());
            stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void supprimer() {
       
        try {
            showListeepmloyee();
            Alert alert = new Alert(AlertType.CONFIRMATION, "le emplyee est suprimée", ButtonType.OK);
            alert.showAndWait();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showListeepmloyee(){
        String sql= "SELECT * FROM  employees";
        ObservableList<modellisteemplyeeadmin> data1 = FXCollections.observableArrayList();
        try {
            stm=cnx.prepareStatement(sql);
            result=stm.executeQuery();
            while(result.next()){
                data1.add(new modellisteemplyeeadmin(result.getInt("id"),result.getString("nom"),result.getString("prenom"),result.getString("metier"),result.getInt("salaire")));
            }

        }catch (SQLException e ) {
            e.printStackTrace();
        }

            clnnum.setCellValueFactory(new PropertyValueFactory<modellisteemplyeeadmin, Integer>("id"));
            clnnom.setCellValueFactory(new PropertyValueFactory<modellisteemplyeeadmin, String>("nom"));
            clnprenom.setCellValueFactory(new PropertyValueFactory<modellisteemplyeeadmin, String>("prenom"));
            clnmetier.setCellValueFactory(new PropertyValueFactory<modellisteemplyeeadmin, String>("metier"));
            clnsalaire.setCellValueFactory(new PropertyValueFactory<modellisteemplyeeadmin, Integer>("salaire"));
            tableemployee.setItems(data1);

            FilteredList<modellisteemplyeeadmin> filtered = new FilteredList<>(data1, b -> true);
            txtsearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filtered.setPredicate(modellisteemplyeeadmin -> {
                    if(newValue.isEmpty() || newValue.isBlank() || newValue == null){
                        return true;
                    }
                    String searchkeyword = newValue.toLowerCase();
                    if(modellisteemplyeeadmin.getNom().toLowerCase().indexOf(searchkeyword) > -1){
                        return true;
                    } else if (modellisteemplyeeadmin.getPrenom().toLowerCase().indexOf(searchkeyword) > -1){
                        return true;
                    } else
                        return false;
                });
            });

            SortedList<modellisteemplyeeadmin> sorted = new SortedList<>(filtered);
            sorted.comparatorProperty().bind(tableemployee.comparatorProperty());

            tableemployee.setItems(sorted);
    }

   


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) { 
        cnx=connexionmysql.connexionDB();
        showListeepmloyee();
    }
    
}
