package entity.promotions;

public class Coupon {
    //when appling a coupon, customer gets a discount on his cart
    private String id;
    private double discount;
    private boolean isRedeemed;
    
    public Coupon(String id, double discount) {
        this.id = id;
        this.discount = discount;
        this.isRedeemed = false;
    }
    //will handle later
}
