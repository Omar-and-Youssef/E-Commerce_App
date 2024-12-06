package entity.products;
import java.util.*;

import entity.promotions.Discount;

public class Category {

private String name;
private String description;
private ArrayList<Product> products;

private Discount discount;
//=======================================Constructor===================================
public Category(String name,String description){
    this.name=name;
    this.description=description;
    this.products=new ArrayList<Product>();
}
public Category(String name){
    this(name,"NA");
}
//=======================================Get&Set=======================================
public String getName() {
    return name;
}
public void setName(String name) {
    this.name = name;
}
public String getDescription() {
    return description;
}
public void setDescription(String description) {
    this.description = description;
}
public ArrayList<Product> getProducts() {
    return products;
}
public void setProducts(ArrayList<Product> products) {
    this.products = products;
}

}
