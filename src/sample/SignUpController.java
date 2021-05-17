package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField surnameRFX;

    @FXML
    private TextField middleRFX;

    @FXML
    private TextField nameRFX;

    @FXML
    private Button signUpRBtn;

    @FXML
    private Button back;

    @FXML
    private TextField numberRFX;

    @FXML
    private TextField addressRFX;

    @FXML
    private TextField loginRFX;

    @FXML
    private TextField passwordRFX;

    @FXML
    private Label writeAnothLogFX;

    CLDAO_Client cldao_client= new CLDAO_Client();
    @FXML
    void initialize() {
    registration();
    back.setOnAction(event -> setNewScene("main.fxml"));
    styleBtnSet(signUpRBtn);
    styleBtnSet(back);

    }
    public void registration() {
        signUpRBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<Client> clients=new ArrayList<>(cldao_client.find(loginRFX.getText(),passwordRFX.getText()));
                if(clients.size()>0) {
                    loginRFX.clear();
                    passwordRFX.clear();
                    writeAnothLogFX.setVisible(true);
                }
                else {
                    writeAnothLogFX.setVisible(false);
                    Client client=new Client(nameRFX.getText(),surnameRFX.getText(),middleRFX.getText(),Integer.parseInt(numberRFX.getText()),addressRFX.getText(),loginRFX.getText(),passwordRFX.getText());
                    cldao_client.create(client);
                    clearField();
                }
            }
        });
    }

    public void clearField() {
        nameRFX.clear();
        surnameRFX.clear();
        middleRFX.clear();
        numberRFX.clear();
        addressRFX.clear();
        loginRFX.clear();
        passwordRFX.clear();
    }
    public  void setNewScene(String nameFx) {
        Stage str=(Stage) back.getScene().getWindow();
        str.hide();
        Parent root= null;
        try {
            root = FXMLLoader.load(getClass().getResource(nameFx));
            str.setTitle("Drugstore");
            Scene scene=new Scene(root);
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
        btn.setOnMouseExited(event-> btn.setStyle("-fx-background-color: ffffff"));
    }

}
