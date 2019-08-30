package com.algorithmics.gui;

import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.text.TextFlow;
import javafx.scene.text.Text;
import javafx.beans.property.SimpleStringProperty;

public class ConsoleController {
    @FXML
    TextFlow feedBack;

    MainController mainController;

    public void initialize() {
        ObservableList<Node> children = feedBack.getChildren();
        children.add(new Text("Welcome\n"));
    }

    public void init(MainController mainController) {
        this.mainController = mainController;
    }

    public void clear() {
        feedBack.getChildren().clear();
    }
    
}
