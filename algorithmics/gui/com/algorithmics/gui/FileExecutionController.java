package com.algorithmics.gui;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

import com.algorithmics.invocation.SolverLocator;
import com.algorithmics.invocation.SolverMapping;
import com.algorithmics.np.core.NPProblem;
import com.algorithmics.np.core.ProblemStructure;
import com.algorithmics.np.core.Solver;
import com.algorithmics.np.preprocessor.SpecificFormatReader;
import com.algorithmics.servicesupport.UserExecutionException;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

public class FileExecutionController {

    @FXML
    public TextArea inputTextArea;

    @FXML
    public TextArea outputTextArea;

    private MainController mainController;

    public void init(MainController mainController) {
        this.mainController = mainController;
    }

    public void initialize() {
        outputTextArea.setWrapText(true);
        inputTextArea.setWrapText(true);
    }

    public void execute() {

    }

    public void chooseFile() {
        FileChooser fileChooser = new FileChooser();

        try {
            Solver solver = SolverLocator.locate(mainController.getSolver());
            SolverMapping mapping = solver.getClass().getAnnotation(SolverMapping.class);
            String []extension = mapping.fileExtensions();
            List<String> exts = Arrays.asList(extension).stream().map(e->"*."+e).collect(Collectors.toList());
            FileChooser.ExtensionFilter extFilter =
                    new FileChooser.ExtensionFilter(exts + " file", exts);
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(mainController.primaryStage);
            long currentTimeMillis = System.currentTimeMillis();
            SpecificFormatReader reader = locateReader(getFileExtension(file));
            ProblemStructure instance = reader.readFromFile(file.getAbsolutePath());
            inputTextArea.setText(instance.toString());
            NPProblem o = reader.getWithParameter(instance, 6);
            Optional solution = solver.solve(o);
            if (solution.isPresent()) {
                outputTextArea.setText("SATISFIABLE\n" + String.valueOf(solution.get()));
            } else {
                outputTextArea.setText(String.valueOf("UNSATISFIABLE\n"));
            }
            mainController.appendInfo("execution finished in "
                    + (System.currentTimeMillis() - currentTimeMillis) + " ms");
        } catch (UserExecutionException e) {
            mainController.appendException(e);
        }

    }


private String getFileExtension(File file) {
    String name = file.getName();
    int lastIndexOf = name.lastIndexOf(".");
    if (lastIndexOf == -1) {
        return ""; // empty extension
    }
    return name.substring(lastIndexOf+1);
}


    public SpecificFormatReader locateReader(String extension) throws UserExecutionException {
        ServiceLoader<SpecificFormatReader> readers =
                ServiceLoader.load(SpecificFormatReader.class);
        for (SpecificFormatReader specificFormatReader : readers) {
            if (specificFormatReader.getExtension().equals(extension)) {
                return specificFormatReader;
            }
        }
        throw new UserExecutionException("reader for file " + extension + " not found");
    }

}
