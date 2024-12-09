package dao;

import java.util.ArrayList;

import database.Database;
import entity.users.details.*;

public class OrderDAO {
private static ArrayList<Order> orders= Database.getOrderDB();
//=======================================CRUD=======================================
public static void addOrder(Order order){
    orders.add(order);
}
public static boolean deleteOrder(Order order){
    return orders.remove(order);
}
public static void updateOrder(Order newOrder){
    Order o=getOrderById(newOrder.getOrderId());
    o.setOrderStatus(newOrder.getOrderStatus());
}

public static ArrayList<Order> getAllOrders(){
    return orders;
}
public static Order getOrderById(String id){
    for(Order o: orders){
        if(o.getOrderId().equals(id)){
            return o;
        }
    }
    return null;
} 
public static boolean orderInDB(Order order){
    return getOrderById(order.getOrderId())!=null;
}
}
