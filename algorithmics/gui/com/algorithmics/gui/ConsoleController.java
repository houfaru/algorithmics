package com.algorithmics.gui;
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
    
    public void initialize() {
        feedBack.textProperty().setValue("okay");

    }
    
    public void setText(String text) {
        feedBack.textProperty().setValue("xxxx");
    }
    
}
