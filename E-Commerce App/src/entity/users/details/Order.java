package entity.users.details;
import java.util.*;
import entity.users.accounts.*;
import java.text.SimpleDateFormat;
public class Order { 
    private static int orderCounter;
    private OrderStatus orderStatus;
    private final Customer CUSTOMER;
    private final String ORDER_ID;
    private final Date ORDER_DATE;
    private final String SHIPPING_ADDRESS;
    private final String PAYMENT_METHOD;
    private final Cart ORDERED_ITEMS;
    private double orderTotal;
//=======================================Constructor===================================
    public Order(Customer CUSTOMER, Cart ORDERED_ITEMS, 
        String SHIPPING_ADDRESS, String PAYMENT_METHOD){
        orderStatus=OrderStatus.PLACED;
        ORDER_ID="O"+(++orderCounter);
        this.CUSTOMER=CUSTOMER;
        this.ORDERED_ITEMS=ORDERED_ITEMS;
        this.orderTotal=ORDERED_ITEMS.getTotalPrice();
        this.SHIPPING_ADDRESS=SHIPPING_ADDRESS;
        this.PAYMENT_METHOD=PAYMENT_METHOD;
        ORDER_DATE=new Date();
    }
//=======================================Methods=======================================
    public void cancelOrder(){//Incase Remote Cancellation
        this.orderStatus=OrderStatus.CANCELLED;
    }
    public void updateOrderStatus(OrderStatus newStatus){
        //Probable exception here
        orderStatus=newStatus;
    }
//=======================================Get&Set=======================================
    public String getOrderID(){
        return ORDER_ID;
    }
    public double getOrderTotal(){
        return orderTotal;
    }
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
    public Customer getCustomer() {
        return CUSTOMER;
    }

    public String getOrderId() {
        return ORDER_ID;
    }

    public String getOrderDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(ORDER_DATE);
    }

    public String getShippingAddress() {
        return SHIPPING_ADDRESS;
    }

    public String getPaymentMethod() {
        return PAYMENT_METHOD;
    }
    public Cart getOrderedItems() {
        return ORDERED_ITEMS;
    }
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
    @Override
    public String toString() {
        String orderItems="";
        for(int i=0;i<ORDERED_ITEMS.getCartItems().size();i++){
            CartItem c=ORDERED_ITEMS.getCartItems().get(i);
            if(i==ORDERED_ITEMS.getCartItems().size()-1) orderItems+=c.getProduct().getProductName();
            orderItems+=c.getProduct().getProductName()+"🔸";
        }
        return orderItems;
    }

}
