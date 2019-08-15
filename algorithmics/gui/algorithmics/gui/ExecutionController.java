package algorithmics.gui;

import java.io.IOException;
import java.util.Optional;

import com.algorithmics.np.SAT.instance.VariableAssignment;
import com.algorithmics.np.SAT.solver.SATSolverRecursive;

import algorithmics.minisat.SATSolverSystemCall;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.beans.property.SimpleStringProperty;


public class ExecutionController {

    private String problem;
    private String solver;
    private String input = "x AND y";
    @FXML
    public TextArea inputTextArea;
    @FXML
    public TextArea outputTextArea;
    SimpleStringProperty line;
    public void initialize() {
        inputTextArea.setText("x AND y");
        inputTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
            input = newValue;
        });

    }

    

    public void execute() throws IOException {

        SATSolverRecursive satSolver = SolverLocator.getSolver(SATSolverRecursive.class);
        Optional<VariableAssignment> solution = satSolver.solve(input);
        outputTextArea.setText(String.valueOf(solution.isPresent()) + "\n");
        if (solution.isPresent()) {
            outputTextArea.appendText("assignment " + String.valueOf(solution.get()));
        }
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsoleLayout.fxml"));
        final Parent root = loader.load();
        ConsoleController consoleController = loader.getController();
        consoleController.append("test");
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public void setSolver(String solver) {
        System.out.println("selecting solver " + solver);
        this.solver = solver;
    }

}
