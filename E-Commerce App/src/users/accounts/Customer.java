package users.accounts;
import products.Product;
import users.details.*;

public class Customer extends User{
    private MembershipType membershipType;
    private static int customerCounter; 
    private final Cart cart;
//=======================================Constructor===================================
    public Customer(String name,String email,String password,Gender gender,String phoneNumber,
    String address){
        super("C"+customerCounter++,name,email,password,phoneNumber,address,gender);
        this.membershipType=MembershipType.STANDARD;
        cart = new Cart();
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
//addToCart(Product),placeOrder(Order), 
//getOrderHistory(Order), getOrderStatus(Order), createHelpTicket(), applyCoupon()

    public void addToCart(Product product,int quantity){
        CartItem cartItem = new CartItem(product, quantity);
        cart.addItem(cartItem);
    }

//=======================================Get&Set=======================================
    public MembershipType getMembershipType() {
        return membershipType;
    }


}