package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ShowOrdOrClients {


    @FXML
    private Button exit;

    @FXML
    private TableView<Order> ordersTable;

    @FXML
    private TableColumn<Order, String> date;

    @FXML
    private TableColumn<Order, Integer> number;

    @FXML
    private TableColumn<Order, Double> sum;

    @FXML
    private TableColumn<Order, String> nameProducts;

    @FXML
    private TableColumn<Order, String> address;

    @FXML
    private TableColumn<Order, Integer> idClient;

    @FXML
    private Label orderLabel;

    @FXML
    private RadioButton ordersRadBtn;

    @FXML
    private RadioButton clientsRadBtn;

    @FXML
    private TableView<Client> clientTable;

    @FXML
    private TableColumn<Client, Integer> id;

    @FXML
    private TableColumn<Client, String> name;

    @FXML
    private TableColumn<Client, String> surname;

    @FXML
    private TableColumn<Client, String> middleName;

    @FXML
    private TableColumn<Client, Integer> phoneNumber;

    @FXML
    private TableColumn<Client, String> address1;

    @FXML
    void initialize() {
        styleBtnSet(exit);
        exit.setOnAction(event -> setNewScene("AdminPage.fxml"));
        showRadioBtn();


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
    public void showRadioBtn() {
        ToggleGroup group=new ToggleGroup();
        ordersRadBtn.setToggleGroup(group);
        clientsRadBtn.setToggleGroup(group);
        ordersRadBtn.setOnAction(event -> showOrderTable());
        clientsRadBtn.setOnAction(event -> showClientTable());
    }
    public void showOrderTable() {
        CLDAO_Order cldao_order = new CLDAO_Order();
        ObservableList<Order> orders= FXCollections.observableArrayList(cldao_order.findAll());
        date.setCellValueFactory(new PropertyValueFactory<Order, String>("date"));
        number.setCellValueFactory(new PropertyValueFactory<Order, Integer>("number"));
        sum.setCellValueFactory(new PropertyValueFactory<Order, Double>("sum"));
        nameProducts.setCellValueFactory(new PropertyValueFactory<Order, String>("nameProducts"));
        address.setCellValueFactory(new PropertyValueFactory<Order, String>("address"));
        idClient.setCellValueFactory(new PropertyValueFactory<Order,Integer>("idClient"));
        ordersTable.setItems(orders);
        ordersTable.setVisible(true);
        clientTable.setVisible(false);
        orderLabel.setText("Orders");
    }
    public void showClientTable() {
        CLDAO_Client cldao_client=new CLDAO_Client();
        ObservableList<Client> clients=FXCollections.observableArrayList(cldao_client.findAll());
        id.setCellValueFactory(new PropertyValueFactory<Client,Integer>("id"));
        surname.setCellValueFactory(new PropertyValueFactory<Client,String>("surname"));
        name.setCellValueFactory(new PropertyValueFactory<Client,String>("name"));
        middleName.setCellValueFactory(new PropertyValueFactory<Client,String>("middleName"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<Client,Integer>("phoneNumber"));
        address1.setCellValueFactory(new PropertyValueFactory<Client,String>("address"));
        clientTable.setItems(clients);
        clientTable.setVisible(true);
        ordersTable.setVisible(false);
        orderLabel.setText("Clients");
    }


}
