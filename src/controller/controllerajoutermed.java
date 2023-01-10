package controller;

import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaException;

public class controllerajoutermed implements Initializable {

    @FXML
    private DatePicker text_dateexp;

    @FXML
    private DatePicker text_datefab;

    @FXML
    private TextField text_prix;

    @FXML
    private TextField txt_dosage;

    @FXML
    private TextField txt_nom;

    @FXML
    private TextField txt_num;

    @FXML
    private ComboBox<String> txt_type;
    private String[] choix = { "", "", "" };

    @FXML
    void enregistermed(MouseEvent event) {
     Integer numero=txt_num.getText();
     String nommed=txt_nom.getText();
     String dosagemed=txt_dosage.getText();
     String typemed=txt_type.getValue();
    
    float prix =text_prix.getText();

     String sql="INSERT INTO medicament(numero,nom,type,dosage,dateexpiration,datefabrication,prix) VALUES(?,?,?,?,?,?,?)";
     try{
        st=conx.prepareStatement(sql);
        st.setString(1,numero);
        st.setString(2,nommed);
        st.setString(3,typemed);
        st.setString(4,dosagemed);

        java.util.Date dateexp=java.util.Date.from(DatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date sqldateexp =new Date(dateexp.getTime());
        st.setString(5,sqldateexp);
        java.util.Date datefab=java.util.Date.from(DatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date sqldatefab =new Date(datefab.getTime());
        st.setString(6,sqldatefab);
        st.setString(7,prix);
        st.execute();
        txt_num.setText("");
        txt_nom.setText("");
        dosagemed.setText("");
        prix.setText("");
        text_dateexp.setValue(null);
        text_datefab.setValue(null);
        Alert alert =new Alert(AlertType.CONFIRMATION,"medicament ajouter avec succés!",javafx.scene.control.ButtonType.OK);
        alert.showAndWait();
        showListemedadmin();
     }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

    }

}}