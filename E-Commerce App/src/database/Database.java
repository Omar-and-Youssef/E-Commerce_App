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

    //TODO dummy data 
    static {
        Product p1=new Product(1000.0, 50, "ASUS Laptop", 
        "High-end gaming laptop", "ASUS", Category.ELECTRONICS);
        Product p2=new Product(15.0, 500, "Black t-shirt", 
        "Basic t-shirt ", "Nike", Category.CLOTHING);

        PRODUCT_DB.add(p1);
        PRODUCT_DB.add(p2);

        Customer c1 = new Customer("John Doe", "john@gmail.com", "password", Gender.MALE);
        Customer c2 = new Customer("Jane Doe", "jane@gmail.com", "password", Gender.FEMALE);

        CUSTOMER_DB.add(c1);
        CUSTOMER_DB.add(c2);

        Admin a1= new Admin("Admin1", "admin@example.com", "adminpassword", Gender.FEMALE);
        Admin a2 = new Admin("Admin2", "admin2@example.com", "adminpassword", Gender.MALE);
        
        ADMIN_DB.add(a1);
        ADMIN_DB.add(a2);
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
