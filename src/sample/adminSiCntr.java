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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class adminSiCntr {

    @FXML
    private TextField loginAdmTF;

    @FXML
    private TextField passAdmTF;

    @FXML
    private Button signInAdminBtn;

    @FXML
    private Button back;

    @FXML
    void initialize() {
        styleBtnSet(back);
        styleBtnSet(signInAdminBtn);
        signInAdminBtn.setOnAction(event -> adminSignIn());
        back.setOnAction(event -> setNewScene("main.fxml"));
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
    public void adminSignIn() {
        CLDAO_Admin cldao_admin=new CLDAO_Admin();
        ArrayList<Admin> admins=new ArrayList<>(cldao_admin.find());
        Admin checkAdmin=new Admin();
        for(int i=0;i<admins.size();i++) {
            checkAdmin=admins.get(i);
            if(loginAdmTF.getText().equals(checkAdmin.getLogin())&&passAdmTF.getText().equals(checkAdmin.getPassword())) {
                setNewScene("AdminPage.fxml");
            }
            else {
                loginAdmTF.clear();
                passAdmTF.clear();
            }
        }
    }
}
