package equivalence;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A collection of Equivalence Classes stored in an arrayList
 * 
 * <p>Bugs: ???
 * 
 * @author Jace Rettig and Sally Stahl
 * @Date 9-12-22
 * @param <T>
 */
public class EquivalenceClasses<T> {
	protected Comparator<T> _comparator;

	protected List<LinkedEquivalenceClass<T>> _classes;

	/**
	 * Creates a new arraylist to store equivalence classes
	 * @param comparator	
	 */
	public EquivalenceClasses(Comparator<T> comparator) {
		_comparator = comparator;
		_classes = new ArrayList<LinkedEquivalenceClass<T>>();
	}

	/**
	 * Creates 
	 * @param element
	 * @return true if addition was successful
	 */
	public boolean add(T element) {
		//check if element null
		if (element == null) return false;
		//find index element belongs to and add to that class
		int index = indexOfClass(element);
		if (!(index == -1)) {
			_classes.get(index).add(element);
			return true;
		}
		
		//otherwise create a new equivalence class and set element as that classes canonical
		LinkedEquivalenceClass<T> c = new LinkedEquivalenceClass<T>(_comparator);
		c.demoteAndSetCanonical(element);
		_classes.add(c);
		return true;
	}

	/**
	 * Checks if the arrayList contains the input target
	 * @param target
	 * @return True if containment
	 */
	public boolean contains(T target) {
		//check if target is null
		if (target == null) return false;
		//check each class in classes and see if contains target
		for (LinkedEquivalenceClass<T> c:_classes) {
			if (c.contains(target)) return true;
		}
		//item not contained
		return false;
	}
	
	/**
	 * Returns the number of equivalence classes contained in the list
	 * @return and integer 
	 */
	public int size() {
		//calculate # of items in all classes
		int size = 0;
		for (LinkedEquivalenceClass<T> c:_classes) {
			size += c.size();
		}
		return size;
	}

	/**
	 * Returns the number of classes contained in the list
	 * @return
	 */
	public int numClasses() {
		//return size of list, list should not have duplicates
		return _classes.size();
	}

	/**
	 * Returns the index a particular class is located at; Returns -1 if
	 * the input is null or the class is not contained in the list
	 * @param element
	 * @return index of particular class
	 */
	protected int indexOfClass(T element) {
		//check if element is null
		if (element == null) return -1;
		//index location element belongs to
		for (int i = 0; i < _classes.size(); i++) {
			//check if target is equal to current item
			if (_classes.get(i).belongs(element)) return i;
		}
		//not contained
		return -1;
	}

	/**
	 * Returns a String representation of the contents of
	 * each Linked Equivalence Class the arrayList
	 * @return String 
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < _classes.size(); i++) {
			//add each item in arrayList to string on a new line
			s.append(_classes.get(i).toString() + "\n");
		}
		//String representation of all the classes in arrayList 
		return s.toString();
	}
	

}
