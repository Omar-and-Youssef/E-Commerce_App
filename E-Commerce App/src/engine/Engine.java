package engine;

import database.Database;
import entity.products.Product;
import entity.users.details.Cart;
import entity.users.accounts.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;
import service.*;

public class Engine {
    private Scene loginScene;
    // private LoginController loginController;

    private Scene signUpScene;
    // private SignUpController signUpController;

    private Scene homeScene;
    // private HomeController homeController;

    private Scene productScene;
    // private ProductController productController;

    private Scene cartScene;
    // private CartController cartController;

    private Scene wishlistScene;
    // private WishlistController wishlistController;
    
    private Scene ordersScene;
    // private OrdersController ordersController;

    private Scene adminDashboardScene;
    // private AdminDashboardController adminDashboardController;
    
    private Scene adminProductScene;
    // private AdminProductController adminProductController;

    private FXMLLoader loginLoader,signUpLoader,homeLoader,productLoader,cartLoader,
    ordersLoader,wishlistLoader,adminDashboardLoader,adminProductLoader;

    private Stage stage;
    private Screen currentScreen;
    
    private Product viewedProduct;
    private Cart cart;
    private User currentUser;
    private Database database;

    public Engine(Stage stage){
        try{
        this.stage=stage;
        currentScreen=Screen.LOGIN;
        database= new Database();
        initializeScenes();
        stage.setScene(loginScene); 
        stage.show();
        }        
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void initializeScenes() throws Exception{        
            //we load all the scenes 
            loginLoader = new FXMLLoader(getClass().getResource("/gui/LogIn.fxml"));
            loginScene = new Scene(loginLoader.load());
            // setControllerEngine(loginLoader);

            // signUpLoader = new FXMLLoader(getClass().getResource("/gui/SignUp.fxml"));
            // signUpScene = new Scene(signUpLoader.load());
            // setControllerEngine(signUpLoader);

            // homeLoader = new FXMLLoader(getClass().getResource("/gui/Home.fxml"));
            // homeScene = new Scene(homeLoader.load());
            // setControllerEngine(homeLoader);

            // productLoader = new FXMLLoader(getClass().getResource("/gui/Product.fxml"));
            // productScene = new Scene(productLoader.load());
            // setControllerEngine(productLoader);

            // cartLoader = new FXMLLoader(getClass().getResource("/gui/Cart.fxml"));
            // cartScene = new Scene(cartLoader.load());
            // setControllerEngine(cartLoader);

            // ordersLoader = new FXMLLoader(getClass().getResource("/gui/Orders.fxml"));
            // ordersScene = new Scene(ordersLoader.load());
            // setControllerEngine(ordersLoader);

            // wishlistLoader = new FXMLLoader(getClass().getResource("/gui/Wishlist.fxml"));
            // wishlistScene = new Scene(wishlistLoader.load());
            // setControllerEngine(wishlistLoader);
            
            // adminDashboardLoader = new FXMLLoader(getClass().getResource("/gui/AdminDashboard.fxml"));
            // adminDashboardScene = new Scene(adminDashboardLoader.load());
            // setControllerEngine(adminDashboardLoader);

            // adminProductLoader = new FXMLLoader(getClass().getResource("/gui/AdminProduct.fxml"));
            // adminProductScene = new Scene(adminProductLoader.load());
            // setControllerEngine(adminProductLoader);
    }
    public void switchCustomerScene(Screen nextScreen){
        currentScreen=nextScreen;
        switch(currentScreen){
            case SIGNUP:
                stage.setScene(signUpScene);
                break;
            case HOME: 
                //HomeController homeController=homeLoader.getController();
                //homeController.displayName(currentUser.getName());
                stage.setScene(homeScene);
                break;
            case PRODUCT:
                // ProductController productController = productLoader.getController();
                // change viewed product in controller so when we go back we get the same product
                //if we want to display the same product
                // update cart, wishlist if invoked(in controller)
                stage.setScene(productScene);
                break;
            case CART:
                //will have checkout here, will empty cart after checkout
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
    public User logIn(String email,String password){
        try{
        User user=CustomerService.logIn(email, password);
        if(user!=null){
            currentUser=user;
            return user;
        }
        else {
            user=AdminService.logIn(email, password);
            return user;
        }
        }
        catch(Exception e){
            //TODO Handle alert in controller
            System.out.println("Invalid login credentials");
            return null;
        }
    }
    private void setControllerEngine(FXMLLoader loader) {
        BaseController controller = loader.getController();
        if (controller != null) {
            controller.setEngine(this);
        }
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
    public Database getDatabase(){
        return database;
    }
} 