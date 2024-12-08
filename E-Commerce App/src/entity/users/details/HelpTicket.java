package entity.users.details;
import entity.users.accounts.*;

public class HelpTicket {
private final Customer CUSTOMER;
private String issueDescription;
private TicketStatus ticketStatus;
private Admin assignedAdmin;//Assigned To
//=======================================Constructor===================================
    public HelpTicket(Customer CUSTOMER,String issue) {
        ticketStatus=TicketStatus.SUBMITTED;
        this.CUSTOMER=CUSTOMER;
        issueDescription=issue;
    }
//=======================================Methods=======================================
    public void assignAdmin(Admin admin){
        this.assignedAdmin=admin;
    }
    public void updateTicketStatus(TicketStatus status){
        this.ticketStatus=status;
    }
//=======================================Get&Set=======================================
    public Admin getAssignedAdmin(){
        return assignedAdmin;
    }
    public String getIssueDescription(){
        return issueDescription;
    }
    public TicketStatus getTicketStatus(){
        return ticketStatus;
    }
    public Customer getCustomer(){
        return CUSTOMER;
    }
}
