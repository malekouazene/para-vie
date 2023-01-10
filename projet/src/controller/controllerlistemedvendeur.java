package controller;

import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import application.connexionmysql;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.modellistemedadmin;

public class controllerlistemedvendeur implements Initializable{

    Connection cnx;
    public PreparedStatement stm;
    public ResultSet result;
    


    @FXML
    private TableView<modellistemedadmin> tablemed;

    @FXML
    private TableColumn<modellistemedadmin, Integer> clnnum;

    @FXML
    private TableColumn<modellistemedadmin, String> clnnom;

    @FXML
    private TableColumn<modellistemedadmin, String> clntype;

    @FXML
    private TableColumn<modellistemedadmin, String> clndosage;

    @FXML
    private TableColumn<modellistemedadmin, Date> clndateexp;

    @FXML
    private TableColumn<modellistemedadmin, Date> clndatefab;

    @FXML
    private TableColumn<modellistemedadmin, Integer> clnprix;

    @FXML
    private TextField txtsearch;

    


    public void showListemedadmin(){
        String sql= "SELECT * FROM  medicament";
        ObservableList<modellistemedadmin> data = FXCollections.observableArrayList();
        try {
            stm=cnx.prepareStatement(sql);
            result=stm.executeQuery();
            while(result.next()){
                data.add(new modellistemedadmin(result.getInt("numero"),result.getString("nom"),result.getString("type"),result.getString("dosage"),result.getDate("dateexpiration"),result.getDate("datefabrication"),result.getInt("prix")));
            }

        }catch (SQLException e ) {
            e.printStackTrace();
        }

            clnnum.setCellValueFactory(new PropertyValueFactory<modellistemedadmin, Integer>("num"));
            clnnom.setCellValueFactory(new PropertyValueFactory<modellistemedadmin, String>("nom"));
            clntype.setCellValueFactory(new PropertyValueFactory<modellistemedadmin, String>("type"));
            clndosage.setCellValueFactory(new PropertyValueFactory<modellistemedadmin, String>("dosage"));
            clndateexp.setCellValueFactory(new PropertyValueFactory<modellistemedadmin, Date>("dateexp"));
            clndatefab.setCellValueFactory(new PropertyValueFactory<modellistemedadmin, Date>("datefab"));
            clnprix.setCellValueFactory(new PropertyValueFactory<modellistemedadmin, Integer>("prix"));
            tablemed.setItems(data);

            
            FilteredList<modellistemedadmin> filtered = new FilteredList<>(data, b -> true);
            txtsearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filtered.setPredicate(modellistemedadmin -> {
                    if(newValue.isEmpty() || newValue.isBlank() || newValue == null){
                        return true;
                    }
                    String searchkeyword = newValue.toLowerCase();
                    if(modellistemedadmin.getNom().toLowerCase().indexOf(searchkeyword) > -1){
                        return true;
                    } else if (modellistemedadmin.getType().toLowerCase().indexOf(searchkeyword) > -1){
                        return true;
                    } else
                        return false;
                });
            });

            SortedList<modellistemedadmin> sorted = new SortedList<>(filtered);
            sorted.comparatorProperty().bind(tablemed.comparatorProperty());

            tablemed.setItems(sorted);
    }



  
    
    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        cnx=connexionmysql.connexionDB();
        showListemedadmin();
    }
    
}
