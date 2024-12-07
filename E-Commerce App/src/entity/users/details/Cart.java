package entity.users.details;
import java.util.*;


public class Cart {
    private double totalPrice;
    private final ArrayList<CartItem> CART_ITEMS;

//=======================================Constructor===================================
    public Cart() {
        this.totalPrice = 0;
        this.CART_ITEMS = new ArrayList<CartItem>(); 
    }
//=======================================Methods=======================================
    public void addItem(CartItem item) {
        CART_ITEMS.add(item);
        calcTotalPrice();
    }
    public void deleteItem(int index) {
        CART_ITEMS.remove(index);
        calcTotalPrice();
    }
    public void clearCart() {
        CART_ITEMS.clear();
        this.totalPrice=0;
    }
    public void incrementCartItem(int index){
        CART_ITEMS.get(index).incrementProduct();
    }
    public void decrementCartItem(int index){
        if(CART_ITEMS.get(index).decrementProduct())CART_ITEMS.remove(index);
        calcTotalPrice();
    }
    public void calcTotalPrice(){
        for (CartItem item : CART_ITEMS) {
            totalPrice += item.getTotalPrice();
        }
    }
//=======================================Get&Set=======================================
    public double getTotalPrice() {
        return totalPrice;
    }
    public ArrayList<CartItem> getCartItems() {
        return CART_ITEMS;
    }

}