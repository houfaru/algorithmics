package algorithmics.gui;

import java.io.IOException;
import java.util.Optional;

import com.algorithmics.np.SAT.instance.VariableAssignment;
import com.algorithmics.np.SAT.solver.SATSolverRecursive;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class ExecutionController {

    private String problem;
    private String solver;
    private String input = "x AND y";

    @FXML
    public TextArea inputTextArea;

    public void initialize() {
        inputTextArea.setText("x AND y");
        inputTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
            input = newValue;
        });
    }

    public void execute() {
        SATSolverRecursive satSolver = SolverLocator.getSolver(SATSolverRecursive.class);
        Optional<VariableAssignment> solution = satSolver.solve(input);
        System.out.println(solution.isPresent());
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public void setSolver(String solver) {
        System.out.println("selecting solver " + solver);
        this.solver = solver;
    }

}
