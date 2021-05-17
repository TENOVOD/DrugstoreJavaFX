package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AdminPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addNewProduct;

    @FXML
    private Button addQuantity;

    @FXML
    private Button exit;

    @FXML
    private Label welcomeLabel;

    @FXML
    private Button showOrdersOrClients;

    @FXML
    void initialize() {
    styleBtnSet(addNewProduct);
    styleBtnSet(exit);
    styleBtnSet(showOrdersOrClients);
    styleBtnSet(addQuantity);
    exit.setOnAction(event -> setNewScene("main.fxml"));
    addNewProduct.setOnAction(event -> setNewScene("AddProduct.fxml"));
    addQuantity.setOnAction(event -> setNewScene("UpdateProduct.fxml"));
    showOrdersOrClients.setOnAction(event -> setNewScene("ShowOrdOrClients.fxml"));
    }
    public void setNewScene(String nameFx) {
        Stage str = (Stage) exit.getScene().getWindow();
        str.hide();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(nameFx));
            str.setTitle("Drugstore");
            Scene scene = new Scene(root);
            str.setScene(scene);
            str.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void styleBtnSet(Button btn) {
        btn.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btn.setStyle("-fx-background-color: ff6347; -fx-background-radius: 30px");
            }
        });
        btn.setOnMouseExited(event -> btn.setStyle("-fx-background-color: ffffff"));
    }
}
