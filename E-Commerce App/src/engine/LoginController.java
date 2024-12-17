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
        String email=userEmail.getText();
        String pass=password.getText();
        try{
            if (email == null || email.isEmpty())
                throw new Exception("Email is required");
            if (pass == null || pass.isEmpty())
                throw new Exception("Password is required");
            if (!engine.logIn(email, pass)) 
                throw new Exception("Invalid email or password");
            if(!engine.logIn(email,pass)) 
                throw new Exception("Invalid email or password");
            engine.switchScene(Screen.HOME);
            errorLabel.setVisible(false);
        }
        catch(Exception e){
            e.printStackTrace();
            errorLabel.setVisible(true);
            errorLabel.setText(e.getMessage());
        }        
    }
    @FXML
    private void handleSignUp(ActionEvent event){
        engine.switchScene(Screen.SIGNUP);
        errorLabel.setVisible(false);

    }
    public Label getErrorLabel(){
        return errorLabel;
    }
}
