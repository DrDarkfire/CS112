import java.util.NoSuchElementException;

public class MyStack<E> {
	E item;
	MyQueue<E> head = null;
	MyQueue<E> tail = null;
	public MyStack(E item) {
		this.item = item;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public E pop() {
		if(head == null)
			throw new NoSuchElementException("MyStack is empty");
		else
			return head.dequeue();
	}
}
