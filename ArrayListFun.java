
public class ArrayListFun {

	public static void main(String[] args) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		al.add(1); // add element to the end of the list
		al.add(2);
		al.add(3);
		System.out.println(al); // toString
		System.out.println(al.size());
		if (al.isEmpty()) // isEmpty
			System.out.println("This list is empty");
		else
			System.out.println("This list is not empty");
		for (char ch : Double.toString(Math.PI).toCharArray())
			if (Character.isDigit(ch))
				al.add(ch - '0');
		System.out.println(al);
		System.out.println(al.contains(0));// contains
		System.out.println(al.contains(9));// contains
		System.out.println(al.indexOf(0)); // indexOf (-1 means not found)
		System.out.println(al.indexOf(1)); // returns first index
		System.out.println(al.lastIndexOf(1)); // returns last index
		al.add(al.lastIndexOf(1), 42);
		System.out.println(al);
		//al.remove(42); // remove at given index
		// error because no index at 42
		//al.remove(6); // we know where it is
		al.remove(new Integer(42)); // we don't know so java needs to search for it
		// Object parameter -> remove the object if it's found
		System.out.println(al);
		al.set(3, 42);
		System.out.println(al);
		al.set(3, 69); // for the memes
		System.out.println(al);
		System.out.println(al.get(3));
		//     array: a[3] = 1; System.out.println(a[3]);
		// ArrayList: al.set(3, 69); System.out.println(al.get(3));
	}

}

