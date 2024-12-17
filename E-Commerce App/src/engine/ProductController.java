package engine;

import entity.products.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ProductController extends BaseController {
    @FXML
    private Label productNameLabel;
    @FXML
    private Label brandLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label categoryLabel;  
    @FXML
    private Label descriptionLabel;
    @FXML
    private ImageView productImage;
    @FXML
    private Button backButton;
    @FXML
    private Button addToCartButton;
    @FXML
    private Button addToWishlistButton;
    @FXML
    private Spinner<Integer>  quantitySpinner;
    @FXML
    private Label cartSuccessLabel;
    @FXML
    private Label wishlistSuccessLabel;
    @FXML
    private ImageView ratingImage;

    




    public void resetQuantity(){
        SpinnerValueFactory<Integer> valueFactory = 
            new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 1);
        quantitySpinner.setValueFactory(valueFactory);
        quantitySpinner.setEditable(false);
        quantitySpinner.setPrefWidth(80);
    }
    // public Label getCartErrorLabel(){
    //     return cartErrorLabel;
    // }
    // public Label getWishlistErrorLabel(){
    //     return wishlistErrorLabel;
    // }
    public void initialize(){
        cartSuccessLabel.setVisible(false);
        wishlistSuccessLabel.setVisible(false);
        resetQuantity();
    }
    public void loadProduct(){
        if(engine ==null) return;

        Product viewedProduct=engine.getViewedProduct();

        if(viewedProduct==null) return;


        productNameLabel.setText(viewedProduct.getProductName());
        categoryLabel.setText(viewedProduct.getCategory().toString());
        brandLabel.setText(viewedProduct.getBrand());
        priceLabel.setText("$"+viewedProduct.getPrice());
        descriptionLabel.setText(viewedProduct.getDescription());

        String imagePath = viewedProduct.getImagePath();
        if(imagePath!=null)
            productImage.setImage(new Image(getClass().getResourceAsStream(imagePath)));
        else productImage.setImage(new Image(getClass().getResourceAsStream("resources/default.png")));
        productImage.setLayoutX(0);
        productImage.setLayoutY(0);
        productImage.setPickOnBounds(false);

        ratingImage.setImage(new Image(getClass().getResourceAsStream("resources/"+engine.getIntegerRating(viewedProduct)+"star.png")));
    }
    public void handleBack(){
        cartSuccessLabel.setVisible(false);
        engine.switchScene(Screen.HOME);
    }

    @FXML
    public void handleAddToCart(){
        int quantity = quantitySpinner.getValue();
        engine.addToCart(quantity);//the viewed product
        cartSuccessLabel.setVisible(true);
    }
    @FXML 
    private Label stockAvailability;//TODO handle after order is placed

    // public void handleAddToWishlist(){
    //     if(engine.addToWishlist(viewedProduct)){
    //         wishlistErrorLabel.setVisible(false);
    //     }
    //     else{
    //         wishlistErrorLabel.setVisible(true);
    //     }
    // }
//==================================Home methods===================================
    @FXML
    public void handleSearch(){
        engine.switchScene(Screen.HOME);
        engine.getHomeController().handleSearch();
    }
    @FXML
    public void handleLogout(){
        engine.setCurrentUser(null);
        engine.switchScene(Screen.LOGIN);
    }
    @FXML
    public void handleCategoryAll() {
        engine.switchScene(Screen.HOME);
        engine.getHomeController().handleCategoryAll();
    }
    @FXML
    public void handleCategoryElectronics() {
        engine.switchScene(Screen.HOME);
        engine.getHomeController().handleCategoryElectronics();
    }
    @FXML
    public void handleCategoryClothing() {
        engine.switchScene(Screen.HOME);
        engine.getHomeController().handleCategoryClothing();
    }
    @FXML
    public void handleCategoryFurniture() {
        engine.switchScene(Screen.HOME);
        engine.getHomeController().handleCategoryFurniture();
    }
    @FXML
    public void handleCategoryBooks() {
        engine.switchScene(Screen.HOME);
        engine.getHomeController().handleCategoryBooks();
    }
    @FXML 
    public void handleCategoryBeauty() {
        engine.switchScene(Screen.HOME);
        engine.getHomeController().handleCategoryBeauty();
    }
    @FXML
    public void handleCategoryToys() {
        engine.switchScene(Screen.HOME);
        engine.getHomeController().handleCategoryToys();
    }
    @FXML
    public void handleCategorySports() {
        engine.switchScene(Screen.HOME);
        engine.getHomeController().handleCategorySports();
    }
    @FXML
    public void handleCart(){
        engine.switchScene(Screen.CART);
    }
}


