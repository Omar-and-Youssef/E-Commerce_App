package engine;


import java.time.LocalDate;
import java.time.Period;

import entity.products.Category;
import entity.users.accounts.Customer;
import entity.users.accounts.Gender;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class SignUpController extends BaseController{
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
    @FXML
    private DatePicker birthDayPicker;
    @FXML
    private ToggleGroup genderToggleGroup;
    @FXML
    private ChoiceBox<String> categoryChoiceBox;
    @FXML
    private Label errorLabel;
    @FXML 
    private Button signUpButton;
    
    @FXML
    private void handleSignUp(ActionEvent event) {
        try {
            String name=validateName(nameField.getText());
            String email=validateEmail(emailField.getText());
            String password=validatePassword(passwordField.getText());
            String address=validateAddress(addressField.getText());
            String phoneNumber=validatePhoneNumber(phoneNumberField.getText());

            RadioButton selectedGender=(RadioButton) genderToggleGroup.getSelectedToggle();
            if (selectedGender==null) throw new IllegalArgumentException("Gender is required.");
            Gender gender=Gender.valueOf(selectedGender.getText().toUpperCase());
    
            String selectedCategory=categoryChoiceBox.getValue();
            if (selectedCategory==null) throw new IllegalArgumentException("Category is required.");
            Category category=Category.valueOf(selectedCategory.toUpperCase());

            LocalDate dob = validateDateOfBirth(birthDayPicker.getValue());
            
            Customer customer=new Customer(name,email,password,gender,phoneNumber,address,category);

            engine.signUp(customer);
            engine.switchScene(Screen.HOME);
            engine.getHomeController().displayName(customer.getName());
    
        } catch (Exception e) {
            errorLabel.setText(e.getMessage()!=null?e.getMessage() : "All fields are required.");
            errorLabel.setVisible(true);
        }
    }
    
    @FXML 
    private void handleBack(ActionEvent event) {
        engine.switchScene(Screen.LOGIN);
    }
    public Label geterrorLabel(){
        return errorLabel;
    }
    public void populateChoiceBox(){
        categoryChoiceBox.getItems().clear();
        categoryChoiceBox.getItems().addAll("Electronics","Books","Clothing",
        "Home",
        "Beauty",
        "Toys",
        "Sports");
    }
    private String validateName(String name) {
        if (name == null || name.trim().isEmpty()) 
            throw new IllegalArgumentException("Name is required");

        if (!name.matches("^[a-zA-Z\\s]+$"))
            throw new IllegalArgumentException("Name must contain only alphabets and spaces");
        if(name.trim().length()<3) 
            throw new IllegalArgumentException("Name is too short");
        return name.trim();
    }
    
    private String validateEmail(String email) {
        if (email==null||email.trim().isEmpty()) 
            throw new IllegalArgumentException("Email is required");
    
        String emailRegex="^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!email.matches(emailRegex)) 
            throw new IllegalArgumentException("Invalid email syntax");
        
        if (!engine.isValidEmail(email.trim())) 
            throw new IllegalArgumentException("Account already exists");
        return email.trim();
    }
    
    private String validatePassword(String password) {
        if (password == null||password.isEmpty()) 
            throw new IllegalArgumentException("Password is required");
        
        String passwordRegex="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        if (!password.matches(passwordRegex))
            throw new IllegalArgumentException("Invalid Password");
        
        return password;
    }
    
    private String validateAddress(String address) {
        if (address == null||address.trim().isEmpty()) 
            throw new IllegalArgumentException("Address is required");
            String addressRegex = "^[a-zA-Z\\s]+, \\s*[a-zA-Z\\s]+, \\s*[a-zA-Z0-9\\s]+, \\s*(Apt\\.?\\s?[0-9]+|No\\.?\\s?[0-9]+|\\d+)$";
        if(!address.matches(addressRegex))
            throw new IllegalArgumentException("Invalid address syntax");
        return address.trim();
    }
    private String validatePhoneNumber(String phoneNumber) {
        if (phoneNumber==null||phoneNumber.isEmpty())
            throw new IllegalArgumentException("Phone number is required");

    String phoneRegex ="^\\+?\\d{1,3}[-\\s]?\\(?\\d{1,4}\\)?[-\\s]?\\d{1,4}[-\\s]?\\d{1,4}[-\\s]?\\d{1,4}$";
        if (!phoneNumber.matches(phoneRegex))
            throw new IllegalArgumentException("Invalid number");
        return phoneNumber.trim();
    }
    public LocalDate  validateDateOfBirth(LocalDate dob) {
        if (dob == null)
            throw new IllegalArgumentException("Date of birth is required");
        LocalDate today = LocalDate.now();
        int age=Period.between(dob, today).getYears();
        if (age<16) 
            throw new IllegalArgumentException("You must be at least 16 years old");
        return dob;
    }
}
