package equivalence;

/**
* A singly linked list...yay...
*
* <p>Bugs: Does not use previous helper method
* 
* @author Jace Rettig and Sally Stahl
* @Date 9-11-22
*/

public class LinkedList <Item> {
	
	//NODE INNER CLASS
	private class Node<T> {
		protected Item _data;
		protected Node <Item> _next;
		
		public Node(Item data, Node<Item> next) {
			_data = data;
			_next = next;
		}
		
	}
	
	protected Node<Item> _head;
	protected Node<Item> _tail;
	protected int _size;
	
	/**
	 * Creates a new LinkedList
	 */
	public LinkedList() {
		_head = new Node<Item>(null, _tail);
		_tail = new Node<Item>(null, null);
		_size = 0;
	}
	
	
	
	
	//helper methods
	/**
	 * inserts a node between two nodes
	 * @param left
	 * @param item
	 */
	private void insert(Node<Item> left, Item item, Node<Item> right) {
		//add node to right of left
		left._next = new Node<Item>(item, right);
		//increase size
		_size++;
	}
	
	/**
	 * Helps build a string representation of a linked list recursively
	 * @param s
	 * @param n
	 */
	private void toStringHelper(StringBuilder s, Node<Item> n) {
		//base case
		if (n == _tail || n == null) return;
		//add current nodes data to string
		s.append(n._data + " ");
		//call string helper on next node
		toStringHelper(s, n._next);
	}
	
	/**
	 * Checks if linked list contains target recursively
	 * @param curr
	 * @param target
	 * @return True if found
	 */
	private boolean containsHelper(Node<Item> curr, Item target) {
		//if not contained
		if (curr == _tail) return false;
		//if contained
		if (curr._data.equals(target)) return true;
		
		//recursively call containsHelper on next node
		return containsHelper(curr._next, target);
	}
	
	/**
	 * Return last node in linked list
	 * @return
	 */
	private Node<Item> last() {
		Node<Item> curr = _head._next;
		//check if empty
		if (isEmpty()) return _head;
		
		while (curr != null) {
			//find last node and return
			if ((curr._next == _tail)) return curr;
			//otherwise continue cycling through list
			curr = curr._next;
		}
		//default case
		return _head;
	}
	
	/**
	 * Helps get previous node based on input target data
	 * @param prev
	 * @param curr
	 * @param target
	 * @return
	 */
	private Node<Item> previousHelper(Node<Item> prev, Node<Item> curr, Item target) {
		//if not contained or first item in list (cannot have null prev)
		if (curr == _tail || _head._next._data.equals(target)) {
			//System.out.println("NULL");
			return null;
		}
		//if contained
		if (curr._data.equals(target)) {
			//System.out.println("TARGET FOUND: " + prev._data);
			return prev;
		}
		//call prevHelper on next set of nodes
		previousHelper(prev._next, curr._next, target);
		return prev;
	}
	
	/**
	 * Helps reverse a linked list
	 * @param first
	 * @param prev
	 * @param curr
	 */
	private Node<Item> reverseHelper(Node<Item> curr) {
		//create recursion stacks till last item in list
		if (curr._next == _tail) {
			//point head at last node
			_head._next = curr;
			//point last node at tail
			curr._next = _tail;
			//return last node
			return curr;
		}
		//return last node of normal list AKA front of reversed list
		Node<Item > reversedListFront = reverseHelper(curr._next);
		//set reversed front node's next to the current stack node
		//aka current node's previous
		reversedListFront._next = curr;
		//set current node's next to the tail
		curr._next = _tail;
		//return the current reversed list
		return reversedListFront._next;
	}

	
	
	
	//
	//ALL ALGORITHMS MUST BE LINEAR TIME OR BETTER, USE RECURSION ;n;
	//
	/**
	 * Checks if the list is empty
	 * @return True if empty
	 */
	public boolean isEmpty() {
		//if (_head._next == _tail) return true;
		if (_size == 0) return true;
		return false;
	}
	
	/**
	 * CLears the linked list
	 */
	public void clear() {
		//set head node's next to tail
		_head._next = _tail;
		//reset size
		_size = 0;
	}
	
	/**
	 * returns the size of the linked list
	 * @return
	 */
	public int size() {
		return _size;
	}
	
	/**
	 * adds a node to the front of the linked list
	 * @param element
	 */
	public void addToFront(Item element) {
		//add between head and head's next
		insert(_head, element, _head._next);
	}
	
	/**
	 * Adds a node to the end of the linked list
	 * @param element
	 */
	public void addToBack(Item element) {
		//insert between last node and tail
		insert(last(), element, _tail);
	}
	
	/**
	 * Check if linked list contains target data	
	 * @param target
	 * @return True if found
	 */
	public boolean contains(Item target) {
		//check if list is empty
		if (isEmpty()) return false;
		//first node in list
		Node<Item> curr = _head._next;
		//call recursive containsHelper
		return containsHelper(curr, target);
	}
	
	/**
	 * Creates a sting representation of the linked list
	 */
	public String toString( ) {
		//create string builder
		StringBuilder s = new StringBuilder();
		//call helper with string builder and first node (MUST USE RECURSION)
		toStringHelper(s, _head._next);
		//return new string
		return s.toString();
	}
	
	/**
	 * Return previous node of node with target data
	 * @param target
	 * @return
	 */
	//TODO functions properly, but where to use?
	private Node<Item> previous(Item target) {
		//check if list is empty
		if (isEmpty()) return null;
		
		//head and first nodes
		Node<Item> prev = _head;
		Node<Item> curr = _head._next;
		
		//call previousHelper method to find previous node
		return previousHelper(prev, curr, target);
	}
	
	/**
	 * Remove node with target data from list
	 * @param target
	 * @return
	 */
	public boolean remove(Item target) {
		//track previous and current nodes
		Node<Item> prev = _head;
		Node<Item> curr = _head._next;
		
		//check if list is empty
		if (isEmpty()) return false;
		
		while (curr != _tail) {
			//check if current nodes data matches target 
			if (curr._data.equals(target)) {
				//set prev node's next to curr node's next to skip/delete node
				prev._next = curr._next;
				//decrease size
				_size = _size - 1;
				return true;
			}
			//update nodes
			prev = prev._next;
			curr = curr._next;
		}
		//if item does not exist
		return false;
		
	}
	
	/**
	 * Reverses the list
	 */
	public void reverse() {
		//check if list is empty, if it is nothing to reverse
		if (isEmpty()) return;
		//first node in list
		Node<Item> curr = _head._next;
		//call reverse helper
		reverseHelper(curr);
	}
	
	/**
	 * Gets an element at a certain index 
	 * @param index
	 * @return item at specified index
	 */
	public Item getIndex(int index) {
		//TODO check if valid
		if (index > size()) return null;
		//start at head
		Node<Item>curr = _head._next;
		for (int i = 1; i <= index; i++) {
			//get next node
			curr = curr._next;
		}
		//return index node's data
		return curr._data;
		
		
	}
}
