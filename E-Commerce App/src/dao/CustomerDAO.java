package dao;
import java.util.ArrayList;
import entity.users.accounts.Customer;
import database.Database;
public class CustomerDAO {
private static ArrayList<Customer> customers= Database.getCustomerDB();
//=======================================CRUD=======================================
public static void addCustomer(Customer customer){
    customers.add(customer);
}
public static boolean deleteCustomer(Customer c){
    return customers.remove(c);
}
public static boolean updateCustomer(Customer customer) {
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

public static ArrayList<Customer> getAllCustomers(){
    return customers;
}
public static Customer getCustomerById(String id){
    for(Customer c: customers){
        if(c.getUserID().equals(id)){
            return c;
        }
    }
    return null;
}
public static Customer getCustomerByEmail(String email){
    for(Customer c: customers){
        if(c.getEmail().equals(email)){
            return c;
        }
    }
    return null;
}
public static boolean customerInDB(Customer customer){
    try{
        getCustomerById(customer.getUserID());
        return true;
    }catch(Exception e){
        return false;
    }
}

}
