package engine;

import entity.users.accounts.Customer;
import entity.users.accounts.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ProfileController extends BaseController{
    User user;
    
    @FXML
    Label nameLabel;

    @FXML
    Label emailLabel;

    @FXML
    Label addressLabel;

    @FXML
    Label phoneLabel;

    @FXML
    Label walletLabel;

    @FXML
    HBox walletBox;

    @FXML
    Label profileTitle;

    public void setUser(User user){
        this.user=user;
        nameLabel.setText(user.getName());
        emailLabel.setText(user.getEmail());
        phoneLabel.setText(user.getPhoneNumber());
        addressLabel.setText(user.getAddress());
        if(engine.isCustomer){
            profileTitle.setText("My Profile");
            walletBox.setVisible(true);
            Customer customer=(Customer)user;
            walletLabel.setText("$"+String.format("%.2f",customer.getWallet()));
        }
        else{
            profileTitle.setText("Admin Profile");
            walletBox.setVisible(false);
        }
    }
    @FXML
    public void handleBack(){
        engine.switchScene(Screen.HOME);
    }
}