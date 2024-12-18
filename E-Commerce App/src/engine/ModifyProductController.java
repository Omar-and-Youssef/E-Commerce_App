package engine;

import entity.products.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    public void initialize(){
        
    }
    public void setScreen(Product product){
        currentProduct=product;
        String name="";
        String brand="";
        String price="";
        String desc="";
        UpdateB.setText("Add Product");
        //Image ...
        if(product!=null){
            name=currentProduct.getProductName();
            brand=currentProduct.getBrand();
            price=""+currentProduct.getPrice();
            desc=currentProduct.getDescription();
            UpdateB.setText("Update Product");
        }
        NameF.setText(name);
        BrandF.setText(brand);
        PriceF.setText(price);
        DescF.setText(desc);
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
        }
        handleBack();
    }
    @FXML
    public void handleBack(){
        if(currentProduct==null)engine.switchScene(Screen.HOME);
        else engine.switchScene(Screen.PRODUCT);
    }
}