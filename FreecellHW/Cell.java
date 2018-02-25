
public class Cell extends CardStack{

	Cell(){
		return;
	}

	public boolean playTo(Card card) {
		if(stack.empty()) {
			stack.add(card);
			return true;
		}
		return false;
	}
}
