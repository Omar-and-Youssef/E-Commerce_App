package service;

import dao.AdminDAO;
import dao.CustomerDAO;

public class UserService {
    AdminDAO adminDAO;
    CustomerDAO customerDAO;
    public UserService(){
        adminDAO = new AdminDAO();
        customerDAO= new CustomerDAO();
    }
    public boolean userInDB(String email){
        try{
            adminDAO.getAdminByEmail(email);
            customerDAO.getCustomerByEmail(email);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}
