import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.PixelWriter;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

// Interesting places to look:
// [0.38415990889072477, 0.5688290476799011] zoom: 4.194304E8
// [0.36068359375, -0.3570611572265624] zoom: 819200.0
// [0.3813074660301208, -0.23186030745506286] zoom: 3.3554432E9
// [0.36068290289491406, -0.35707186311483397] zoom: 1.34217728E10

public class Mandelbrot extends Application {

	private final int ZOOM_FACTOR = 2;
	private IntegerProperty maxIterProp = new SimpleIntegerProperty(1000); // 570 suggested on RosettaCode
	private DoubleProperty zoomProp = new SimpleDoubleProperty(100);
	private DoubleProperty centerXProp = new SimpleDoubleProperty(0);
	private DoubleProperty centerYProp = new SimpleDoubleProperty(0);
	private IntegerProperty colorPalette = new SimpleIntegerProperty(MandelbrotColors.SMOOTH_SPECTRUM);
	private Canvas canvas;

	public static void main(String[] arghs) {
		launch(arghs);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Mandelbrot Set Fractal"); // Set the stage title
		primaryStage.setScene(new Scene(getRootPane(), 750, 750)); // Place the scene in the stage
		render();
		primaryStage.show(); // Display the stage
	}

	private BorderPane getRootPane() {
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(getMenu());
		borderPane.setCenter(getCanvasPane());
		borderPane.setBottom(getControls());
		borderPane.setRight(getZoomSlider());
		return borderPane;
	}

	private Node getZoomSlider() {
		// TODO Auto-generated method stub
		return null;
	}

	private Node getControls() {
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(5);
		grid.setPadding(new Insets(10, 10, 10, 10));
		
		// labels
		Label xLabel = new Label("Center X");
		Label yLabel = new Label("Center Y");
		Label zoomLabel = new Label("Zoom");
		GridPane.setHalignment(xLabel, HPos.RIGHT);
		GridPane.setHalignment(yLabel, HPos.RIGHT);
		GridPane.setHalignment(zoomLabel, HPos.RIGHT);
		grid.add(xLabel, 0, 0);
		grid.add(yLabel, 0, 1);
		grid.add(zoomLabel, 0, 2);
		
		// text fields
		TextField xField = new TextField("" + centerXProp.get());
		TextField yField = new TextField("" + centerYProp.get());
		TextField zoomField = new TextField("" + zoomProp.get());
		// add invalidation listeners to properties
		// to update these fields
		centerXProp.addListener(e -> {
			xField.setText("" + centerXProp.get());
		});
		centerXProp.addListener(e -> {
			yField.setText("" + centerYProp.get());
		});
		centerXProp.addListener(e -> {
			zoomField.setText("" + zoomProp.get());
		});
		// add action listeners for user enter
		xField.setOnAction(e -> {
			try {
				double newValue = Double.parseDouble(xField.getText());
				centerXProp.set(newValue);
				render();
			}
			catch (NumberFormatException ex) {
				// do nothing
			}
		});
		yField.setOnAction(e -> {
			try {
				double newValue = Double.parseDouble(yField.getText());
				centerYProp.set(newValue);
				render();
			}
			catch (NumberFormatException ex) {
				// do nothing
			}
		});
		zoomField.setOnAction(e -> {
			try {
				double newValue = Double.parseDouble(zoomField.getText());
				zoomProp.set(newValue);
				render();
			}
			catch (NumberFormatException ex) {
				// do nothing
			}
		});
		
		// add fields to grid
		grid.add(xField, 1, 0);
		grid.add(yField, 1, 1);
		grid.add(zoomField, 1, 2);
		return grid;
	}

	private Node getCanvasPane() {
		// create the canvas and add it to the pane
		Pane pane = new Pane();
		canvas = new Canvas();
		canvas.widthProperty().bind(pane.widthProperty());
		canvas.heightProperty().bind(pane.heightProperty());
		// invalidation listener
		canvas.widthProperty().addListener(observable -> render());
		canvas.heightProperty().addListener(observable -> render());
		pane.getChildren().add(canvas);
		
		// listen for mouse clicks in the pane
		pane.setOnMouseClicked(e -> {
			centerXProp.set((e.getX() - pane.getWidth() / 2) / zoomProp.get() + centerXProp.get());
			centerYProp.set((e.getY() - pane.getHeight() / 2) / zoomProp.get() + centerYProp.get());
			zoomProp.set(zoomProp.get() * (e.getButton() == MouseButton.PRIMARY ? ZOOM_FACTOR : 1.0 / ZOOM_FACTOR));
			System.out.println("[" + centerXProp.get() + ", " + centerYProp.get() + "]" + "zoom: " + zoomProp);
			render();
		});
		
		return pane;
	}

	private Node getMenu() {
		MenuBar menuBar = new MenuBar();

		//TODO
		// --- Menu File
		Menu menuFile = new Menu("File");
		MenuItem exit = new MenuItem("Exit");
		exit.setOnAction(e -> System.exit(0));
		menuFile.getItems().add(exit);
		
		// --- Menu View
		Menu menuView = new Menu("View");
		MenuItem reset = new MenuItem("Reset");
		exit.setOnAction(e -> {
			centerXProp.set(0);
			centerYProp.set(0);
			zoomProp.set(100);
			render();
		});
		menuFile.getItems().add(exit);
		// Color Palette submenu
		Menu menuColors = new Menu("Color Palette");
		ToggleGroup groupColor = new ToggleGroup();
		for (int i = 0; i < MandelbrotColors.paletteNames.length; i++) {
			RadioMenuItem itemColor = new RadioMenuItem(MandelbrotColors.paletteNames[i]);
			final int paletteID = i;
			itemColor.setOnAction(e -> {
				colorPalette.set(paletteID);
				render();
			});
			itemColor.setUserData(i);
			itemColor.setToggleGroup(groupColor);
			menuColors.getItems().add(itemColor);
		}
		menuView.getItems().addAll(reset, menuColors);
		
		menuBar.getMenus().addAll(menuFile, menuView);
		return menuBar;
	}

	private void render() {
		int width = (int) canvas.widthProperty().get();
		int height = (int) canvas.heightProperty().get();
		int maxIter = maxIterProp.get();
		double zoom = zoomProp.get();
		double centerX = centerXProp.get();
		double centerY = centerYProp.get();
		int palette = colorPalette.get();
		
		Color[][] pixelColors = MandelbrotColors.compute(width, height, centerX, centerY, zoom, maxIter, palette);
		
		PixelWriter pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();
		for (int y = 0; y < height; y++) 
			for (int x = 0; x < width; x++)
				pixelWriter.setColor(x, y, pixelColors[x][y]);
	}


}
