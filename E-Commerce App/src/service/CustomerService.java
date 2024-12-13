package service;
import entity.products.*;
import entity.users.accounts.*;
import entity.users.details.*;
import exceptions.*;
import dao.*;
import java.util.*;
public class CustomerService {
//Orchestrates operations between a customer's different components (Cart,order and product)
//Handles validation of CRUD operations as well as business logic
//==================================Methods=========================================

public static Customer logIn(String email,String password) throws ServiceException{
    Customer customer = CustomerDAO.getCustomerByEmail(email);
    if(customer==null) throw new ServiceException("Invalid email");

    if(customer.getPassword().equals(password)) return customer;
    else throw new ServiceException("Invalid password");
    
}

public static void registerCustomer(Customer customer) throws ServiceException{
        if(customer==null) 
            throw new IllegalArgumentException("Customer cannot be null");

        if(CustomerDAO.customerInDB(customer))
            throw new ServiceException("Customer with this email already exists");
        CustomerDAO.addCustomer(customer);
    
}
public static void updateCustomer(Customer customer) throws ServiceException{
        if(customer==null)
            throw new IllegalArgumentException("Customer cannot be null");
        if(!CustomerDAO.updateCustomer(customer)) //if customer not found
            throw new ServiceException("Customer not found");
        //TODO handle partial updates
}
public static void deleteCustomer(Customer customer) throws ServiceException{
    //if customer chooses to delete their account
        if(customer==null)
            throw new IllegalArgumentException("Customer cannot be null");

        if(CustomerDAO.deleteCustomer(customer)) //if customer not found
            throw new ServiceException("Customer not found");
    
}

public static void addToCart(Customer customer, Product product, int quantity) throws ServiceException{
        if (customer == null || product == null || quantity <= 0) {
            throw new IllegalArgumentException("Invalid input parameters");
        }
        if(CustomerDAO.customerInDB(customer)) 
            customer.getCart().addItem(new CartItem(product, quantity));
        else 
            throw new ServiceException("Customer not found");
}
public static void removeFromCart(Customer customer, CartItem cartItem) throws ServiceException{
        if(customer==null||cartItem==null)
            throw new IllegalArgumentException("Customer cannot be null");
        if(CustomerDAO.customerInDB(customer))
            customer.getCart().deleteItem(cartItem);
        else 
            throw new ServiceException("Customer not found");
}
public static void clearCart(Customer customer) throws ServiceException{
        if(customer==null)
            throw new IllegalArgumentException("Customer cannot be null");
        if(CustomerDAO.customerInDB(customer))
            customer.getCart().clearCart();
        else
            throw new ServiceException("Customer not found");
}
public static void incrementCartItem(Customer customer,CartItem cartItem){
//TODO Find a way to getCartItem from gui
    customer.getCart().incrementCartItem(cartItem);
} 
public static void decrementCartItem(Customer customer, int index){
    customer.getCart().decrementCartItem(index);
}
public static void addReview(Customer customer, String comment,double rating,
Product product) throws ServiceException{
        if(rating<0 || rating>5)throw new IllegalArgumentException("Rating must be between 0 and 5");
       
        if(!CustomerDAO.customerInDB(customer)) 
            throw new IllegalArgumentException("Customer not found");
        if(!ProductDAO.productInDB(product)) 
            throw new IllegalArgumentException("Product not found");
        Customer customerInDB=CustomerDAO.getCustomerById(customer.getUserID());

        Review review=new Review(product, customer, rating, comment);
        customerInDB.getReviewsSubmitted().add(review);
        product.addReview(review);
        //review is added to product as well as customer's list of reviews
}
public static void deleteReview(Customer customer, Product product) throws ServiceException{
    if(!CustomerDAO.customerInDB(customer))
        throw new IllegalArgumentException("Customer not found");
    if(!ProductDAO.productInDB(product))
        throw new IllegalArgumentException("Product not found");

    Review foundReview=null;
    for(Review rev:customer.getReviewsSubmitted()){
        if(rev.getProduct().equals(product)){
            foundReview=rev;
            customer.getReviewsSubmitted().remove(rev);
            break;
        }
    }
    product.deleteReview(foundReview);
    
}
public static void updateReview(Customer customer, String comment, 
double rating, Product product) throws ServiceException{
    if(rating<0 || rating>5)throw new IllegalArgumentException("Rating must be between 0 and 5");

    if(!CustomerDAO.customerInDB(customer))
        throw new IllegalArgumentException("Customer not found");
    if(!ProductDAO.productInDB(product))
        throw new IllegalArgumentException("Product not found");

    deleteReview(customer, product);
    addReview(customer, comment, rating, product);
}
public static void upgradeMemberShip(Customer customer,Date expiryDate) 
throws ServiceException{ //only updates expiry date
        if(!CustomerDAO.customerInDB(customer))
        throw new IllegalArgumentException("Customer not found");

        customer.getMembership().upgradeMemberShip(expiryDate);
}
public static void placeOrder(Customer customer, String address, String paymentMethod)
throws ServiceException{
        if(!CustomerDAO.customerInDB(customer))
            throw new IllegalArgumentException("Customer not found");

        Order newOrder=new Order(customer,customer.getCart().getCartItems(),address,paymentMethod);
        OrderDAO.addOrder(newOrder);
        customer.getCart().clearCart();
        customer.getOrders().add(newOrder);
}
public static void cancelOrder(Customer customer, Order order) throws ServiceException{
        if(!CustomerDAO.customerInDB(customer))
            throw new IllegalArgumentException("Customer not found");
        if(!OrderDAO.orderInDB(order))
        order.cancelOrder();
        OrderDAO.deleteOrder(order);
        customer.getOrders().remove(order);
}
public static void addToWishlist(Customer customer, Product product) throws ServiceException{

        if(!CustomerDAO.customerInDB(customer))
            throw new IllegalArgumentException("Customer not found");
        if(!ProductDAO.productInDB(product))
            throw new IllegalArgumentException("Product not found");

        customer.getWishList().addProduct(product);
    
}
public static void removeFromWishlist(Customer customer, int index) throws ServiceException{
        if(!CustomerDAO.customerInDB(customer))
            throw new IllegalArgumentException("Customer not found");

        customer.getWishList().removeProduct(index);
}
public static void submitHelpTicket(Customer customer, String issue) throws ServiceException{
        if(!CustomerDAO.customerInDB(customer))
            throw new IllegalArgumentException("Customer not found");

        HelpTicket ticket=new HelpTicket(customer, issue);
        customer.getHelpTicketsSubmitted().add(ticket);
}
}
