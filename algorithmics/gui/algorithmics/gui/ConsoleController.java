package algorithmics.gui;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
public class ConsoleController {
    @FXML
    TextArea feedBack;
    public void initialize() {
        feedBack.setText("Welcome....");
    }
    
    public void append(String line) throws IOException {
        System.out.println(feedBack.getText());
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsoleLayout.fxml"));
        final Parent root = loader.load();
        feedBack.appendText(line+"\n");
    }
    
}
