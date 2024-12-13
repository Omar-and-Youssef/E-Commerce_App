package engine;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entity.products.Category;
import entity.users.accounts.Customer;
import entity.users.accounts.Gender;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class SignUpController extends BaseController{
    //Validations:
        //Name:  cannot be empty & only alphabets, 
            //min 2 characters, max 50 characters
        //Email:emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$"; then email.matches(emailRegex)
            //throw Password must be at least 8 characters long
        //Password:String passwordRegex = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+])[A-Za-z\\d!@#$%^&*()_+]{8,}";
            //throw Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character
        //Phone number : matches("[0-9]+")
            //throw Phone number must be 10 digits long and contain only numbers
        //Date: check:
             // Check if the user has selected a date
        //LocalDate dob = dobPicker.getValue();
        // if (dob == null) {
        //     throw new IllegalArgumentException("Date of birth is required");
        // }

        // // Check if the user is at least 18 years old
        // LocalDate today = LocalDate.now();
        // Period age = Period.between(dob, today);
        
        // if (age.getYears() < 18) {
        //     throw new IllegalArgumentException("User must be at least 18 years old");
        // }
    @FXML 
    private Button backButton;
    @FXML 
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML 
    private PasswordField passwordField;
    @FXML
    private TextField addressField;
    @FXML 
    private TextField phoneNumberField;
    @FXML
    private RadioButton female;
    @FXML
    private RadioButton male;
    // @FXML
    // private DatePicker birthDayPicker;
    @FXML
    private ToggleGroup genderToggleGroup;
    @FXML
    private ChoiceBox<String> categoryChoiceBox;
    @FXML
    private Label errorLabel;
    @FXML 
    private Button signUpButton;
    // @FXML
    // private Label title;
    
    @FXML
    private void handleSignUp(ActionEvent event){
        // we create the customer object here
        try{
        String name = nameField.getText();
        String email = emailField.getText(); 
        String password = passwordField.getText(); 
        String address = addressField.getText();
        // LocalDate dob = birthDayPicker.getValue();
        String phoneNumber = phoneNumberField.getText();

        RadioButton selectedGender = (RadioButton) genderToggleGroup.getSelectedToggle();
        Gender gender = Gender.valueOf(selectedGender.getText().toUpperCase());

        categoryChoiceBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Selected category: " + newValue);
        });
        Category category = Category.valueOf(categoryChoiceBox.getValue().toUpperCase());

        String tempName=name.trim();
        String tempEmail=email.trim();

            if(tempEmail.isEmpty()||tempName.isEmpty()
            || password.isEmpty()|| address.isEmpty()
            ||phoneNumber.isEmpty()||selectedGender==null
            )
                throw new NullPointerException();
            
                if(!tempName.matches("^[a-zA-Z\\s]+$"))
                throw new IllegalArgumentException("Name must contain only alphabets and spaces");
                        
            if(!tempEmail.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"))
                throw new IllegalArgumentException("Invalid email syntax");
            if(!engine.isValidEmail(tempEmail))
                throw new IllegalArgumentException("Account already exists");
            
            if(!isValidPassword(password)) 
                throw new IllegalArgumentException("Invalid Password");
            

            // String addressRegex = "^[a-zA-Z\\s]+,\\s*[a-zA-Z\\s/]+,\\s*[a-zA-Z0-9\\s]+,?\\s*(\\d+[A-Za-z]*)?$";                
            // if (!address.matches(addressRegex)) {
            //     throw new IllegalArgumentException("Invalid address format.");
            // }
            
            
            //TODO check for age
            // LocalDate today = LocalDate.ofEpochDay(System.currentTimeMillis() / (24 * 60 * 60 * 1000)); // Static instantiation
            // int age = Period.between(dob, today).getYears();
            // if (age < 16) {
            //     throw new IllegalArgumentException("You must be at least 16 years old");
            // } 
                
            if (!phoneNumber.matches("^\\+\\d{1,4}(?:\\s?\\d{1,4}){3,}$")) //format is +...
                throw new IllegalArgumentException("Invalid phone number format.");
            
            Customer customer = new Customer(name, email, password,gender,phoneNumber, address, category);
            System.out.println(customer.toString()); 
            engine.setCurrentUser(customer);
            engine.switchScene(Screen.HOME);
        } 
        catch(Exception e){
            if(e.getMessage()==null) e=new IllegalArgumentException("All fields are required");
            errorLabel.setText(e.getMessage());
            errorLabel.setVisible(true);
            return;
        }    
        errorLabel.setVisible(false);
    }
    // private void showAlert(String title, String message) {
    //     Alert alert = new Alert(Alert.AlertType.ERROR);
    //     alert.setTitle(title);
    //     alert.setHeaderText(null);
    //     alert.setContentText(message);
    //     alert.showAndWait();
    // }
    private boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password.matches(passwordRegex);
    }
    
    @FXML 
    private void handleBack(ActionEvent event) {
        engine.switchScene(Screen.LOGIN);
    }
    public Label geterrorLabel(){
        return errorLabel;
    }
    public ChoiceBox<String> getCategoryChoiceBox(){
        return categoryChoiceBox;
    }

}
