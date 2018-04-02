import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AnimationFun2 extends Application {

	double width, height;
	Pane drawPane = new Pane(); // Create a new draw pane (in case we want to add other components).

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {

		// Create scene bottom-up
		Pane rootPane = new Pane(); // Create a new root pane.
		drawPane.setPrefSize(500, 500);
		drawPane.setStyle("-fx-background-color: black");
		rootPane.getChildren().add(drawPane); // Add draw pane to root pane's children
		Scene scene = new Scene(rootPane, 500, 500); // Create a scene with the root pane
		primaryStage.setTitle("Animation Fun"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage

		width = drawPane.getWidth();
		height = drawPane.getHeight();

		// InvalidationListener
		primaryStage.widthProperty().addListener(e -> {width = primaryStage.getWidth(); drawPane.setPrefWidth(width); System.out.println("width " + width);});
		primaryStage.heightProperty().addListener(e -> {height = primaryStage.getHeight(); drawPane.setPrefHeight(height); System.out.println("height " + height);});

		// *** Platform.runLater passes a Runnable to be executed at some later time on the JavaFX Application thread.
		drawPane.setOnMousePressed(e -> {
			Platform.runLater(() -> newCircle(e.getX(), e.getY()));
		});

		// *** Here's a thread to auto-create new circles at random positions at a rate of 2 per second:
		new Thread(() -> {
			while (true) {
				Platform.runLater(()
						-> newCircle(Math.random() * drawPane.getWidth(), Math.random() * drawPane.getHeight()));
				try {
					Thread.sleep(1000 / 2);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}).start();


		drawPane.requestFocus();
	}

	private void newCircle(double x, double y) {
		System.out.println("New circle at " + x + " " + y);

		CubicCurve cubic = new CubicCurve();
		cubic.setStartX(x);
		cubic.setStartY(y);
		cubic.setControlX1(Math.random() * width);
		cubic.setControlY1(Math.random() * height);
		cubic.setControlX2(Math.random() * width);
		cubic.setControlY2(Math.random() * height);
		cubic.setEndX(Math.random() * width);
		cubic.setEndY(Math.random() * height);
		cubic.setStroke(Color.TRANSPARENT); // Use WHITE initially to show random cubic paths, then TRANSPARENT
		cubic.setFill(Color.TRANSPARENT);
		double radius = Math.min(width, height) / 10;
		Circle circle = new Circle(0, 0, radius);
		circle.setFill(new Color(Math.random(), Math.random(), Math.random(), 1));
		circle.setEffect(new GaussianBlur(radius));

		// PathTransition
		long ms = 20000;
		PathTransition pt = new PathTransition();
		pt.setDuration(Duration.millis(ms));
		pt.setPath(cubic);
		pt.setNode(circle);
		pt.setCycleCount(1);
		pt.play();
		pt.setOnFinished(e2 -> {
			drawPane.getChildren().remove(circle);
			drawPane.getChildren().remove(cubic);
			System.out.println("End circle started at " + x + " " + y);
		});

		// FadeTransition
		FadeTransition ft = new FadeTransition(Duration.millis(ms / 2), circle);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.setCycleCount(Timeline.INDEFINITE);
		ft.setAutoReverse(true);
		ft.play(); // Start animation

		drawPane.getChildren().add(cubic);
		drawPane.getChildren().add(circle);
	}

	public static void main(String[] args) {
		launch(args);
	}

}