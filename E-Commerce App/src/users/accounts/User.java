package users.accounts;
public abstract class User {
    private String email,password;
    protected String name,address,phoneNumber;
    protected char gender;
//=======================================Constructor===================================
    public User(String name,String email,String password,String phoneNumber,
           String address,char gender){

    }
    //Constructor for name email password gender
    //Constructor for name email password gender address
    //Constructor for name email password gender phoneNumber
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
