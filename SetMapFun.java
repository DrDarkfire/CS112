import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class SetMapFun {

	public static void main(String[] args) throws MalformedURLException, IOException {
		
		// Monday: Sets and Maps: sets, HashSet, LinkedHashSet, TreeSet (21.1-21.2)
		// "A set is an efficient data structure for storing and processing nonduplicate elements.
		//  A map is like a dictionary that provides a quick lookup to retrieve a value using a key."
	
		// Abstract Set has three concrete subclasses: HashSet, LinkedHashSet, or TreeSet.
		// Create a shuffled list of integers
		
		int size = 10;
		int min = -size / 2;
		ArrayList<Integer> aList = new ArrayList<>();
		for (int i = 0; i < size; i++)
			aList.add(min + i);
		Collections.shuffle(aList);
		System.out.println("Shuffled ArrayList: " + aList);
		
		// Add them to a HashSet, a LinkedHashSet, and a TreeSet
		// Printing them out, we see the order of their respective iterators:
		
		System.out.println("HashSet: " + new HashSet<Integer>(aList));
		// HashSets use the object hashCode method to find a location for storage in a table.
		// Two objects can have the same hashCode (i.e. collide), but if the _load factor_ is
		// not too high, very few positions need be searched to find an object in a hash table.
		// When the number of objects in the set exceeds the capacity times the load factor,
		// the underlying capacity is doubled.  One can specify the capacity up front.
		// The ordering of the set iterator has to do with the layout of items in the hash table
		// and thus depends on the hashCode and order of addition to the set.
		
		System.out.println("LinkedHashSet: " + new LinkedHashSet<Integer>(aList));
		// LinkedHashSets additionally maintain a LinkedList of set elements so as to be
		// able to iterate through elements in the order they were added.
		// Thus, order of addition is preserved at a linear cost of additional linked-list memory.
		
		System.out.println("TreeSet: " + new TreeSet<Integer>(aList));
		// TreeSets are for Comparable objects (or any objects with a constructor-supplied 
		// Comparator). Items are stored in a self-balancing binary search tree (e.g. a red-black
		// tree) with log(n) add, remove, and search operations. 
		// So the TreeSet maintains a sorted order of elements at a cost of going from an
		// effectively constant cost per operation to a logarithmic cost per operation.
		
		// Basic set operations: contains, size, 
		// addAll (union), removeAll (subtraction), retainAll (intersection)
		// These last three operations affect a set; they do not create new sets.
		Set<Integer> set1 = new HashSet<>(Arrays.asList(new Integer[] {1, 2}));
		Set<Integer> set2 = new HashSet<>(Arrays.asList(new Integer[] {2, 3}));

		System.out.printf("%s.size() -> %d\n", set1, set1.size());
		for (int i = 1; i <= 3; i++)
			System.out.printf("%s.contains(%d) -> %s\n", set1, i, set1.contains(i));
		
		System.out.printf("%s.addAll(%s) -> ", set1, set2);
		set1.addAll(set2);
		System.out.printf("%s (union)\n", set1);

		set1 = new HashSet<>(Arrays.asList(new Integer[] {1, 2}));
		System.out.printf("%s.removeAll(%s) -> ", set1, set2);
		set1.removeAll(set2);
		System.out.printf("%s (subtraction)\n", set1);

		set1 = new HashSet<>(Arrays.asList(new Integer[] {1, 2}));
		System.out.printf("%s.retainAll(%s) -> ", set1, set2);
		set1.retainAll(set2);
		System.out.printf("%s (intersection)\n", set1);
		
		// TreeSet operations
		// from interface SortedSet:
		TreeSet<Double> data = new TreeSet<>();
		for (int i = 0; i < size; i++)
			data.add(Math.round(1000 * Math.random()) / 1000.0);
		System.out.println("data: " + data);
		System.out.println("data.first(): " + data.first());
		System.out.println("data.last(): " + data.last());
		System.out.println("data.headSet(.5): " + data.headSet(.5)); // set less than
		System.out.println("data.tailSet(.5): " + data.tailSet(.5)); // set greater than
		// from interface NavigableSet:
		System.out.println("After data.pollFirst() returns " + data.pollFirst() + ": " + data);
		System.out.println("After data.pollLast() returns " + data.pollLast() + ": " + data);
		System.out.println("data.lower(.5): " + data.lower(.5)); // next <
		System.out.println("data.lower(data.lower(.5)): " + data.lower(data.lower(.5))); 
		System.out.println("data.higher(.5): " + data.higher(.5)); // next >
		System.out.println("data.higher(data.higher(.5)): " + data.higher(data.higher(.5)));
		System.out.println("data.floor(.5): " + data.floor(.5)); // next <=
		System.out.println("data.floor(data.floor(.5)): " + data.floor(data.floor(.5)));
		System.out.println("data.ceiling(.5): " + data.ceiling(.5)); // next >=
		System.out.println("data.ceiling(data.ceiling(.5)): " + data.ceiling(data.ceiling(.5)));
		
		
		// Wednesday: Sets and Maps: sets versus lists, applications (21.3-21.4)
		
		// "Sets are more efficient than lists for storing nonduplicate elements. Lists are useful for
		// accessing elements through the index."
		// Note the chapter performance tests:
		// - All sets are efficient with randomly testing membership (.contains(E)) and removal (.remove(E)).
		// - ArrayList and LinkedList are both inefficient with randomly testing membership and removal.
		
		
		// Friday: Sets and Maps: maps, HashMap, LinkedHashMap, TreeMap, Collections static methods	(21.5-21.7)
		
		// HashMap, LinkedHashMap, and TreeMap work as corresponding sets, but with
		// Map.Entry objects, i.e. key, value pairs.
		
		String[] digitNames = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
		Map<String, Integer> map = new HashMap<>(); // String digit name to Integer map
		for (int i = 0; i < digitNames.length; i++)
			map.put(digitNames[i], i);
		System.out.println(map);
		System.out.println("isEmpty(): " + map.isEmpty());
		System.out.println("size(): " + map.size());
		System.out.println("containsKey(\"seven\"): " + map.containsKey("seven"));
		System.out.println("containsValue(7): " + map.containsValue(7));
		System.out.println("get(\"seven\"): " + map.get("seven"));
		System.out.println("entrySet(): " + map.entrySet());
		System.out.println("keySet(): " + map.keySet());
		System.out.println("values(): " + map.values());
		Map<String, Integer> map2 = new HashMap<>();
		map2.putAll(map);
		System.out.println("putAll(map) into new HashMap map2 --> " + map2);
		map2.remove("seven");
		System.out.println("after remove(\"seven\") --> " + map2);
		
		// Note that TreeMap analogues for the TreeSet methods exist as well.

		// There are immutable constants for empty sets, lists, and maps:
		System.out.println(Collections.EMPTY_SET);
		System.out.println(Collections.EMPTY_LIST);
		System.out.println(Collections.EMPTY_MAP);

		// One can create immutable singleton (single-value) sets, lists, and maps:
		Set<Integer> singletonSet = Collections.singleton(42);
		System.out.println(singletonSet);
		List<Integer> singletonList = Collections.singletonList(42);
		System.out.println(singletonList);
		Map<String, Integer> singletonMap = Collections.singletonMap("forty-two", 42);
		System.out.println(singletonMap);
		
		// One can create immutable collections, sets, lists, and maps from mutable ones:
		Set<Integer> answerSet = new HashSet<>();
		answerSet.add(42);
		Set<Integer> immutableAnswerSet = Collections.unmodifiableSet(answerSet);
		System.out.println(immutableAnswerSet);
		List<Integer> answerList = new ArrayList<>();
		answerList.add(42);
		List<Integer> immutableAnswerList = Collections.unmodifiableList(answerList);
		System.out.println(immutableAnswerList);
		Map<String, Integer> answerMap = new HashMap<>();
		answerMap.put("forty-two", 42);
		Map<String, Integer> immutableAnswerMap = Collections.unmodifiableMap(answerMap);
		System.out.println(immutableAnswerMap);
		
		// One can also create synchronized versions of collections, sets, lists, and maps from unsynchronized ones:
		// Collections.synchronizedCollection(c)
		// Collections.synchronizedList(list)
		// Collections.synchronizedMap(m)
		// Collections.synchronizedSet(s)
		
		System.out.println("\nNow for the grand finale... Markovian Fox in Socks!\n");
		
		// Markov word generator:
		// Read a text and create a map of each word to an ArrayList of words following that word in the text.
		// Begin with a tag <BEGIN> and end with tag <END>.
		// Then randomly generate a text where the probability of choosing a next word is in proportion to the word before.
		
		LinkedList<String> queue = new LinkedList<>();
		queue.offer("<BEGIN>");
		Scanner foxInSocks = new Scanner(new URL("http://cs.gettysburg.edu/~tneller/cs112/data/FoxInSocks.txt").openStream());
		while (foxInSocks.hasNext())
			queue.offer(foxInSocks.next());
		foxInSocks.close();
		queue.offer("<END>");
		
		// Build associations between each word and the next word:
		Map<String, ArrayList<String>> nextWordsMap = new HashMap<>(); 
		String word = queue.poll();
		while (!word.equals("<END>")) {
			String nextWord = queue.poll();
			ArrayList<String> nextWords = nextWordsMap.get(word);
			if (nextWords == null) {
				nextWords = new ArrayList<String>();
				nextWordsMap.put(word, nextWords);
			}
			nextWords.add(nextWord);
			word = nextWord;
		}
		
		// Generate nonsense text according to next word frequency.
		word = "<BEGIN>";
		int i = 0;
		while (true) {
			ArrayList<String> nextWords = nextWordsMap.get(word);
			String nextWord = nextWords.get((int) (nextWords.size() * Math.random()));
			if (nextWord.equals("<END>"))
				break;
			else
				System.out.print(nextWord + " ");
			if (++i % 10 == 0) // line breaks every ten words
				System.out.println();
			word = nextWord;
		}
		
	}

}
