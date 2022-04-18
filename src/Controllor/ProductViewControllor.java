package Controllor;

import Entity.Product;
import Helpers.DbConnect;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductViewControllor implements Initializable {

    @FXML
    private Button toaddfile;
    @FXML
    private Button back;
    @FXML
    private TableView<Product> TableProduct;

    @FXML
    private TableColumn<Product, String> id;//id_file

    @FXML
    private TableColumn<Product, String> title;//id

    @FXML
    private TableColumn<Product, String> image;//file blob(contenu)

    @FXML
    private TableColumn<Product, String> price;//date_creation

    @FXML
    private TableColumn<Product, String> categ_id;
    @FXML
    private TableColumn<Product, String> editCol;

    @FXML
    private Button addFileB;

    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Product formation = null ;
/********************//***********************/
    //  int index = -1;

/********************//***********************/

    ObservableList<Product> formationList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDate();

    }








    /***********************************************REFRECH*************************************************************/
    @FXML
    void refresh() {
        try {
            formationList.clear();

            query = "SELECT * FROM `products`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                formationList.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("image"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("category_id")

                ));
                TableProduct.setItems(formationList);



            }


        } catch (SQLException ex) {
            Logger.getLogger(ProductViewControllor.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    /***********************************************REFRECH*************************************************************/




    private void loadDate() {

        connection = DbConnect.getConnect();
        refresh();


        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        categ_id.setCellValueFactory(new PropertyValueFactory<>("category_id"));


        /*this*/ Callback<TableColumn<Product, String>, TableCell<Product, String>> cellFoctory = (TableColumn<Product, String> param) -> {
            // make cell containing buttons
            final TableCell<Product, String> cell = new TableCell<Product, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {

                            try {
                                formation = TableProduct.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `products` WHERE id  ="+formation.getId();
                                connection = DbConnect.getConnect();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                refresh();

                            } catch (SQLException ex) {
                                Logger.getLogger(ProductViewControllor.class.getName()).log(Level.SEVERE, null, ex);
                            }





                        });
                        editIcon.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {

                            /*file*/     formation = TableProduct.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/gui/add.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(ProductViewControllor.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            AddController addFileController = loader.getController();
                            addFileController.setUpdate(true);
                            addFileController.setTextField(formation.getId(),formation.getTitle(),formation.getImage(),formation.getPrice(),formation.getCategory_id());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();




                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };

        editCol.setCellFactory(cellFoctory);
        TableProduct.setItems(formationList);
    }
    /******************************************************************************************/
    public void close(javafx.scene.input.MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    /*********************************************************************************************/
//ci dessous pour faire le call ADD scene
    public void getAddView(javafx.scene.input.MouseEvent mouseEvent) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("../gui/add.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);

            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProductViewControllor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*********************************************************************************************************/
    public void print(javafx.scene.input.MouseEvent mouseEvent) {
    }

    public void addFile(ActionEvent event) {
    }

    public void backAction(ActionEvent event) throws Exception{
        Parent root  = FXMLLoader.load(getClass().getResource("../gui/commands.fxml"));

        Stage window =(Stage) back.getScene().getWindow();
        window.setScene(new Scene(root, 1200, 570));
    }

    public void addAttachment(ActionEvent event) throws Exception {
        Parent root  = FXMLLoader.load(getClass().getResource("../gui/commands.fxml"));

        Stage window =(Stage) toaddfile.getScene().getWindow();
        window.setScene(new Scene(root, 1200, 570));
    }
    /*************************************************************************************************/
  /*  public void addFile() throws Exception {
        Parent root  = FXMLLoader.load(getClass().getResource("../gui/interfaceFormation.fxml"));

        Stage window =(Stage) addFileB.getScene().getWindow();
        window.setScene(new Scene(root, 1500, 1300));
    }*/

/*******************************************************************************************************/


}