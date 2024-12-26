package database;
import entity.users.accounts.*;
import entity.users.details.*;
import entity.products.*;
import java.util.ArrayList;

public class Database {
    private static final ArrayList<Customer> CUSTOMER_DB= new ArrayList<Customer>();
    private static final ArrayList<Admin> ADMIN_DB= new ArrayList<Admin>();
    private static final ArrayList<Product> PRODUCT_DB= new ArrayList<Product>();
    private static final ArrayList<Order> ORDER_DB= new ArrayList<Order>();
  static { 
        try {
        Customer c1 = new Customer("John Doe", "q", "q", Gender.MALE,Category.ELECTRONICS);
        c1.setWallet(5000);
        Customer c2 = new Customer("Jane Doe", "jane@gmail.com", "password", Gender.FEMALE,Category.CLOTHING);
        CUSTOMER_DB.add(c1);
        CUSTOMER_DB.add(c2);   
        
        Admin a1 = new Admin("Montaser", "w", "w", Gender.MALE, "Development","8-4");
        Admin a2 = new Admin("Yousuf", "montaser@gmail.com", "password", Gender.MALE, "Development","9-5");        

        ADMIN_DB.add(a1);
        ADMIN_DB.add(a2);

            // Create product objects
            // Electronics
            PRODUCT_DB.add(new Product(1000.00, 50, "ASUS Laptop", "High-end gaming laptop", "ASUS", Category.ELECTRONICS, "resources/laptop.png"));
            PRODUCT_DB.add(new Product(1200.00, 30, "Apple iPhone 13", "Latest Apple smartphone", "Apple", Category.ELECTRONICS, "resources/iphone13.png"));
            PRODUCT_DB.add(new Product(800.00, 40, "Samsung Galaxy Tab", "10-inch tablet", "Samsung", Category.ELECTRONICS, "resources/galaxytab.png"));
            PRODUCT_DB.add(new Product(450.00, 60, "Sony Headphones", "Noise-canceling over-ear headphones", "Sony", Category.ELECTRONICS, "resources/sonyHeadphones.png"));
            PRODUCT_DB.add(new Product(199.99, 100, "Fitbit Charge 5", "Fitness tracker with heart rate monitor", "Fitbit", Category.ELECTRONICS, "resources/fitbit.png"));
            // // Clothing
            PRODUCT_DB.add(new Product(15.05, 500, "Black T-shirt", "Basic cotton t-shirt", "Nike", Category.CLOTHING, "resources/blackShirt.png"));
            PRODUCT_DB.add(new Product(60.07, 300, "Blue Jeans", "Slim fit jeans", "Levi's", Category.CLOTHING, "resources/blueJeans.png"));
            PRODUCT_DB.add(new Product(120.09, 100, "Leather Jacket", "Stylish leather jacket", "Zara", Category.CLOTHING, "resources/leatherJacket.png"));
            PRODUCT_DB.add(new Product(25.50, 200, "Graphic T-shirt", "Printed cotton t-shirt", "H&M", Category.CLOTHING, "resources/graphicsTshirt.png"));
            PRODUCT_DB.add(new Product(99.99, 150, "Casual Sneakers", "Comfortable and trendy sneakers", "Adidas", Category.CLOTHING, "resources/sneakers.png"));

            // Furniture
            PRODUCT_DB.add(new Product(300.00, 20, "Office Chair", "Ergonomic office chair", "IKEA", Category.FURNITURE, "resources/officeChair.png"));
            PRODUCT_DB.add(new Product(700.00, 10, "Dining Table", "6-person wooden dining table", "IKEA", Category.FURNITURE, "resources/diningTable.png"));
            PRODUCT_DB.add(new Product(350.00, 15, "Sofa Set", "3-seater modern sofa set", "Ashley", Category.FURNITURE, "resources/default.png"));
            PRODUCT_DB.add(new Product(200.00, 50, "Bookshelf", "Wooden 5-shelf bookshelf", "IKEA", Category.FURNITURE, "resources/default.png"));
            PRODUCT_DB.add(new Product(150.00, 40, "Coffee Table", "Simple modern coffee table", "Wayfair", Category.FURNITURE, "resources/default.png"));
            // Books
            PRODUCT_DB.add(new Product(15.99, 200, "The Great Gatsby", "Classic novel by F. Scott Fitzgerald", "Penguin", Category.BOOKS, "resources/default.png"));
            PRODUCT_DB.add(new Product(25.50, 150, "Java Programming", "Comprehensive guide to Java programming", "O'Reilly", Category.BOOKS, "resources/default.png"));
            PRODUCT_DB.add(new Product(12.99, 100, "Atomic Habits", "Self-help book by James Clear", "Penguin", Category.BOOKS, "resources/default.png"));
            PRODUCT_DB.add(new Product(18.75, 250, "Becoming", "Autobiography of Michelle Obama", "Crown Publishing", Category.BOOKS, "resources/default.png"));
            PRODUCT_DB.add(new Product(10.50, 300, "To Kill a Mockingbird", "Novel by Harper Lee", "HarperCollins", Category.BOOKS, "resources/default.png"));

            // Beauty
            PRODUCT_DB.add(new Product(25.99, 400, "Facial Serum", "Hydrating facial serum for glowing skin", "L'Oréal", Category.BEAUTY, "resources/default.png"));
            PRODUCT_DB.add(new Product(15.50, 350, "Lipstick", "Matte red lipstick", "Maybelline", Category.BEAUTY, "resources/default.png"));
            PRODUCT_DB.add(new Product(50.00, 100, "Anti-Aging Cream", "Wrinkle-reducing anti-aging cream", "Olay", Category.BEAUTY, "resources/default.png"));
            PRODUCT_DB.add(new Product(20.99, 200, "Shampoo", "Nourishing shampoo for dry hair", "Head & Shoulders", Category.BEAUTY, "resources/default.png"));
            PRODUCT_DB.add(new Product(30.00, 150, "Face Mask", "Hydrating face mask for all skin types", "Neutrogena", Category.BEAUTY, "resources/default.png"));

            // Toys
            PRODUCT_DB.add(new Product(10.99, 500, "Lego Set", "Building blocks for kids", "Lego", Category.TOYS, "resources/default.png"));
            PRODUCT_DB.add(new Product(7.99, 350, "Rubik's Cube", "3x3 puzzle for all ages", "Rubik's", Category.TOYS, "resources/default.png"));
            PRODUCT_DB.add(new Product(12.00, 200, "Dollhouse", "Wooden dollhouse with furniture", "Barbie", Category.TOYS, "resources/default.png"));
            PRODUCT_DB.add(new Product(9.50, 300, "Action Figure", "Superhero action figure with accessories", "Hasbro", Category.TOYS, "resources/default.png"));
            PRODUCT_DB.add(new Product(15.99, 250, "Remote Control Car", "RC car with working lights", "Hot Wheels", Category.TOYS, "resources/default.png"));

            // Sports
            PRODUCT_DB.add(new Product(40.00, 150, "Yoga Mat", "Non-slip yoga mat for comfort", "Gaiam", Category.SPORTS, "resources/default.png"));
            PRODUCT_DB.add(new Product(80.00, 100, "Dumbbells Set", "Adjustable dumbbells for home workout", "Bowflex", Category.SPORTS, "resources/default.png"));
            PRODUCT_DB.add(new Product(120.00, 50, "Treadmill", "Electric treadmill with digital display", "NordicTrack", Category.SPORTS, "resources/default.png"));
            PRODUCT_DB.add(new Product(25.99, 200, "Soccer Ball", "Standard size 5 soccer ball", "Adidas", Category.SPORTS, "resources/default.png"));
            PRODUCT_DB.add(new Product(60.00, 150, "Tennis Racket", "Professional tennis racket with strings", "Wilson", Category.SPORTS, "resources/default.png"));
            
    } 
    catch(Exception e) {
        e.printStackTrace();
    }
  }
    public  ArrayList<Customer> getCustomerDB(){
        return CUSTOMER_DB;
    }
    public  ArrayList<Admin> getAdminDB(){
        return ADMIN_DB;
    }
    public  ArrayList<Product> getProductDB(){
        return PRODUCT_DB;
    }
    public  ArrayList<Order> getOrderDB(){
        return ORDER_DB;
    }
}

