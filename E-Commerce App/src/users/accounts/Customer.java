package users.accounts;
import users.details.*;

public class Customer extends User{
    private MembershipType membershipType;
    private static int customerCounter; 
//=======================================Constructor===================================
    public Customer(String name,String email,String password,Gender gender,String phoneNumber,
    String address){
        super("C"+customerCounter++,name,email,password,phoneNumber,address,gender);
        this.membershipType=MembershipType.STANDARD;
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
//=======================================Get&Set=======================================
    public MembershipType getMembershipType() {
        return membershipType;
    }
//addToCart(Product),incrementCartItem(CartItem),placeOrder(Order), 
//getOrderHistory(Order), getOrderStatus(Order), createHelpTicket(), applyCoupon()

}