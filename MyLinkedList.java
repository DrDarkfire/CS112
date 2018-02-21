import java.util.NoSuchElementException;

public class MyLinkedList<E> {
	MyLinkedListNode<E> head = null;
	MyLinkedListNode<E> tail = null;
	
	
	public static void main(String[] args) {
         MyLinkedList<Integer> list = new MyLinkedList<Integer>();
         java.util.Random random = new java.util.Random(3);
         int trials = 20;
         for (int i = 0; i < trials; i++) { 
              int op = random.nextInt(3);
              if (op == 0) { // remove head
                   try {
                        int item = list.removeHead();
                        System.out.println("removeHead() --> " + item);
                   }
                   catch (NoSuchElementException e) {
                        System.out.println(e.getMessage());
                   }
              }
              else if (op == 1) { // add to head
                   int item = random.nextInt(trials);
                   list.addToHead(item);
                   System.out.printf("addToHead(%d)\n", item);
              }
              else { // add to tail
                   int item = random.nextInt(trials);
                   list.addToTail(item);
                   System.out.printf("addToTail(%d)\n", item);
              }
              if (list.isEmpty())
                   System.out.println(list);
              else
                   System.out.printf("%s head:%s tail:%s\n", list, list.getHead(), list.getTail());
         }
	}

	private E getTail() {
		if (tail == null)
			throw new NoSuchElementException("MyLinkedList is empty");
		return tail.item;
	}

	private E getHead() {
		if (head == null)
			throw new NoSuchElementException("MyLinkedList is empty");
		return head.item;
	}

	private boolean isEmpty() {
		return head == null;
	}

	private void addToTail(E item) {
		MyLinkedListNode<E> newNode = new MyLinkedListNode<E>(item);
		if (head == null)
			head = newNode;
		else
			tail.next = newNode;
		tail = newNode;
	}

	private void addToHead(E item) {
		MyLinkedListNode<E> newNode = new MyLinkedListNode<E>(item, head);
		if (head == null)
			tail = newNode;
		head = newNode;
	}

	private E removeHead() {
		if (head == null)
			throw new NoSuchElementException("MyLinkedList is empty");
		E retVal =  head.item;
		head = head.next;
		if (head == null)
			tail = null;
		return retVal;
	}
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		MyLinkedListNode<E> current = head;
		while (current != null) {
			sb.append(current.item);
			current = current.next;
			if (current != null)
				sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}
}

