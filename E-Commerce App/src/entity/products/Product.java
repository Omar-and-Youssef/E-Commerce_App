package entity.products;
import java.awt.image.BufferedImage;
import java.util.*;
public class Product {
    private static int productCounter;
    private final String PRODUCT_ID;
    private double price;
    private int stockQuantity;
    private String productName;
    private String description;
    private String brand;
    private Category category;
    private BufferedImage image; 
    private final ArrayList<Review>REVIEW_LIST;
    private double rating;
//=======================================Constructor===================================
    public Product(double price, int stockQuantity,String productName,String description,
    String brand,Category category,BufferedImage image){
        this.PRODUCT_ID="P"+productCounter++;
        this.price=price;
        this.stockQuantity=stockQuantity;
        this.productName=productName;
        this.description=description;
        this.brand=brand;
        this.category=category;
        this.image=image;
        REVIEW_LIST=new ArrayList<Review>();
    }
    public Product(double price, int stockQuantity, String productName, String description, 
    String brand, Category category){
        this(price,stockQuantity,productName,description,brand,category,null);
    }
    public Product(double price,int stockQuantity, String productName, String description, 
    Category category){
        this(price,stockQuantity,productName,description,"NA",category,null);
    }

//=======================================Methods=======================================
    public void addReview(Review r){
        REVIEW_LIST.add(r);
        calculateRating();
    }
    public void deleteReview(Review review){
        REVIEW_LIST.remove(review);
        calculateRating();
    }
    public void calculateRating(){
        int total=0;
        for(Review rev:REVIEW_LIST){
            total+=rev.getRating();
        }
        this.rating=total/REVIEW_LIST.size();
    }
//=======================================Get&Set=======================================
    public String getProductID() {
        return PRODUCT_ID;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getStockQuantity() {
        return stockQuantity;
    }
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public void setImage(BufferedImage image){
        this.image=image;
    }
    public BufferedImage getImage(){
        return image;
    }
    public double getRating(){
        calculateRating();
        return rating;
    }
}
