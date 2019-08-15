package algorithmics.gui;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


@SuppressWarnings("deprecation")
public class MainController extends Application {
    Stage primaryStage;
    
    @FXML
    ConsoleController consoleController;

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
        final FXMLLoader consoleloader = new FXMLLoader(getClass().getResource("ConsoleLayout.fxml"));
        System.out.println(consoleController.getLine());
        this.primaryStage = primaryStage;
        

    }

    public void setTitle(String title) {
        primaryStage.setTitle(title);
    }



}
