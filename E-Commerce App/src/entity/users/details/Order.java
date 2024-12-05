package entity.users.details;
import java.time.LocalDateTime;
import java.util.ArrayList;

import entity.users.accounts.*;

public class Order {

private final int ORDER_ID;

private static int orderCounter; 
private Customer customer;
private LocalDateTime orderDate;
private String shippingAddress; //might be different from the customer's default address
private String paymentMethod;
private OrderStatus orderStatus;
private ArrayList<CartItem> OrderedItems;
//=======================================Constructor===================================
    
public Order(Customer customer, ArrayList<CartItem> items, String shippingAddress, String paymentMethod){
        this.ORDER_ID=orderCounter++;
        this.customer=customer;
}
//in an order service, the cart will pass its list to create an order

//=======================================Methods=======================================

//=======================================Get&Set=======================================

public int getOrderID() {
    return ORDER_ID;
}

public Customer getCustomer() {
    return customer;
}

public LocalDateTime getOrderDate() {
    return orderDate;
}

public void setOrderDate(LocalDateTime orderDate) {
    this.orderDate = orderDate;
}

public String getShippingAddress() {
    return shippingAddress;
}

public void setShippingAddress(String shippingAddress) {
    this.shippingAddress = shippingAddress;
}

public String getPaymentMethod() {
    return paymentMethod;
}

public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
}

public OrderStatus getOrderStatus() {
    return orderStatus;
}

public void setOrderStatus(OrderStatus orderStatus) {
    this.orderStatus = orderStatus;
}

public ArrayList<CartItem> getOrderedItems() {
    return OrderedItems;
}

public void setOrderedItems(ArrayList<CartItem> orderedItems) {
    OrderedItems = orderedItems;
}   //after payment


}
