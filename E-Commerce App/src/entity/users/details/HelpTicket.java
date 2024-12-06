package entity.users.details;

import entity.users.accounts.Customer;

public class HelpTicket {
private Customer customer;
private final int ORDER_ID;
private static int orderCounter;//Useless
private String issueDescription;
private String ticketStatus;//Enum


//=======================================Constructor===================================
public HelpTicket(Customer customer, int ORDER_ID) {
    this.ORDER_ID = orderCounter++;
    this.customer = customer;
    
}
//=======================================Methods=======================================

//=======================================Get&Set=======================================

public int getORDER_ID() {
    return ORDER_ID;
}

public String getIssueDescription() {
    return issueDescription;
}


public void setIssueDescription(String issueDescription) {
    this.issueDescription = issueDescription;
}


public String getTicketStatus() {
    return ticketStatus;
}


public void setTicketStatus(String ticketStatus) {
    this.ticketStatus = ticketStatus;
}


}
