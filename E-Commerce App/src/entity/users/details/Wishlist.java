package entity.users.details;
import java.util.*;
import entity.products.*;
public class Wishlist {
    private final ArrayList<Product>WISH_LIST;
//=======================================Constructor===================================
    public Wishlist(){
        WISH_LIST=new ArrayList<Product>();
    }
//=======================================Methods=======================================
    public void addProduct(Product product){
        if(!WISH_LIST.contains(product))//Already there
            WISH_LIST.add(product);
    }
    public void removeProduct(int index){
        WISH_LIST.remove(index);
    }
//=======================================Get&Set=======================================
    public ArrayList<Product> getWishList(){
        return WISH_LIST;
    }
}   
