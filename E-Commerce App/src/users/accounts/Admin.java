package users.accounts;
import users.details.*;

public class Admin extends User {

    private Department department;

//=======================================Constructor===================================

    public Admin(String userId,String name,String email,String password,String phoneNumber,
    String address,char gender,Department department){
        super(userId,name,email,password,phoneNumber,address,gender);
        this.department=department;
    }

    public Admin(String userId,String name,String email,String password,Department department){
        super(userId,name,email,password);
        this.department=department;
    }

    public Admin(String userId,String name,String email,String password,char gender, String address, 
    Department department){
        super(userId,email,password,gender,address);
        this.department=department;
    }

    public Admin(String userId,String email,String password,String phoneNumber,char gender, 
    Department department){
        super(userId,email,password,phoneNumber,gender);
        this.department=department;
}
//=======================================Get&Set=======================================
    public Department getDepartment() {
        return department;
    }

}
