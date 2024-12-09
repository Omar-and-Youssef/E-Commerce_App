package dao;
import java.util.ArrayList;
import entity.users.accounts.Customer;
import database.Database;
public class CustomerDAO {
private static ArrayList<Customer> customers= Database.getCustomerDB();
//=======================================CRUD=======================================
public void addCustomer(Customer customer){
    customers.add(customer);
}
public void deleteCustomer(Customer c){
    customers.remove(c);
}
public void updateCustomer(Customer newCustomer){
    Customer c=getCustomerById(newCustomer.getUserID());
    c.setName(newCustomer.getName());
    c.setEmail(newCustomer.getEmail());
    c.setAddress(newCustomer.getAddress());
}
// public void updateCustomerWallet(){}
// public void updatePassword()
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
    public Customer getCustomerByEmail(String email){
        for(Customer c: customers){
            if(c.getEmail().equals(email)){
                return c;
            }
        }
        return null;
    }

}
