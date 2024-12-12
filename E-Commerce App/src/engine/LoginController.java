// package engine;

// import javafx.event.ActionEvent;
// import javafx.fxml.FXML;
// import javafx.scene.control.*;
// import service.*;
// import entity.users.accounts.*;

// public class LoginController extends BaseController {

//     @FXML
//     private TextField userEmail;
//     @FXML
//     private PasswordField password;

//     @FXML
//     private void handleLogin(ActionEvent event){
//         String email=(String)userEmail.getText();
//         String pass=(String)password.getText();
//         try{
//             User user= engine.logIn(email,pass);
//             if(user instanceof Customer){
//                 engine.setCurrentUser(user);
//                 engine.switchScene(Screen.HOME);
//             }
//             else if(user instanceof Admin){
//                 engine.setCurrentUser(user);
//                 //TODO ADMIN
//                 // engine.switchAdminScene(Screen.ADMIN_DASHBOARD);
//             }
//         }
//         catch(Exception e){
//             //handle alert based on exception, if message ==..alert ...
//             System.out.println("Invalid login credentials");
//         }        
//     }
//     private void handleSignUp(ActionEvent event){
//         engine.switchScene(Screen.SIGNUP);
//     }
// }
