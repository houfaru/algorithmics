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
        // problemTree.setRoot(new TreeItem<String>("NP-Complete"));
        //
        // problemTree.getRoot().getChildren().add(new TreeItem<String>("SAT"));
        // problemTree.getRoot().getChildren().add(new TreeItem<String>("VERTEX COVER"));
        // problemTree.getRoot().getChildren().add(new TreeItem<String>("LINEAR PROGRAMMING"));
        //
        // solverTree.setRoot(new TreeItem<String>("Solver"));
        // solverTree.getRoot().getChildren().add(new TreeItem<String>("DPLL"));
        // solverTree.getRoot().getChildren().add(new TreeItem<String>("minisat C++ Core"));
        // solverTree.getRoot().getChildren().add(new TreeItem<String>("Brute Force"));

    }

}
