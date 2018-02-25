
public class MahFravrit<E> {
	
	E item;
	MahFravrit<E> thing = null;
	/**
	 * @constructor MahFravrit constructor
	 * Create a new MahFravrit object with the given Comparator to discern mah fravrit
	 * ("my favorite") item of that type
	 * @param fravriter
	 * fravriter - a Comparator for determining a favorite item given two items of a type. A Comparator 
	 * implements a compare method that takes two items as parameters, returns a positive/negative number 
	 * if the first item is "greater"/"lesser" in some sense, respectively. If the two are equivalent, 
	 * a zero value is returned.
	 */
	public MahFravrit(java.util.Comparator<E> fravriter) {
			
	}
	/**
	 * @method isEmpty
	 * @return - whether it is empty or not
	 */
	public boolean isEmpty() {
		return false;
	}
	/**
	 * @method add
	 * Add an item to this set of items
	 * @param item - the item to be added to this set
	 */
	public void add(E item) {
		
	}
	/**
	 * @method remove
	 * @param item - the item requested to be removed
	 * @return - whether or not the item was found and removed
	 */
	public boolean remove(E item) {
		return false;
	}
	/**
	 * @getter getFavrit
	 * Find, remove, and return the fravrit ("favorite"), item in the set of items. 
	 * This operation should not take more comparisons than the number of items contained.
	 * @return the current fravrit ("favorite") from among the items in the set, or null if there are no items
	 */
	public E getFravrit() {
		return null;
	}
}
