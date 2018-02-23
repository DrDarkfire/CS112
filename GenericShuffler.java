import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.Stack;

public class GenericShuffler<E> {
	
	private Stack<E> items = new Stack<E>();
	private Random random = new Random();
    /**
     * Test our GenericShuffler.
     * @param args (unused)
     */
     public static void main(String[] args) {
           GenericShuffler<Integer> intShuffler = new GenericShuffler<>();
           intShuffler.setSeed(42);
           for (int i = 0; i < 10; i++)
                  intShuffler.add(i);
           while (!intShuffler.empty())
                  System.out.print(intShuffler.get() + " ");
           System.out.println();
           ArrayList<Integer> intList = new ArrayList<>();
           for (int i = 0; i < 10; i++)
                  intList.add(i);
           intShuffler.setSeed(42);
           intShuffler.addAll(intList);
           Stack<Integer> intStack = intShuffler.getAll();
           while (!intStack.empty())
                  System.out.print(intStack.pop() + " ");
           System.out.println();
           GenericShuffler<String> stringShuffler = new GenericShuffler<>();
           String[] stringArray = {"a", "b", "c"};
           for (String s : stringArray)
                  stringShuffler.add(s);
           while (!stringShuffler.empty())
                  System.out.print(stringShuffler.get() + " ");
           System.out.println();
           // demonstrate even distribution of shuffles over 1000000 trials
           // columns denote number of times at a given position
           int[][] counts = new int[stringArray.length][stringArray.length];
           for (int i = 0; i < 1000000; i++) {
                  for (String s : stringArray)
                         stringShuffler.add(s);
                  int j = 0;
                  while (!stringShuffler.empty())
                         counts[stringShuffler.get().charAt(0) - 'a'][j++]++;
           }
           for (int i = 0; i < stringArray.length; i++)
                  System.out.printf("%s: %s\n", stringArray[i], Arrays.toString(counts[i]));
     }
     /**
      * remove and return a random item from our GenericShuffler
      * @return
      */
 	private E get() {
		// TODO Auto-generated method stub
		return items.pop();
	}
 	/**
 	 * Return a shuffled Stack<E> of all items in our GenericShuffler
 	 * @return
 	 */
	private Stack<E> getAll() {
		Stack<E> retVal = items;
		items = new Stack<E>();
		return retVal;
	}
	/**
	 * Given a collection of items to be added. addAll adds all of them
	 * @param collection 
	 */
	private void addAll(Collection<E> collection) {
		for (E item : collection)
			items.add(item);
	}
	private boolean empty() {
		return items.empty();
	}
	/**
	 * Add an item to our GenericShuffler. Swap it to a random position
	 * @param item item to be added to our Generic Shuffler
	 */
	private void add(E item) {
		// TODO Auto-generated method stub
		int j = random.nextInt(items.size() + 1);
		if (j == items.size())
			items.push(item);
		else {
			items.push(items.get(j));
			items.set(j, item);
		}
	}
	/**
	 * set the internal seed for our GenericShuffler
	 * @param seed the internal seed
	 */
	private void setSeed(long seed) {
		random.setSeed(seed);
		
	}

}
