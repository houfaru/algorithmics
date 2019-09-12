package com.algorithmics.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Properties;

import com.algorithmics.servicesupport.UserExecutionException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.text.TextFlow;
import javafx.scene.text.Text;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;

public class HelpController {
    @FXML
    WebView helpPage;

    MainController mainController;

    public void initialize() {}

    public void init(MainController mainController) throws UserExecutionException {
        this.mainController = mainController;

        renderHelpPage();
    }

    public String loadHelpFileName(String solverLabel) throws UserExecutionException {
        Properties p = new Properties();
        InputStream inputStream;
        inputStream = getClass().getClassLoader().getResourceAsStream("i18helppage.properties");
        if (inputStream != null) {
            try {
                p.load(inputStream);
            } catch (IOException e) {
                throw new UserExecutionException(e);
            }
        } else {
            throw new UserExecutionException(new FileNotFoundException(
                    "property file 'i18helppage' not found in the classpath"));
        }
        return p.getProperty(solverLabel);

    }

    public void renderHelpPage() throws UserExecutionException {
        String fileName = loadHelpFileName(mainController.getSolver());
        mainController.appendInfo("loading help page "+fileName);
        final WebEngine webEngine = helpPage.getEngine();
        String location;
        try {
            location =
                    new File("help" + File.separator + fileName.trim()).toURI().toURL().toExternalForm();
            Platform.runLater(new Runnable() {
                public void run() {
                    webEngine.load(location);
                    webEngine.reload();
                }
            });

        } catch (MalformedURLException e) {
            throw new UserExecutionException(e);
        }

    }



}
