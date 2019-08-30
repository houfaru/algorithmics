package com.algorithmics.gui;

import java.io.File;
import java.util.ServiceLoader;

import com.algorithmics.invocation.SolverLocator;
import com.algorithmics.invocation.SolverMapping;
import com.algorithmics.np.core.Solver;
import com.algorithmics.np.preprocessor.SpecificFormatReader;
import com.algorithmics.servicesupport.ExecutionException;

import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

public class FileExecutionController {

    public TextArea outputTextArea;

    private MainController mainController;
    private InstanceController instanceController;

    public void init(MainController mainController) {
        this.mainController = mainController;
    }

    public void init(InstanceController instanceController) {
        this.instanceController = instanceController;
    }

    public void initialize() {


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
            System.out.println(file);
            SpecificFormatReader reader = locateReader(extension);
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
