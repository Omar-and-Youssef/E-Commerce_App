package entity.promotions;
import java.util.ArrayList;

public class Coupon {
    //when appling a coupon, customer gets a discount on his cart
    private String id;
    private double discount;
    private static ArrayList<Coupon> coupons;

    
    public Coupon(String id, double discount) {
        this.id = id;
        this.discount = discount;
    }
    //will handle later
    public void addCouponToList(Coupon coupon){
        coupons.add(coupon);
    }
    public void removeCouponFromList(Coupon coupon){
        coupons.remove(coupon);//after customer redeemes
    }
    public String getID(){
        return id;
    }
    public double getDiscount(){
        return discount;
    }
}
