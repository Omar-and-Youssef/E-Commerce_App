package entity.users.accounts;
import entity.products.*;
import entity.promotions.*;
import entity.users.details.*;
import java.util.*;
public class Customer extends User{
    private static int customerCounter; 

    private int wallet;
    private final Cart CART;
    private final Wishlist WISH_LIST;
    private final ArrayList<Order>ORDERS;
    private final Membership MEMBERSHIP;
    private final ArrayList<Points> COLLECTED_POINTS;
    private final ArrayList<Coupon> COLLECTED_COUPON;
    //TODO customer should have some way to see all reviews he left
    //TODO cutomer should be able to see tickets he left
//=======================================Constructor===================================
    public Customer(String name,String email,String password,Gender gender,String phoneNumber,
    String address){
        super("C"+(++customerCounter),name,email,password,phoneNumber,address,gender);
        this.MEMBERSHIP=new Membership();
        CART = new Cart();
        WISH_LIST=new Wishlist();
        ORDERS=new ArrayList<Order>();
        COLLECTED_COUPON=new ArrayList<Coupon>();
        COLLECTED_POINTS=new ArrayList<Points>();
    }
    public Customer(String name,String email,String password,Gender gender){
        this(name,email,password,gender,"NA","NA");
    }

    public Customer(String name,String email,String password, String address,Gender gender){
        this(name,email,password,gender,"NA",address);
    }

    public Customer(String name,String email,String password,Gender gender,String phoneNumber){
        this(name,email,password,gender,phoneNumber,"NA");
    }
//=======================================Methods=======================================
    public void addReview(String comment,double rating,Product product ){
        product.addReview(new Review(product, this, rating,comment));
    }
    public void deleteReview(Product product){
        product.deleteReview(0);//TODO FIX THE DELETE REVIEW INDEX OR WHAT?

    }
    public void updateReview(String comment,int rating,Product product){
        deleteReview(product);
        //TODO FIX IMPLEMENTATION OFF DELETE FROM ALL ARRAYLIST STUFF (iS IT INDEX OR OBJECT)
        addReview(comment, rating, product);
    }
    public void upgradeMemberShip(Date expiryDate){
        MEMBERSHIP.upgradeMemberShip(expiryDate);
    }
    public void placeOrder(String shippingAddress, String paymentMethod){
        Order newOrder=new Order(this,CART.getCartItems(),shippingAddress,paymentMethod);
        CART.clearCart();
        ORDERS.add(newOrder);
    }   
    public void cancelOrder(Order order){
        order.cancelOrder();//TODO should remove?? based on Implementation
    }
    public void addToWishlist(Product product){
        WISH_LIST.addProduct(product);
    }
    public void removeFromWishlist(int index){
        WISH_LIST.removeProduct(index);
    }
    public void addToCart(Product product,int quantity){
        CART.addItem(new CartItem(product, quantity));
    }
    public void deleteFromCart(int index){
        CART.deleteItem(index);
    }
    public void submitHelpTicket(String complaint){
        HelpTicket ticket=new HelpTicket(this, complaint);
        //TODO there should be an algorithm to assign to the appropirate admin in the engine
    }
    //TODO should add increment/decrement?
    //TODO ADD PRODUCTS TO STORE
    //T
//=======================================Get&Set=======================================
    
}