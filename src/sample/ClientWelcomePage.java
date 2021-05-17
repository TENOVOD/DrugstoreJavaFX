package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.Pane;

public class ClientWelcomePage {

    CLDAO_Client cldao_client = new CLDAO_Client();
    CLDAO_Id cldao_id = new CLDAO_Id();
    @FXML
    private Button makeAnOrder;

    @FXML
    private Button viewProductList;

    @FXML
    private Button exit;

    @FXML
    private Label welcomeLabel;

    @FXML
    private TableView<Order> table;

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
    void initialize() {
        styleBtnSet(exit);
        styleBtnSet(makeAnOrder);
        styleBtnSet(viewProductList);
        setNameToLabel();
        exit.setOnAction(event -> setNewScene("clientSi.fxml"));
        makeAnOrder.setOnAction(event -> setNewScene("CreateOrder.fxml"));
        viewProductList.setOnAction(event ->showProductionList());
        showTable();

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
        viewProductList.setOnAction(new EventHandler<ActionEvent>() {
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
    public void setNameToLabel() {
        ArrayList<SaveID> arrList = new ArrayList<>(cldao_id.find());
        ArrayList<Client> clientArrayList = new ArrayList<>(cldao_client.foundForId(arrList.get(0).getId()));
        welcomeLabel.setText(" " + clientArrayList.get(0).getName() + " " + clientArrayList.get(0).getSurname() + " " + clientArrayList.get(0).getMiddleName());

    }

    public void showTable() {
        CLDAO_Order cldao_order = new CLDAO_Order();
        ArrayList<Order> arrayList = new ArrayList<>(cldao_order.findAll());
        ArrayList<Order> redyForShow = new ArrayList<>();
        Order order = new Order();
        ArrayList<SaveID> arrList = new ArrayList<>(cldao_id.find());
        ArrayList<Client> clientArrayList = new ArrayList<>(cldao_client.foundForId(arrList.get(0).getId()));
        for (int i = 0; i < arrayList.size(); i++) {
            order = arrayList.get(i);
            if (order.getIdClient() == clientArrayList.get(0).getId()) {
                redyForShow.add(order);
            }
        }
        ObservableList<Order> show = FXCollections.observableArrayList(redyForShow);
        date.setCellValueFactory(new PropertyValueFactory<Order, String>("date"));
        number.setCellValueFactory(new PropertyValueFactory<Order, Integer>("number"));
        sum.setCellValueFactory(new PropertyValueFactory<Order, Double>("sum"));
        nameProducts.setCellValueFactory(new PropertyValueFactory<Order, String>("nameProducts"));
        address.setCellValueFactory(new PropertyValueFactory<Order, String>("address"));
        table.setItems(show);
    }


}
