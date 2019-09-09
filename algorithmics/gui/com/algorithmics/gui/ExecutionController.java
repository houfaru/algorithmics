package com.algorithmics.gui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

import com.algorithmics.invocation.SolverLocator;
import com.algorithmics.np.core.Certificate;
import com.algorithmics.np.core.Solver;
import com.algorithmics.servicesupport.UserExecutionException;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ExecutionController {

    private String problem;
    private String solver = "SAT_SOLVER_RECURSIVE";
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

    public String loadExample(String solverLabel) throws UserExecutionException  {
        Properties p=new Properties();
        InputStream inputStream;
        inputStream = getClass().getClassLoader().getResourceAsStream("i18example.properties");
        if (inputStream != null) {
            try {
                p.load(inputStream);
            } catch (IOException e) {
                throw new UserExecutionException(e);
            }
        } else {
            throw new UserExecutionException(new FileNotFoundException("property file 'i18example' not found in the classpath"));
        }
        return p.getProperty(solverLabel);
        
    }
    
    public void initialize() {
        inputTextArea.setWrapText(true);
        inputTextArea.setText(input);
        inputTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
            input = newValue;
        });
        prettyFormatInput.setWrapText(true);

    }

    public void execute() throws IOException {

        if (null == solver) {
            mainController.appendException(new UserExecutionException("No solver is selected"));
        }
        try {
            Solver solver = SolverLocator.locate(this.solver);
            Optional<Certificate> solution = solver.solveForDefaultFormat(input);
            prettyFormatInput.setText(solver.getProblem(input).toString());
            outputTextArea.setText("result:" + String.valueOf(solution.isPresent()) + "\n");
            long currentTimeMillis = System.currentTimeMillis();
            mainController.appendInfo("execution started...");
            if (solution.isPresent()) {
                outputTextArea.appendText(String.valueOf(solution.get()));
            }
            mainController.appendInfo("execution finished in "
                    + (System.currentTimeMillis() - currentTimeMillis) + " ms");
            mainController.appendInfo("");
        } catch (UserExecutionException e) {
            mainController.appendException(e);
        }

    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public void setSolver(String solver) throws UserExecutionException {
        this.solver = solver;
        input=loadExample(solver);
        inputTextArea.setText(input);
        
    }

    public String getSolver() {
        return solver;
    }

}
