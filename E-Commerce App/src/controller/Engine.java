package controller;
import entity.users.accounts.*;
import entity.products.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;
public class Engine {
    private Screen currentScreen;
    private User currentUser;
    private Product viewedProduct;
    private Stage stage;
    private Scene greenRealmScene;
    // private GreenRealmController greenController;   
//=======================================Constructor===================================
    public Engine(){
        currentScreen=Screen.LOGIN;
        try{
            FXMLLoader endLoader = new FXMLLoader(getClass().getResource("/game/gui/GameEnd.fxml"));
            Parent endRoot = endLoader.load();
            endScene = new Scene(endRoot);
            //Up is to create actual scene maybe save it
            endController = (GameEndController) endLoader.getController();
            //Up is to create cotnroller for the scene not if both not saved it may cause
            //weird behaviour if invoke controlelr
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Could not load scenes");
        }
    }
//=======================================Methods=======================================
    //Note all  methods here are conencted and invoked by the gui
    //they are redundant because the gui cannot access the individual classes
    //All methods to be invoked from the user will be invoked from here and call user
    //If values are required to be passed as parameters those values will be obtained form gui


    //Note below method can be written in the Controller of the Home screen (FXML FILE)
    public void viewProduct(Product product){
        viewedProduct=product;
        switchScene(Screen.PRODUCT,product);
    }
    public void userLogin(String name,String password){
        //check for name and password
        // retreive user object
        currentScreen=Screen.HOME;
    }
    public void userSignUp(String name,String password){
        //create new user account in database
        userLogin(name,password);
    }
    public void addToCart(){
        //get The quantity from the GUI
        //validate to not be admin this validation will be done in all typcasted methods
        int quantity=0;
        ((Customer)currentUser).addToCart(viewedProduct,quantity);
    }
    public void addToWishList(){
        ((Customer)currentUser).addToWishlist(viewedProduct);
    }
    public void switchScene(Screen nextScene,Product product){
        currentScreen=nextScene;
        switch(nextScreen){
            greenController.setGreenRealm(greenDiceVal);
            //Controller is used to set a scene before displaying it
            //for example if input was 5 and want to display first invoke set... with parameter
            //implement logic then stage.setScene to actually switch scene 
            stage.setScene(greenRealmScene);   
        }
    }
//=======================================Get&Set=======================================
    public Stage getStage(){
        return stage;
    }
    public User getCurrentUser(){
        return currentUser;
    }

}   
