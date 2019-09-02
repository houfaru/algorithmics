package com.algorithmics.gui;

import java.io.File;
import java.util.Optional;
import java.util.ServiceLoader;

import com.algorithmics.invocation.SolverLocator;
import com.algorithmics.invocation.SolverMapping;
import com.algorithmics.np.core.NPProblem;
import com.algorithmics.np.core.ProblemStructure;
import com.algorithmics.np.core.Solver;
import com.algorithmics.np.preprocessor.SpecificFormatReader;
import com.algorithmics.servicesupport.ExecutionException;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

public class FileExecutionController {
    @FXML
    public TextArea outputTextArea;

    private MainController mainController;

    public void init(MainController mainController) {
        this.mainController = mainController;
    }

    public void initialize() {
        outputTextArea.setWrapText(true);
    }

    public void execute() {

    }

    public void chooseFile() {
        FileChooser fileChooser = new FileChooser();

        try {
            Solver solver = SolverLocator.locate(mainController.getSolver());
            SolverMapping mapping = solver.getClass().getAnnotation(SolverMapping.class);
            String extension = mapping.fileExtension();
            FileChooser.ExtensionFilter extFilter =
                    new FileChooser.ExtensionFilter("*." + extension + " file", "*." + extension);
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(mainController.primaryStage);
            long currentTimeMillis = System.currentTimeMillis();
            SpecificFormatReader reader = locateReader(extension);
            ProblemStructure instance = reader.readFromFile(file.getAbsolutePath());
            NPProblem o = reader.getWithParameter(instance, 6);
            Optional solution = solver.solve(o);
            if (solution.isPresent()) {
                outputTextArea.setText("SATISFIABLE\n" + String.valueOf(solution.get()));
            } else {
                outputTextArea.setText(String.valueOf("UNSATISFIABLE\n"));
            }
            mainController.appendInfo("execution finished in "
                    + (System.currentTimeMillis() - currentTimeMillis) + " ms");
        } catch (ExecutionException e) {
            mainController.appendException(e);
        }

    }

    public SpecificFormatReader locateReader(String extension) throws ExecutionException {
        ServiceLoader<SpecificFormatReader> readers =
                ServiceLoader.load(SpecificFormatReader.class);
        for (SpecificFormatReader specificFormatReader : readers) {
            if (specificFormatReader.getExtension().equals(extension)) {
                return specificFormatReader;
            }
        }
        throw new ExecutionException("reader for file " + extension + " not found");
    }

}
