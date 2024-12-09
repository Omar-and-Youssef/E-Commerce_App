package service;
import entity.products.*;
import entity.users.accounts.*;
import entity.users.details.*;
import exceptions.*;
import dao.*;
import java.util.*;
public class CustomerService {
//Orchestrates operations between different components (Customer,Cart,order and product)
//Handles validation of CRUD operations and business logic
private CustomerDAO customerDAO;
private OrderDAO orderDAO;
private ProductDAO productDAO;

    public CustomerService(){
        customerDAO= new CustomerDAO();
        orderDAO= new OrderDAO();
        productDAO=new ProductDAO();
    }
//==================================Methods=========================================
public Customer logIn(String email,String password) throws ServiceException{
    try{
    Customer customer = customerDAO.getCustomerByEmail(email);
    if(customer!=null&&customer.getPassword().equals(password))
        return customer;
    else 
       throw new ServiceException("Invalid email or password");
    }
    catch(Exception e){
        throw new ServiceException("Error logging in: "+e.getMessage(),e); //rethrow to caaller
    }
}
public void registerCustomer(Customer customer) throws ServiceException{
    try{
        if(customer==null) 
            throw new ServiceException("Customer cannot be null");

        if(customerDAO.getCustomerByEmail(customer.getEmail())!=null)
            throw new ServiceException("Customer with this email already exists");
        customerDAO.addCustomer(customer);
    }
    catch(Exception e){
        throw new ServiceException("Error registering customer: "+e.getMessage(), e);
    }
}








public void addReview(Customer customer, String comment,double rating,Product product){
        if(rating<0 || rating>5)throw new IllegalArgumentException("Rating must be between 0 and 5");
        
        Customer customerInDB=customerDAO.getCustomerById(customer.getUserID());
        Product productInDB=productDAO.getProductById(product.getPRODUCT_ID());
       
        if(customerInDB==null)throw new IllegalArgumentException("Customer not found");
        if(productInDB==null)throw new IllegalArgumentException("Product not found");

        Review review=new Review(product, customer, rating, comment);
        ArrayList<Review> reviews=customerInDB.getREVIEWS_SUBMITTED();
        reviews.add(review);
        product.addReview(review);
    }
    

}
