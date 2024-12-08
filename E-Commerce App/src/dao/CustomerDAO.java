package dao;
import java.util.ArrayList;
import entity.users.accounts.Customer;
import database.Database;
public class CustomerDAO {
    private static ArrayList<Customer> customers= Database.getCustomerDB();
//=======================================Methods=======================================
public void addCustomer(Customer customer){
    customers.add(customer);
}
public void deleteCustomer(String id){
    for(Customer c: customers){
        if(c.getUserID().equals(id)){
            customers.remove(c);
            break;
        }
    }
} 
public void updateCustomerDetails(String id, String newName, String newEmail, String address ){
    Customer c=getCustomerById(id);
    c.setName(newName);
    c.setEmail(newEmail);
    c.setAddress(address);
}
// public void updateCustomerWallet(){}
// public void updatePassword()
//=======================================Get&Set=======================================
    public ArrayList<Customer> getAllCustomers(){
        return customers;
    }
    public Customer getCustomerById(String id){
        for(Customer c: customers){
            if(c.getUserID().equals(id)){
                return c;
            }
        }
        return null;
    }

}
