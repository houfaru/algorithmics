package com.algorithmics.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.algorithmics.invocation.SolverLocator;
import com.algorithmics.np.core.NPProblem;
import com.algorithmics.servicesupport.UserExecutionException;

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
    private MainController mainController;

    public void initialize() throws IOException {

        ResourceBundle bundle = ResourceBundle.getBundle("i18");
        Enumeration<String> keys = bundle.getKeys();
        while (keys.hasMoreElements()) {
            solverList.getItems().add(keys.nextElement());
        }
        solverList.getSelectionModel().select(1);

    }

    public void init(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void problemSelected() {
        mainController.setProblem(problemList.getSelectionModel().getSelectedItem());
        mainController.appendLog("selecting " + problemList.getSelectionModel().getSelectedItem());

    }

    @FXML
    public void solverSelected() {
        try {
            mainController.setSolver(solverList.getSelectionModel().getSelectedItem());
        } catch (UserExecutionException e) {
            mainController.appendException(e);
            e.printStackTrace();
        }
        mainController.appendLog("selecting " + solverList.getSelectionModel().getSelectedItem());
    }

}
