package service;
import entity.users.accounts.*;
import entity.users.details.*;
import exceptions.ServiceException;
import entity.products.*; 
import dao.*;
import database.Database;

import java.util.*;
public class AdminService {
    private AdminDAO adminDAO;
    private ProductDAO productDAO;
    private CustomerDAO customerDAO;
    private OrderDAO orderDAO;
    private double totalRevenue;
    public AdminService(){
        adminDAO=new AdminDAO();
        productDAO=new ProductDAO();
        customerDAO=new CustomerDAO();
        orderDAO=new OrderDAO();
    }
    public Admin logIn(String email,String password){
        Admin admin = adminDAO.getAdminByEmail(email);
        if(admin!=null&&admin.getPassword().equals(password)) 
        return admin;
        return null;
    }
    public void addAdmin(Admin admin) throws ServiceException{
            if(admin==null)
                throw new IllegalArgumentException("Admin cannot be null");

            if(adminDAO.adminInDB(admin))
                throw new ServiceException("Admin already exists");

            adminDAO.addAdmin(admin);
    }
    public boolean addProduct(Product product){
            if(productDAO.productInDB(product)) return false;
            productDAO.addProduct(product);
            return true;
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
    public ArrayList<Product> getProductsByCategory(Category category){
            return productDAO.getProductsByCategory(category);
    }
    public ArrayList<Product> getProductsByName(String name){
        return productDAO.getProductsByName(name);
    }
    public int getAllCustomers(){
        int c;
        try{
            c=customerDAO.getAllCustomers().size();
        }catch(Exception e){
            return 0;
        }
        return c;
    }
    public int getAllAdmins(){
        int p;
        try{
            p=adminDAO.getAllAdmins().size();
        }catch(Exception e){
            return 0;
        }
        return p;
    }
    public int getTotalProducts(){
        int p;
        try{
            p=productDAO.getAllProducts().size();
        }catch(Exception e){
            return 0;
        }
        return p;
    }
    public ArrayList<Product> getAllProducts(){
        return productDAO.getAllProducts();
    }
    public int getTotalOrders(){
        int t;
        try{
            t=orderDAO.getAllOrders().size();
        }catch(Exception e){
            return 0;
        }
        return t;
    }

    public double getTotalRevenue(){
        return totalRevenue;
    }
    public void updateTotalRevenue(){
        totalRevenue=0;
        ArrayList<Order> orders=orderDAO.getAllOrders();
        for(Order order:orders){
            totalRevenue+=order.getOrderTotal();
        }
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
    public int getAvgOrders(){ //average no. of orders per customer
        return getTotalOrders()/getAllCustomers();
    }
    public Product getBestSellingProduct(){
        ArrayList<Product> products=productDAO.getAllProducts();
        if(products.isEmpty()) return null;

        Product bestSellingProduct=null;
        int maxSales=0;
        for(Product product:products){
            if(product.getSalesCount()>maxSales){
                maxSales=product.getSalesCount();
                bestSellingProduct=product;
            }
        }
        return bestSellingProduct;
    }
    public Product getWorstSellingProduct(){
        ArrayList<Product> products=productDAO.getAllProducts();
        Product worstSellingProduct=null;
        int minSales=Integer.MAX_VALUE;
        for(Product product:products){
            if(product.getSalesCount()<minSales){
                minSales=product.getSalesCount();
                worstSellingProduct=product;
            }
        }
        return worstSellingProduct;
    }
    public void setTotalRevenue(double newTotal){
        totalRevenue=newTotal;
    }
}
