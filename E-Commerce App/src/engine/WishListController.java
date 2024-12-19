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
public class WishListController extends BaseController{
    @FXML
    public void handleBack(){
        engine.switchScene(Screen.HOME);
    } 
    public int wishListCount;
    @FXML
    public void navigateWishlistRight(){
        ArrayList<Product> cartItems=engine.getCurrentCustomer().getWishList().getWishList();
        if(cartItems.size()>wishListCount+3){
            wishListCount+=3;
            populateWishlist(wishListCount);
        }
    }
    @FXML
    public void navigateWishlistLeft(){    
        if(wishListCount>=3){
            wishListCount-=3;
            populateWishlist(wishListCount);
        }
    }
    @FXML
    public void removeWishItem1(){
        engine.getCurrentCustomer().removeFromWishList(wishListCount);
        populateWishlist(wishListCount);
    }
    @FXML
    public void removeWishItem2(){
        engine.getCurrentCustomer().removeFromWishList(wishListCount+1);
        populateWishlist(wishListCount);
    }
    @FXML
    public void removeWishItem3(){
        engine.getCurrentCustomer().removeFromWishList(wishListCount+2);
        populateWishlist(wishListCount);
    }
    public void populateWishlist(int startingIndex){
        ArrayList<Product> wishItems=engine.getCurrentCustomer().getWishList().getWishList();
     
        Label[] nameLabels={nameLabel1,nameLabel2,nameLabel3};
        Label[] brandLabels={brandLabel1,brandLabel2,brandLabel3};
        Label[] priceLabels={priceLabel1,priceLabel2,priceLabel3};
        HBox[] wishListItemHbox={wishListItemHbox1,wishListItemHbox2,wishListItemHbox3};
        ImageView[] wishImages={wishListImage1,wishListImage2,wishListImage3};
        int wishSize = wishItems.size();
        int i;

        
        for(i=0;i<3 && startingIndex+i < wishSize ;i++){
            Product productItem=wishItems.get(startingIndex+i);
            
            String name=productItem.getProductName();
            String brand=productItem.getBrand();
            ImageView wishImage=wishImages[i];
            String imagePath=productItem.getImagePath();
            if(imagePath!=null)
            wishImage.setImage(new Image(getClass().getResourceAsStream(imagePath)));
            else wishImage.setImage(new Image(getClass().getResourceAsStream("/resources/default.png")));

            nameLabels[i].setText(name);
            brandLabels[i].setText(brand);
            priceLabels[i].setText("$"+productItem.getPrice());

            wishListItemHbox[i].setVisible(true);
       }

        for (int j=i; j<3; j++)
            wishListItemHbox[j].setVisible(false); //i.e unused

            navigateWishLeft.setVisible(startingIndex>0);
            navigateWishRight.setVisible(startingIndex+3<wishItems.size());
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
    private Button removeButton1;
    @FXML
    private Button removeButton2;
    @FXML
    private Button removeButton3;
   
   
    @FXML
    private Button backButton;
   
    @FXML
    private VBox wishListVBox;
    @FXML
    private HBox wishListItemHbox1;
    @FXML
    private HBox wishListItemHbox2;
    @FXML
    private HBox wishListItemHbox3;
    @FXML
    private ImageView wishListImage1;
    @FXML
    private ImageView wishListImage2;
    @FXML
    private ImageView wishListImage3;
    @FXML
    private Button navigateWishLeft;
    @FXML
    private Button navigateWishRight;
}

