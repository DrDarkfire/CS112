
public class MyLinkedListNode<E> {
	// Note that it's sometimes preferable to allow
	// mutable objects with accessible fields.
	E item;
	MyLinkedListNode<E> next;
	/**
	 * @param item
	 */
	public MyLinkedListNode(E item) {
		this.item = item;
	}
	/**
	 * @param item
	 * @param next
	 */
	public MyLinkedListNode(E item, MyLinkedListNode<E> next) {
		this.item = item;
		this.next = next;
	}
	
	
	
}
