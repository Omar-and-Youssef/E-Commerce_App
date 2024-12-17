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
    private Label cartErrorLabel;
    @FXML
    private Label wishlistErrorLabel;
 
    @FXML
    private Label QuantityLabel;
    

    @FXML
    private Button DelProductButton;

    @FXML
    private Button UpProductButton;


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
        cartErrorLabel.setVisible(false);
        wishlistErrorLabel.setVisible(false);
        resetQuantity();
    }
    public void configureScreenByRole(){//all admin stuff do !
    //    // boolean isCustomer=engine.getCurrentUser().getUserID().charAt(0)!='A';
         boolean isCustomer=true;
    //     //Customer stuff
        addToWishlistButton.setVisible(isCustomer);
        addToCartButton.setVisible(isCustomer);
        quantitySpinner.setVisible(isCustomer);
        QuantityLabel.setVisible(isCustomer);
    //     //Admin stuff
        DelProductButton.setVisible(!isCustomer);
        UpProductButton.setVisible(!isCustomer);
        loadProduct();
    }
    @FXML
    public void delProductB(ActionEvent e){
        engine.deleteViewedProdcut();
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
    }
    public void handleBack(){
        engine.switchScene(Screen.HOME);
    }

    // public void handleAddToCart(){
    //     int quantity = quantitySpinner.getValue();
    //     if(engine.addToCart(viewedProduct, quantity)){
    //         cartErrorLabel.setVisible(false);
    //     }
    //     else{
    //         cartErrorLabel.setVisible(true);
    //     }
    // }
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

}
