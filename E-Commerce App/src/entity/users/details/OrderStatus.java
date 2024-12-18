package entity.users.details;

public enum OrderStatus {
    PLACED,CANCELLED,PREPARING,IN_ROUTE,DELIVERED;

    @Override
    public String toString() {
        String name = name();
        return name.charAt(0) + name.substring(1).toLowerCase();
    }
}
