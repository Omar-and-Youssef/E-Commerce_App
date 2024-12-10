package entity.users.accounts;
import entity.users.details.HelpTicket;
public class Admin extends User {
    private static int adminCounter;
    private HelpTicket helpTicket;
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
public void assignHelpTicket(HelpTicket helpTicket){
    this.helpTicket=helpTicket;
}
public void helpTicketResolved(){
    this.helpTicket=null;
}
//=======================================Get&Set=======================================

public HelpTicket getHelpTicketAssigned(){
    return helpTicket;
}
}
