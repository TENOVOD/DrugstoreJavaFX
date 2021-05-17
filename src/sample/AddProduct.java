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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddProduct {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exit;

    @FXML
    private Label welcomeLabel;

    @FXML
    private Button add;

    @FXML
    private Label welcomeLabel1;

    @FXML
    private TextField name;

    @FXML
    private TextField price;

    @FXML
    private TextField quantity;

    @FXML
    private TextField manufactured;

    @FXML
    private TextField producingCountry;

    @FXML
    void initialize() {
       styleBtnSet(add);
       styleBtnSet(exit);
       exit.setOnAction(event -> setNewScene("AdminPage.fxml"));
       add.setOnAction(event -> addProd());
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
    public void addProd() {
        CLDAO_Production cldao_production=new CLDAO_Production();
        Production production=new Production(name.getText(),Double.valueOf(price.getText()),Integer.valueOf(quantity.getText()),manufactured.getText(),producingCountry.getText());
        cldao_production.create(production);
        name.clear();
        price.clear();
        manufactured.clear();
        quantity.clear();
        producingCountry.clear();
    }

}
