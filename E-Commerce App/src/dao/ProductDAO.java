package dao;
import database.Database;
import entity.products.*;

import java.util.*;
public class ProductDAO {
    Database database=new Database();
    private ArrayList<Product> products= database.getProductDB();
//=======================================CRUD=======================================
public  void  addProduct(Product product){
        products.add(product);
    }
public boolean deleteProduct(Product product){
        return products.remove(product);
    }
public  boolean updateProduct(Product product) { 
    for (int i=0;i<products.size();i++) {
        if (products.get(i).getProductID().equals(product.getProductID())) {
            products.set(i, product);
            return true;
        }
    }
    return false;    
}
public  ArrayList<Product> getAllProducts(){
    return products;
}
public  Product getProductById(String id){
    for(Product p: products){
        if(p.getProductID().equals(id)){
            return p;
        }
    }
    return null;
}
public  ArrayList<Product> getProductsByCategory(Category category){
    ArrayList<Product> productsByCategory=new ArrayList<>();
    for(Product p: products){
        if(p.getCategory().equals(category)){
            productsByCategory.add(p);
        }
    }
    return productsByCategory;
}
public ArrayList<Product> getProductsByName(String name){
    ArrayList<Product> productsByName=new ArrayList<>();
    for(Product p: products){
        if(p.getProductName().equals(name)){
            productsByName.add(p);
        }
    }
    return productsByName;
}
public  boolean productInDB(Product product){
    return getProductById(product.getProductID())!=null;
}
}
