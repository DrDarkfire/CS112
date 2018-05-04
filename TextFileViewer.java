import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TextFileViewer extends Application{

	public void start(Stage primaryStage) throws FileNotFoundException {
		
		
		String file = "FoxInSocks.txt";
		Scanner in = new Scanner(new File(file));
		StringBuilder sb = new StringBuilder();
		while (in.hasNextLine())
			sb.append(in.nextLine() + "\n");
		in.close();
		//Text text = new Text();
		//text.setText(sb.toString());
		TextArea textArea = new TextArea();
		textArea.setText(sb.toString());
		textArea.setMinHeight(400);
		Pane pane = new Pane();
		pane.getChildren().add(textArea);
		Scene scene = new Scene(pane, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Fox In Socks View");
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
