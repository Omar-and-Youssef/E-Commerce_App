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
    public void deleteItem(CartItem item) {
        CART_ITEMS.remove(item);
        calcTotalPrice();
    }
    public void clearCart() {
        CART_ITEMS.clear();
        this.totalPrice=0;
    }
    public void incrementCartItem(CartItem cartItem){
        cartItem.incrementProduct();
    }
    public void setQuantityInCart(CartItem cartItem, int quantity){
        cartItem.setQuantity(quantity);
        calcTotalPrice();
    }
    public void decrementCartItem(CartItem cartItem){
        if(cartItem.decrementProduct())CART_ITEMS.remove(cartItem);
        calcTotalPrice();
    }
    public void calcTotalPrice(){
        totalPrice=0;
        for (CartItem item : CART_ITEMS) {
            totalPrice += item.getSubTotal();
        }
    }
//=======================================Get&Set=======================================
    public double getTotalPrice() {
        calcTotalPrice();
        return totalPrice;
    }
    public ArrayList<CartItem> getCartItems() {
        return CART_ITEMS;
    }

}