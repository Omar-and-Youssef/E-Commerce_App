package entity.users.details;

public class OrderStatus {
    private String statusName;
    private String description;
    
//=====================================Constructor================================
    public OrderStatus(String statusName, String description) {
        this.statusName = statusName;
        this.description = description;
    } 
//=======================================Get&Set=======================================

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
