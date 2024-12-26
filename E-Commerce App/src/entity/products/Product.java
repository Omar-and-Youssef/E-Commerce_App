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
    private String imagePath;
    private final ArrayList<Review>REVIEW_LIST;
    private double rating;
    public boolean maxedOutInCart;
    private int salesCount;

//=======================================Constructor===================================
    public Product(){
        this(0,0,"NA","NA","NA",Category.ALL,null);
    }
    public Product(double price, int stockQuantity,String productName,String description,
    String brand,Category category,String imagePath){
        this.PRODUCT_ID="P"+productCounter++;
        this.price=price;
        this.stockQuantity=stockQuantity;
        this.productName=productName;
        this.description=description;
        this.brand=brand;
        this.category=category;
        this.imagePath=imagePath;
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
    public String getProductID(){
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
    public void setImage(String imagePath){
        this.imagePath=imagePath;
    }
    public String getImagePath(){
        return imagePath;
    }
    public double getRating(){
        calculateRating();
        return rating;
    }
    @Override
    public String toString() {
        return "Product [price=" + price + ", productName=" + productName + ", description=" + description + ", brand="
                + brand + ", category=" + category + ", rating=" + rating + "]";
    }
    public int getSalesCount() {
        return salesCount;
    }
    public void setSalesCount(int noOfSales) {
        salesCount = noOfSales;
    }
}
