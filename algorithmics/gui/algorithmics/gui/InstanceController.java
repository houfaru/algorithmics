package algorithmics.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class InstanceController {

	@FXML
	private TreeView<String> problemTree;
	@FXML
	private TreeView<String> solverTree;
	@FXML
	Button myButton;

	public InstanceController() {

		// initialize();
	}

	@FXML
	public void reactToClick() {
	}

	public void initialize() {

		problemTree.getRoot().getChildren().add(new TreeItem<String>("SAT"));
		problemTree.getRoot().getChildren().add(new TreeItem<String>("VERTEX COVER"));
		problemTree.getRoot().getChildren().add(new TreeItem<String>("LINEAR PROGRAMMING"));
		solverTree.getRoot().getChildren().add(new TreeItem<String>("DPLL"));
		solverTree.getRoot().getChildren().add(new TreeItem<String>("minisat C++ Core"));
		solverTree.getRoot().getChildren().add(new TreeItem<String>("Brute Force"));

		problemTree.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {

			@Override
			public void changed(ObservableValue<? extends TreeItem<String>> arg0, TreeItem<String> arg1,
					TreeItem<String> arg2) {
				// TODO Auto-generated method stub

			}
		});
	}
}