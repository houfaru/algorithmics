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


public class ExecutionController {

    private String problem;
    private String solver;
    private String input = "x AND y";

    @FXML
    public TextArea inputTextArea;
    @FXML
    public TextArea outputTextArea;

    public void initialize() {
        inputTextArea.setText("x AND y");
        inputTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
            input = newValue;
        });

    }

    public void reload() throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("ExecutionLayout.fxml"));
        final Parent root = loader.load();
    }

    public void execute() throws IOException {

        reload();

        SATSolverRecursive satSolver = SolverLocator.getSolver(SATSolverRecursive.class);
        Optional<VariableAssignment> solution = satSolver.solve(input);
        getConsole().append("solving...");
        outputTextArea.setText(String.valueOf(solution.isPresent()) + "\n");
        if (solution.isPresent()) {
            outputTextArea.appendText("assignment " + String.valueOf(solution.get()));
        }
        getConsole().append("finished...");

    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public void setSolver(String solver) {
        System.out.println("selecting solver " + solver);
        this.solver = solver;
    }

    public ConsoleController getConsole() throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsoleLayout.fxml"));
        final Parent root = loader.load();
        return loader.getController();

    }

}
