package service;
import entity.users.accounts.*;
import entity.users.details.*;
import entity.products.*; 
import dao.*; 
public class AdminService {
    private CustomerDAO customerDAO;
    private ProductDAO productDAO;
    private OrderDAO orderDAO;
    private AdminDAO adminDAO=new AdminDAO();

    public AdminService(){
        customerDAO= new CustomerDAO();
        productDAO= new ProductDAO();
        orderDAO= new OrderDAO();
    }

    public void deleteCustomer(Customer customer){
        customerDAO.deleteCustomer(customer);
    }
    public void updateCustomer(Customer newCustomer){
        customerDAO.updateCustomer(newCustomer);
    }

    public void addProduct(Product product){
        productDAO.addProduct(product);
    }
    public void deleteProduct(Product product){
        productDAO.deleteProduct(product);
    }

    
    // public void assignHelpTicket(HelpTicket helpTicket, Admin admin){
    //     Admin a=adminDAO.getAdminById(admin.getUserID());
    //     a.assignHelpTicket(helpTicket);
    // }

}
