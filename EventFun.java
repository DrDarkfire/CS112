import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class EventFun extends Application {

	double pressX, pressY;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Create scene bottom-up
		Pane rootPane = new Pane();
		Pane drawPane = new Pane();
		drawPane.prefWidthProperty().bind(rootPane.widthProperty());
		drawPane.prefHeightProperty().bind(rootPane.heightProperty());
		rootPane.getChildren().add(drawPane);
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
			System.out.println("key pressed");
			if (CTRL_Z.match(e)) {
				System.out.println("undo");
				int numLines = drawPane.getChildren().size();
				if (numLines > 0)
					drawPane.getChildren().remove(numLines - 1);
			}				
		});

		drawPane.requestFocus();
	}
	// inner class
	class MyMousePressedHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent e) {
			System.out.println("Mouse pressed: " + e.getX() +
					", " + e.getY());
			System.out.println("Event source: " + e.getSource());
			System.out.println("Event: " + e);
			System.out.println("Event handler: " + this);
			pressX = e.getX();
			pressY = e.getY();
		}

	}

}
