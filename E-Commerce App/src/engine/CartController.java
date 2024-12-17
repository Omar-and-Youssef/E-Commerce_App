package engine;

import java.util.ArrayList;
import java.util.List;

import entity.users.details.CartItem;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CartController extends BaseController{

    @FXML
    public void handleLogout(){
        engine.logout();
        engine.switchScene(Screen.LOGIN);
    }
    @FXML
    public void handleBack(){
        engine.switchScene(Screen.HOME);
    } 
    int cartItemCount;
    @FXML
    private void navigateCartRight(){
        ArrayList<CartItem> cartItems=engine.getCurrentCustomer().getCart().getCartItems();
        if(cartItems.size()>cartItemCount+3){
            cartItemCount+=3;
            populateCart(cartItemCount);
        }
    }
    @FXML
    private void navigateCartLeft(){    
        if(cartItemCount>=3){
            cartItemCount-=3;
            populateCart(cartItemCount);
        }
    }
    public void populateCart(int startingIndex){
        ArrayList<CartItem> cartItems= engine.getCurrentCustomer().getCart().getCartItems();
        if(cartItems.isEmpty()) {
            displayEmptyCartMessage();
            return;
        }
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
        Button[] removeButtons={removeButton1,removeButton2,removeButton3};
        int cartSize = cartItems.size();
        int i;

        // TextFormatter<Integer> textFormatter = new TextFormatter<>(change -> {
        //     String newText = change.getControlNewText();
        //     if (newText.matches("\\d*")) {
        //         try {
        //             int value=Integer.parseInt(newText);
        //             if (value>=1 && value <= 99) return change;
        //             if (value<1) change.setText("1");
        //             if (value>99) change.setText("99");
        //         } catch (NumberFormatException e) {}
        //     }
        //     return null; 
        // });
        
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
            quantitySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, cartItem.getQuantity()));
            final int index=i;
            quantitySpinner.setEditable(false);
            quantitySpinner.valueProperty().addListener((obs, oldValue, newValue) -> {
                if (newValue != null) {
                    // Update the quantity in the cart item
                    engine.setQuantityInCart(index, newValue);
            
                    // Update the subtotal label dynamically
                    double newSubTotal = cartItems.get(index).getSubTotal();
                    subTotalLabels[index].setText("$" + String.format("%.2f", newSubTotal));
            
                    // TODO updateOrderTotal();
                }
            });

            subTotalLabels[i].setText("$"+cartItem.getSubTotal());
            cartItemHBoxes[i].setVisible(true);

            removeButtons[i].setOnAction(event -> removeCartItem(index));
        }

        for (int j=i; j<3; j++)
            cartItemHBoxes[j].setVisible(false); //i.e unused

            navigateCartLeft.setVisible(startingIndex>0);
            navigateCartRight.setVisible(startingIndex+3<cartItems.size());
    }
    public void displayEmptyCartMessage(){
        displayClearCart();
        emptyCartText.setText("");
        emptyCartText.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");  
    }   
    public void displayClearCart(){
        cartItemHBox1.setVisible(false);
        cartItemHBox2.setVisible(false);
        cartItemHBox3.setVisible(false);
    }
    public void removeCartItem(int i){
        switch(i){ //ensuring that cartItemCount will always be <i
            case 0:
                engine.removeFromCart(i);
                populateCart(cartItemCount);
                break;
            case 1:
                engine.removeFromCart(i);
                populateCart(cartItemCount);
                break;
            case 2:
                engine.removeFromCart(i+1);
                populateCart(cartItemCount);
                break;
        }
        populateCart(cartItemCount);
        // updateOrderTotal();
    }
    public Label getOrderSuccessLabel(){
        return orderSuccessLabel;
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
    private ChoiceBox<String> paymemtChoiceBox;
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