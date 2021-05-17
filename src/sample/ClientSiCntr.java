package sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

public class ClientSiCntr {

    @FXML
    private TextField loginClientTF;

    @FXML
    private TextField passClientTF;

    @FXML
    private Button signInClientBtn;

    @FXML
    private Button back;

    @FXML
    private Label incorrectFx;

    CLDAO_Client cldao_client = new CLDAO_Client();

    @FXML
    void initialize() {
        styleBtnSet(signInClientBtn);
        styleBtnSet(back);
        signInClientBtn.setOnAction(event -> signInBtn());
        back.setOnAction(event -> setNewScene("main.fxml"));

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
        Stage str = (Stage) back.getScene().getWindow();
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

    public void signInBtn() {

        ArrayList<Client> clients = new ArrayList<>(cldao_client.findAll());
        Client checkClient = new Client();
        for (int i = 0; i < clients.size(); i++) {
            checkClient = clients.get(i);
            if (loginClientTF.getText().equals(checkClient.getLogin()) && passClientTF.getText().equals(checkClient.getPassword())) {
                incorrectFx.setVisible(false);
                CLDAO_Id cldao_id = new CLDAO_Id();
                SaveID saveID = new SaveID(checkClient.getId());
                cldao_id.delete();
                cldao_id.create(saveID);
                setNewScene("ClientWelcomePage.fxml");
            } else {
                incorrectFx.setVisible(true);
            }
        }
        loginClientTF.clear();
        passClientTF.clear();
    }
}
