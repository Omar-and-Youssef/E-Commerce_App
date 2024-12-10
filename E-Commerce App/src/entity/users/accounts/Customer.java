package entity.users.accounts;
import entity.products.*;
import entity.promotions.*;
import entity.users.details.*;
import java.util.*;
public class Customer extends User{
    private static int customerCounter; 

    private int balance;
    private final Cart CART;
    private final Wishlist WISH_LIST;
    private final ArrayList<Order>ORDERS; //MY ORDERS, WHICH SHOULD BE ADDED TO ORDERS DATABASE
    private final Membership MEMBERSHIP;
    private final ArrayList<Points> COLLECTED_POINTS;
    private final ArrayList<Coupon> COLLECTED_COUPON;
    //TODO customer should have some way to see all reviews he left
    //TODO cutomer should be able to see tickets he left
    private final ArrayList<Review> REVIEWS_SUBMITTED;   
    private final ArrayList<HelpTicket> HELP_TICKETS_SUBMITTED; 
    private Category preferredCategory;
//=======================================Constructor===================================
    public Customer(String name,String email,String password,Gender gender,String phoneNumber,
    String address,Category preferredCategory){
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
    public Customer(String name,String email,String password,Gender gender,
    Category preferredCategory){
        this(name,email,password,gender,"NA","NA",preferredCategory);
    }

    public Customer(String name,String email,String password, String address,Gender gender, 
    Category preferredCategory){
        this(name,email,password,gender,"NA",address,preferredCategory);
    }

    public Customer(String name,String email,String password,Gender gender,String phoneNumber,
    Category preferredCategory){
        this(name,email,password,gender,phoneNumber,"NA",preferredCategory);
    }
    public Customer(String name, String email, String password, String phoneNumber, String address, Gender gender){
        this(name, email, password, gender, phoneNumber, address, null);
    }
//=======================================Get&Set=======================================

    public int getBalance() {
        return balance;
    }
    public Cart getCart() {
        return CART;
    }
    public Wishlist getWishList() {
        return WISH_LIST;
    }
    public ArrayList<Order> getOrders() {
        return ORDERS;
    }
    public Membership getMembership() {
        return MEMBERSHIP;
    }
    public ArrayList<Points> getCollectedPoints() {
        return COLLECTED_POINTS;
    }
    public ArrayList<Coupon> getCollectedCoupons() {
        return COLLECTED_COUPON;
    }
    public ArrayList<Review> getReviewsSubmitted() {
        return REVIEWS_SUBMITTED;
    }
    public ArrayList<HelpTicket> getHelpTicketsSubmitted() {
        return HELP_TICKETS_SUBMITTED;
    }
    public Category getPreferredCategory() {
        return preferredCategory;
    }
    public void setPreferredCategory(Category preferredCategory) {
        this.preferredCategory = preferredCategory;
    }
}