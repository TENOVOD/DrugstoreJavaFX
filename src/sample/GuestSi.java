package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class GuestSi {

    @FXML
    private Button exit;

    @FXML
    private Label orderLabel;

    @FXML
    private TableView<Production> table;

    @FXML
    private TableColumn<Production,Integer> num;

    @FXML
    private TableColumn<Production,String> name;

    @FXML
    private TableColumn<Production,Double> price;

    @FXML
    private TableColumn<Production,Integer> quantity;

    @FXML
    private TableColumn<Production,String> manufactured;

    @FXML
    private TableColumn<Production,String> producingCountry;
    @FXML
    private Label orderLabel1;

    @FXML
    private Button signUpBtn;

    @FXML
    private Button signInBtn;

    @FXML
    void initialize() {
        showTable();
        styleBtnSet(exit);
        styleBtnSet(signInBtn);
        styleBtnSet(signUpBtn);
        exit.setOnAction(event -> setNewScene("main.fxml"));
        signUpBtn.setOnAction(event -> setNewScene("signUp.fxml"));
        signInBtn.setOnAction(event -> setNewScene("clientSi.fxml"));
    }
    public  void showTable(){
        CLDAO_Production cldao_production=new CLDAO_Production();
        ObservableList<Production> productionsInfo= FXCollections.observableArrayList(cldao_production.findAll());
        num.setCellValueFactory(new PropertyValueFactory<Production,Integer>("num"));
        name.setCellValueFactory(new PropertyValueFactory<Production,String>("name"));
        price.setCellValueFactory(new PropertyValueFactory<Production,Double>("price"));
        quantity.setCellValueFactory(new PropertyValueFactory<Production,Integer>("quantity"));
        manufactured.setCellValueFactory(new PropertyValueFactory<Production,String>("manufactured"));
        producingCountry.setCellValueFactory(new PropertyValueFactory<Production,String>("producingCountry"));
        table.setItems(productionsInfo);
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
}

