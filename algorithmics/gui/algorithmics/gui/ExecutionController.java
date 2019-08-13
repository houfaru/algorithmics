package algorithmics.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class ExecutionController {

    public void initialize() {}

    public void execute() throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("InstanceLayout.fxml"));

        final Parent root = loader.load();
        InstanceController instanceController = loader.getController();
        instanceController.problemSelected();
        instanceController.solverSelected();

    }

}
