package com.algorithmics.gui;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;

public class HelpController {
    @FXML
    WebView helpPage;

    MainController mainController;

    public void initialize() throws MalformedURLException {
        final WebEngine webEngine = helpPage.getEngine();
        String location =
                new File(
                        "help" + File.separator + "sat.html"
                ).toURI().toURL().toExternalForm();
        webEngine.load(location);
    }

    public void init(MainController mainController) {
        this.mainController = mainController;
    }

}
