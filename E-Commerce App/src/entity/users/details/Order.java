package entity.users.details;
import java.util.*;
import entity.users.accounts.*;
public class Order { 
    private static int orderCounter;
    private OrderStatus orderStatus;
    private final Customer CUSTOMER;
    private final String ORDER_ID;
    private final Date ORDER_DATE;
    private final String SHIPPING_ADDRESS;
    private final String PAYMENT_METHOD;
    private final ArrayList<CartItem> ORDERED_ITEMS;
//=======================================Constructor===================================
    public Order(Customer CUSTOMER, ArrayList<CartItem> ORDERED_ITEMS, 
    String SHIPPING_ADDRESS, String PAYMENT_METHOD){
        orderStatus=OrderStatus.PLACED;
        ORDER_ID="O"+(++orderCounter);
        this.CUSTOMER=CUSTOMER;
        this.ORDERED_ITEMS=ORDERED_ITEMS;
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
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Customer getCustomer() {
        return CUSTOMER;
    }

    public String getOrderId() {
        return ORDER_ID;
    }

    public Date getOrderDate() {
        return ORDER_DATE;
    }

    public String getShippingAddress() {
        return SHIPPING_ADDRESS;
    }

    public String getPaymentMethod() {
        return PAYMENT_METHOD;
    }

    public ArrayList<CartItem> getOrderedItems() {
        return ORDERED_ITEMS;
    }

}
