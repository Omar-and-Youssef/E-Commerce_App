package engine;

import java.util.ArrayList;

import entity.users.accounts.Customer;
import entity.users.details.Order;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class CustomerDBController extends BaseController{
    @FXML
public void handleBack(){
        engine.switchScene(Screen.ANALYTICS);
}
@FXML
private Label id1;
@FXML
private Label id2;
@FXML
private Label id3;
@FXML
private Label name1;
@FXML
private Label name2;
@FXML
private Label name3;
@FXML
private Label phone1;
@FXML
private Label phone2;
@FXML
private Label phone3;
@FXML
private Label address1;
@FXML
private Label address2;
@FXML
private Label address3;
@FXML
private Label wallet1;
@FXML
private Label wallet2;
@FXML
private Label wallet3;
@FXML
private HBox customerBox1;
@FXML
private HBox customerBox2;
@FXML
private HBox customerBox3;
@FXML
private Button navLeftB;
@FXML
private Button navRightB;


int customerCount;
ArrayList<Customer> customers;

public void populateCustomers(int startingIndex){
    customers=engine.getCustomerDB();

    if(startingIndex==-1) startingIndex=customerCount;
    int customerSize=customers.size();
    Label[] idLabels={id1,id2,id3};
    Label[] nameLabels={name1,name2,name3};
    Label[] phoneLabels={phone1,phone2,phone3};
    Label[] addressLabels={address1,address2,address3};
    Label[] walletLabels={wallet1,wallet2,wallet3};
    HBox[] customerBoxes={customerBox1,customerBox2,customerBox3};
    int i;  
    for(i=0;i<3&&startingIndex+i<customerSize;i++){
        Customer customer=customers.get(startingIndex+i);
        idLabels[i].setText(customer.getUserID());
        nameLabels[i].setText(customer.getName());
        phoneLabels[i].setText(customer.getPhoneNumber());
        addressLabels[i].setText(customer.getAddress());
        walletLabels[i].setText("$"+String.valueOf(customer.getWallet()));
        customerBoxes[i].setVisible(true);
    }
    for (int j=i; j<3; j++)
        customerBoxes[j].setVisible(false);

    navLeftB.setVisible(startingIndex>0);
    navRightB.setVisible(startingIndex+3<customers.size());
}
@FXML
public void navLeft(){
    if(customerCount>=3){
        customerCount-=3;
        populateCustomers(customerCount);
    }
}
@FXML
public void navRight(){
    if(customers.size()>customerCount+3){
        customerCount+=3;
        populateCustomers(customerCount);
    }
}
}
