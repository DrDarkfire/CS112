
public class MyLinkedListNode<E> {
	// Note that it's sometimes preferable to allow
	// mutable objects with accessible fields.
	
	// How you can declare a generic method in a non-generic class
	// motivation for generics
	//
	E item;
	MyLinkedListNode<E> next = null;
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
