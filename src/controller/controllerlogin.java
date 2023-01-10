package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.ConnexionMlysql;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class controllerlogin implements Initializable {
    Connection cnx;
    public PreparedStatement stm;
    public ResultSet result;
    @FXML
    private TextField nom;

    @FXML
    private PasswordField password;

    @FXML
    private Button login;

    @FXML
    private ComboBox<String> metier;
    private String[] choix = { "admin", "manager", "vendeur" };

    @FXML
    private AnchorPane pane;

    private Parent fxml;

    @FXML
    void openhome() {
        String nm = nom.getText();
        String pss = password.getText();
        String met = metier.getValue();
        String sql = "SELECT * FROM 'liste employées' WHERE nom=? AND mot de passe=? AND métier=?";
        try {

            stm = cnx.prepareStatement(sql);
            stm.setString(1, nm);
            stm.setString(2, pss);
            stm.setString(3, met);

            result = stm.executeQuery();
            if (result.next()) {
                if (nm.equals(result.getString("nom")) && pss.equals(result.getString("mot de passe"))
                        && met.equals(result.getString("métier"))) {
                    if (met.equals("vendeur")) {
                        pane.getScene().getWindow().hide();
                        Stage home = new Stage();
                        try {
                            fxml = FXMLLoader.load(getClass().getResource("/fxml/homevendeur.fxml"));
                            Scene scene = new Scene(fxml);
                            home.setScene(scene);
                            home.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        if (met.equals("manager")) {
                            pane.getScene().getWindow().hide();
                            Stage home = new Stage();
                            try {
                                fxml = FXMLLoader.load(getClass().getResource("/fxml/homemanager.fxml"));
                                Scene scene = new Scene(fxml);
                                home.setScene(scene);
                                home.show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            if (met.equals("admin")) {
                                pane.getScene().getWindow().hide();
                                Stage home = new Stage();
                                try {
                                    fxml = FXMLLoader.load(getClass().getResource("/fxml/homeadmin.fxml"));
                                    Scene scene = new Scene(fxml);
                                    home.setScene(scene);
                                    home.show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                } else {
                    Alert alert = new Alert(AlertType.ERROR, "le nom ou le mot de passe incorrect!", ButtonType.OK);
                    alert.showAndWait();
                }

            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        metier.getItems().addAll(choix);
        cnx = ConnexionMlysql.connexionBD();
    }

}