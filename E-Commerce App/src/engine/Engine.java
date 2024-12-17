package engine;

import java.util.ArrayList;

import entity.products.Category;
import entity.products.Product;
import entity.users.details.Cart;
import entity.users.details.CartItem;
import exceptions.ServiceException;
import entity.users.accounts.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;
import service.*;
//TODO Handle alerts in controller
public class Engine {
    private Scene loginScene;
    private LoginController loginController;

    private Scene signUpScene;
    private SignUpController signUpController;

    private Scene homeScene;
    private HomeController homeController;

    private Scene productScene;
    private ProductController productController;

    private Scene cartScene;
    private CartController cartController;

    private Scene wishlistScene;
    // private WishlistController wishlistController;
    
    private Scene ordersScene;
    // private OrdersController ordersController;

    private Scene adminDashboardScene;
    // private AdminDashboardController adminDashboardController;
    
    private Scene adminProductScene;
    // private AdminProductController adminProductController;

    private Stage stage;
    private Screen currentScreen;
    
    private Product viewedProduct;
    private User currentUser;
    private Customer currentCustomer;
    private Admin currentAdmin;
    private AdminService adminService;
    private CustomerService customerService;
    public Engine(Stage stage){
        try{
            adminService = new AdminService();
            customerService = new CustomerService();
            initializeScenes();
            this.stage=stage;
            currentScreen=Screen.LOGIN;
            stage.setScene(loginScene);
            stage.setResizable(false);
            stage.setTitle("E-CommerceApp");
            stage.show();
            viewedProduct = new Product();
        }        
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void initializeScenes() throws Exception{ 
            FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("LogIn.fxml"));
            loginScene = new Scene(loginLoader.load());
            loginController =(LoginController) loginLoader.getController();
            loginController.setEngine(this);

            FXMLLoader signUpLoader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
            signUpScene = new Scene(signUpLoader.load());
            signUpController =(SignUpController) signUpLoader.getController();
            signUpController.setEngine(this);


            FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
            homeScene = new Scene(homeLoader.load());
            homeController =(HomeController) homeLoader.getController();
            homeController.setEngine(this);
            if(adminService.getAllProducts().size()<6) 
                homeController.getnavRightButton().setVisible(false);
            
            homeController.getnavLeftButton().setVisible(false);
            
            homeController.populateProducts(adminService.getAllProducts(),0);
            
            FXMLLoader productLoader = new FXMLLoader(getClass().getResource("Product.fxml"));
            productScene = new Scene(productLoader.load());
            productController =(ProductController) productLoader.getController();
            productController.setEngine(this);


            FXMLLoader cartLoader = new FXMLLoader(getClass().getResource("Cart.fxml"));
            cartScene = new Scene(cartLoader.load());
            cartController =(CartController) cartLoader.getController();
            cartController.setEngine(this);

            // FXMLLoader ordersLoader = new FXMLLoader(getClass().getResource("Orders.fxml"));
            // ordersScene = new Scene(ordersLoader.load());
            // setControllerEngine(ordersLoader);

            // FXMLLoader wishlistLoader = new FXMLLoader(getClass().getResource("Wishlist.fxml"));
            // wishlistScene = new Scene(wishlistLoader.load());
            // setControllerEngine(wishlistLoader);
            
            // FXMLLoader adminDashboardLoader = new FXMLLoader(getClass().getResource("AdminDashboard.fxml"));
            // adminDashboardScene = new Scene(adminDashboardLoader.load());
            // setControllerEngine(adminDashboardLoader);

            // FXMLLoader adminProductLoader = new FXMLLoader(getClass().getResource("AdminProduct.fxml"));
            // adminProductScene = new Scene(adminProductLoader.load());
            // setControllerEngine(adminProductLoader);
    }
    public void switchScene(Screen nextScreen){
        currentScreen=nextScreen;
        switch(currentScreen){
            case SIGNUP:
                signUpController.geterrorLabel().setVisible(false);
                signUpController.getCategoryChoiceBox().getItems().addAll("Electronics","Books","Clothing",
                "Home",
                "Beauty",
                "Toys",
                "Sports");
                stage.setScene(signUpScene);
                break;
            case HOME: 
                homeController.displayName(currentUser.getName());
                stage.setScene(homeScene);
                break;
            case PRODUCT:
                stage.setScene(productScene);
                cartController.getOrderSuccessLabel().setVisible(false);
                break;
            case CART:
                cartController.populateCart(0);
                stage.setScene(cartScene);
                break;
            case ORDERS:
                stage.setScene(ordersScene);
                break;
            case WISH_LIST:
                stage.setScene(wishlistScene);
                break;
            case ADMIN_DASHBOARD:
                stage.setScene(adminDashboardScene);
                break;
            case ADMIN_PRODUCT:
                stage.setScene(adminProductScene);
                break;
            case LOGIN:
                stage.setScene(loginScene); 
                //after user logged out, we save cart and wishlist in database
                break;
            default: break;
        }
    }
    public User logIn(String email,String password) throws ServiceException{
            User user=customerService.logIn(email, password);
            if(user!=null){
                currentCustomer=(Customer)user;
                return user;
            }
            user=adminService.logIn(email, password);
            return user;
        
    }
    public void signUp(Customer customer){
        customerService.registerCustomer(customer);
        cartController.populateCart(0);
    }
    public boolean isValidEmail(String email){
        return customerService.isValidEmail(email);
    }
    public void addToCart(int quantity){
        customerService.addToCart(currentCustomer, viewedProduct, quantity);
        cartController.populateCart(0);
    }
    public void removeFromCart(int i){
        customerService.removeFromCart(currentCustomer, null);
        cartController.populateCart(0);
    }
    
    public Stage getStage(){
        return stage;
    }
    public User getCurrentUser(){
        return currentUser;
    }
    public void setCurrentUser(User user){
        currentUser=user;
    }
    public void setViewedProduct(Product product){
        viewedProduct=product;
    }
    public Product getViewedProduct(){
        return viewedProduct;
    }
    public ArrayList<Product> getProductDatabase(){
        return adminService.getAllProducts();
    }
    public ArrayList<Product> getProductsByCategory(Category category){
        return adminService.getProductsByCategory(category);
    }
    public HomeController getHomeController(){
        return homeController;
    }
    public ProductController getProductController(){
        return productController;
    }
    public Customer getCurrentCustomer(){
        return currentCustomer;
    }
    public int getIntegerRating(Product product){
        try{
            return(int) Math.floor(product.getRating());
        }
        catch(Exception e){
            return 0;
        }
    }

} 