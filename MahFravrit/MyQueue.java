
public class MyQueue<E> {
	E item;
	MyQueue<E> next = null;
	
	public MyQueue(E item){
		this.item = item;
	}
	
	public MyQueue(E item, MyQueue<E> next) {
		this.item = item;
		this.next = next;
	}
	
	public boolean isEmpty() {
		return next == null;
	}
	
	public E dequeue() {
		next.item = null;
		return item;
	}
	
	public E peek() {
		return null;
	}
	
	public void enqueue(E item) {
		MyQueue<E> newItem = new MyQueue<E>(item, next);
	}
	
	public String toString() {
		return null;
	}
}
