package algorithmics.gui;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


@SuppressWarnings("deprecation")
public class Driver extends Application implements PropertyChangeListener{
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		final FXMLLoader loader = new FXMLLoader(getClass().getResource("main_layout.fxml"));
		
		final Parent root = loader.load();
		
        primaryStage.setTitle("Algorithmics");
        
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
        
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
	

}