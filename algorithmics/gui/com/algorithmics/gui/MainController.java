package com.algorithmics.gui;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


@SuppressWarnings("deprecation")
public class MainController extends Application {

    Stage primaryStage;

    @FXML
    ConsoleController consoleController;
    @FXML
    ExecutionController executionController;
    @FXML
    TextArea feedBack;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("MainLayout.fxml"));
        final Parent root = loader.load();
        primaryStage.setTitle("Algorithmics");

        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
        primaryStage.setMaximized(true);
        this.primaryStage = primaryStage;

        
        
    }
    public void initialize() {
        consoleController.init(this);
        executionController.init(this);
    }

    public void appendLog(String log) {
        consoleController.feedBack.appendText(log+"\n");
    };
    public void setTitle(String title) {
        primaryStage.setTitle(title);
    }



}
