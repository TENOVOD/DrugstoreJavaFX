package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button adminLogBtn;

    @FXML
    private Button clientLogBtn;

    @FXML
    private Button guestLogBtn;

    @FXML
    private Button signUpLogBtn;

    @FXML
    void initialize() {
        styleBtnSet(adminLogBtn);
        styleBtnSet(guestLogBtn);
        styleBtnSet(signUpLogBtn);
        styleBtnSet(clientLogBtn);
       adminLogBtn.setOnAction(event -> setNewScene("adminSi.fxml"));
       clientLogBtn.setOnAction(event -> setNewScene("clientSi.fxml"));
       signUpLogBtn.setOnAction(event -> setNewScene("signUp.fxml"));
       guestLogBtn.setOnAction(event -> setNewScene("guestSiCntr.fxml"));
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
    public  void setNewScene(String nameFx) {
            Stage str=(Stage) clientLogBtn.getScene().getWindow();
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
}
