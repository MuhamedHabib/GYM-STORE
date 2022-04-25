package Controllor;

import Entity.Product;
import Helpers.DbConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
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

public class AddController implements Initializable {

   // public Button fileChooser;
    public AnchorPane anchorpane;

    public TextField fprice;
    public Button clean;
    @FXML
    private TextField ftiltlefield;
    @FXML
    private TextField categfield;

    String imagePath = null;



    @FXML
    void CleanB(MouseEvent event) {

    }

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Product f = null;
    private boolean update;
    int ProduitId;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



    public void AddB(javafx.scene.input.MouseEvent mouseEvent) {



        connection = DbConnect.getConnect();
        if (validatePrice()&&validatetitle()){
        String title = ftiltlefield.getText();

        String price = fprice.getText();
        String categoryid = categfield.getText();

        if (title.isEmpty() || price.isEmpty() || categoryid.isEmpty()||imagePath== null ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
            getQuery();
            insert();
            //CleanB();

        }}
    }


    private void getQuery() {



        if (update == false) {

            query = "INSERT INTO `products`( `title`, `image`, `price`, `category_id`) VALUES (?,?,?,?)";

        }else{
            query = "UPDATE `products` SET "
                    + "`title`=?,"
                    + "`image`=?,"
                    + "`price`= ?,"
                    +"`category_id`= ? WHERE id = '"+ ProduitId +"'";
        }

    }

    private void insert() {

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ftiltlefield.getText());
            preparedStatement.setString(2, imagePath);
            preparedStatement.setString(3, fprice.getText());
            preparedStatement.setString(4, categfield.getText());



            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AddController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

  

   /* public void CleanB() {
        fnamefield.setText(null);
        descrF.setText(null);
        dateF.setValue(null);
    }*/

    void setUpdate(boolean b) {
        this.update = b;

    }

    public String handleButtonAction(ActionEvent event) throws IOException {



        FileChooser fc = new FileChooser();


        fc.setInitialDirectory(new java.io.File("C:\\Users\\DELL\\Downloads\\spring\\web_project\\SpringBoot4DS2\\metafitness\\src\\resources"));
      //  fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("pdf Files", "*.pdf"));
      //  fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("text Files", "*.txt"));
    //    fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("video Files", "*.mp4"));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("pdf Files", "*.png"));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("pdf Files", "*.jpg"));



        java.io.File f = fc.showOpenDialog(null);
        if(f != null)
        {
            System.out.println(f);
        }
        imagePath=f.getPath();
        imagePath =imagePath.replace("\\","\\\\");
        return f.getName();


    }
    void setTextField(int id_file, String id, String file, double price, int myfile) {

        ProduitId = id_file;
        ftiltlefield.setText(id);
        fprice.setText(file);
        categfield.setText(String.valueOf(myfile));

    }

    public void CleanB(ActionEvent event) throws Exception {
        Parent root  = FXMLLoader.load(getClass().getResource("../gui/plateformeUI.fxml"));

        Stage window =(Stage) clean.getScene().getWindow();
        window.setScene(new Scene(root, 1500, 1700));
    }

    private boolean validatetitle() {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(ftiltlefield.getText());
        if (m.find() && m.group().equals(ftiltlefield.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Name hotel");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid Product title");
            alert.showAndWait();

            return false;
        }
    }

    private boolean validatePrice(){
        Pattern p = Pattern.compile("[1-9][0-9][0-9]+");
        Matcher m = p.matcher(fprice.getText());
        if(m.find() && m.group().equals(fprice.getText())){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate price");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid price");
            alert.showAndWait();

            return false;
        }

    }

}


