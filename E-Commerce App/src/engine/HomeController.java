package engine;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.awt.Button;
import java.awt.image.BufferedImage;
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
    public void handleLogout(){
        engine.switchScene(Screen.LOGIN);
        //TODO ensure saving cart, wishlist, orders,... 
    }
    public void displayName(String name){
        String tempName=name.substring(0,name.indexOf(" "));
        nameLabel.setText("Welcome, "+tempName);
    }
    public void populateProducts(ArrayList<Product> products){
        productGrid.getChildren().clear();
        int coloumns =3;
        int row=0,col=0;
        for(Product product:products){
            VBox productBox = new VBox(10);
            productBox.setAlignment(Pos.CENTER);

            Image fxImage=convertToFxImage(product.getImage());

            ImageView productImage=new ImageView(fxImage);
            productImage.setFitWidth(90);
            productImage.setFitHeight(71);
            productImage.setPreserveRatio(true);

            ImageView productRating=new ImageView();
            productRating.setFitHeight(20); 
            productRating.setFitWidth(100);
            productRating.setPreserveRatio(true);
            int rating =(int) Math.floor(product.getRating());
            switch(rating){
                case 1:
                    productRating.setImage(new Image(getClass().getResourceAsStream("@resources/1star.png")));
                    break;
                case 2:
                    productRating.setImage(new Image(getClass().getResourceAsStream("@resources/2star.png")));
                    break;
                case 3:
                    productRating.setImage(new Image(getClass().getResourceAsStream("@resources/3star.png")));
                    break;
                case 4:
                    productRating.setImage(new Image(getClass().getResourceAsStream("@resources/4star.png")));
                    break;
                case 5:
                    productRating.setImage(new Image(getClass().getResourceAsStream("@resources/5star.png")));
                    break;
                default:
                    productRating.setImage(new Image(getClass().getResourceAsStream("@resources/1star.png")));
            }


            Text productName = new Text(product.getProductName());
            productName.setStyle("-fx-font-size: 10px; -fx-font-weight: bold;");

            Text productbrand = new Text(product.getBrand());
            productbrand.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
            
            Text productPrice = new Text("$ " + product.getPrice());
            productPrice.setStyle("-fx-font-size: 14px; -fx-fill: #32CD32;");

            productBox.getChildren().addAll(productImage,productName,productbrand,productPrice);
            
            productGrid.add(productBox, col, row);
            col++;
            if(col==coloumns){
                col=0;
                row++;
            }
            if(row==3) break;
        }
    }








    public static Image convertToFxImage(BufferedImage bufferedImage) {
        return SwingFXUtils.toFXImage(bufferedImage, null);
    }
    public GridPane getGrid(){
        return productGrid;
    }
    public void setGrid(GridPane gridPane){
        productGrid=gridPane;
    }
}
