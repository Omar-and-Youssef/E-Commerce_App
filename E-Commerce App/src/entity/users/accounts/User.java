package entity.users.accounts;
public abstract class User {
    private final String USER_ID;
    private String email,password;
    protected String name,address,phoneNumber;
    protected Gender gender;
//=======================================Constructor===================================

    public User(String USER_ID,String name,String email,String password,String phoneNumber,
           String address,Gender gender){
            this.USER_ID=USER_ID;
            this.name=name;
            this.email=email;
            this.password=password;
            this.phoneNumber=phoneNumber;
            this.address=address;
            this.gender=gender;
    }
//id,name,email,password
    public User(String USER_ID,String name,String email,String password){
        this.USER_ID=USER_ID;
        this.name=name;
        this.email=email;
        this.password=password;
}
//id,email,password,gender,address
    public User(String USER_ID,String email,String password,Gender gender, String address){
        this.USER_ID=USER_ID;
        this.email=email;
        this.password=password;
        this.address=address;
        this.gender=gender;
}
//id,email,password,phonenumber,gender
    public User(String USER_ID,String email,String password,String phoneNumber,Gender gender){
        this.USER_ID=USER_ID;
        this.email=email;
        this.password=password;
        this.phoneNumber=phoneNumber;
        this.gender=gender;
}
//=======================================Methods=======================================
    public void changePassword(String newPassword){ 
        //Put Conditions + salting+ hashing 
    }   
//=======================================Get&Set=======================================
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public String getUserID(){
        return USER_ID;
    }

}
