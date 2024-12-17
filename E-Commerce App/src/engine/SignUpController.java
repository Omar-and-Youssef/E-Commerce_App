package engine;


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
            engine.signUp(customer);
            engine.switchScene(Screen.HOME);
            engine.getHomeController().displayName(customer.getName());

            // resetSignUpScreen();
        } 
        catch(Exception e){
            if(e.getMessage()==null) e=new IllegalArgumentException("All fields are required");
            errorLabel.setText(e.getMessage());
            errorLabel.setVisible(true);
            return;
        }    
        errorLabel.setVisible(false);
    }
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

    //TODO
    // public void resetSignUpScreen(){
    //     nameField.setText("");
    //     emailField.setText("");
    //     passwordField.setText("");
    //     addressField.setText("");
    //     phoneNumberField.setText("");
    //     genderToggleGroup.selectToggle(null);
    //     categoryChoiceBox.setValue(null);
    //     errorLabel.setVisible(false);
    // }

}
