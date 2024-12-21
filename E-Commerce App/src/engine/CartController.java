package engine;

import java.util.ArrayList;
import java.util.List;

import entity.products.Product;
import entity.users.details.CartItem;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CartController extends BaseController{
    @FXML
    public void handleLogout(){
        orderSuccessLabel.setVisible(false);
        engine.logout();
        engine.switchScene(Screen.LOGIN);
    }
    @FXML
    public void handleBack(){
        orderSuccessLabel.setVisible(false);
        engine.switchScene(Screen.HOME);
    } 
    int cartItemCount;
    @FXML
    public void navigateCartRight(){
        ArrayList<CartItem> cartItems= engine.getCartItems();
        if(cartItems.size()>cartItemCount+3){
            cartItemCount+=3;
            populateCart(cartItemCount);
        }
    }
    @FXML
    public void navigateCartLeft(){    
        if(cartItemCount>=3){
            cartItemCount-=3;
            populateCart(cartItemCount);
        }
    }
    @FXML
    public void placeOrder(){
            if(engine.isEmptyCart()){
                displayErrorMessage("Your cart is empty");
                return;
            }


            String paymentMethod=paymentMethodCB.getValue();
            String address=addressField.getText();
            for(CartItem c: engine.getCurrentCustomer().getCart().getCartItems())
            if (c.getQuantity() > c.getProduct().getStockQuantity()) {
                displayErrorMessage("Insufficient stock to fulfill the order");
                return;
            }//just in case
            //TODO needs more testing
            if (address == null || address.isEmpty()) {
                displayErrorMessage("Please enter an address");
                return;
            }
            if (paymentMethod == null || paymentMethod.isEmpty()) {
                displayErrorMessage("Please select a payment method");
                return;
            }
            if(paymentMethod.equals("Wallet")){
                if(!engine.validWalletPayment())
                    displayErrorMessage("Insufficient balance in wallet");
                
                else{ engine.orderByWallet(address);
                        displaySuccessMessage();
                    }
            }
            else { 
                engine.orderByCOD(address);
                displaySuccessMessage();
                engine.getOrders().get(0).toString();
            }
        walletLabel.setText("$" + String.format("%.2f",engine.getCurrentCustomer().getWallet()));
            //same for card for now
    }
    @FXML
    public void removeCartItem1(){
        engine.removeFromCart(cartItemCount);
        populateCart(cartItemCount);
        updateOrderTotal(); 
    }
    @FXML
    public void removeCartItem2(){
        engine.removeFromCart(cartItemCount+1);
        populateCart(cartItemCount);
        updateOrderTotal();
    }
    @FXML
    public void removeCartItem3(){
        engine.removeFromCart(cartItemCount+2);
        populateCart(cartItemCount);
        updateOrderTotal();
    }
    public void displayErrorMessage(String s){
        orderSuccessLabel.setVisible(true);
        orderSuccessLabel.getStyleClass().clear();
        orderSuccessLabel.getStyleClass().add("errorLabel");
        orderSuccessLabel.setText(s);
    }
    public void displaySuccessMessage(){
        orderSuccessLabel.setVisible(true);
        orderSuccessLabel.getStyleClass().clear();
        orderSuccessLabel.getStyleClass().add("successful-add");
        orderSuccessLabel.setText("Order placed successfully!");
    }
    public void populateCart(int startingIndex){
        walletLabel.setText("$" + String.format("%.2f",engine.getCurrentCustomer().getWallet()));

        ArrayList<CartItem> cartItems= engine.getCartItems();
        emptyCartText.setVisible(false);
        

        Label[] nameLabels={nameLabel1,nameLabel2,nameLabel3};
        Label[] brandLabels={brandLabel1,brandLabel2,brandLabel3};
        Label[] priceLabels={priceLabel1,priceLabel2,priceLabel3};
        Label[] subTotalLabels={subTotalLabel1,subTotalLabel2,subTotalLabel3};
        HBox[] cartItemHBoxes={cartItemHBox1,cartItemHBox2,cartItemHBox3};
        ImageView[] cartImages={cartImage1,cartImage2,cartImage3};
        List<Spinner<Integer>> quantitySpinners = new ArrayList<>();
        quantitySpinners.add(quantitySpinner1);
        quantitySpinners.add(quantitySpinner2);
        quantitySpinners.add(quantitySpinner3);
        int cartSize = cartItems.size();
        int i;
        for(i=0;i<3 && startingIndex+i < cartSize ;i++){
            CartItem cartItem=cartItems.get(startingIndex+i);
            
            String name=cartItem.getProduct().getProductName();
            String brand=cartItem.getProduct().getBrand();
            ImageView cartImage=cartImages[i];
            String imagePath=cartItem.getProduct().getImagePath();
            if(imagePath!=null)
                cartImage.setImage(new Image(getClass().getResourceAsStream(imagePath)));
            else cartImage.setImage(new Image(getClass().getResourceAsStream("/resources/default.png")));

            nameLabels[i].setText(name);
            brandLabels[i].setText(brand);
            priceLabels[i].setText("$"+cartItem.getProduct().getPrice());

            Spinner<Integer> quantitySpinner=quantitySpinners.get(i);
            int stockQuantity= cartItem.getProduct().getStockQuantity();
            quantitySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, stockQuantity, cartItem.getQuantity()));
            quantitySpinner.setEditable(false);
            final int itemIndex = startingIndex+i; 
            quantitySpinner.valueProperty().addListener((obs, oldValue, newValue) -> {
                if (newValue!=null && oldValue!=newValue) {
                    engine.setQuantityInCart(itemIndex, newValue);
                    double newSubTotal = cartItem.getSubTotal();
                    subTotalLabels[itemIndex].setText("$" + String.format("%.2f", newSubTotal));
                    updateOrderTotal(); 
                }
            });
            subTotalLabels[i].setText("$"+cartItem.getSubTotal());
            cartItemHBoxes[i].setVisible(true);
       }

        for (int j=i; j<3; j++)
            cartItemHBoxes[j].setVisible(false); //i.e unused

            navigateCartLeft.setVisible(startingIndex>0);
            navigateCartRight.setVisible(startingIndex+3<cartItems.size());
    }
    public void updateOrderTotal(){
        double total=engine.getCartTotal();
        orderTotal.setText("$"+String.format("%.2f", total));
    }    public Label getOrderSuccessLabel(){
        return orderSuccessLabel;
    }
    public ChoiceBox<String> getPaymentChoiceBox(){
        return paymentMethodCB;
    }
    @FXML
    private Label nameLabel1;
    @FXML
    private Label nameLabel2;
    @FXML
    private Label nameLabel3;
    @FXML
    private Label brandLabel1;
    @FXML
    private Label brandLabel2;
    @FXML
    private Label brandLabel3;
    @FXML
    private Label priceLabel1;
    @FXML
    private Label priceLabel2;
    @FXML
    private Label priceLabel3;
    @FXML
    private Spinner<Integer>  quantitySpinner1;
    @FXML
    private Spinner<Integer>  quantitySpinner2;
    @FXML
    private Spinner<Integer>  quantitySpinner3;
    @FXML
    private Button removeButton1;
    @FXML
    private Button removeButton2;
    @FXML
    private Button removeButton3;
    @FXML
    private Label subTotalLabel1;
    @FXML
    private Label subTotalLabel2;
    @FXML
    private Label subTotalLabel3;
    @FXML
    private Label orderSuccessLabel;
    @FXML
    private TextField addressField;
    @FXML
    private ChoiceBox<String> paymentMethodCB; 
    @FXML 
    private Label orderTotal;
    @FXML
    private Button placeOrderButton;
    @FXML
    private Button backButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Label walletLabel;

    @FXML
    private VBox cartVBox;
    @FXML
    private HBox cartItemHBox1;
    @FXML
    private HBox cartItemHBox2;
    @FXML
    private HBox cartItemHBox3;
    @FXML 
    private Text emptyCartText;
    @FXML
    private ImageView cartImage1;
    @FXML
    private ImageView cartImage2;
    @FXML
    private ImageView cartImage3;
    @FXML
    private Button navigateCartLeft;
    @FXML
    private Button navigateCartRight;
    @FXML
    private AnchorPane root;
}