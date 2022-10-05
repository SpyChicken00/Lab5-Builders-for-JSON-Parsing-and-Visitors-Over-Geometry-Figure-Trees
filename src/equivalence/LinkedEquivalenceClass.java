package equivalence;
import java.util.Comparator;
/**
 * A LinkedEquivalence Class, determines if an object "belongs" 
 * to a set based on a specified comparator
 * 
 * <p>Bugs: 
 * DemoteAndSetCanonical: When Initially setting the canonical, if the user incorrectly 
 * assigns the initial value wrong then all comparisons will be false
 * After the 1st time set will check if new canonical belongs before adding
* 
* @author Jace Rettig and Sally Stahl
* @Date 9-12-22
* @param <T>
*/
public class LinkedEquivalenceClass<T> {

	protected T _canonical;
	protected Comparator<T> _comparator;
	protected LinkedList<T> _rest;
	
	/**
	 * Creates a new LinkedEquivalence Class Object
	 * @param comparator
	 */
	public LinkedEquivalenceClass(Comparator<T> comparator) {
		_canonical = null;
		_comparator = comparator;
		_rest = new LinkedList<T>();
	}
	
	/**
	 * Returns the current canonical for the Linked Equivalence Class
	 * @return the current canonical
	 */
	public T canonical() {
		return _canonical;
	}
	
	/**
	 * Checks if the Linked Equivalence Class is empty
	 * @return True if empty
	 */
	public boolean isEmpty() {
		//check that list is empty AND that canonical is null;
		if (_canonical == null && _rest.isEmpty()) return true;
		//if not empty
		return false;
	}
	
	/**
	 * Clears the entire Linked Equivalence Class, including the canonical
	 */
	public void clear() {
		//clear list AND clear canonical;
		_canonical = null;
		_rest.clear();
	}
	
	/**
	 * Clears the Linked Equivalence Class but the canonical does not change
	 */
	public void clearNonCanonical() {
		//clear list but NOT canonical
		_rest.clear();
	}
	
	/**
	 * Returns the size of the Linked Equivalence Class, including the canonical
	 * @return
	 */
	public int size() {
		//if canonical is not null size = linked list size + 1
		if (_canonical != null) return _rest._size + 1;
		//return the size of the rest of the linked list 
		return _rest.size();
	}
	
	/**
	 * Adds a new item to the Linked Equivalence Class
	 * @param element
	 * @return True if successfully added
	 */
	public boolean add(T element) {
		//check if element is null- FALSE
		if (element == null) return false;
		//check if already contained- FALSE
		if (contains(element)) return false;
		//check if item belongs before adding
		if (!(belongs(element))) return false;
		//check if list is empty and need to set canonical
		//if (isEmpty()) return demoteAndSetCanonical(element);
		
		//add element to back of linked list
		_rest.addToBack(element);
		return true;
	}
	
	/**
	 * Checks if the target is contained in the Linked Equivalence Class
	 * @param target
	 * @return True if contained
	 */
	public boolean contains(T target) {
		//TODO test
		if (target == null || _canonical == null) return false;
		//check if target is canonical or in linked list
		if (_canonical.equals(target) || _rest.contains(target)) return true;
		//not contained
		return false;
	}
	
	public boolean belongs(T target) {
		//TODO test
		//returns whether the target belongs in the equivalence class
		if (_comparator.compare(_canonical, target) == 0) return true;
		return false;
	}
	
	/**
	 * Removes the input target from the Linked Equivalence Class
	 * @param target
	 * @return True if successfully removed
	 */
	public boolean remove(T target) {
		//if target item is canonical
		if (target.equals(_canonical)) return removeCanonical();
		
		//check if in list
		if(_rest.contains(target)) {
			_rest.remove(target);
			return true;
		}
		
		//if cannot remove/not in list
		return false;
	}
	
	/**
	 * Removes the current canonical and replaces with 
	 * the first item in the rest of the list
	 * @return True if successful 
	 */
	public boolean removeCanonical() {
		//TODO test
		//if canonical is null or list is empty
		if ((_canonical == null) || (_rest.isEmpty())) return false;
		
		//get first item in rest
		T firstItem = _rest.getIndex(0);
		//remove first item from rest
		_rest.remove(firstItem);
		//set as new canonical
		_canonical = firstItem;
		//successfully removed
		return true;
		
		
	}
	
	/**
	 * Adds previous Canonical to Linked Equivalence Class and sets input to new canonical
	 * @param element
	 * @return True if element is different than canonical
	 */
	public boolean demoteAndSetCanonical(T element) {
		
		//if 1st time setting canonical//
		if (_canonical == null ) {
			_canonical = element;
			return true;
		}
		//resetting canonical//
		//check if canonical and element are identical or if element is null
		if (_canonical.equals(element) || element == null) return false;
		//check if element belongs in list
		if (!(belongs(element))) return false;
		//check if element is already contained , if so remove element
		if (contains(element)) _rest.remove(element);
		
		//add canonical to front of list
		_rest.addToFront(_canonical);
		//set input value to canonical
		_canonical = element;
		return true;
	}
	
	/**
	 * Returns a string representation of the Linked Equivalence Class
	 */
	public String toString() {
		//Create a string that represents list/canonical EX: {2 | 4, 6, 8, 12}
		StringBuilder s = new StringBuilder();
		s.append("{" + _canonical + " | ");
		s.append(_rest.toString() + "}");
		return s.toString();
	}
}








