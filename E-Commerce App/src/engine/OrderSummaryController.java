package engine;

import entity.users.details.Order;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OrderSummaryController extends BaseController {
    private Order order;
    @FXML
    private Label orderIDLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label orderedItems;
    @FXML
    private Label addressLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private Label paymentLabel;
    @FXML
    private Label totalLabel;


    public void setOrder(Order order){
        this.order=order;
    }
    @FXML
    public void handleCancelOrder(){
        engine.cancelOrder();
        engine.switchScene(Screen.ORDERS);
    }
    @FXML
    public void handleBack(){
        engine.switchScene(Screen.ORDERS);
    }
    public void fillOrderSummary(){
        orderIDLabel.setText(String.valueOf(order.getOrderID()));
        dateLabel.setText(order.getOrderDate().toString());
        statusLabel.setText(order.getOrderStatus().toString());
        totalLabel.setText(String.valueOf(order.getOrderTotal()));
        addressLabel.setText(order.getShippingAddress());
        paymentLabel.setText(order.getPaymentMethod());
        orderedItems.setText(order.getOrderedItems().toString());
    }
}
