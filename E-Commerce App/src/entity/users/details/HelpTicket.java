package entity.users.details;

import entity.users.accounts.Customer;

public class HelpTicket {
private Customer customer;

private final int ORDER_ID;
private static int orderCounter;

private String issueDescription;
private String ticketStatus;


//=======================================Constructor===================================
public HelpTicket(Customer customer, int ORDER_ID) {
    this.ORDER_ID = orderCounter++;
    this.customer = customer;
    
}
//=======================================Methods=======================================
}
