package entity.products;


public enum Category {
    ALL,
    ELECTRONICS,
    FURNITURE,
    BOOKS,
    CLOTHING,
    BEAUTY,
    TOYS,
    SPORTS;


    @Override
    public String toString() {
        return (this.name().charAt(0) + this.name().substring(1).toLowerCase());
    }
}
