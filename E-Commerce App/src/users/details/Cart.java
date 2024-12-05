package users.details;
import java.util.*;
import products.Product;
public class Cart {
private double totalPrice;
private ArrayList<CartItem> cartItems;

//=======================================Constructor===================================
    
    public Cart() {
        this.totalPrice = 0;
        this.cartItems = new ArrayList<CartItem>(); 
    }

//=======================================Methods=======================================
    public void addItem(CartItem item) {
        cartItems.add(item);
    }
    public void deleteItem(int index) {
        cartItems.remove(index);
    }
    public void clearCart() {
        cartItems.clear();
    }
    public void calcTotalPrice(){
        for (CartItem item : cartItems) {
            totalPrice += item.getTotalPrice();
        }
    }
//=======================================Get&Set=======================================

    public double getTotalPrice() {
        return totalPrice;
    }
    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

}