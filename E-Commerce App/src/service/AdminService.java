package service;
import entity.users.accounts.*;
import entity.users.details.*;
import exceptions.ServiceException;
import entity.products.*; 
import dao.*; 
import java.util.*;
public class AdminService {
    public static Admin logIn(String email,String password) throws ServiceException{
        Admin admin = AdminDAO.getAdminByEmail(email);
        if(admin==null) throw new ServiceException("Invalid email");

        if(admin.getPassword().equals(password)) return admin;
        else throw new ServiceException("Invalid password");
    }
    public static void addAdmin(Admin admin) throws ServiceException{
            if(admin==null)
                throw new IllegalArgumentException("Admin cannot be null");

            if(AdminDAO.adminInDB(admin))
                throw new ServiceException("Admin already exists");

            AdminDAO.addAdmin(admin);
    }
    public static void addProduct(Product product)throws ServiceException{
            if(product==null) 
                throw new IllegalArgumentException("Product cannot be null");
    
            if(ProductDAO.productInDB(product))
                throw new ServiceException("Product already exists");
            ProductDAO.addProduct(product);
    }
    public static void deleteProduct(Product product) throws ServiceException{
            if(product==null) 
                throw new IllegalArgumentException("Product cannot be null");
    
            if(!ProductDAO.deleteProduct(product))
                throw new ServiceException("Product does not exist");
    }
    public static void updateProduct(Product product) throws ServiceException{

            if(product==null)
                throw new IllegalArgumentException("Product cannot be null");

            if(!ProductDAO.productInDB(product))
                throw new ServiceException("Product does not exist");

            ProductDAO.updateProduct(product);
    }
    public static ArrayList<Product> getProductsByCategory(Category category) throws ServiceException{
        //TODO handling empty product database?
            getAllProducts();
            if(category==null)
                throw new IllegalArgumentException("Category cannot be null");

            return ProductDAO.getProductsByCategory(category);
        
  
    }
    public static ArrayList<Customer> getAllCustomers(){
            return CustomerDAO.getAllCustomers();
    }
    public static ArrayList<Product> getAllProducts(){
        return ProductDAO.getAllProducts();
    }
    public static ArrayList<Order> getAllOrders() throws ServiceException{
            if(OrderDAO.getAllOrders().isEmpty())
                throw new ServiceException("No orders found");
            return OrderDAO.getAllOrders();
    }
    
    public static void assignHelpTicket(HelpTicket helpTicket)
    throws ServiceException{
        ArrayList<Admin> admins=AdminDAO.getAllAdmins();
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
    public static void unassignHelpTicket(HelpTicket helpTicket) throws ServiceException{
        if(helpTicket.getAssignedAdmin()==null)
            throw new ServiceException("Ticket not assigned to any admin");
        else{
            helpTicket.assignAdmin(null);
            helpTicket.getAssignedAdmin().assignHelpTicket(null);
        }
    }
    public static void resolveHelpTicket(HelpTicket helpTicket) throws ServiceException{
        if(!(helpTicket.getTicketStatus()==TicketStatus.RESOLVED))
            {
                helpTicket.updateTicketStatus(TicketStatus.RESOLVED);
                unassignHelpTicket(helpTicket);
            }
            
        else throw new ServiceException("Ticket already resolved");
    }

}
