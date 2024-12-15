package engine;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;

import entity.products.Product;

public class HomeController extends BaseController {
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
    public void handleLogout(){
        engine.switchScene(Screen.LOGIN);
        //TODO ensure saving cart, wishlist, orders,... 
    }
    public void displayName(String name){
        String tempName=name.substring(0,name.indexOf(" "));
        nameLabel.setText("Welcome, "+tempName);
    }

    int PRODUCTS_PER_PAGE = 8; 
    int productCount = 0;  
    
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
            System.out.println("Product: " + product.getProductName());
    
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
        productBox.setPrefWidth(140);
        productBox.setPrefHeight(140);
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


        ImageView productImage = new ImageView();
        productImage.setPreserveRatio(true);
        productImage.setFitHeight(50);
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
        int rating;
        try{
        rating =(int) Math.floor(product.getRating());
        }
        catch(Exception e){
            rating=0;
        }
        String ratingPath = "resources/" + rating + "star.png";
        if (getClass().getResourceAsStream(ratingPath) != null) {
            productRating.setImage(new Image(getClass().getResourceAsStream(ratingPath)));
        } else {
            System.err.println("Rating image not found for rating: " + rating);
        }


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
    public Button getnavLeftButton(){
        return navLeftButton;
    }
    public Button getnavRightButton(){
        return navRightButton;
    }
}
