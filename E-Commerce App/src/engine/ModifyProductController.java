package engine;

import entity.products.Category;
import entity.products.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ModifyProductController extends BaseController {
    private Product currentProduct;
    @FXML
    private TextField NameF;
    @FXML
    private TextField BrandF;

    @FXML
    private TextField PriceF;

    @FXML
    private TextField DescF;

    @FXML
    private Button CancelB;
    @FXML
    private Button UpdateB;

    @FXML
    private TextField quantityF;

    @FXML
    private ChoiceBox<String> categoryChoiceBox;

    public void initialize(){
        
    }
    public void setScreen(Product product){
        currentProduct=product;
        String name="";
        String brand="";
        String price="";
        String desc="";
        String quantity="";
        String category="";
        UpdateB.setText("Add Product");
        categoryChoiceBox.getItems().clear();
        categoryChoiceBox.getItems().addAll("Electronics","Books","Clothing",
        "Home",
        "Beauty",
        "Toys",
        "Sports");
        //Image ...
        if(product!=null){
            name=currentProduct.getProductName();
            brand=currentProduct.getBrand();
            price=""+currentProduct.getPrice();
            desc=currentProduct.getDescription();
            UpdateB.setText("Update Product");
            quantity=""+currentProduct.getStockQuantity();
            category=""+currentProduct.getCategory();
            category=category.toLowerCase();
            category=(""+category.charAt(0)).toUpperCase()+category.substring(1);
        }
        NameF.setText(name);
        BrandF.setText(brand);
        PriceF.setText(price);
        DescF.setText(desc);
        quantityF.setText(quantity);
        categoryChoiceBox.setValue(category);
    }
    @FXML
    public void handleChange(){
        if(currentProduct==null){
            //Product newProduct=new Product(NameF.getText(),BrandF.getText(),Double.parseDouble(PriceF.getText()),DescF.getText());
            //add to db
            //id based on static counter
        }else{
            currentProduct.setProductName(NameF.getText());
            currentProduct.setBrand(BrandF.getText());
            currentProduct.setPrice(Double.parseDouble(PriceF.getText()));
            currentProduct.setDescription(DescF.getText());
            currentProduct.setStockQuantity(Integer.parseInt(quantityF.getText()));
            Category category = Category.valueOf(categoryChoiceBox.getValue().toUpperCase());
            currentProduct.setCategory(category);
        }
        handleBack();
    }
    @FXML
    public void handleBack(){
        if(currentProduct==null)engine.switchScene(Screen.HOME);
        else engine.switchScene(Screen.PRODUCT);
    }
}