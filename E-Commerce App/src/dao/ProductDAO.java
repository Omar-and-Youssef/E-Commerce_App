package dao;
import database.Database;
import entity.products.*;

import java.util.*;
public class ProductDAO {
    private static ArrayList<Product> products= Database.getProductDB();
//=======================================Methods=======================================
    public static void  addProduct(Product product){
        products.add(product);
    }
    public static boolean deleteProduct(Product product){
        return products.remove(product);
    }
    public static boolean updateProduct(Product product) { 
        for (int i=0;i<products.size();i++) {
            if (products.get(i).getProductID().equals(product.getProductID())) {
                products.set(i, product);
                return true;
            }
        }
        return false;    
    }
//=======================================Get&Set=======================================
public static ArrayList<Product> getAllProducts(){
    return products;
}
public static Product getProductById(String id){
    for(Product p: products){
        if(p.getProductID().equals(id)){
            return p;
        }
    }
    return null;
}
public static boolean productInDB(Product product){
    return getProductById(product.getProductID())!=null;
}
}
