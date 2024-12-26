package engine;

import entity.products.Category;
import entity.products.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    @FXML
    private ImageView productImage;
    @FXML
    private TextField imageF;
    @FXML
    private Label errorLabel;

    public void initialize(){
        
    }
    public void setScreen(Product product){
        errorLabel.setVisible(false);
        currentProduct=product;
        String name="";
        String brand="";
        String price="";
        String desc="";
        String quantity="";
        String category="";
        String image="";
        UpdateB.setText("Add Product");
        categoryChoiceBox.getItems().clear();
        categoryChoiceBox.getItems().addAll("Electronics","Furniture","Books","Clothing",
        "Beauty",
        "Toys",
        "Sports");
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
            image=currentProduct.getImagePath();
        }
        NameF.setText(name);
        BrandF.setText(brand);
        PriceF.setText(price);
        DescF.setText(desc);
        quantityF.setText(quantity);
        categoryChoiceBox.setValue(category);
        imageF.setText(image);
        if(image=="") image="resources/default.png";
        updateImage(image);
    }
    @FXML
    public void handleChange() {
        errorLabel.setVisible(false);
    
        if (currentProduct == null) {
            try {
                validateInputs();
                Product newProduct = new Product(
                    Double.parseDouble(PriceF.getText()),
                    Integer.parseInt(quantityF.getText()),
                    NameF.getText(),
                    DescF.getText(),
                    BrandF.getText(),
                    Category.valueOf(categoryChoiceBox.getValue().toUpperCase()),
                    (imageF.getText() == null || imageF.getText().isEmpty()) ? "resources/default.png" : imageF.getText()
                );
    
                if (!engine.addProduct(newProduct)) {
                    showError("Product already exists");
                }
            } catch (IllegalArgumentException | NullPointerException e) {
                showError("Invalid input: " + e.getMessage());
            } catch (Exception e) {
                showError("Unexpected error: " + e.getMessage());
            }
        } else {
            try {
                validateInputs();
                engine.updateProduct(currentProduct,
                NameF.getText(),
                BrandF.getText(),
                Double.parseDouble(PriceF.getText()),
                DescF.getText(),
                Integer.parseInt(quantityF.getText()),
                Category.valueOf(categoryChoiceBox.getValue().toUpperCase()),
                imageF.getText()
                );
                updateImage(imageF.getText());
            } catch (IllegalArgumentException | NullPointerException e) {
                showError("Invalid input: "+e.getMessage());
            } catch (Exception e) {
                showError("Unexpected error: "+e.getMessage());
            }
        }
        if (!errorLabel.isVisible()) {
            handleBack();
        }
    }
    @FXML
    public void handleBack(){
        errorLabel.setVisible(false);
        if(currentProduct==null)engine.switchScene(Screen.HOME);
        else engine.switchScene(Screen.PRODUCT);
    }
    public void updateImage(String path){
        try{
        productImage.setImage((new Image(getClass().getResourceAsStream(path))));
        }
        catch(Exception e){
            productImage.setImage((new Image(getClass().getResourceAsStream("resources/default.png"))));
            showError("Could not find image");
        }
    }
    public void showError(String s){
        errorLabel.setText(s);
        errorLabel.setVisible(true);
    }
    public void validateInputs(){
        if (NameF.getText() == null || NameF.getText().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (BrandF.getText() == null || BrandF.getText().isEmpty()) {
            throw new IllegalArgumentException("Brand cannot be empty");
        }
        if (PriceF.getText() == null || !PriceF.getText().matches("\\d+(\\.\\d{1,2})?")) {
            throw new IllegalArgumentException("Price must be a valid number");
        }
        if (quantityF.getText() == null || !quantityF.getText().matches("\\d+")) {
            throw new IllegalArgumentException("Quantity must be a valid number");
        }
        if (categoryChoiceBox.getValue() == null) {
            throw new IllegalArgumentException("Category must be selected");
        }
    }
}