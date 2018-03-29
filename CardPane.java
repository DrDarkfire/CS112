import java.io.File;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class CardPane extends Pane {
	/**
	 * Standard Poker card width/height is 635/889.
	 */
	public static final double ASPECT_RATIO = 635.0/889.0;
	private String str; 
	//private GridPane grid = new GridPane();
	//char[] rank = {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};
	String rank = "A23456789TJQK";
	//char[] suits = {'C', 'H', 'S', 'D'};
	String suits = "CHSD";
	//String[] cards = {"green-club-100.png", "pink-heart-100.png", "black-spade-100.png", "blue-diamond-100.png"};
	private double height;
	private double width;

	//import pictures
	File club = new File("/Courses/cs112/images/suits/suit-green-club-100.png/");
	Image imageC = new Image(club.toURI().toString());

	File heart = new File("/Courses/cs112/images/suits/suit-pink-heart-100.png/");
	Image imageH = new Image(heart.toURI().toString());

	File spade = new File("/Courses/cs112/images/suits/suit-black-spade-100.png/");
	Image imageS = new Image(spade.toURI().toString());

	File diamond = new File("/Courses/cs112/images/suits/suit-blue-diamond-100.png/");
	Image imageD = new Image(diamond.toURI().toString());
	

	//String[] cards1 = {imageC.toString(), imageH.toString(), imageS.toString(), imageD.toString()};
	Image[] cards1 = {imageC, imageH, imageS, imageD};
	
	public CardPane(StringProperty cardStr) {
		super();
		this.str = cardStr.toString().toUpperCase();
		cardStr.addListener(new ChangeListener <String>(){
			public void changed(ObservableValue<? extends String> o, String oldVal, String newVal){
				str = newVal.toUpperCase();
				changeCard();
			}
		});

		this.heightProperty().addListener(new ChangeListener <Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				height = (double) newValue;
				changeCard();
			}
		});

		this.widthProperty().addListener(new ChangeListener <Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				width = (double) newValue;
				changeCard();
			}
		});

	}
	private void changeCard() {
		GridPane grid = new GridPane();
		//clear children
		grid.getChildren().clear();
		this.getChildren().clear();
		
		//create card
		Rectangle rectangle = new Rectangle(); // u-l x, y, w, h
		double cardaspect = width/height;
		boolean first = false;
		boolean second = false;
		char r = str.charAt(0);
		char s = str.charAt(1);
		for(int i = 0; i < rank.length(); i++){
			if(rank.indexOf(r) > -1)
				first = true;
		}
		for(int i = 0; i < rank.length(); i++){
			if(suits.indexOf(s) > -1)
				second = true;
		}
		if (str.length() == 2 && first && second) {
			
			rectangle.setFill(Color.WHITE);
			if (ASPECT_RATIO < cardaspect) {
				rectangle.setWidth(height*ASPECT_RATIO);
				rectangle.setHeight(height);
				rectangle.setX((width - rectangle.getWidth())/2);
				rectangle.setY(0);
				rectangle.setArcWidth(rectangle.getWidth() / 9);
				rectangle.setArcHeight(rectangle.getHeight() / 9);			
			}
			else {
				rectangle.setWidth(width);
				rectangle.setHeight(width/ASPECT_RATIO);
				rectangle.setX(0);
				rectangle.setY((height - rectangle.getHeight())/2);
				rectangle.setArcWidth(rectangle.getWidth() / 9);
				rectangle.setArcHeight(rectangle.getHeight() / 9);
			}

			//adding card
			this.getChildren().add(rectangle);

			grid.setHgap(rectangle.getWidth()/50);
			grid.setVgap(rectangle.getWidth()/50);
			grid.setLayoutX(rectangle.getX());
			grid.setLayoutY(rectangle.getY());
			grid.setMaxHeight(rectangle.getHeight());
			grid.setMaxWidth(rectangle.getWidth());
			grid.setAlignment(Pos.CENTER);
			grid.setPadding(new Insets(rectangle.getWidth()/15, rectangle.getHeight()/7.5, rectangle.getWidth()/7.5, rectangle.getHeight()/7.5));


			int numCols = 3;
			for (int i = 0; i < rank.indexOf(r) + 1; i++) {
				Image suit = cards1[suits.indexOf(s)];
				ImageView view = new ImageView(suit);
				view.setFitWidth(rectangle.getWidth()/5 - 5);
				view.setFitHeight(rectangle.getHeight()/5 - 5);
				//System.out.println(view.getFitHeight());
				view.setPreserveRatio(true);
				grid.add(view, i % numCols, i/numCols);
				GridPane.setHalignment(view, HPos.RIGHT);
				GridPane.setValignment(view, VPos.CENTER);
			}
			this.getChildren().add(grid);
		}
	}
}