package engine;

import entity.users.details.Order;
import entity.users.details.OrderStatus;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    @FXML
    private Label customerLabel;

    public void setOrder(Order order){
        this.order=order;
    }
    @FXML
    private Button backButton;
    @FXML
    private Button cancelOrderButton;

    @FXML
    public void handleCancelOrder(ActionEvent e){
        engine.cancelOrder();
        engine.switchScene(Screen.ORDERS);
    }
    @FXML
    public void handleBack(ActionEvent e){
        engine.switchScene(Screen.ORDERS);
    }
    public void fillOrderSummary(){
        customerLabel.setText("#"+order.getCustomer().getUserID());
        orderIDLabel.setText("#"+order.getOrderID());
        dateLabel.setText(order.getOrderDate().toString());
        statusLabel.setText(order.getOrderStatus().toString());
        totalLabel.setText(String.valueOf(order.getOrderTotal()));
        addressLabel.setText(order.getShippingAddress());
        paymentLabel.setText(order.getPaymentMethod());
        orderedItems.setText(order.toString());

        if(!engine.isCustomer||order.getOrderStatus()==OrderStatus.CANCELLED) 
            cancelOrderButton.setVisible(false);
        else cancelOrderButton.setVisible(true);
    }
}
