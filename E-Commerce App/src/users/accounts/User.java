package users.accounts;
public abstract class User {
    private String email,password;
    protected String userId,name,address,phoneNumber;
    protected char gender;
//=======================================Constructor===================================

    public User(String userId,String name,String email,String password,String phoneNumber,
           String address,char gender){
            this.userId=userId;
            this.name=name;
            this.email=email;
            this.password=password;
            this.phoneNumber=phoneNumber;
            this.address=address;
            this.gender=gender;
    }
//id,name,email,password
    public User(String userId,String name,String email,String password){
        this.userId=userId;
        this.name=name;
        this.email=email;
        this.password=password;
}
//id,email,password,gender,address
    public User(String userId,String email,String password,char gender, String address){
        this.userId=userId;
        this.email=email;
        this.password=password;
        this.address=address;
        this.gender=gender;
}
//id,email,password,phonenumber,gender
    public User(String userId,String email,String password,String phoneNumber,char gender){
        this.userId=userId;
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
    public char getGender() {
        return gender;
    }
    public void setGender(char gender) {
        this.gender = gender;
    }

}
