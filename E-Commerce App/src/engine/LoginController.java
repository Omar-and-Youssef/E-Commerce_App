package engine;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import exceptions.ServiceException;

public class LoginController extends BaseController {

    @FXML
    private TextField userEmail;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginButton;
    @FXML
    private Button signUpButton;
    @FXML
    private Label errorLabel;


    @FXML
    private void handleLogin(){
        try{
            errorLabel.setVisible(false);
            String email=validateEmail(userEmail.getText());
            String pass=validatePassword(password.getText());
            
            if (!engine.logIn(email,pass))
                throw new Exception("Invalid email or password. Please try again.");
            engine.getHomeController().handleCategoryAll();
            engine.switchScene(Screen.HOME);
        }
        catch(Exception e){
            errorLabel.setVisible(true);
            errorLabel.setText(e.getMessage());
        }        
    }
    @FXML
    private void handleSignUp(ActionEvent event){
        engine.switchScene(Screen.SIGNUP);
        errorLabel.setVisible(false);

    }
    private String validateEmail(String email) throws Exception {
        if (email==null||email.trim().isEmpty()) 
            throw new Exception("Email is required.");
        String emailRegex="^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!email.matches(emailRegex)&&!email.equals("w")&&!email.equals("q")) //w&q for testing 
            throw new Exception("Invalid email format.");
        return email.trim();
    }

    private String validatePassword(String pass) throws Exception {
        if (pass==null||pass.trim().isEmpty())
            throw new Exception("Password is required.");
        if (pass.length()<8&&!pass.equals("w")&&!pass.equals("q")) //w&q for testing 
            throw new Exception("Password must be at least 8 characters long.");
        return pass.trim();
    }

}
