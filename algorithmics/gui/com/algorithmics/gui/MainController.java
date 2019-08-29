package com.algorithmics.gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import com.algorithmics.servicesupport.ExecutionException;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;


@SuppressWarnings("deprecation")
public class MainController extends Application {

    Stage primaryStage;

    @FXML
    ConsoleController consoleController;
    @FXML
    ExecutionController executionController;
    @FXML
    FileExecutionController fileExecutionController;
    @FXML
    InstanceController instanceController;
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
        instanceController.init(this);
        fileExecutionController.init(this);

    }

    public void appendLog(String log) {
        ObservableList<Node> children = consoleController.feedBack.getChildren();
        children.add(new Text(log + "\n"));
    };

    public void appendException(ExecutionException exception) {
        ObservableList<Node> children = consoleController.feedBack.getChildren();
        Text exceptionText = new Text(exception.getLocalizedMessage() + "\n");
        exceptionText.setFill(Color.RED);
        children.add(exceptionText);
    };
    public void appendInfo(String info) {
        ObservableList<Node> children = consoleController.feedBack.getChildren();
        Text exceptionText = new Text(info + "\n");
        exceptionText.setFill(Color.BLUE);
        children.add(exceptionText);
    };

    public void setSolver(String solver) {
        executionController.setSolver(solver);
    }

    public void setProblem(String problem) {
        executionController.setProblem(problem);
    }

    public void setTitle(String title) {
        primaryStage.setTitle(title);
    }



}
