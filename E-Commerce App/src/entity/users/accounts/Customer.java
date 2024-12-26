package entity.users.accounts;
import entity.products.*;

import entity.users.details.*;
import java.util.*;
public class Customer extends User{
    private static int customerCounter; 

    private double wallet;
    private final Cart CART;
    private final Wishlist WISH_LIST;
    private final ArrayList<Order>ORDERS;
    private final Membership MEMBERSHIP;
    private final ArrayList<Review> REVIEWS_SUBMITTED;   
    private final ArrayList<HelpTicket> HELP_TICKETS_SUBMITTED; 
    private Category preferredCategory;
//=======================================Constructor===================================
    public Customer(String name,String email,String password,Gender gender,String phoneNumber,
    String address,Category preferredCategory){
        super("C"+(++customerCounter),name,email,password,phoneNumber,address,gender);
        this.MEMBERSHIP=new Membership();
        CART =new Cart();
        WISH_LIST=new Wishlist();
        ORDERS=new ArrayList<Order>();
        REVIEWS_SUBMITTED=new ArrayList<Review>();
        HELP_TICKETS_SUBMITTED=new ArrayList<HelpTicket>();
        this.wallet=5000;
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
    public double getWallet(){
        return wallet;
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
    public void setWallet(double wallet) {
        this.wallet = wallet;
    }
    public void addOrder(Order order){
        ORDERS.add(order);
    }
    public int getCartQuantity(Product p){
        return CART.getCartQuanity(p);
    }
    @Override
    public String toString() {
        return "Customer [name=" + name + ", address=" + address + ", phoneNumber=" + phoneNumber + ", gender=" + gender
                + "]";
    }
}