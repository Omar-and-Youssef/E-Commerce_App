package entity.users.accounts;
import entity.users.details.HelpTicket;
public class Admin extends User {
    private static int adminCounter;
    private HelpTicket helpTicket;
    private final String role;
    private String workingHours;
//=======================================Constructor===================================

    public Admin(String name,String email,String password,String phoneNumber,String address,
    Gender gender,String role, String workingHours){
        super("A"+adminCounter++,name,email,password,phoneNumber,address,gender);
        this.role=role;
        this.workingHours=workingHours;
    }
    public Admin(String name,String email,String password,
    Gender gender,String role, String workingHours){ //no phone number or address
        this(name,email,password,"NA","NA",gender,role,workingHours);
    }
    public Admin(String name,String email,String password,String address,
    Gender gender,String role, String workingHours){
        this(name,email,password,"NA",address,gender,role,workingHours);
    }
    public Admin(String name,String email,String password,Gender gender,String phoneNumber){
        this(name,email,password,gender,phoneNumber,"NA");
    }

    public void assignHelpTicket(HelpTicket helpTicket){
        this.helpTicket=helpTicket;
    }
    public HelpTicket getHelpTicketAssigned(){
        return helpTicket;
    }
    //=======================================Get&Set=======================================
    public String getRole(){
        return role;
    }
    public String getWorkingHours(){
        return workingHours;
    }
    public void setWorkingHours(String workingHours){
        this.workingHours=workingHours;
    }
}
