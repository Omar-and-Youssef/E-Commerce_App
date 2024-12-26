package engine;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AnalyticsController extends BaseController{
    @FXML
    private Label totalCustomers;
    @FXML
    private Label totalOrders;
    @FXML
    private Label totalRevenue;
    @FXML
    private Label totalProducts;
    @FXML
    private Label avgOrders;
    @FXML
    private Label bestProduct;

    @FXML
    private Button viewOrders;
    @FXML
    private Button viewCustomers;

    @FXML
    public void viewAllOrders(){
        engine.switchScene(Screen.ORDERS);
    }
    @FXML
    public void viewAllCustomers(){
        engine.switchScene(Screen.CUSTOMER_DB);
    }
    public void updateAnalytics(){
        if(engine==null) return;
        totalCustomers.setText(String.valueOf(engine.getTotalCustomers()));
            totalOrders.setText(String.valueOf(engine.getTotalOrders()));
            totalRevenue.setText("$"+String.format("%.2f",engine.getTotalRevenue()));
            totalProducts.setText(String.valueOf(engine.getTotalProducts()));
            avgOrders.setText(String.format("%.2f",engine.getAvgOrders()));
            bestProduct.setText(engine.getBestProduct()!=null?engine.getBestProduct().getProductName():"None");
    }
    @FXML
    public void handleBack(){
        engine.switchScene(Screen.HOME);
    }
}
