package engine;

import javafx.application.Application;
import javafx.stage.Stage;

public class AppLauncher extends Application{
    private static Engine app;
    public static void main(String[]args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        app = new Engine(primaryStage);
    }
}
