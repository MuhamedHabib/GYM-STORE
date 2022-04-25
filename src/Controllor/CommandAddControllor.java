package Controllor;

import Entity.command;
import Service.ServiceCommande;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CommandAddControllor implements Initializable {
    String imagePath = null;

    public TextField nom;
    public TextField ville;
    public TextField CodePostal;
    public TextField Adresse;
    public TextField codeP;
    public Button button;
    public TextField Lname;
    public TextField Mail;

    public void Add_users () throws SQLException {

        if(nom.getText().isEmpty()
                || Lname.getText().isEmpty()
                || Mail.getText().isEmpty()
                || ville.getText().isEmpty()
                || CodePostal.getText().isEmpty()
                || Adresse.getText().isEmpty()
                || imagePath== null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Insert Failed, information missing");
            alert.show();
        }
        else{
            command t = new command(null, nom.getText(),
                    Lname.getText(), Mail.getText(),
                    ville.getText(), CodePostal.getText(),
                    Adresse.getText(),imagePath);
            ServiceCommande st= new ServiceCommande();
            st.add(t);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Ajout succes");
            alert.show();
            UpdateTable();
            search_user();
        }
        /**********************************added*************************************/

    }


    private void search_user() {
    }

    private void UpdateTable() {
    }

    public void Edit () throws SQLException {

    }


    public void Delete() throws SQLException {

    }


    public void search_user(KeyEvent keyEvent) {
    }

    @FXML
    String Filechooser() {

        FileChooser fc = new FileChooser();

        fc.setInitialDirectory(new File("C:\\Users\\DELL\\Downloads\\spring\\web_project\\SpringBoot4DS2\\metafitness\\src\\resources"));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg"));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpeg"));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("video Files", "*.mp4"));

        File f = fc.showOpenDialog(null);
        if(f != null)
        {
            System.out.println(f);
        }
        imagePath=f.getPath();
        imagePath =imagePath.replace("\\","\\\\");
        return f.getName();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

