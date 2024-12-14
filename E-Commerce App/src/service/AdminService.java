package service;
import entity.users.accounts.*;
import entity.users.details.*;
import exceptions.ServiceException;
import entity.products.*; 
import dao.*;
import database.Database;

import java.util.*;
public class AdminService {
    AdminDAO adminDAO;
    ProductDAO productDAO;
    CustomerDAO customerDAO;
    OrderDAO orderDAO;
    public AdminService(Database database){
        adminDAO=new AdminDAO(database);
        productDAO=new ProductDAO(database);
        customerDAO=new CustomerDAO(database);
        orderDAO=new OrderDAO(database);
    }
    public Admin logIn(String email,String password) throws ServiceException{
        Admin admin = adminDAO.getAdminByEmail(email);
        if(admin==null) throw new ServiceException("Invalid email");

        if(admin.getPassword().equals(password)) return admin;
        else throw new ServiceException("Invalid password");
    }
    public void addAdmin(Admin admin) throws ServiceException{
            if(admin==null)
                throw new IllegalArgumentException("Admin cannot be null");

            if(adminDAO.adminInDB(admin))
                throw new ServiceException("Admin already exists");

            adminDAO.addAdmin(admin);
    }
    public void addProduct(Product product)throws ServiceException{
            if(product==null) 
                throw new IllegalArgumentException("Product cannot be null");
    
            if(productDAO.productInDB(product))
                throw new ServiceException("Product already exists");
            productDAO.addProduct(product);
    }
    public void deleteProduct(Product product) throws ServiceException{
            if(product==null) 
                throw new IllegalArgumentException("Product cannot be null");
    
            if(!productDAO.deleteProduct(product))
                throw new ServiceException("Product does not exist");
    }
    public void updateProduct(Product product) throws ServiceException{

            if(product==null)
                throw new IllegalArgumentException("Product cannot be null");

            if(!productDAO.productInDB(product))
                throw new ServiceException("Product does not exist");

            productDAO.updateProduct(product);
    }
    public ArrayList<Product> getProductsByCategory(Category category) throws ServiceException{
        //TODO handling empty product database?
            getAllProducts();
            if(category==null)
                throw new IllegalArgumentException("Category cannot be null");

            return productDAO.getProductsByCategory(category);
        
  
    }
    public ArrayList<Customer> getAllCustomers(){
            return customerDAO.getAllCustomers();
    }
    public ArrayList<Product> getAllProducts(){
        return productDAO.getAllProducts();
    }
    public ArrayList<Order> getAllOrders() throws ServiceException{
            if(orderDAO.getAllOrders().isEmpty())
                throw new ServiceException("No orders found");
            return orderDAO.getAllOrders();
    }
    
    public void assignHelpTicket(HelpTicket helpTicket)
    throws ServiceException{
        ArrayList<Admin> admins=adminDAO.getAllAdmins();
        for(Admin admin:admins){
        if(admin.getHelpTicketAssigned()==null){
            helpTicket.assignAdmin(admin);
            admin.assignHelpTicket(helpTicket);
            //we assign admin to ticket and ticket to admin
            return;
        }
        else throw new ServiceException("No admins available to assign ticket");
        }
    }
//unsure of helpticket algorithm
    public void unassignHelpTicket(HelpTicket helpTicket) throws ServiceException{
        if(helpTicket.getAssignedAdmin()==null)
            throw new ServiceException("Ticket not assigned to any admin");
        else{
            helpTicket.assignAdmin(null);
            helpTicket.getAssignedAdmin().assignHelpTicket(null);
        }
    }
    public void resolveHelpTicket(HelpTicket helpTicket) throws ServiceException{
        if(!(helpTicket.getTicketStatus()==TicketStatus.RESOLVED))
            {
                helpTicket.updateTicketStatus(TicketStatus.RESOLVED);
                unassignHelpTicket(helpTicket);
            }
            
        else throw new ServiceException("Ticket already resolved");
    }

}
