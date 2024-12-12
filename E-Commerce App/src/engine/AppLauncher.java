package engine;
import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.stage.*;


public class Applauncher extends Application {
    private static Engine app;
 
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        app=new Engine(primaryStage);
    }
  
}