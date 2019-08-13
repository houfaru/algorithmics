package algorithmics.gui;

import java.io.IOException;
import java.net.URL;
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

    public void initialize() {

        problemList.getItems().add("SAT");
        problemList.getItems().add("VertexCover");
        problemList.getItems().add("Knapsack");

        solverList.getItems().add("DPLL");
        solverList.getItems().add("minisat C++");
        solverList.getItems().add("BruteForce");

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
