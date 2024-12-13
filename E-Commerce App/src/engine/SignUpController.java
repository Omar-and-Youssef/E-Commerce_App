package engine;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    // @FXML 
    // private PasswordField passwordField;
    // @FXML
    // private TextField addressField;
    // @FXML 
    // private TextField phoneNumberField;
    // @FXML
    // private RadioButton male,female;
    // @FXML
    // private DatePicker birthDayPicker;
    // @FXML
    // private ChoiceBox categoryChoice; 
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
 
            String tempName=name.trim();
            String tempEmail=email.trim();
            if(tempEmail.isEmpty()||tempName.isEmpty()){
                throw new NullPointerException();
            }
            if(!tempName.matches("^[a-zA-Z\\\\s]+$")){
                throw new IllegalArgumentException("Name must contain only alphabets");
            }
            if(!isValidEmail(tempEmail)){
                throw new IllegalArgumentException("Invalid email");
            }
        } catch(Exception e){
            if(e.getMessage()==null) e=new IllegalArgumentException("All fields are required");
            errorLabel.setText(e.getMessage());
            errorLabel.setVisible(true);
            return;
        }    
    }
    // private void showAlert(String title, String message) {
    //     Alert alert = new Alert(Alert.AlertType.ERROR);
    //     alert.setTitle(title);
    //     alert.setHeaderText(null);
    //     alert.setContentText(message);
    //     alert.showAndWait();
    // }
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    @FXML 
    private void handleBack(ActionEvent event) {
        engine.switchScene(Screen.LOGIN);
    }
    public Label geterrorLabel(){
        return errorLabel;
    }

}