package engine;

import java.util.ArrayList;

import entity.users.details.Cart;
import entity.users.details.CartItem;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class CartController extends BaseController{
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







    @FXML
    public void handleLogout(){
        engine.setCurrentUser(null);
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
        ArrayList<CartItem> cartItems=engine.getCurrentCustomer().getCart().getCartItems();
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
        Spinner<Integer>[] quantitySpinners = new Spinner[]{quantitySpinner1, 
        quantitySpinner2, quantitySpinner3};
        Button[] removeButtons={removeButton1,removeButton2,removeButton3};
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
            quantitySpinners[i].setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, cartItem.getQuantity()));
            subTotalLabels[i].setText("$"+cartItem.getSubTotal());
            cartItemHBoxes[i].setVisible(true);

            final int index=i;
            removeButtons[i].setOnAction(event -> removeCartItem(index));
        }

        for (int j=i; j<3; j++)
            cartItemHBoxes[j].setVisible(false);

            navigateCartLeft.setVisible(startingIndex>0);
            navigateCartRight.setVisible(startingIndex+3<cartItems.size());
    }
    public void displayEmptyCartMessage(){
        displayClearCart();
        emptyCartText.setText("");
        emptyCartText.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");  
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(emptyCartText);
        stackPane.setAlignment(Pos.CENTER);
        root.getChildren().setAll(stackPane);
    }   
    public void displayClearCart(){
        cartItemHBox1.setVisible(false);
        cartItemHBox2.setVisible(false);
        cartItemHBox3.setVisible(false);
    }
    public void removeCartItem(int i){
        switch(i){
            case 0:
                engine.removeFromCart(0);
                break;
            case 1:
                engine.removeFromCart(1);
                break;
            case 2:
                engine.removeFromCart(2);
                break;
        }
        populateCart(cartItemCount);
        // updateOrderTotal();
    }
    public Label getOrderSuccessLabel(){
        return orderSuccessLabel;
    }
}