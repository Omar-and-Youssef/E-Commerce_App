package users.accounts;
import users.details.*;

public class Customer extends User{
    private MembershipType membershipType;
    private static int customerCounter;
//=======================================Constructor===================================
    public Customer(String name,String email,String password,char gender,String phoneNumber,
    String address){
        super("C"+customerCounter++,name,email,password,phoneNumber,address,gender);
        this.membershipType=MembershipType.STANDARD;
    }
    public Customer(String name,String email,String password,char gender){
        this(name,email,password,gender,"NA","NA");
    }

    public Customer(String name,String email,String password, String address,char gender){
        this(name,email,password,gender,"NA",address);
    }

    public Customer(String name,String email,String password,char gender,String phoneNumber){
        this(name,email,password,gender,phoneNumber,"NA");
    }
//=======================================Get&Set=======================================
    public MembershipType getMembershipType() {
        return membershipType;
    }


}