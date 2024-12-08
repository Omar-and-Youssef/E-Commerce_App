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
    private final ArrayList<Review> REVIEWS_SUBMITTED;   
    private final ArrayList<HelpTicket> HELP_TICKETS_SUBMITTED; 
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
        REVIEWS_SUBMITTED=new ArrayList<Review>();
        HELP_TICKETS_SUBMITTED=new ArrayList<HelpTicket>();
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
        Review review=new Review(product, this, rating, comment);
        REVIEWS_SUBMITTED.add(review);
        product.addReview(review);
    }
    public void deleteReview(Product product){
        Review foundReview=null;
        for(Review rev:REVIEWS_SUBMITTED){
            if(rev.getProduct().equals(product)){
                foundReview=rev;
                REVIEWS_SUBMITTED.remove(rev);
                break;
            }
        }
        product.deleteReview(foundReview);//TODO FIX THE DELETE REVIEW INDEX OR WHAT?
        
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
        ORDERS.remove(order);
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
        HELP_TICKETS_SUBMITTED.add(ticket);
        //TODO there should be an algorithm to assign to the appropirate admin in the engine
    }
    public void incrementCartItem(int index){
        CART.incrementCartItem(index);
    }
    public void decrementCartItem(int index){
        CART.decrementCartItem(index);
    }
    //TODO should add increment/decrement?
    //TODO ADD PRODUCTS TO STORE
    //T
//=======================================Get&Set=======================================

    public int getWallet() {
        return wallet;
    }
    public Cart getCART() {
        return CART;
    }
    public Wishlist getWISH_LIST() {
        return WISH_LIST;
    }
    public ArrayList<Order> getORDERS() {
        return ORDERS;
    }
    public Membership getMEMBERSHIP() {
        return MEMBERSHIP;
    }
    public ArrayList<Points> getCOLLECTED_POINTS() {
        return COLLECTED_POINTS;
    }
    public ArrayList<Coupon> getCOLLECTED_COUPON() {
        return COLLECTED_COUPON;
    }
    public ArrayList<Review> getREVIEWS_SUBMITTED() {
        return REVIEWS_SUBMITTED;
    }
    public ArrayList<HelpTicket> getHELP_TICKETS_SUBMITTED() {
        return HELP_TICKETS_SUBMITTED;
    }
    
}