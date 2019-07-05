package application;
	
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;


public class Main extends Application {
	public Stage mainStage;
	public static Properties prop;
	public static String oPath;
	public static String dPath;
	public static String globPath;
	public static String defaultPath;
	public OutputStream output;
	@Override
	public void start(Stage primaryStage) {
		startMainStage();
		try {
			mainStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void startMainStage() {
		mainStage = new Stage();
		mainStage.setTitle("File Transfer App");
		BorderPane root = new BorderPane();
		root.setCenter(getVBox());
		Scene scene = new Scene(root,400,150);
		mainStage.setMinWidth(400);
		mainStage.setMinHeight(150);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		mainStage.setScene(scene);
	}
	
	private VBox getVBox() {
		VBox box = new VBox();
		HBox oHbox = new HBox();
		TextField t1 = new TextField(oPath);
		t1.prefWidthProperty().bind(mainStage.widthProperty());
		HBox dHbox = new HBox();
		TextField t2 = new TextField(dPath);
		t2.prefWidthProperty().bind(mainStage.widthProperty());
		// Directories
		DirectoryChooser dfc = new DirectoryChooser();
		dfc.setTitle("Destination");
		DirectoryChooser ofc = new DirectoryChooser();
		ofc.setTitle("Origin");
		
		Button oButt = new Button("...");
		oButt.setOnAction(e -> {
			File file = ofc.showDialog(mainStage);
			if (file != null) {
				try {
					editPath(file, 0);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				t1.setText(file.getAbsolutePath());
			}
		});
		Button dButt = new Button("...");
		dButt.setOnAction(e -> {
			File file = dfc.showDialog(mainStage);
			if (file != null) {
				try {
					editPath(file, 1);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				t2.setText(file.getAbsolutePath());
			}
		});
		Button transfer = new Button("Transfer");
		transfer.setOnAction(e -> {
			try {
				tranF();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		Button swap = new Button("Swap");
		swap.setOnAction(e -> {
			File temp = new File(oPath);
			File dP = new File(dPath);
			try {
				editPath(dP, 0);
				t1.setText(dP.getAbsolutePath());
				editPath(temp, 1);
				t2.setText(temp.getAbsolutePath());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			
		});
		Label origin = new Label("Origin");
		Label dest = new Label("Destination");
		HBox buttonBox = new HBox();
		buttonBox.getChildren().addAll(transfer, swap);
		oHbox.getChildren().addAll(t1, oButt);
		dHbox.getChildren().addAll(t2, dButt);
		box.getChildren().addAll(origin, oHbox, dest, dHbox, buttonBox);
		return box;
	}
	
	// transfers files from 
	private void tranF() throws IOException {
		if (!(new File(dPath + "/config").exists())) {
			new File(dPath + "/config").mkdir();
		}
		if (!(new File(dPath + "/scripts").exists())) {
			new File(dPath + "/scripts").mkdir();
		}
		if (!(new File(dPath + "/resources").exists())) {
			new File(dPath + "/resources").mkdir();
		}
		if (!(new File(dPath + "/patchouli_books").exists())) {
			new File(dPath + "/patchouli_books").mkdir();
		}
		File config = new File(dPath + "/config");
		File scripts = new File(dPath + "/scripts");
		File resources = new File(dPath + "/resources");
		File patchouli_books = new File(dPath + "/patchouli_books");
		File oconfig = new File(oPath + "/config");
		File oscripts = new File(oPath + "/scripts");
		File oresources = new File(oPath + "/resources");
		File opatchouli_books = new File(oPath + "/patchouli_books");
		
		FileUtils.copyDirectory(oconfig, config);
		FileUtils.copyDirectory(oscripts, scripts);
		FileUtils.copyDirectory(oresources, resources);
		FileUtils.copyDirectory(opatchouli_books, patchouli_books);
	}

	// Configs(properties)
	public static void propFile() {
		prop = new Properties();
		globPath = System.getProperty("user.dir");
		InputStream input = null;
		OutputStream output = null;
		try {
			input = new FileInputStream(globPath + "/" + "config.properties");
			prop.load(input);
			oPath = prop.getProperty("originpath");
			dPath = prop.getProperty("destpath");
			input.close();
		}
		catch (IOException e){
			try {
				output = new FileOutputStream(globPath + "/" + "config.properties");
				prop.setProperty("originpath", "default");
				prop.setProperty("destpath", "default");
				oPath = prop.getProperty("originpath");
				dPath = prop.getProperty("destpath");
				prop.store(output, null);
				output.close();

			} catch (IOException f) {
				f.printStackTrace();
			}
		}
	}
	
	private void editPath(File file, int num) throws IOException {
		output = new FileOutputStream(globPath + "config.properties");
		if (num == 0) {
			prop.setProperty("originpath", file.getAbsolutePath());
			oPath = prop.getProperty("originpath");
			prop.store(output, "changed orign");
		} else {
			prop.setProperty("destpath", file.getAbsolutePath());
			dPath = prop.getProperty("destpath");
			prop.store(output, "changed destination");
		}
		output.close();
	}
	
	private MenuBar getMenu() {
		MenuBar bar = new MenuBar();
		Menu menuFile = new Menu("File");
		
		bar.getMenus().add(menuFile);
		return bar;
	}
	
	// configs rewrite
	public static void npropFile() {
		prop = new Properties();
		defaultPath = System.getProperty("user.dir");
		InputStream input = null;
		OutputStream output = null;
		try {
			input = new FileInputStream(defaultPath + "/" + "config.properties");
			prop.load(input);
			globPath = prop.getProperty("profPath");
			input.close();
			//input = new FileInputStream()
		}
		catch (IOException e) {
			try {
				output = new FileOutputStream(defaultPath + "/" + "config.properties");
				globPath = defaultPath;
				prop.setProperty("profPath", defaultPath);
				prop.store(output, null);
				output.close();

			} catch (IOException f) {
				f.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		propFile();
		launch(args);
	}
}
