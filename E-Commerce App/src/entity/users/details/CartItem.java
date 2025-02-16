package entity.users.details;
import entity.products.Product;

public class CartItem {
    
    private Product product;
    private int quantity;
    private double totalPrice;        
    
//=======================================Constructor===================================
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = product.getPrice() * quantity;
    }
//=======================================Methods=======================================
    public boolean decrementProduct(){
        if(--quantity==0)return true;
        totalPrice=product.getPrice()*quantity;
        return false;
    }   
    public void incrementProduct(){
        ++quantity;
        totalPrice=product.getPrice()*quantity;
    }
//=======================================Get&Set=======================================
    public Product getProduct() {
        return product;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        totalPrice=product.getPrice()*quantity;
    }
    public double getSubTotal() {
        return totalPrice;
    }
    @Override
    public String toString() {
        return "CartItem [product=" + product.getProductName() + ", quantity=" + quantity + ", totalPrice=" + totalPrice + "]";
    }

}

