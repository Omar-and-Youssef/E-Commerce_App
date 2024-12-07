package entity.products;
import entity.users.accounts.Customer;
public class Review{ 
    private int rating;
    private String comment;
    private final Product PRODUCT;
    private final Customer CUSTOMER;
//=======================================Constructor===================================
    public Review(Product PRODUCT, Customer CUSTOMER, int rating,String comment){
        this.PRODUCT=PRODUCT;
        this.CUSTOMER=CUSTOMER;
        this.rating=rating;
        this.comment=comment;
    }
    public Review(Product PRODUCT, Customer CUSTOMER, int rating){
        this(PRODUCT,CUSTOMER,rating,"");
    }
//=======================================Methods=======================================
    public void changeRating(int rating){
        this.rating=rating;
    }
    public void changeComment(String comment){
        this.comment=comment;
    }
//=======================================Get&Set=======================================
    public String getComment(){
        return comment;
    }
    public Customer getCustomer(){
        return CUSTOMER;
    }
    public Product getProduct(){
        return PRODUCT;
    }
    public int getRating(){
        return rating;
    }
}
