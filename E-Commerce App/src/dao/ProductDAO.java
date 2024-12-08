package dao;
import database.Database;
import entity.products.*;
import java.util.*;
public class ProductDAO {
    ArrayList<Product> products= Database.getProductDB();
//=======================================Methods=======================================
    public void addProduct(Product product){
        products.add(product);
    }
    public void deleteProduct(String id){
        for(Product p: products){
            if(p.getPRODUCT_ID().equals(id)){
                products.remove(p);
                break;
            }
        }
    }
    public boolean updateProduct(Product updatedProduct) { 
        //updated product is created at controller layer
        Product product = getProductById(updatedProduct.getPRODUCT_ID());
        if (product != null) {
            product.setPrice(updatedProduct.getPrice());
            product.setStockQuantity(updatedProduct.getStockQuantity());
            product.setProductName(updatedProduct.getProductName());
            product.setDescription(updatedProduct.getDescription());
            product.setBrand(updatedProduct.getBrand());
            product.setCategory(updatedProduct.getCategory());
            product.setImage(updatedProduct.getImage());
            return true;
        }
        return false;     
    }
//=======================================Get&Set=======================================
public ArrayList<Product> getAllProducts(){
    return products;
}
public Product getProductById(String id){
    for(Product p: products){
        if(p.getPRODUCT_ID().equals(id)){
            return p;
        }
    }
    return null;
}
}
