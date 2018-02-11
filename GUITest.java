import java.awt.*;
import java.awt.event.*;

public class GUITest extends Frame implements ActionListener{
	private Label labelCount;
	private TextField textCount;
	private Button buttonCount;
	private int count = 0;
	
	public GUITest () {
		setLayout(new FlowLayout());
		labelCount = new Label("Counter");
		add(labelCount);
		
		textCount = new TextField("0");
		textCount.setEditable(false);
		add(textCount);
		
		buttonCount = new Button("Count");
		add(buttonCount);
		buttonCount.addActionListener(this);
		
		setTitle("Counters for Idiots");
		setSize(250,100);
		setVisible(true);
	}
	public static void main(String[] args){
		// TODO Auto-generated method stub
		GUITest app = new GUITest();
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		++count;
		textCount.setText(count + "");
	}

}
