package engine;

import java.util.ArrayList;
import entity.products.Category;
import entity.products.Product;
import entity.users.details.CartItem;
import entity.users.details.Order;
import entity.users.accounts.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.stage.*;
import service.*;
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
    private WishListController wishlistController;
    
    private Scene ordersScene;
    private OrdersController ordersController;

    private Scene orderSummaryScene;
    private OrderSummaryController orderSummaryController;

    private Scene modifyScene;
    private ModifyProductController modifyController;

    private Scene analyticsScene;
    private AnalyticsController analyticsController;

    private Scene profileScene;
    private ProfileController profileController;

    private Scene customerDBScene;
    private CustomerDBController customerDBController;

    private Stage stage;
    private Screen currentScreen;


    
    private Product viewedProduct;
    private User currentUser;
    private Customer currentCustomer;
    private Order viewedOrder;
    private Admin currentAdmin;
    private AdminService adminService;
    private CustomerService customerService;
    private UserService userService;

    protected boolean isCustomer;
    protected String searchFromProductView;

    public Engine(Stage stage){
        try{
            userService = new UserService(); 
            adminService = new AdminService();
            customerService = new CustomerService();
            initializeScenes();
            this.stage=stage;
            currentScreen=Screen.LOGIN;
            stage.setScene(loginScene);
            stage.setResizable(false);
            stage.setTitle("Cadabra");
            Image star = new Image(getClass().getResourceAsStream("resources/wishlist.png"));
            stage.getIcons().add(star);
            stage.show();
            viewedProduct = new Product(); //TODO
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
            if(adminService.getTotalProducts()<6) 
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

            FXMLLoader ordersLoader = new FXMLLoader(getClass().getResource("Orders.fxml"));
            ordersScene = new Scene(ordersLoader.load());
            ordersController =(OrdersController) ordersLoader.getController();
            ordersController.setEngine(this);

            FXMLLoader orderSummaryLoader = new FXMLLoader(getClass().getResource("OrderSummary.fxml"));
            orderSummaryScene = new Scene(orderSummaryLoader.load());
            orderSummaryController =(OrderSummaryController) orderSummaryLoader.getController();
            orderSummaryController.setEngine(this);

            FXMLLoader wishlistLoader = new FXMLLoader(getClass().getResource("Wishlist.fxml"));
            wishlistScene = new Scene(wishlistLoader.load());
            wishlistController =(WishListController) wishlistLoader.getController();
            wishlistController.setEngine(this);

            FXMLLoader modifyLoader = new FXMLLoader(getClass().getResource("ModifyProduct.fxml"));
            modifyScene = new Scene(modifyLoader.load());
            modifyController =(ModifyProductController) modifyLoader.getController();
            modifyController.setEngine(this);

            FXMLLoader analyticsLoader = new FXMLLoader(getClass().getResource("Analytics.fxml"));
            analyticsScene = new Scene(analyticsLoader.load());
            analyticsController =(AnalyticsController) analyticsLoader.getController();
            analyticsController.setEngine(this);

            FXMLLoader profileLoader = new FXMLLoader(getClass().getResource("Profile.fxml")); 
            profileScene = new Scene(profileLoader.load());
            profileController =(ProfileController) profileLoader.getController();
            profileController.setEngine(this);
        
            FXMLLoader customerDBLoader = new FXMLLoader(getClass().getResource("CustomerDB.fxml"));
            customerDBScene = new Scene(customerDBLoader.load());
            customerDBController =(CustomerDBController) customerDBLoader.getController();
            customerDBController.setEngine(this);
    }
    public void switchScene(Screen nextScreen){
        currentScreen=nextScreen;
        switch(currentScreen){
            case SIGNUP:
                signUpController.geterrorLabel().setVisible(false);
                signUpController.populateChoiceBox();
                stage.setScene(signUpScene);
                break;
            case HOME: 
                homeController.displayName(currentUser.getName());
                homeController.clearSearch();
                homeController.setNavigationMenu(isCustomer);
                stage.setScene(homeScene);
                break;
            case PRODUCT:
                productController.configureScreenByRole();
                stage.setScene(productScene);
                break;
            case CART:
                cartController.cartItemCount=0;
                cartController.populateCart(0);
                cartController.updateOrderTotal();
                cartController.getPaymentChoiceBox().getItems().clear();
                cartController.getPaymentChoiceBox().getItems().addAll(
                    "Wallet","Credit Card", "Cash On Delivery");
                stage.setScene(cartScene);
                break;
            case ORDERS:
                ordersController.populateOrders(0);
                stage.setScene(ordersScene);
                break;
            case ORDER_SUMMARY:
                stage.setScene(orderSummaryScene);
                break;
            case WISH_LIST:
                wishlistController.wishListCount=0;
                wishlistController.populateWishlist(0);
                stage.setScene(wishlistScene);
                break;
            case LOGIN:
                stage.setScene(loginScene); 
                break;
            case MODIFYPRODUCT:
                if(productController.getIsUpdating())
                modifyController.setScreen(viewedProduct);
                else modifyController.setScreen(null);
                stage.setScene(modifyScene);
                break;
            case ANALYTICS:
                analyticsController.updateAnalytics();
                stage.setScene(analyticsScene);
                break;
            case PROFILE:
                profileController.setUser(currentUser);
                stage.setScene(profileScene);
                break;
            case CUSTOMER_DB:
                customerDBController.populateCustomers(0);
                stage.setScene(customerDBScene);
                break;
            default: break;
        }
    }
    
//=================================CUSTOMER===================================    
    public boolean logIn(String email, String password) {  
        if (!userService.userInDB(email)) {
            return false;
        }

        currentUser = customerService.logIn(email, password);
        if (currentUser != null) {
            currentCustomer = (Customer) currentUser;
            isCustomer=true;
            return true;
        }
        currentUser = adminService.logIn(email, password);
        if (currentUser != null) {
            currentAdmin = (Admin) currentUser;
            isCustomer=false;
            return true;
        }
        currentUser = null;
        return false;
    }
    public void logout(){
        currentUser=null;
        currentAdmin=null;
        currentCustomer=null;
    }
    public void signUp(Customer customer){
        customerService.registerCustomer(customer);
        logIn(customer.getEmail(),customer.getPassword());
        cartController.populateCart(0);
    }
    public boolean isValidEmail(String email){
        return customerService.isValidEmail(email);
    }
    public boolean isEmptyCart(){
        return customerService.isEmptyCart(currentCustomer);
    }
    public void addToCart(int quantity){
        customerService.addToCart(currentCustomer, viewedProduct, quantity);
        cartController.populateCart(0);
        cartController.updateOrderTotal();
    }
    public void removeFromCart(int i){
        customerService.removeFromCart(currentCustomer,i);
    }
    public void setQuantityInCart(int index,int value){
        customerService.setQuantityInCart(currentCustomer,index,value);
    }
    public void incrementCartItem(int index){
        customerService.incrementCartItem(currentCustomer, index);
    }
    public void decrementCartItem(int index){
        customerService.decrementCartItem(currentCustomer,index);
    }
    public void addToWishlist(){
        customerService.addToWishlist(currentCustomer, viewedProduct);
    }
    public void removeFromWishList(){
        customerService.removeFromWishlist(currentCustomer, viewedProduct);
    }
    public void removeFromWishList(int index){
        customerService.removeFromWishlist(currentCustomer, index);
    }
    public boolean isInWishList(){
        return customerService.isInWishList(currentCustomer, viewedProduct);
    }

    public double getCartTotal(){
        return customerService.getCartTotal(currentCustomer);
    }
    public boolean validWalletPayment(){
        return customerService.validWalletPayment(currentCustomer,currentCustomer.getCart().getTotalPrice());
    }
    public void orderByWallet(String address){
        customerService.deductFromWallet(currentCustomer,currentCustomer.getCart().getTotalPrice());
        customerService.placeOrder(currentCustomer, address, "Wallet");
        adminService.updateTotalRevenue();
        cartController.populateCart(0);
        cartController.updateOrderTotal();
    }
    public void orderByCOD(String address){
        customerService.placeOrder(currentCustomer,address, "Cash On Delivery");
        adminService.updateTotalRevenue();

        cartController.populateCart(0);
        cartController.updateOrderTotal();
    }
    public void cancelOrder(){
        customerService.cancelOrder(viewedOrder);
        adminService.updateTotalRevenue();
        adminService.setTotalRevenue(adminService.getTotalRevenue()-viewedOrder.getOrderTotal());
        ordersController.populateOrders(-1);//from where we left off, but updated
    }
//==================================ADMIN====================================
    public void deleteViewedProduct(){
        try{
        adminService.deleteProduct(viewedProduct);
        }
        catch(Exception ex){
            System.err.print(ex.getMessage());
            ex.printStackTrace();
        }
    }
    public boolean addProduct(Product product){
        return adminService.addProduct(product);
    }
    public void updateProduct(Product product,String name,
    String brand, Double price, String desc, int stockQuantity, 
    Category category, String imagePath){
        try{
        adminService.updateProduct(product,name,
        brand, price, desc, stockQuantity, 
        category, imagePath);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();   
        }
    }
    public void updateOrderStatus(){
        adminService.updateOrderStatus(viewedOrder);
    }
//=================================Get&Set====================================
    public Stage getStage(){
        return stage;
    }
    public User getCurrentUser(){
        return currentUser;
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
    public ArrayList<CartItem> getCartItems(){
        return currentCustomer.getCart().getCartItems();
    }
    public int getIntegerRating(Product product){
        try{
            return(int) Math.floor(product.getRating());
        }
        catch(Exception e){
            return 0;
        }
    }
    public ArrayList<Order> getCustomerOrders(){
        return currentCustomer.getOrders();
    }
    public ArrayList<Order> getAllOrders(){
        return adminService.getAllOrders();
    }
    public ArrayList<Customer> getCustomerDB(){
        return adminService.getCustomerDB();
    }
    public void setViewedOrder(Order order){
        this.viewedOrder=order;
        orderSummaryController.setOrder(viewedOrder);
        orderSummaryController.fillOrderSummary();
    }
    public ModifyProductController getModifyController(){
        return modifyController;
    }
    public double getTotalRevenue(){
        return adminService.getTotalRevenue();
    }   

    public int getTotalOrders(){
        return adminService.getTotalOrders();
    }

    public int getTotalProducts(){
        return adminService.getTotalProducts();
    }
    public int getTotalCustomers(){
        return adminService.getAllCustomers();
    }
    public double getAvgOrders(){
        return adminService.getAvgOrders();
    }
    public Product getBestProduct(){
        return adminService.getBestSellingProduct();
    }
    // public AdminDBViewController getAdminDBViewController(){
    //     return adminDBViewController;
    // }
} 