package entity.users.details;
import entity.products.Product;

public class CartItem {
    
    private Product product;
    private int quantity;
    private double totalPrice;        
    
//=======================================Constructor===================================

    public CartItem(Product product) {
        this.product = product;
        this.quantity = 1;
        this.totalPrice = product.getPrice();
    }
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = product.getPrice() * quantity;
    }

//=======================================Methods=======================================

//incrementQuantity(), decrementQuantity()  for + - and update total price inside

//=======================================Get&Set=======================================
    public Product getProduct() {
        return product;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalPrice = product.getPrice() * quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
