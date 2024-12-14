package engine;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    public void handleLogout(){
        engine.switchScene(Screen.LOGIN);
        //TODO ensure saving cart, wishlist, orders,... 
    }
    public void displayName(String name){
        String tempName=name.substring(0,name.indexOf(" "));
        nameLabel.setText("Welcome, "+tempName);
    }
    public void populateProducts(ArrayList<Product> products){
        hBox.getStyleClass().add("curved-hbox");
        int coloumns =3;
        int row=0,col=0;
        System.out.println(products.isEmpty());
        for(Product product:products){
            System.out.println("Product: "+product.getProductName());
            VBox productBox = new VBox(10);
            productBox.setAlignment(Pos.CENTER);

            ImageView productImage = new ImageView();
            productImage.setFitWidth(90);
            productImage.setFitHeight(71);
            productImage.setPreserveRatio(true);
            String imagePath = product.getImagePath();
            if (getClass().getResourceAsStream(imagePath) != null) {
                productImage.setImage(new Image(getClass().getResourceAsStream(imagePath)));
            } else {
                System.err.println("Image not found: " + imagePath);
            }
            
            ImageView productRating=new ImageView();
            productRating.setFitHeight(20); 
            productRating.setFitWidth(100);
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
            productName.setStyle("-fx-font-size: 10px; -fx-font-weight: bold;");

            Text productbrand = new Text(product.getBrand());
            productbrand.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
            
            Text productPrice = new Text("$ " + product.getPrice());
            productPrice.setStyle("-fx-font-size: 14px; -fx-fill: #32CD32;");

            productBox.getChildren().addAll(productImage,productRating,productName,productbrand,productPrice);
            
            productGrid.add(productBox, col, row);
            col++;
            if(col==coloumns){
                col=0;
                row++;
            }
            if(row==3) break;
        }
    }
}
