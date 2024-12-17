package engine;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.stream.Collectors;

import entity.products.Category;
import entity.products.Product;

public class HomeController extends BaseController {
   @FXML
    public void handleLogout(){
        engine.switchScene(Screen.LOGIN);
        //TODO ensure saving cart, wishlist, orders,... 
    }
    @FXML
    public void handleCart(){
        engine.switchScene(Screen.CART);
    }
    // @FXML
    // public void handleWishlist(){
    //     engine.switchScene(Screen.WISHLIST);
    // }
    // @FXML
    // public void handleProfile(){
    //     engine.switchScene(Screen.PROFILE);
    // }
    public void displayName(String name){
        String tempName;
        if(name.contains(" ")) tempName= name.substring(0,name.indexOf(" "));
        else tempName=name;
        nameLabel.setText("Welcome, "+tempName);
    }

    int PRODUCTS_PER_PAGE=8; 
    int productCount;  
    
    public void populateProducts(ArrayList<Product> products, int startingIndex) {
        hBox.getStyleClass().add("curved-hbox");
        final int COLUMNS = 4;
        final int MAX_ROWS = 2;
    
        int row = 0, col = 0;
        productGrid.getChildren().clear();
        productGrid.getColumnConstraints().clear();
        productGrid.getRowConstraints().clear();
    
        for (int i=startingIndex; i<startingIndex+PRODUCTS_PER_PAGE && i<products.size(); i++) {
            Product product = products.get(i);
    
            VBox productBox = createProductBox(product);
            productGrid.add(productBox, col, row);
    
            col++;
            if (col == COLUMNS) {
                col = 0;
                row++;
            }
            if (row == MAX_ROWS) break;
        }
    
        // Update button visibility, will see how we update category grouping
        navLeftButton.setVisible(startingIndex > 0);
        navRightButton.setVisible(startingIndex + PRODUCTS_PER_PAGE < products.size());
    }
    public VBox createProductBox(Product product){
        VBox productBox = new VBox();
        productBox.setAlignment(Pos.CENTER);
        productBox.getStyleClass().add("product-box");
        productBox.setPrefWidth(160);
        productBox.setPrefHeight(160);
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.GRAY);
        shadow.setRadius(10); 
        productBox.setOnMouseEntered(event -> {
            productBox.setEffect(shadow);
            productBox.setStyle("-fx-cursor: hand;");
        });
        productBox.setOnMouseExited(event -> {
            productBox.setEffect(null);
            productBox.setStyle("-fx-cursor: default;");
        });
        productBox.setOnMouseClicked(event -> {
            engine.setViewedProduct(product);
            engine.getProductController().loadProduct();
            engine.switchScene(Screen.PRODUCT);
        });


        ImageView productImage = new ImageView();
        productImage.setPreserveRatio(true);
        productImage.setFitHeight(90);
        String imagePath = product.getImagePath();
        if (getClass().getResourceAsStream(imagePath) != null) {
            productImage.setImage(new Image(getClass().getResourceAsStream(imagePath)));
        } else {
            System.err.println("Image not found: " + imagePath);
        }
        
        ImageView productRating=new ImageView();
        productRating.setFitHeight(15); 
        productRating.setFitWidth(80);
        productRating.setPreserveRatio(true);
        int rating=engine.getIntegerRating(product);
        String ratingPath = "resources/" + rating + "star.png";
        if (getClass().getResourceAsStream(ratingPath) != null)
            productRating.setImage(new Image(getClass().getResourceAsStream(ratingPath)));
        else
            System.err.println("Rating image not found for rating: " + rating);
    


        Text productName = new Text(product.getProductName());
        productName.getStyleClass().add("product-name");
        productName.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        Text productBrand = new Text(product.getBrand());
        productBrand.getStyleClass().add("product-brand");
        productBrand.setStyle("-fx-font-size: 10px; -fx-font-weight: bold;");
        
        Text productPrice = new Text("$ " + product.getPrice());
        productPrice.setStyle("-fx-font-size: 10px;");

        // productBox.getStyleClass().add("product-box");
        // productName.getStyleClass().add("product-name");
        // productBrand.getStyleClass().add("product-brand");
        // productPrice.getStyleClass().add("product-price");
        
        productBox.getChildren().addAll(productImage,productRating,productName,productBrand,productPrice);
        return productBox;
    }

    @FXML
    public void navigateProductsRight() {
        ArrayList<Product> products = engine.getProductDatabase();
        if (productCount+PRODUCTS_PER_PAGE < products.size()) {
            productCount+=PRODUCTS_PER_PAGE;
            populateProducts(products, productCount);
        }
    }
    
    @FXML
    public void navigateProductsLeft() {
        if (productCount - PRODUCTS_PER_PAGE >= 0) {
            productCount -= PRODUCTS_PER_PAGE;
            populateProducts(engine.getProductDatabase(), productCount);
        }
    }
    @FXML
    public void handleCategoryAll() {
        productCount = 0;
        populateProducts(engine.getProductDatabase(), productCount);
    }
    @FXML
    public void handleCategoryElectronics() {
        productCount = 0;
        populateProducts(engine.getProductsByCategory(Category.ELECTRONICS), productCount);
    }
    @FXML
    public void handleCategoryClothing() {
        productCount = 0;
        populateProducts(engine.getProductsByCategory(Category.CLOTHING), productCount);
    }
    @FXML
    public void handleCategoryFurniture() {
        productCount = 0;
        populateProducts(engine.getProductsByCategory(Category.FURNITURE), productCount);
    }
    @FXML
    public void handleCategoryBooks() {
        productCount = 0;
        ArrayList<Product> books = engine.getProductsByCategory(Category.BOOKS);
        populateProducts(books, productCount);
    }
    @FXML
    public void handleCategoryBeauty() {
        productCount = 0;
        populateProducts(engine.getProductsByCategory(Category.BEAUTY), productCount);
    }
    @FXML
    public void handleCategoryToys() {
        productCount = 0;
        populateProducts(engine.getProductsByCategory(Category.TOYS), productCount);
    }
    @FXML
    public void handleCategorySports() {
        productCount = 0;
        populateProducts(engine.getProductsByCategory(Category.SPORTS), productCount);
    }
    @FXML
    public void handleSearch(){
        String searchedProduct=searchField.getText();
        if (searchedProduct.isEmpty()) {
            populateProducts(engine.getProductDatabase(), 0);
            return;
        }
        ArrayList<Product> searchedProducts = engine.getProductDatabase()
        .stream()
        .filter(product -> 
            product.getProductName().toLowerCase().contains(searchedProduct.toLowerCase()) ||
            product.getCategory().name().toLowerCase().contains(searchedProduct.toLowerCase()) || // Enum value search
            (product.getBrand() != null && product.getBrand().toLowerCase().contains(searchedProduct.toLowerCase())) // Brand search
        )
        .collect(Collectors.toCollection(ArrayList::new));
        productCount = 0;
        populateProducts(searchedProducts, productCount);
    }
    public Button getnavLeftButton(){
        return navLeftButton;
    }
    public Button getnavRightButton(){
        return navRightButton;
    }

    public void setNavigationMenu(boolean isCustomer){
        if(isCustomer){
            cartLabel.setVisible(true);
            cartIcon.setVisible(true);
            wishlistLabel.setText("Wishlist");
            wishlistIcon.setImage((new Image(getClass().getResourceAsStream("resources/wishlist.png"))));
            ordersLabel.setText("Orders");
            ordersIcon.setImage((new Image(getClass().getResourceAsStream("resources/orders.png"))));
        }
        else{
            cartLabel.setVisible(false);
            cartIcon.setVisible(false);
            wishlistLabel.setText("Add");
            wishlistIcon.setImage((new Image(getClass().getResourceAsStream("resources/addIcon.png"))));
            ordersLabel.setText("Analytics");
            ordersIcon.setImage((new Image(getClass().getResourceAsStream("resources/analyticsIcon.png"))));
        }
    }
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
    @FXML
    private Label nameLabel;
    @FXML
    private Button logoutButton;
    @FXML
    private GridPane productGrid;
    @FXML
    private HBox hBox;
    @FXML
    private Button navRightButton;
    @FXML
    private Button navLeftButton;
    @FXML 
    private TextField searchField;

}