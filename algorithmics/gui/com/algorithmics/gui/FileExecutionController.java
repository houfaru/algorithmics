package com.algorithmics.gui;

import java.io.File;

import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

public class FileExecutionController {



    public TextArea outputTextArea;

    private MainController mainController;

    public void init(MainController mainController) {
        this.mainController = mainController;
    }

    public void initialize() {


    }

    public void execute() {}

    public void chooseFile() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(mainController.primaryStage);
        System.out.println(file);
    }

}
