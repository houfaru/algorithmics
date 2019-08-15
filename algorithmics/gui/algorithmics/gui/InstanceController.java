package algorithmics.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import javafx.scene.control.ListView;

public class InstanceController {

    @FXML
    ListView<String> problemList;
    @FXML
    ListView<String> solverList;

    public void initialize() throws IOException {

        ResourceBundle bundle = ResourceBundle.getBundle("i18");
        Enumeration<String> keys = bundle.getKeys();
        while(keys.hasMoreElements()) {
            solverList.getItems().add(keys.nextElement());
        }
        problemList.getItems().add("SAT");
        problemList.getItems().add("VertexCover");
        problemList.getItems().add("Knapsack");

        final FXMLLoader loader = new FXMLLoader(getClass().getResource("ExecutionLayout.fxml"));

        final Parent root = loader.load();
        ExecutionController executionController = loader.getController();
        problemList.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable,
                            String oldValue, String newValue) {
                        executionController.setProblem(newValue);
                    }
                });

        solverList.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable,
                            String oldValue, String newValue) {
                        executionController.setSolver(newValue);
                    }
                });

    }

    @FXML
    public void problemSelected() {
        System.out.println("selecting " + problemList.getSelectionModel().getSelectedItem());

    }

    @FXML
    public void solverSelected() {
        System.out.println("selecting " + solverList.getSelectionModel().getSelectedItem());
    }

}
