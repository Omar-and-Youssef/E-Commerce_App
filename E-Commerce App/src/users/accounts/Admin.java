package users.accounts;

public class Admin extends User {
    private static int adminCounter;
    //private Department department;

//=======================================Constructor===================================

    public Admin(String userId,String name,String email,String password,String phoneNumber,
    String address,char gender){
        super(userId,name,email,password,phoneNumber,address,gender);
    }

    public Admin(String userId,String name,String email,String password){
        super(userId,name,email,password);
    }

    public Admin(String userId,String name,String email,String password,char gender, String address){
        super(userId,email,password,gender,address);
    }

    public Admin(String userId,String email,String password,String phoneNumber,char gender){
        super(userId,email,password,phoneNumber,gender);
}
//=======================================Get&Set=======================================
    

}
