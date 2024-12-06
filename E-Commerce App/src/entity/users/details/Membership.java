package entity.users.details;
import entity.users.accounts.Customer;
import entity.promotions.*;
import java.util.Date;

public class Membership {

private String memberID;
private Customer customer;
private MembershipType membershipType;
private Date startDate;
private Date expirationDate;
private String status; // active,expired,suspended
private Points points;
private Discount discount;

}
