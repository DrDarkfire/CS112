import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class JavaFXFun extends Application {
	
	DoubleProperty paneAngle = new SimpleDoubleProperty(0);
	StringProperty paneAngleStr = new SimpleStringProperty("Angle 0");
	
	public void start(Stage primaryStage) {
		// created scene bottom-up
		Button button = new Button("New Stage");
		Pane pane = new Pane();
		pane.getChildren().add(button);
		Scene scene = new Scene(pane, 200, 200);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Primary Stage Title");
		primaryStage.show();

		// skipping ahead to ch. 15 action handling
		// and lambda expressions
		button.setOnAction(e -> {
			Button button2 = new Button("Stage 2");
			Pane pane2 = new Pane();
			pane2.getChildren().add(button2);
			Scene scene2 = new Scene(pane2, 200, 200);
			Stage stage2 = new Stage();
			stage2.setScene(scene2);
			stage2.setTitle("Stage 2 Title");
			stage2.show();

		});
		
		Circle circle = new Circle();
		circle.setCenterX(100);
		circle.setCenterY(100);
		circle.setRadius(69);
		circle.setStroke(Color.BLUE);
		circle.setFill(Color.ORANGE);
		pane.getChildren().add(0, circle);
		
		//binding property
		circle.centerXProperty().bind(pane.widthProperty().divide(2));
		circle.centerYProperty().bind(pane.heightProperty().divide(2));
		circle.radiusProperty().bind(pane.widthProperty().divide(4));
		
		// create angle properties above, a subpane,
		// a square, and a label
		
		Pane subPane = new StackPane();
		subPane.setPrefSize(100, 100);
		subPane.setTranslateX(100);
		subPane.setTranslateY(100);
		subPane.rotateProperty().bind(paneAngle);
		pane.getChildren().add(subPane);
		// Cascading Style Specifications
		Rectangle square = new Rectangle(100, 100);
		square.setStyle("-fx-stroke: black; -fx-fill: red;");
		subPane.getChildren().add(square);
		
		Label label = new Label();
		label.textProperty().bind(paneAngleStr);
		subPane.getChildren().add(label);
		
		Button angleBtn = new Button("Increment Angle");
		pane.getChildren().add(angleBtn);
		angleBtn.setTranslateY(35);
		angleBtn.setOnAction(e -> {
			paneAngle.set(paneAngle.get() + 5);
			paneAngleStr.set("Angle " + paneAngle.get());
//			square.setFill(Color.color(Math.random(),Math.random(),Math.random()));
			square.setFill(Color.hsb(paneAngle.get(), 1, 1));
		});
		
		label.setFont(Font.font("Serif", FontWeight.BOLD, FontPosture.ITALIC, 16));
	}
	
	public static void main(String[] arghs) {
		launch(arghs);
	}
}
