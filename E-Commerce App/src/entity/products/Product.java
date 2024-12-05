package entity.products;

public class Product {
    private final String PRODUCT_ID;
    private double price;
    private double stockQuantity;
    private String productName;
    private String description;
    private String brand;
    private Category category;
    private int rating;
    private String image; //???
    private static int productCounter;
//=======================================Constructor===================================
    public Product(double price, double stockQuantity,String productName,String description,
    String brand,Category category,String image){
        this.PRODUCT_ID="P"+productCounter++;
        this.price=price;
        this.stockQuantity=stockQuantity;
        this.productName=productName;
        this.description=description;
        this.brand=brand;
        this.category=category;
        this.rating=0;
        this.image=image;
    }
    public Product(double price, double stockQuantity, String productName, String description, 
    String brand, Category category){
        this(price,stockQuantity,productName,description,brand,category,"NA");
    }
    public Product(double price,double stockQuantity, String productName, String description, 
    Category category){
        this(price,stockQuantity,productName,description,"NA",category,"NA");
    }

//=======================================Methods=======================================
//product database is implemented alone, as well as a product dao to query that database
//=======================================Get&Set=======================================
    public String getPRODUCT_ID() {
        return PRODUCT_ID;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getStockQuantity() {
        return stockQuantity;
    }
    public void setStockQuantity(double stockQuantity) {
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
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
}
