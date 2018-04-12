import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class DrawApplicatoin extends Application {

	double pressX, pressY;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Create scene bottom-up
		BorderPane rootPane = new BorderPane();
		Pane drawPane = new Pane();
		drawPane.prefWidthProperty().bind(rootPane.widthProperty());
		drawPane.prefHeightProperty().bind(rootPane.heightProperty());
		rootPane.setCenter(drawPane);
		Scene scene = new Scene(rootPane, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Draw Application");
		primaryStage.show();

		// Add listeners for mouse and key events...

		// ... using inner class handler defined below
		drawPane.setOnMousePressed(new MyMousePressedHandler());

		// ... using anonymous inner class handler defined here
		drawPane.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				Line line = new Line(pressX, pressY, e.getX(), e.getY());
				drawPane.getChildren().add(line);
			}

		});
		
		// ... using a lambda expression
		// Add keyboard listener for Control-z undo events
		
		final KeyCombination CTRL_Z = new KeyCodeCombination(KeyCode.Z,
				KeyCombination.CONTROL_DOWN);
		drawPane.setOnKeyPressed(e -> {
//			System.out.println("key pressed");
			if (CTRL_Z.match(e)) {
//				System.out.println("undo");
				int numLines = drawPane.getChildren().size();
				if (numLines > 0)
					drawPane.getChildren().remove(numLines - 1);
			}				
		});

		drawPane.requestFocus();
		
		MenuBar menuBar = new MenuBar();
		
		Menu menuFile = new Menu("File");
		
		MenuItem itemNew = new MenuItem("New");
		itemNew.setOnAction(e -> {
			drawPane.getChildren().clear();
		});
		final FileChooser fileChooser = new FileChooser();
		MenuItem itemOpen = new MenuItem("Open");
		itemOpen.setOnAction(e -> {
			File file = fileChooser.showOpenDialog(null);
			if (file != null) {
				try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));) {
					ArrayList<Line> lines = (ArrayList<Line>) input.readObject();
					drawPane.getChildren().clear();
					for (Line line : lines)
						drawPane.getChildren().add(line);
				}
				catch (FileNotFoundException ex) {
					ex.printStackTrace();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		MenuItem itemSave = new MenuItem("Save");
		itemSave.setOnAction(e -> {
			File file = fileChooser.showSaveDialog(null);
			if (file != null) {
				try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));) {
					ArrayList<Line> lines = new ArrayList<>();
					for (Node node : drawPane.getChildren())
						lines.add((Line) node);
					output.writeObject(lines);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		MenuItem itemExit = new MenuItem("Exit");
		// BASH 0 is the true value
		itemExit.setOnAction(e -> {
			System.exit(0);
		});
		
		menuFile.getItems().addAll(itemNew, itemOpen, itemSave, itemExit);
		menuBar.getMenus().add(menuFile);
		rootPane.setTop(menuBar);
		
	}
	// inner class
	class MyMousePressedHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent e) {
//			System.out.println("Mouse pressed: " + e.getX() +
//					", " + e.getY());
//			System.out.println("Event source: " + e.getSource());
//			System.out.println("Event: " + e);
//			System.out.println("Event handler: " + this);
			pressX = e.getX();
			pressY = e.getY();
		}

	}

}