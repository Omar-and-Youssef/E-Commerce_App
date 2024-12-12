package engine;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
public class HomeController {
    @FXML
    Label nameLabel;
    public void displayName(String name){
        nameLabel.setText("Hello: "+name);
    }

}
