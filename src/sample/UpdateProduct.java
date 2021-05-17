package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
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

public class UpdateProduct {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exit;

    @FXML
    private Label welcomeLabel;

    @FXML
    private Button update;

    @FXML
    private Label welcomeLabel1;

    @FXML
    private TextField id;

    @FXML
    private Button productList;

    @FXML
    private Button search;

    @FXML
    private Label welcomeLabel2;

    @FXML
    private Label name;

    @FXML
    private TextField quantity;

    @FXML
    private Label welcomeLabel211;

    @FXML
    void initialize() {
        styleBtnSet(exit);
        styleBtnSet(productList);
        styleBtnSet(update);
        styleBtnSet(search);
        exit.setOnAction(event -> setNewScene("AdminPage.fxml"));
        showProductionList();
        searchBtn();
        updateQuantity();

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
    public void showProductionList() {
        productList.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage primaryStage = new Stage();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("ProducList.fxml"));
                    primaryStage.setTitle("Drugstore");
                    primaryStage.setScene(new Scene(root));
                    primaryStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
    public void searchBtn() {
        ArrayList<Production> productions = new ArrayList<>(cldao_production.findAll());
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int checkId = Integer.valueOf(id.getText());
                Production production = new Production();
                for (int i = 0; i < productions.size(); i++) {
                    production = productions.get(i);
                    if (production.getNum() == checkId) {
                        productions1.add(production);
                        name.setText(production.getName());
                        name.setVisible(true);
                        quantity.setText(String.valueOf(production.getQuantity()));
                        /*
                        production.setQuantity(Integer.valueOf(quantity.getText()));
                        cldao_production.update(production);*/
                    }
                }
            }
        });
    }
    CLDAO_Production cldao_production = new CLDAO_Production();
    ArrayList<Production> productions1=new ArrayList<>();
    public void updateQuantity() {
        update.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                productions1.get(0).setQuantity(Integer.valueOf(quantity.getText()));
                cldao_production.update(productions1.get(0));
                quantity.clear();
                name.setVisible(false);
            }
        });
    }
}
