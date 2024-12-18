package engine;

import java.util.ArrayList;

import entity.users.details.Order;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class OrdersController extends BaseController {

int orderCount;
public void populateOrders(int startingIndex){
    ArrayList<Order> orders=engine.getOrders();

    if(startingIndex==-1) startingIndex=orderCount;
    int orderSize=orders.size();
    Label[] dateLabels={dateLabel1,dateLabel2,dateLabel3};
    Label[] statusLabels={statusLabel1,statusLabel2,statusLabel3};
    Label[] totalLabels={totalLabel1,totalLabel2,totalLabel3};
    Label[] addressLabels={addressLabel1,addressLabel2,addressLabel3};
    Label[] paymentLabels={paymentLabel1,paymentLabel2,paymentLabel3};
    HBox[] orderBoxes={orderBox1,orderBox2,orderBox3};
    int i;
    for(i=0;i<3&&startingIndex+i<orderSize;i++){
        Order order=orders.get(startingIndex+i);
        dateLabels[i].setText(order.getOrderDate());
        statusLabels[i].setText(order.getOrderStatus().toString());
        totalLabels[i].setText("$"+String.valueOf(order.getOrderTotal()));
        addressLabels[i].setText(order.getShippingAddress());
        paymentLabels[i].setText(order.getPaymentMethod());
        orderBoxes[i].setVisible(true);
        final int index=startingIndex+i;
        orderBoxes[i].setOnMouseClicked(event -> {
            engine.setViewedOrder(orders.get(index));
            engine.switchScene(Screen.ORDER_SUMMARY);
        });
    }
    for (int j=i; j<3; j++)
        orderBoxes[j].setVisible(false);

    navOrdersLeftButton.setVisible(startingIndex>0);
    navOrdersRightButton.setVisible(startingIndex+3<orders.size());
}
@FXML
public void navigateOrdersLeft(){
    if(orderCount>=3){
        orderCount-=3;
        populateOrders(orderCount);
    }
}
@FXML
public void navigateOrdersRight(){
    ArrayList<Order> orders=engine.getOrders();
    if(orders.size()>orderCount+3){
        orderCount+=3;
        populateOrders(orderCount);
    }
}
@FXML
public void handleBack(){
    engine.switchScene(Screen.HOME);
}
@FXML
private Button backButton;
@FXML
private HBox orderBox1;
@FXML
private HBox orderBox2;
@FXML
private HBox orderBox3;
@FXML
private Label dateLabel1;
@FXML
private Label dateLabel2;
@FXML
private Label dateLabel3;
@FXML
private Label statusLabel1;
@FXML
private Label statusLabel2;
@FXML
private Label statusLabel3;
@FXML
private Label totalLabel1;
@FXML
private Label totalLabel2;
@FXML
private Label totalLabel3;
@FXML
private Label addressLabel1;
@FXML
private Label addressLabel2;
@FXML
private Label addressLabel3;
@FXML
private Label paymentLabel1;
@FXML
private Label paymentLabel2;
@FXML
private Label paymentLabel3;
@FXML
private Button navOrdersLeftButton;
@FXML
private Button navOrdersRightButton;
}
