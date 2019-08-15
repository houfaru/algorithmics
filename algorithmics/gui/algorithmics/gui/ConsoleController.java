package algorithmics.gui;
import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.beans.property.SimpleStringProperty;
public class ConsoleController {
    @FXML
    TextArea feedBack;
    String line="test";
    
    SimpleStringProperty log;
    public void initialize() {
        log=new SimpleStringProperty("...");
        log.setValue("welcome");
        feedBack.textProperty().bindBidirectional(log);
        log.addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                feedBack.setText(newValue);
                
            }});
    }
    
    public void append(String line) throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsoleLayout.fxml"));
        final Parent root = loader.load();
        log.setValue(line);
        
    }
    public String getLine() {
        return line;
    }
    
}
