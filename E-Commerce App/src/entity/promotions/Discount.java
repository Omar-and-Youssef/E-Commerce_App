package entity.promotions;

import java.util.Locale.Category;
import java.time.LocalDate;

public class Discount {
    //discount gets applied on a category of products
    //for a set period
    private double discount;
    private Category category;
    private LocalDate expiryDate;

    public Discount(double discount, Category category, LocalDate expiryDate) {
        this.discount = discount;
        this.category = category;
        this.expiryDate = expiryDate;
    }
    
}
