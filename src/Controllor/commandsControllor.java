package Controllor;

import Entity.command;
import Service.ServiceCommande;
import Helpers.DbConnect;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class commandsControllor implements Initializable {
    public ImageView imageView;
    public Button user_button;
    String imagePath = null;

    private File file;
    private FileInputStream fin;
    public AnchorPane anchorpane;

    @FXML
    private TextField txt_fn;

    @FXML
    private TextField txt_ln;

    @FXML
    private TextField txt_mail;

    @FXML
    private TextField txt_ville;

    @FXML
    private TextField txt_id;

    @FXML
    private Button button;

    @FXML
    private TextField txt_postcode;

    @FXML
    private TextField txt_adresse;

    @FXML
    private TableView<command> table_commands;

    @FXML
    private TableColumn<command, Integer> col_id;

    @FXML
    private TableColumn<command, String> fname;

    @FXML
    private TableColumn<command, String> lname;

    @FXML
    private TableColumn<command, String> email;

    @FXML
    private TableColumn<command, String> ville;

    @FXML
    private TableColumn<command, String> codepostal;

    @FXML
    private TableColumn<command, String> adresse;

    @FXML
    private TableColumn<command, String> face;




    @FXML
    private TextField filterField;


    private Image image;

    @FXML
    private Button toaddfile;

    @FXML
    private Button showRelatedFiles;

    ObservableList<command> listM;
    ObservableList<command> dataList;




    int index = -1;

    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    private boolean validateprenom(){
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(txt_ln.getText());
        if(m.find() && m.group().equals(txt_ln.getText())){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate your first name");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid First Name");
            alert.showAndWait();

            return false;
        }
    }
    private boolean validatenom(){
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(txt_fn.getText());
        if(m.find() && m.group().equals(txt_fn.getText())){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate your last name");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid Last Name");
            alert.showAndWait();

            return false;
        }
    }

    private boolean Emailvalide(){
        Pattern p = Pattern.compile( "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$");
        Matcher m = p.matcher(txt_mail.getText());
        if(m.find() && m.group().equals(txt_mail.getText())){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate email");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid email");
            alert.showAndWait();

            return false;
        }
    }
    private boolean validatePostCode(){
        Pattern p = Pattern.compile("[1-9][1-9][0-9][0-9]");
        Matcher m = p.matcher(txt_postcode.getText());
        if(m.find() && m.group().equals(txt_postcode.getText())){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Post code");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid Post Code");
            alert.showAndWait();

            return false;
        }

    }
    public void Add_users () throws SQLException {

        if (validatenom()&&validateprenom()&&validatePostCode()&&Emailvalide()){

            if(txt_fn.getText().isEmpty()
                || txt_postcode.getText().isEmpty()
                || txt_adresse.getText().isEmpty()
                || txt_ln.getText().isEmpty()
                || txt_mail.getText().isEmpty()
                || txt_ville.getText().isEmpty()
                || imagePath== null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Insert Failed, information missing");
            alert.show();
        }
        else{
            command t = new command(null, txt_fn.getText(),
                    txt_postcode.getText(), txt_adresse.getText(),
                    txt_ln.getText(), txt_mail.getText(),
                    txt_ville.getText(),imagePath);
            ServiceCommande st= new ServiceCommande();
            st.add(t);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Ajout succes");
            alert.show();
            UpdateTable();
            search_user();
        }
        /**********************************added*************************************/

    }}

    //////// methode select users ///////
    @FXML
    void getSelected (MouseEvent event){
        index = table_commands.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }
        txt_id.setText(col_id.getCellData(index).toString());
        txt_fn.setText(fname.getCellData(index).toString());
        txt_ln.setText(lname.getCellData(index).toString());
        txt_mail.setText(email.getCellData(index).toString());
        txt_ville.setText(ville.getCellData(index).toString());
        txt_postcode.setText(codepostal.getCellData(index).toString());
        txt_adresse.setText(adresse.getCellData(index).toString());




        /**********************************/
        Image image = new Image("file:///"+ table_commands.getSelectionModel().getSelectedItem().getTelephone());
        imageView.setImage(image);
        imagePath= table_commands.getSelectionModel().getSelectedItem().getTelephone();

        /*********************************************/

    }

    public void Edit () throws SQLException {

        if(txt_fn.getText().isEmpty()
                || txt_postcode.getText().isEmpty()
                || txt_adresse.getText().isEmpty()
                || txt_ln.getText().isEmpty()
                || txt_mail.getText().isEmpty()
                || txt_ville.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Update Failed, information missing");
            alert.show();
        }
        else{
            command t = new command(table_commands.getSelectionModel().getSelectedItem().getIdcommande(),
                txt_fn.getText(),
                txt_postcode.getText(),
                txt_adresse.getText(),
                txt_ln.getText(),
                txt_mail.getText(),
                txt_ville.getText(),
                imagePath);
            ServiceCommande st= new ServiceCommande();
            st.update(t);
            UpdateTable();
            search_user();}
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Update succes");
        alert.show();

    }

    public void Delete() throws SQLException {

        ServiceCommande st= new ServiceCommande();
        st.delete((long) table_commands.getSelectionModel().getSelectedItem().getIdcommande());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Delete Success");
        alert.show();
        UpdateTable();
        search_user();
    }


    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<command,Integer>("iddemande"));
        fname.setCellValueFactory(new PropertyValueFactory<command,String>("nom_c"));
        lname.setCellValueFactory(new PropertyValueFactory<command,String>("prenom_c"));
        ville.setCellValueFactory(new PropertyValueFactory<command,String>("email"));
        codepostal.setCellValueFactory(new PropertyValueFactory<command,String>("ville"));
        adresse.setCellValueFactory(new PropertyValueFactory<command,String>("code_postale"));
        email.setCellValueFactory(new PropertyValueFactory<command,String>("adresse"));
        face.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        listM = DbConnect.getDatausers();
        table_commands.setItems(listM);
    }




    @FXML
    void search_user() {
        fname.setCellValueFactory(new PropertyValueFactory<command,String>("nom_c"));
        codepostal.setCellValueFactory(new PropertyValueFactory<command,String>("prenom_c"));
        adresse.setCellValueFactory(new PropertyValueFactory<command,String>("email"));
        lname.setCellValueFactory(new PropertyValueFactory<command,String>("ville"));
        email.setCellValueFactory(new PropertyValueFactory<command,String>("code_postale"));
        ville.setCellValueFactory(new PropertyValueFactory<command,String>("adresse"));

        dataList = DbConnect.getDatausers();
        table_commands.setItems(dataList);
        FilteredList<command> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getNom_c().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches username
                } else if (person.getPrenom_c().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }else if (person.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (person.getVille().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }else if (person.getCode_postale().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }
                else if (String.valueOf(person.getAdresse()).indexOf(lowerCaseFilter)!=-1)
                    return true;// Filter matches email

                else
                    return false; // Does not match.
            });
        });
        SortedList<command> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_commands.comparatorProperty());
        table_commands.setItems(sortedData);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UpdateTable();
        search_user();
        // Code Source in description
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


    public void handleButtonAction(ActionEvent event) throws IOException {

        Stage stage = (Stage)anchorpane.getScene().getWindow();

        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter ext1 = new FileChooser.ExtensionFilter("JPG files(*.jpg)","*.JPG");
        FileChooser.ExtensionFilter ext2 = new FileChooser.ExtensionFilter("PNG files(*.png)","*.PNG");
        fc.getExtensionFilters().addAll(ext1,ext2);
        File file = fc.showOpenDialog(stage);

        BufferedImage bf;
        try {
            bf = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bf, null);
            imageView.setImage(image);
            FileInputStream fin = new FileInputStream(file);
            int len = (int)file.length();

        } catch (IOException ex) {
            Logger.getLogger(commandsControllor.class.getName()).log(Level.SEVERE, null, ex);

        }


    }

    public void multipleFileChooserAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Multiple file dialog");
        Stage stage = (Stage)anchorpane.getScene().getWindow();

        java.util.List<File> list=fileChooser.showOpenMultipleDialog(stage);

        if(list != null)
        {
            for(File file: list)
            {
                Desktop desktop =Desktop.getDesktop();
                try {
                    desktop.open(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addAttachment(ActionEvent event ) throws Exception {
        Parent root  = FXMLLoader.load(getClass().getResource("../gui/ProductView.fxml"));

        Stage window =(Stage) toaddfile.getScene().getWindow();
        window.setScene(new Scene(root, 1064, 600));
    }

    public void showHandleBtn(ActionEvent event) throws Exception {
        Parent root  = FXMLLoader.load(getClass().getResource("../gui/plateformeUI.fxml"));

        Stage window =(Stage) showRelatedFiles.getScene().getWindow();
        window.setScene(new Scene(root, 1500, 1700));
    }




    /**************************************************************************************/

    /**************************************************************************************/
    /**************************************************************************************/

    /**************************************************************************************/




}
