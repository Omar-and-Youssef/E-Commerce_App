package engine;

import entity.products.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

public class ProductController extends BaseController {
    boolean isUpdating;
    @FXML
    private Label productNameLabel;
    @FXML
    private Label brandLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private ImageView productImage;
    @FXML
    private Button backButton;
    @FXML
    private Button addToCartButton;
    @FXML
    private Spinner<Integer>  quantitySpinner;
    @FXML
    private Label cartSuccessLabel;
    @FXML
    private Label wishlistSuccessLabel;
    @FXML
    private ImageView ratingImage;
    @FXML
    private Label QuantityLabel;
    @FXML
    private Button DelProductButton;
    @FXML
    private Button UpProductButton;
    @FXML
    private Label cartLabel;
    @FXML
    private Label wishlistLabel;
    @FXML
    private Label ordersLabel;
    @FXML
    private ImageView cartIcon;
    @FXML
    private ImageView wishlistIcon;
    @FXML
    private ImageView ordersIcon;

    private boolean liked;
    @FXML
    private ImageView UnLikedPicture;
    @FXML
    private ImageView LikedPicture;
    @FXML
    private TextField search;

    private int stockQuantity;

    @FXML
    public void toggleLiked(){
        LikedPicture.setVisible(!liked);
        UnLikedPicture.setVisible(liked);
        if(liked)
            engine.removeFromWishList();
        else
            engine.addToWishlist();
            
        liked=!liked;
    }
    public void initialize(){
        cartSuccessLabel.setVisible(false);
    }

    public void configureScreenByRole(){
            boolean isCustomer=engine.isCustomer;

            if(isCustomer){
                cartLabel.setVisible(true);
                cartIcon.setVisible(true);
                wishlistLabel.setText("Wishlist");
                wishlistIcon.setImage((new Image(getClass().getResourceAsStream("resources/wishlist.png"))));
                ordersLabel.setText("Orders");
                ordersIcon.setImage((new Image(getClass().getResourceAsStream("resources/orders.png"))));
                boolean liked=engine.isInWishList();
                LikedPicture.setVisible(liked);
                UnLikedPicture.setVisible(!liked);
                LikedPicture.setImage((new Image(getClass().getResourceAsStream("resources/LikedProduct2.png"))));
                UnLikedPicture.setImage((new Image(getClass().getResourceAsStream("resources/UnlikedProduct2.png"))));
            }
            else{
                cartLabel.setVisible(false);
                cartIcon.setVisible(false);
                wishlistLabel.setText("Add");
                wishlistIcon.setImage((new Image(getClass().getResourceAsStream("resources/addIcon.png"))));
                ordersLabel.setText("Analytics");
                ordersIcon.setImage((new Image(getClass().getResourceAsStream("resources/analyticsIcon.png"))));
                LikedPicture.setVisible(false);
                UnLikedPicture.setVisible(false);
            }
            
            addToCartButton.setVisible(isCustomer);
            quantitySpinner.setVisible(isCustomer);
            QuantityLabel.setVisible(isCustomer);

            DelProductButton.setVisible(!isCustomer);
            UpProductButton.setVisible(!isCustomer);
            loadProduct();
        }
    @FXML
    public void delProductB(ActionEvent e){
        engine.deleteViewedProduct();
        engine.getHomeController().handleCategoryAll();
        handleBack();
    }
    public boolean getIsUpdating(){
        boolean temp=isUpdating;
        isUpdating=false;
        return temp;
    }
    @FXML
    public void upProductB(ActionEvent e){
        isUpdating=true;
        engine.switchScene(Screen.MODIFYPRODUCT);
    }
    public void loadProduct(){
        if(engine ==null) return;

        Product viewedProduct=engine.getViewedProduct();
        if(viewedProduct==null) return;


        productNameLabel.setText(viewedProduct.getProductName());
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

        cartSuccessLabel.setVisible(false);


        int maxQuantity=(viewedProduct.getStockQuantity()==0)?1:viewedProduct.getStockQuantity();
        
        SpinnerValueFactory<Integer> valueFactory = 
            new SpinnerValueFactory.IntegerSpinnerValueFactory(1,maxQuantity, 1);
        quantitySpinner.setValueFactory(valueFactory);
        quantitySpinner.setEditable(false);
        quantitySpinner.setPrefWidth(80);

        search.setOnKeyPressed(event->{
        if (event.getCode()==KeyCode.ENTER)
                handleSearch();
        });

        displayStockAvailiability();
    
    }
    public void handleBack(){
        cartSuccessLabel.setVisible(false);
        engine.switchScene(Screen.HOME);
    }

    @FXML
    public void handleAddToCart() {
        int quantity = quantitySpinner.getValue();
        Product product = engine.getViewedProduct();
    
        int currentCartQuantity = engine.getCurrentCustomer().getCartQuantity(product);
    
        if (currentCartQuantity+quantity>product.getStockQuantity()) {
            if(product.getStockQuantity()==0) cartSuccessLabel.setText("Out of stock");
            else cartSuccessLabel.setText("Cannot add more");
            cartSuccessLabel.setStyle("-fx-background-color: red;");
        } else {
            engine.addToCart(quantity);
            cartSuccessLabel.setText("Added to cart");
            cartSuccessLabel.setStyle("-fx-background-color: green;");
        }
    
        cartSuccessLabel.setVisible(true);
    }
    
    @FXML 
    private Label stockAvailability;
    public void displayStockAvailiability(){
        int stock = engine.getViewedProduct().getStockQuantity();
        if (stock == 0) {
            stockAvailability.setText("Out of Stock");
            stockAvailability.setStyle("-fx-text-fill: red;");
        } else if (stock == 1) {
            stockAvailability.setText("Only one left in stock");
            stockAvailability.setStyle("-fx-text-fill: red;");
        } else if (stock < 10) {
            stockAvailability.setText("Limited Stock");
            stockAvailability.setStyle("-fx-text-fill: orange;");
        } else {
            stockAvailability.setText("Available In Stock");
            stockAvailability.setStyle("-fx-text-fill: green;");
        }
    }


//==================================Home methods===================================
    @FXML
    public void handleSearch(){
        engine.switchScene(Screen.HOME);
        engine.searchFromProductView=(search.getText().isEmpty())?null:search.getText();
        engine.getHomeController().handleSearch();
        cartSuccessLabel.setVisible(false);
    }
    @FXML
    public void handleLogout(){
        engine.logout();
        engine.switchScene(Screen.LOGIN);
        cartSuccessLabel.setVisible(false);

    }
    @FXML
    public void handleCategoryAll() {
        engine.switchScene(Screen.HOME);
        engine.getHomeController().handleCategoryAll();
        cartSuccessLabel.setVisible(false);
    }
    @FXML
    public void handleCategoryElectronics() {
        engine.switchScene(Screen.HOME);
        engine.getHomeController().handleCategoryElectronics();
        cartSuccessLabel.setVisible(false);
    }
    @FXML
    public void handleCategoryClothing() {
        engine.switchScene(Screen.HOME);
        engine.getHomeController().handleCategoryClothing();
        cartSuccessLabel.setVisible(false);
    }
    @FXML
    public void handleCategoryFurniture() {
        engine.switchScene(Screen.HOME);
        engine.getHomeController().handleCategoryFurniture();
        cartSuccessLabel.setVisible(false);
    }
    @FXML
    public void handleCategoryBooks() {
        engine.switchScene(Screen.HOME);
        engine.getHomeController().handleCategoryBooks();
        cartSuccessLabel.setVisible(false);
    }
    @FXML 
    public void handleCategoryBeauty() {
        engine.switchScene(Screen.HOME);
        engine.getHomeController().handleCategoryBeauty();
        cartSuccessLabel.setVisible(false);
    }
    @FXML
    public void handleCategoryToys() {
        engine.switchScene(Screen.HOME);
        engine.getHomeController().handleCategoryToys();
        cartSuccessLabel.setVisible(false);
    }
    @FXML
    public void handleCategorySports() {
        engine.switchScene(Screen.HOME);
        engine.getHomeController().handleCategorySports();
        cartSuccessLabel.setVisible(false);
    }
    @FXML
    public void handleCart(){
        engine.switchScene(Screen.CART);
        cartSuccessLabel.setVisible(false);
    }
    @FXML
    public void handleWishListOrAdd(){

        if(!engine.isCustomer){
        engine.getModifyController().setScreen(null);
        engine.switchScene(Screen.MODIFYPRODUCT);
        }
        else engine.switchScene(Screen.WISH_LIST);
    }
    @FXML
    public void handleOrdersOrAnalytics(){
        if(engine.isCustomer)
            engine.switchScene(Screen.ORDERS);
        else engine.switchScene(Screen.ANALYTICS);
    }
    
    @FXML 
    ImageView profile;
    @FXML
    public void viewProfile(){
        engine.switchScene(Screen.PROFILE);
    }
    public void clearSerach(){
        
    }
}


