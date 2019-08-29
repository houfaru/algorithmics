package com.algorithmics.gui;

import java.io.IOException;
import java.util.Optional;

import com.algorithmics.invocation.InvocationHandler;
import com.algorithmics.invocation.SolverLocator;
import com.algorithmics.minisat.MiniSatSystemCallSATSolver;
import com.algorithmics.np.SAT.instance.VariableAssignment;
import com.algorithmics.np.SAT.solver.SATSolverRecursive;
import com.algorithmics.np.core.Certificate;
import com.algorithmics.np.core.Solver;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.beans.property.SimpleStringProperty;
import javafx.stage.Stage;

public class ExecutionController {

    private String problem;
    private String solver;
    private String input = "x AND y";
    @FXML
    public TextArea inputTextArea;
    @FXML
    public TextArea outputTextArea;

    private MainController mainController;

    public void init(MainController mainController) {
        this.mainController = mainController;
    }

    public void initialize() {
        inputTextArea.setText("x AND y");
        inputTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
            input = newValue;
        });

    }

    public void execute() throws IOException {
        
        Solver solver = InvocationHandler.handle(this.solver, null);
        
        Optional<Certificate> solution = solver.solveForDefaultFormat(input);
        outputTextArea.setText(String.valueOf(solution.isPresent()) + "\n");
        if (solution.isPresent()) {
            outputTextArea.appendText("assignment " + String.valueOf(solution.get()));
        }
        mainController.appendLog("execution finished");
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public void setSolver(String solver) {
        System.out.println("selecting solver " + solver);
        this.solver = solver;
    }

}
