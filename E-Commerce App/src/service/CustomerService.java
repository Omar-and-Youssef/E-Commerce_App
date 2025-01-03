package service;
import entity.products.*;
import entity.users.accounts.*;
import entity.users.details.*;
import exceptions.*;
import dao.*;
import database.Database;

import java.util.*;
public class CustomerService {
//TODO we pass parameters to the methods here
private ProductDAO productDAO;
private CustomerDAO customerDAO;
private OrderDAO orderDAO;
//==================================Login&Register=========================================
public CustomerService(){
    productDAO=new ProductDAO();
    customerDAO=new CustomerDAO();
    orderDAO=new OrderDAO();
}
public  Customer logIn(String email,String password){
    Customer customer=customerDAO.getCustomerByEmail(email);
    if(customer!=null&&customer.getPassword().equals(password)) 
        return customer;  
    return null;  
}
public void registerCustomer(Customer customer) {
        customerDAO.addCustomer(customer);
}
public boolean isValidEmail(String email){
    return customerDAO.getCustomerByEmail(email)==null;
}
//==================================Business Logic=========================================
public void addToCart(Customer customer, Product product, int quantity){
    customer.getCart().addItem(new CartItem(product, quantity));
}
public void removeFromCart(Customer customer, int index){
    CartItem cartItem=customer.getCart().getCartItems().get(index);
    customer.getCart().deleteItem(cartItem);
}
public void clearCart(Customer customer){
    customer.getCart().clearCart();
}
public boolean isEmptyCart(Customer customer){
    return customer.getCart().getCartItems().isEmpty();
}
public void incrementCartItem(Customer customer,int index){
    CartItem cartItem=customer.getCart().getCartItems().get(index);
    customer.getCart().incrementCartItem(cartItem);
} 
public void decrementCartItem(Customer customer, int index){
    CartItem cartItem=customer.getCart().getCartItems().get(index);
    customer.getCart().decrementCartItem(cartItem);
}
public void setQuantityInCart(Customer customer, int index, int quantity){
    CartItem cartItem=customer.getCart().getCartItems().get(index);
    customer.getCart().setQuantityInCart(cartItem, quantity);
}
public double getCartTotal(Customer customer){
    return customer.getCart().getTotalPrice();
}
public void addToWishlist(Customer customer, Product product){
    customer.getWishList().addProduct(product);
}
public void removeFromWishlist(Customer customer, Product product){
    customer.getWishList().removeProduct(product);
}
public void removeFromWishlist(Customer customer, int index){
    customer.getWishList().removeProduct(index);
}
public boolean isInWishList(Customer customer, Product product){
    return customer.getWishList().getWishListItems().contains(product);
}
public void addReview(Customer customer, String comment,double rating,
Product product) throws ServiceException{
        if(rating<0 || rating>5)throw new IllegalArgumentException("Rating must be between 0 and 5");
       
        if(!customerDAO.customerInDB(customer)) 
            throw new IllegalArgumentException("Customer not found");
        if(!productDAO.productInDB(product)) 
            throw new IllegalArgumentException("Product not found");
        Customer customerInDB=customerDAO.getCustomerById(customer.getUserID());

        Review review=new Review(product, customer, rating, comment);
        customerInDB.getReviewsSubmitted().add(review);
        product.addReview(review);
        //review is added to product as well as customer's list of reviews
}
public void deleteReview(Customer customer, Product product) throws ServiceException{
    if(!customerDAO.customerInDB(customer))
        throw new IllegalArgumentException("Customer not found");
    if(!productDAO.productInDB(product))
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
public void updateReview(Customer customer, String comment, 
double rating, Product product) throws ServiceException{
    if(rating<0 || rating>5)throw new IllegalArgumentException("Rating must be between 0 and 5");

    if(!customerDAO.customerInDB(customer))
        throw new IllegalArgumentException("Customer not found");
    if(!productDAO.productInDB(product))
        throw new IllegalArgumentException("Product not found");

    deleteReview(customer, product);
    addReview(customer, comment, rating, product);
}
public void upgradeMemberShip(Customer customer,Date expiryDate) 
throws ServiceException{ 
    //TODO
    //only updates expiry date
        if(!customerDAO.customerInDB(customer))
        throw new IllegalArgumentException("Customer not found");

        customer.getMembership().upgradeMemberShip(expiryDate);
}
public void placeOrder(Customer customer, String address, String paymentMethod)
{       
        Order newOrder=new Order(customer,customer.getCart(),address,paymentMethod);
        orderDAO.addOrder(newOrder);
        Cart cart=customer.getCart();
        for(CartItem cartItem:cart.getCartItems()){
            Product p=cartItem.getProduct();
            p.setStockQuantity(p.getStockQuantity()-cartItem.getQuantity());
            p.setSalesCount(p.getSalesCount()+1);
        }
        customer.getCart().clearCart();
        customer.getOrders().add(newOrder);
        //we create an order, add to database, add to customers orders, clear their cart
}
public void cancelOrder(Order order){
    Cart cart=order.getOrderedItems();
    for(CartItem cartItem:cart.getCartItems()){
        Product p=cartItem.getProduct();
        p.setStockQuantity(p.getStockQuantity()+cartItem.getQuantity());
    }
        if(orderDAO.orderInDB(order))
        order.setOrderStatus(OrderStatus.CANCELLED);
        // orderDAO.deleteOrder(order); 
}
public void updateOrderStatus(Order order,OrderStatus newStatus){
    order.setOrderStatus(newStatus);
}
public void submitHelpTicket(Customer customer, String issue) throws ServiceException{
        if(!customerDAO.customerInDB(customer))
            throw new IllegalArgumentException("Customer not found");

        HelpTicket ticket=new HelpTicket(customer, issue);
        customer.getHelpTicketsSubmitted().add(ticket);
}
public boolean validWalletPayment(Customer customer,double amount){
    return customer.getWallet()>=amount;
}
public void deductFromWallet(Customer customer, double amount){
    customer.setWallet(customer.getWallet()-amount);
}
}
