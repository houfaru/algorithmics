package com.algorithmics.gui;

import java.io.IOException;
import java.util.Optional;

import com.algorithmics.invocation.SolverLocator;
import com.algorithmics.invocation.SolverLocator;
import com.algorithmics.minisat.MiniSatSystemCallSATSolver;
import com.algorithmics.np.SAT.instance.VariableAssignment;
import com.algorithmics.np.SAT.solver.SATSolverRecursive;
import com.algorithmics.np.core.Certificate;
import com.algorithmics.np.core.Solver;
import com.algorithmics.servicesupport.ExecutionException;

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
    String solver = "SAT_SOLVER_RECURSIVE";
    private String input =
            "x26 OR x48 OR x1 AND x15 OR -x4 OR x28 OR x1 AND -x24 OR -x50 OR x26 OR x1 AND x34";
    @FXML
    public TextArea inputTextArea;
    @FXML
    public TextArea outputTextArea;
    @FXML
    public TextArea prettyFormatInput;
    
    private MainController mainController;

    public void init(MainController mainController) {
        this.mainController = mainController;
    }

    public void initialize() {
        inputTextArea.setWrapText(true);
        inputTextArea.setText(input);
        inputTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
            input = newValue;
        });

    }

    public void execute() throws IOException {

        if (null == solver) {
            mainController.appendException(new ExecutionException("No solver is selected"));
        }
        try {
            Solver solver = SolverLocator.locate(this.solver);
            Optional<Certificate> solution = solver.solveForDefaultFormat(input);
            prettyFormatInput.setText(solver.getProblem(input).toString());
            outputTextArea.setText("result:" + String.valueOf(solution.isPresent()) + "\n");
            long currentTimeMillis=System.currentTimeMillis();
            mainController.appendInfo("execution started...");
            if (solution.isPresent()) {
                outputTextArea.appendText(String.valueOf(solution.get()));
            }
            mainController.appendInfo("execution finished in "+ (System.currentTimeMillis()-currentTimeMillis)+" ms");
            mainController.appendInfo("");
        } catch (ExecutionException e) {
            mainController.appendException(e);
        }

    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public void setSolver(String solver) {
        this.solver = solver;
    }

}
