import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class MP3Fun extends Application {

	// Source: http://www.orangefreesounds.com/little-fugue-in-g-minor/ listed as "Public Domain"
	public static final String AUDIO_URL = "http://cs.gettysburg.edu/~tneller/audio/example/Little-fugue-in-g-minor/Little-fugue-in-g-minor.mp3";
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// Create Media, MediaPlayer and play
		Media media = new Media(AUDIO_URL);
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.cycleCountProperty().set(MediaPlayer.INDEFINITE); // loop infinitely
		mediaPlayer.setVolume(.25);
		mediaPlayer.setRate(8);
		mediaPlayer.play();
		
		// Create simple window that plays media as long as it's open
		Label label = new Label("Bach's Little Fugue in G Minor, BWV 578");
		Pane pane = new Pane();
		pane.getChildren().add(label);
		Scene scene = new Scene(pane, pane.getPrefWidth(), pane.getPrefHeight());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}