package dao;
import java.util.ArrayList;
import entity.users.accounts.Customer;
import database.Database;


public class CustomerDAO {
Database database=new Database();
private ArrayList<Customer> customers= database.getCustomerDB();

//=======================================CRUD=======================================
public void addCustomer(Customer customer){
    customers.add(customer);
}
public boolean deleteCustomer(Customer c){
    return customers.remove(c);
}
public boolean updateCustomer(Customer customer) {
    for (int i=0;i<customers.size();i++) {
        if (customers.get(i).getUserID().equals(customer.getUserID())) {
            customers.set(i, customer);
            return true;
        }
    }
    return false;
}
// public void updateCustomerWallet(){}
// public void updatePassword()
// all handled in the control layer 

public ArrayList<Customer> getAllCustomers(){
    return customers;
}
public Customer getCustomerById(String id){
    try{
    for(Customer c: customers){
        if(c.getUserID().equals(id)){
            return c;
        }
    }
    }
    catch(Exception e){
        return null;
    }
    return null;
}
public Customer getCustomerByEmail(String email){
    try{
    for(Customer c: customers){
        if(c.getEmail().equals(email)){
            return c;
        }
    }
    }
    catch(Exception e){return null;}
    return null;
}
public boolean customerInDB(Customer customer){
    try{
        getCustomerById(customer.getUserID());
        return true;
    }catch(Exception e){
        return false;
    }
}

}
