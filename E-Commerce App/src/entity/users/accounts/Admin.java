package entity.users.accounts;

public class Admin extends User {
    private static int adminCounter;
    //private Department department;

//=======================================Constructor===================================

public Admin(String name,String email,String password,Gender gender,String phoneNumber,
String address){
    super("A"+adminCounter++,name,email,password,phoneNumber,address,gender);
}
public Admin(String name,String email,String password,Gender gender){
    this(name,email,password,gender,"NA","NA");
}

public Admin(String name,String email,String password, String address,Gender gender){
    this(name,email,password,gender,"NA",address);
}

public Admin(String name,String email,String password,Gender gender,String phoneNumber){
    this(name,email,password,gender,phoneNumber,"NA");
}

//=======================================Methods=======================================
//addProduct(), removeProduct(), viewStatistics(), createCategory(), addToCategory()

//=======================================Get&Set=======================================
}
