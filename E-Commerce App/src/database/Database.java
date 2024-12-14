package database;
import entity.users.accounts.*;
import entity.users.details.*;
import entity.products.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import java.util.ArrayList;

import java.util.ArrayList;

public class Database {
    private static final ArrayList<Customer> CUSTOMER_DB= new ArrayList<Customer>();
    private static final ArrayList<Admin> ADMIN_DB= new ArrayList<Admin>();
    private static final ArrayList<Product> PRODUCT_DB= new ArrayList<Product>();
    private static final ArrayList<Order> ORDER_DB= new ArrayList<Order>();

    //TODO dummy data
  static { 
        try {

        Customer c1 = new Customer("John Doe", "john@gmail.com", "password", Gender.MALE,Category.ELECTRONICS);
        Customer c2 = new Customer("Jane Doe", "jane@gmail.com", "password", Gender.FEMALE,Category.CLOTHING);

        CUSTOMER_DB.add(c1);
        CUSTOMER_DB.add(c2);




            // // Load image from resources using ClassLoader
            // InputStream laptopImageStream = Database.class.getClassLoader().getResourceAsStream("resources/laptop.png");
            // InputStream tshirtImageStream = Database.class.getClassLoader().getResourceAsStream("resources/blackShirt.png");
            
            // if (laptopImageStream != null && tshirtImageStream != null) {
            //     // Read images as BufferedImage
            //     BufferedImage laptopImage = ImageIO.read(laptopImageStream);
            //     BufferedImage tshirtImage = ImageIO.read(tshirtImageStream);
            
                // // Convert BufferedImage to JavaFX Image
                // Image productImage1 = SwingFXUtils.toFXImage(laptopImage, null);
                // Image productImage2 = SwingFXUtils.toFXImage(tshirtImage, null);

            // Create product objects
            // Electronics
            PRODUCT_DB.add(new Product(1000.0, 50, "ASUS Laptop", "High-end gaming laptop", "ASUS", Category.ELECTRONICS, "resources/laptop.png"));
            PRODUCT_DB.add(new Product(1200.0, 30, "Apple iPhone 13", "Latest Apple smartphone", "Apple", Category.ELECTRONICS, "resources/iphone13.png"));
            PRODUCT_DB.add(new Product(800.0, 40, "Samsung Galaxy Tab", "10-inch tablet", "Samsung", Category.ELECTRONICS, "resources/galaxytab.png"));

            // Clothing
            PRODUCT_DB.add(new Product(15.0, 500, "Black T-shirt", "Basic cotton t-shirt", "Nike", Category.CLOTHING, "resources/blackShirt.png"));
            PRODUCT_DB.add(new Product(60.0, 300, "Blue Jeans", "Slim fit jeans", "Levi's", Category.CLOTHING, "resources/blueJeans.png"));
            PRODUCT_DB.add(new Product(120.0, 100, "Leather Jacket", "Stylish leather jacket", "Zara", Category.CLOTHING, "resources/leatherJacket.png"));

            // Furniture
            PRODUCT_DB.add(new Product(300.0, 20, "Office Chair", "Ergonomic office chair", "IKEA", Category.FURNITURE, "resources/officeChair.png"));
            PRODUCT_DB.add(new Product(700.0, 10, "Dining Table", "6-person wooden dining table", "IKEA", Category.FURNITURE, "resources/diningTable.png"));

            // Sports
            PRODUCT_DB.add(new Product(50.0, 150, "Football", "FIFA-approved football", "Adidas", Category.SPORTS, "resources/football.png"));
            PRODUCT_DB.add(new Product(25.0, 200, "Yoga Mat", "Non-slip yoga mat", "Liforme", Category.SPORTS, "resources/yogaMat.png"));

            // Books
            PRODUCT_DB.add(new Product(15.0, 300, "Java Programming Basics", "Learn Java programming", "O'Reilly", Category.BOOKS, "resources/javaBook.png"));
            PRODUCT_DB.add(new Product(20.0, 250, "Cybersecurity Essentials", "A guide to cybersecurity", "Cisco Press", Category.BOOKS, "resources/cybersecurityBook.png"));

                
                Admin a1 = new Admin("Montaser", "montaser@gmail.com", "password", Gender.MALE, "Development","8-4");
                Admin a2 = new Admin("Yousuf", "youssef@gmail.com", "password", Gender.MALE, "Development","9-5");        
                ADMIN_DB.add(a1);
                ADMIN_DB.add(a2);
            
    } 
    catch(Exception e) {
        e.printStackTrace();
    }
  }
    
    public static ArrayList<Customer> getCustomerDB(){
        return CUSTOMER_DB;
    }
    public static ArrayList<Admin> getAdminDB(){
        return ADMIN_DB;
    }
    public static ArrayList<Product> getProductDB(){
        return PRODUCT_DB;
    }
    public static ArrayList<Order> getOrderDB(){
        return ORDER_DB;
    }
}

