package dao;

import java.util.ArrayList;

import database.Database;
import entity.users.details.*;

public class OrderDAO {
private static ArrayList<Order> orders= Database.getOrderDB();
//=======================================CRUD=======================================
public void addOrder(Order order){
    orders.add(order);
}
public void deleteOrder(Order order){
    orders.remove(order);
}
public void updateOrder(Order newOrder){
    Order o=getOrderById(newOrder.getOrderId());
    o.setOrderStatus(newOrder.getOrderStatus());
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

}
