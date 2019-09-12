package com.algorithmics.gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import com.algorithmics.servicesupport.UserExecutionException;

import javafx.application.Application;
import javafx.application.Platform;
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
    HelpController helpController;

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

    public void initialize() throws UserExecutionException {
        consoleController.init(this);
        executionController.init(this);
        instanceController.init(this);
        fileExecutionController.init(this);
        helpController.init(this);
    }

    public void appendLog(String log) {
        ObservableList<Node> children = consoleController.feedBack.getChildren();
        Platform.runLater(new Runnable() {
            public void run() {
                children.add(new Text(log + "\n"));
            }
        });
    };

    public void appendException(UserExecutionException exception) {
        ObservableList<Node> children = consoleController.feedBack.getChildren();
        Text exceptionText = new Text(exception.getLocalizedMessage() + "\n");
        exceptionText.setFill(Color.RED);
        Platform.runLater(new Runnable() {
            public void run() {
                children.add(exceptionText);
            }
        });
        
    };

    public void appendInfo(String info) {
        ObservableList<Node> children = consoleController.feedBack.getChildren();
        Text exceptionText = new Text(info + "\n");
        exceptionText.setFill(Color.BLUE);
        Platform.runLater(new Runnable() {
            public void run() {
                children.add(exceptionText);
            }
        });

    };

    public void loadHelp() throws UserExecutionException {
        helpController.renderHelpPage();
    }
    
    public void setSolver(String solver) throws UserExecutionException {
        executionController.setSolver(solver);
    }
    public String getSolver() {
        return executionController.getSolver();
    }

    public void setProblem(String problem) {
        executionController.setProblem(problem);
    }

    public void setTitle(String title) {
        primaryStage.setTitle(title);
    }



}
