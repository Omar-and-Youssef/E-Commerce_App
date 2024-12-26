package dao;

import java.util.ArrayList;

import database.Database;
import entity.users.details.*;

public class OrderDAO {
Database database=new Database();
private  ArrayList<Order> orders= database.getOrderDB();
//=======================================CRUD=======================================
public void addOrder(Order order){
    orders.add(order);
}
public void updateOrder(Order order, OrderStatus orderStatus){
    order.setOrderStatus(orderStatus);
}
public ArrayList<Order> getAllOrders(){
    return orders;
}
public Order getOrderById(String id){
    for(Order o: orders){
        if(o.getOrderId().equals(id)){
            return o;
        }
    }
    return null;
} 
public boolean orderInDB(Order order){
    if(order==null || getOrderById(order.getOrderID())==null) {
        return false;
    }
    return true;
}
}
