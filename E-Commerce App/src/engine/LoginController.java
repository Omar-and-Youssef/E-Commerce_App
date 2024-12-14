package engine;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import entity.users.accounts.*;
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
    private void handleLogin(ActionEvent event){
        String email=userEmail.getText();
        String pass=password.getText();
        try{
            if(email==null) throw new NullPointerException("Email is required");
            if(pass==null) throw new NullPointerException("Password is required");
            User user= engine.logIn(email,pass);
            if(user instanceof Customer){
                engine.setCurrentUser(user);
                engine.switchScene(Screen.HOME);
                engine.getHomeController().displayName(user.getName());
            }
            else if(user instanceof Admin){
                engine.setCurrentUser(user);
                //TODO ADMIN
                // engine.switchAdminScene(Screen.ADMIN_DASHBOARD);
            }
        }
        catch(Exception e){
            String message=e.getMessage();
            if(e instanceof NullPointerException)
            switch(message){
                case "Email is required":
                    System.out.println(message);
                    //handle alert based on exception, if message ==..alert ...
                    break;
                case "Password is required":
                    System.out.println(message);;
                    //handle alert based on exception, if message ==..alert ...
                    break;
            }
            if(e instanceof ServiceException)
            //handle alert based on exception, if message ==..alert ...
            System.out.println("Invalid login credentials");
        }        
    }
    @FXML
    private void handleSignUp(ActionEvent event){
        engine.switchScene(Screen.SIGNUP);
    }
}
