package service;
import entity.users.accounts.*;
import entity.users.details.*;
import exceptions.ServiceException;
import entity.products.*; 
import dao.*; 
import java.util.*;
public class AdminService {
    public static Admin logIn(String email,String password) throws ServiceException{
        try{
        Admin admin = AdminDAO.getAdminByEmail(email);
        if(admin!=null&&admin.getPassword().equals(password))
            return admin;
        else 
        throw new ServiceException("Invalid email or password");
        }
        catch(Exception e){
            throw new ServiceException("Error logging in: "+e.getMessage(), e);
        }
    }
    public void addAdmin(Admin admin) throws ServiceException{
        try{
            if(admin==null)
                throw new IllegalArgumentException("Admin cannot be null");

            if(AdminDAO.adminInDB(admin))
                throw new ServiceException("Admin already exists");

            AdminDAO.addAdmin(admin);
        }
        catch(Exception e){
            throw new ServiceException("Error adding admin: "+e.getMessage(), e);
        }
    }
    public void addProduct(Product product)throws ServiceException{
        try{
            if(product==null) 
                throw new IllegalArgumentException("Product cannot be null");
    
            if(ProductDAO.productInDB(product))
                throw new ServiceException("Product already exists");
            ProductDAO.addProduct(product);
        }
        catch(Exception e){
            throw new ServiceException("Error adding product: "+e.getMessage(), e);
        }
    }
    public void deleteProduct(Product product) throws ServiceException{
        try{
            if(product==null) 
                throw new IllegalArgumentException("Product cannot be null");
    
            if(!ProductDAO.deleteProduct(product))
                throw new ServiceException("Product does not exist");

        }
        catch(Exception e){
            throw new ServiceException("Error deleting product: "+e.getMessage(), e);
        }    
    }
    public void updateProduct(Product product) throws ServiceException{
        try{
            if(product==null)
                throw new IllegalArgumentException("Product cannot be null");

            if(!ProductDAO.productInDB(product))
                throw new ServiceException("Product does not exist");

            ProductDAO.updateProduct(product);
        }
        catch(Exception e){
            throw new ServiceException("Error updating product: "+e.getMessage(), e);
        }
        //Partial updates are handled in controller layer
    }
    public ArrayList<Product> getProductsByCategory(Category category) throws ServiceException{
        try{
            if(category==null)
                throw new IllegalArgumentException("Category cannot be null");

            return ProductDAO.getProductsByCategory(category);
        }
        catch(Exception e){
            throw new ServiceException("Error showing products by category: "+e.getMessage(), e);
        }
    }
    public ArrayList<Customer> getAllCustomers() throws ServiceException{
        try{   
            if(CustomerDAO.getAllCustomers().isEmpty()) 
                throw new ServiceException("No customers found");
            return CustomerDAO.getAllCustomers();
        }
        catch(Exception e){
            throw new ServiceException("Error showing all users: "+e.getMessage(), e);
        }
    }
    public ArrayList<Product> getAllProducts() throws ServiceException{
        try{
            if(ProductDAO.getAllProducts().isEmpty())
                throw new ServiceException("No products found");
            return ProductDAO.getAllProducts();
        }
        catch(Exception e){
            throw new ServiceException("Error showing all products: "+e.getMessage(), e);
        }
    }
    public ArrayList<Order> getAllOrders() throws ServiceException{
        try{
            if(OrderDAO.getAllOrders().isEmpty())
                throw new ServiceException("No orders found");
            return OrderDAO.getAllOrders();
        }
        catch(Exception e){
            throw new ServiceException("Error showing all orders: "+e.getMessage(), e);
        }
    }
    
    public void assignHelpTicket(HelpTicket helpTicket)
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
