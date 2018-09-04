package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Calculator extends Application 
{
	// the calculator dimensions
	public static int CALC_WIDTH = 400;
	public static int CALC_HEIGHT = 300;

	// the calculator screen
	private TextField screen; 

	// the calculator buttons
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Button button5;
	private Button button6;
	private Button button7;
	private Button button8;
	private Button button9;
	private Button button0;
	private Button buttonAdd;
	private Button buttonSub;
	private Button buttonMul;
	private Button buttonDiv;
	private Button buttonOParen;
	private Button buttonCParen;
	private Button buttonDec;
	private Button buttonEq;
	private Button buttonPow;
	private Button buttonClear;

	@Override
	public void start(Stage primaryStage) 
	{
		// create the calculator screen
		screen = new TextField(); 

		// create the buttons
		button1 = new Button("1");
		button2 = new Button("2");
		button3 = new Button("3");
		button4 = new Button("4");
		button5 = new Button("5");
		button6 = new Button("6");
		button7 = new Button("7");
		button8 = new Button("8");
		button9 = new Button("9");
		button0 = new Button("0");
		buttonAdd= new Button("+");
		buttonSub = new Button("-");
		buttonMul = new Button("*");
		buttonDiv = new Button("/");
		buttonOParen = new Button("(");
		buttonCParen = new Button(")");
		buttonDec = new Button(".");
		buttonEq = new Button("=");
		buttonPow = new Button("^");
		buttonClear = new Button("C");

		// attach a handler to process button clicks 
		ButtonHandler handler = new ButtonHandler();       
		button1.setOnAction(handler);
		button2.setOnAction(handler);
		button3.setOnAction(handler);
		button4.setOnAction(handler);
		button5.setOnAction(handler);
		button6.setOnAction(handler);
		button7.setOnAction(handler);
		button8.setOnAction(handler);
		button9.setOnAction(handler);
		button0.setOnAction(handler);
		buttonAdd.setOnAction(handler);
		buttonSub.setOnAction(handler);
		buttonMul.setOnAction(handler);
		buttonDiv.setOnAction(handler);
		buttonOParen.setOnAction(handler);
		buttonCParen.setOnAction(handler);
		buttonDec.setOnAction(handler);
		buttonEq.setOnAction(handler);
		buttonPow.setOnAction(handler);
		buttonClear.setOnAction(handler);

		// setup a grid panel for the keypad
		GridPane keypad = new GridPane();  
		keypad.setMinSize(CALC_WIDTH, CALC_HEIGHT); 
		keypad.setPadding(new Insets(10, 10, 10, 10));  
		keypad.setVgap(5); 
		keypad.setHgap(5);       
		keypad.setAlignment(Pos.CENTER); 

		// attach the buttons to the keypad grid
		keypad.add(button1, 0, 0); 
		keypad.add(button2, 1, 0); 
		keypad.add(button3, 2, 0);   
		keypad.add(buttonAdd, 3, 0);
		keypad.add(buttonSub, 4, 0);
		keypad.add(button4, 0, 1); 
		keypad.add(button5, 1, 1);
		keypad.add(button6, 2, 1);
		keypad.add(buttonMul, 3, 1);
		keypad.add(buttonDiv, 4, 1);
		keypad.add(button7, 0, 2);
		keypad.add(button8, 1, 2);
		keypad.add(button9, 2, 2);
		keypad.add(buttonOParen, 3, 2);
		keypad.add(buttonCParen, 4, 2);
		keypad.add(button0, 0, 3);
		keypad.add(buttonDec, 1, 3);
		keypad.add(buttonEq, 2, 3);
		keypad.add(buttonPow, 3, 3);
		keypad.add(buttonClear, 4, 3);

		// put screen and keypad together
		BorderPane gui = new BorderPane();
		gui.setTop(screen);
		gui.setCenter(keypad);

		// set up the scene
		Scene scene = new Scene(gui); 
		primaryStage.setTitle("Calculator");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	// Handler for processing the button clicks 
	private class ButtonHandler implements EventHandler<ActionEvent>
	{ 
		@Override 
		public void handle(ActionEvent e) 
		{
			if (e.getSource() == button1) {
				System.out.println("Button 1 Pressed");
				screen.appendText("1");
			}
			else if (e.getSource() == button2) {
				System.out.println("Button 2 Pressed");
				screen.appendText("2");
			}
			else if (e.getSource() == button3) {
				System.out.println("Button 3 Pressed");
				screen.appendText("3");
			}
			else if (e.getSource() == button4) {
				System.out.println("Button 4 Pressed");
				screen.appendText("4");
			}
			else if (e.getSource() == button5) {
				System.out.println("Button 5 Pressed");
				screen.appendText("5");
			}
			else if (e.getSource() == button6) {
				System.out.println("Button 6 Pressed");
				screen.appendText("6");
			}
			else if (e.getSource() == button7) {
				System.out.println("Button 7 Pressed");
				screen.appendText("7");
			}
			else if (e.getSource() == button8) {
				System.out.println("Button 8 Pressed");
				screen.appendText("8");
			}
			else if (e.getSource() == button9) {
				System.out.println("Button 9 Pressed");
				screen.appendText("9");
			}
			else if (e.getSource() == button0) {
				System.out.println("Button 0 Pressed");
				screen.appendText("0");
			}
			else if (e.getSource() == buttonAdd) {
				System.out.println("Button Add Pressed");
				screen.appendText("+");
			}
			else if (e.getSource() == buttonSub) {
				System.out.println("Button Sub Pressed");
				screen.appendText("-");
			}
			else if (e.getSource() == buttonMul) {
				System.out.println("Button Mul Pressed");
				screen.appendText("*");
			}
			else if (e.getSource() == buttonDiv) {
				System.out.println("Button Div Pressed");
				screen.appendText("/");
			}
			else if (e.getSource() == buttonOParen) {
				System.out.println("Button OParen Pressed");
				screen.appendText("(");
			}
			else if (e.getSource() == buttonCParen) {
				System.out.println("Button CParen Pressed");
				screen.appendText(")");
			}
			else if (e.getSource() == buttonDec) {
				System.out.println("Button Dec Pressed");
				screen.appendText(".");
			}
			else if (e.getSource() == buttonEq) {
				System.out.println("Button Eq Pressed");
				screen.setText("");
			}
			else if (e.getSource() == buttonPow) {
				System.out.println("Button Pow Pressed");
				screen.appendText("^");
			}
			else if (e.getSource() == buttonClear) {
				System.out.println("Button Clear Pressed");
				screen.setText("");
			}
		} 
	}  

	public static void main(String[] args) 
	{
		launch(args);
	}
}