package sample;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

public class CreateOrder {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button viewProductList;

    @FXML
    private Button exit;

    @FXML
    private TextField address;

    @FXML
    private TextField id;

    @FXML
    private Button viewProductList1;

    @FXML
    private Label prod1;

    @FXML
    private Label prod2;

    @FXML
    private Label prod3;

    @FXML
    private Label prod4;

    @FXML
    private Label prod5;

    @FXML
    private Button delProd1;

    @FXML
    private Button delProd2;

    @FXML
    private Button delProd3;

    @FXML
    private Button delProd4;

    @FXML
    private Button delProd5;

    @FXML
    private Label sum;

    @FXML
    private Label maxProducts;

    @FXML
    private Button orderBtn;

    @FXML
    void initialize() {
        styleBtnSet(exit);
        styleBtnSet(delProd1);
        styleBtnSet(delProd2);
        styleBtnSet(delProd3);
        styleBtnSet(delProd4);
        styleBtnSet(delProd5);
        styleBtnSet(viewProductList);
        styleBtnSet(viewProductList1);
        styleBtnSet(orderBtn);
        exit.setOnAction(event -> setNewScene("ClientWelcomePage.fxml"));
        newOrder();
        showProductionList();

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

    public void newOrder() {
        CLDAO_Production cldao_production = new CLDAO_Production();
        ArrayList<Production> productions = new ArrayList<>(cldao_production.findAll());
        viewProductList1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int checkId = Integer.parseInt(id.getText());
                Production production = new Production();
                for (int i = 0; i < productions.size(); i++) {
                    production = productions.get(i);
                    if (production.getNum() == checkId){ //перевіряємо чи дорівнює введене id товару  до товару на складі, якщо так, то виконується наступне
                        if (counter < 5) {
                            counter++; //лічильник +1 так як додали товар до кошика
                            SUM += production.getPrice();  //до загальної суми додаємо вартість обраного товару
                            production.setQuantity(production.getQuantity()-1); //забираємо 1 товар зі складу до кошика
                            cldao_production.update(production);
                            sum.setText(String.valueOf(SUM));
                            maxProducts.setVisible(false);
                            //якщо це перший товар в кошику, то виконається 1 кейс і т.д.
                            switch (counter) {
                                case 1: {
                                    prod1.setVisible(true); //напис стає видимим
                                    delProd1.setVisible(true); //кнопка стає видима
                                    prod1.setText(production.getName()); //на напис встановлюється назва продукту який було додано
                                    first = production.getPrice();
                                    num1=production.getNum();
                                }
                                break;
                                case 2: {
                                    prod2.setVisible(true);
                                    prod2.setText(production.getName());
                                    delProd2.setVisible(true);
                                    second = production.getPrice();
                                    num2=production.getNum();
                                }
                                break;
                                case 3: {
                                    prod3.setVisible(true);
                                    delProd3.setVisible(true);
                                    prod3.setText(production.getName());
                                    third = production.getPrice();
                                    num3=production.getNum();
                                }
                                break;
                                case 4: {
                                    prod4.setVisible(true);
                                    delProd4.setVisible(true);
                                    prod4.setText(production.getName());
                                    forth = production.getPrice();
                                    num4=production.getNum();
                                }
                                break;
                                case 5: {
                                    prod5.setVisible(true);
                                    delProd5.setVisible(true);
                                    prod5.setText(production.getName());
                                    fifth = production.getPrice();
                                    num5=production.getNum();
                                }
                                break;
                            }
                        }else {
                            maxProducts.setVisible(true);
                        }
                    }
                }
            }

        });
        id.clear();
        //при натискані на певну кнопку видаляється назва товару та мінусується ціна
        delProd1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                prod1.setVisible(false);
                SUM -= first; //від всієї суми мінусується вартість першого товару
                delProd1.setVisible(false);
                sum.setText(String.valueOf(SUM));
                counter--; //лічильник кількості товарів -1
                production=cldao_production.find(num1).get(0);//у об'єкт додається товар, який ми видаляємо
                production.setQuantity((production.getQuantity()+1));//та додаємо до кількості товару +1 так як ми видяляємо його з кошика
                cldao_production.update(production);//оновлюємо кількість продукції
            }
        });
        delProd2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                prod2.setVisible(false);
                SUM -= second;
                delProd2.setVisible(false);
                sum.setText(String.valueOf(SUM));
                counter--;
                production=cldao_production.find(num2).get(0);
                production.setQuantity((production.getQuantity()+1));
                cldao_production.update(production);
            }
        });
        delProd3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                prod3.setVisible(false);
                SUM -= third;
                delProd3.setVisible(false);
                sum.setText(String.valueOf(SUM));
                counter--;
                production=cldao_production.find(num3).get(0);
                production.setQuantity((production.getQuantity()+1));
                cldao_production.update(production);
            }
        });
        delProd4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                prod4.setVisible(false);
                SUM -= forth;
                delProd4.setVisible(false);
                sum.setText(String.valueOf(SUM));
                counter--;
                production=cldao_production.find(num4).get(0);
                production.setQuantity((production.getQuantity()+1));
                cldao_production.update(production);
            }
        });
        delProd5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                prod5.setVisible(false);
                SUM -= fifth;
                delProd5.setVisible(false);
                sum.setText(String.valueOf(SUM));
                counter--;
                production=cldao_production.find(num5).get(0);
                production.setQuantity((production.getQuantity()+1));
                cldao_production.update(production);
            }
        });
        orderBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CLDAO_Order cldao_order=new CLDAO_Order();
                CLDAO_Id cldao_id=new CLDAO_Id();
                ArrayList<SaveID> saveIDS=new ArrayList<>(cldao_id.find());
                String time = new SimpleDateFormat("yyyy.MM.dd_HH:mm").format(Calendar.getInstance().getTime());
                String prodName=(prod1.getText()+" "+prod2.getText()+" "+prod3.getText()+" "+prod4.getText()+" "+prod5.getText());
                Order order=new Order(time,SUM,prodName,address.getText(),saveIDS.get(0).getId());
                cldao_order.create(order);
                clear();
                SUM=0;
                sum.setText(String.valueOf(SUM));
            }
        });
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
    public void clear(){
        address.clear();
        counter=0;
        prod1.setVisible(false);
        prod2.setVisible(false);
        prod3.setVisible(false);
        prod4.setVisible(false);
        prod5.setVisible(false);
        delProd1.setVisible(false);
        delProd2.setVisible(false);
        delProd3.setVisible(false);
        delProd4.setVisible(false);
        delProd5.setVisible(false);
        id.clear();
        maxProducts.setVisible(false);

    }
    Production production=new Production();
    double SUM = 0;
    int counter = 0;
    double first;
    double second;
    double third;
    double forth;
    double fifth;
    int firstQ;
    int secondQ;
    int thirdQ;
    int forthQ;
    int fifthQ;
    int num1;
    int num2;
    int num3;
    int num4;
    int num5;


}
