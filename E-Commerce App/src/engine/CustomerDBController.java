package engine;

import javafx.fxml.FXML;

public class CustomerDBController extends BaseController{
    @FXML
    public void handleBack(){
        engine.switchScene(Screen.ANALYTICS);
    }
}
