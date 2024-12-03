package users.accounts;
import users.details.*;

public class Customer extends User{
    
    private MembershipType membershipType;
    
//=======================================Constructor===================================
    public Customer(String name,String email,String password,String phoneNumber,
    String address,char gender,MembershipType membershipType){
        super(address,name,email,password,phoneNumber,address,gender);
        this.membershipType=membershipType;
    }
    public Customer(String userId,String name,String email,String password,MembershipType membershipType){
        super(userId,name,email,password);
        this.membershipType=membershipType;
    }

    public Customer(String userId,String name,String email,String password,char gender, String address, 
    MembershipType membershipType){
        super(userId,email,password,gender,address);
        this.membershipType=membershipType;
    }

    public Customer(String userId,String email,String password,String phoneNumber,char gender, 
    MembershipType membershipType){
        super(userId,email,password,phoneNumber,gender);
        this.membershipType=membershipType;
}
//=======================================Get&Set=======================================
    public MembershipType getMembershipType() {
        return membershipType;
    }


}