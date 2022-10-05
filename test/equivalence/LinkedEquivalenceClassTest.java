package equivalence;


import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import equivalence.LinkedEquivalenceClass;



/**
 * Runs Junit tests on the methods of Linked Equivalence Class class to ensure
 * capability and works on a variety of test cases.
 * @author Sally Stahl and Jace Rettig
 * @version September 15, 2022
 */



class LinkedEquivalenceClassTest {
	

	/*
	 * A test equivalence class that hold integers 
	 */
	LinkedEquivalenceClass<Integer> l; 
	
	/*
	 * a comparator of integers that sees if the integer is even
	 * or odd
	 */
	Comparator<Integer> c;

			
			
	public LinkedEquivalenceClassTest() {
		
		
		c = new Comparator<Integer>() {
			// All even integers are 'equivalent' // All odd integers are 'equivalent'
			public int compare(Integer x, Integer y) {
				return x*5  % 2 == y*5  % 2 ? 0 : 1; }
			};
			
			l = new LinkedEquivalenceClass<Integer>(c);	
	}

	
	/*
	 * a JUnit test that returns the canonical element
	 */
	@Test
	void testCanonical() {
		
		
		//clear the list
		l.clear();
		
		//check that the canonical is null
		assertEquals(null , l.canonical());
		
		//test with a canonical that is null
		assertTrue(l.demoteAndSetCanonical(null));
		
		//test that the canonical is empty
		assertEquals(null , l.canonical());
		
		//test on an empty canonical
		//TODO- change this once demote and set is finalized
		assertEquals(true , l.demoteAndSetCanonical(2));
		
		//check that the canonical is 1
		assertEquals(2, l.canonical());
		
		//test on a canonical element
		l.demoteAndSetCanonical(4);
		
		//check that the new canonical is 2
		assertEquals(4 , l.canonical());
		
		//check a canonical that does not belong
		assertFalse(l.demoteAndSetCanonical(3));
		
		//check that the canonical is still what is was before
		assertEquals(4 , l.canonical());
		
		
	}
	
	
	/*
	 * a Junit test that check if the canonical and the 
	 * rest of the list are empty
	 */
	@Test
	void testisEmpty() {
		//clear the list
		l.clear();
		
		//test on an empty canonical and empty rest
		assertTrue(l.isEmpty());
		
		//add a canonical 
		l.demoteAndSetCanonical(2);
		
		//check that the canonical and rest are not empty
		assertFalse(l.isEmpty());
		
		//add element to canonical and have an element in the rest
		l.add(4);
		
		//check that it is still containing elements
		assertFalse(l.isEmpty());
		
		//clear the list
		l.clearNonCanonical();
		
		//check that there is still an element in canonical
		assertFalse(l.isEmpty());
		
		//clear the whole thing
		l.clear();
		
		//check that everything is empty
		assertTrue(l.isEmpty());

	}
	
	
	/*
	 * a Junit test to check that the canonical
	 * element and the rest of the list are cleared when the 
	 * method is called
	 * 
	 */
	
	@Test
	public void testClear() {
		//clear the list
		l.clear();
		
		
		//test that the list is empty
		assertTrue(l.isEmpty());
		
		//add elements to the list
		l.demoteAndSetCanonical(2);
		l.add(4);
		l.add(6);
		l.add(8);
		
		//test that the list is not empty
		assertFalse(l.isEmpty());
		
		//clear the list 
		l.clear();
		
		//test that the canonical is null
		assertEquals(null, l.canonical());
		
		//check that the list is now empty
		assertTrue(l.isEmpty());
	
		
	}
	
	
	/*
	 * A Junit test that check the the rest of the list,
	 * and not the canonical element is cleared
	 */
	
	@Test
	public void testClearNonCanonical() {
		//clear the list and the canonical
		l.clear();
		
		//add an element to the canonical
		l.demoteAndSetCanonical(2);
		
		//check the size of the list
		assertEquals(1 , l.size());
		
		//add elements to the rest of the list
		l.add(4);
		l.add(6);
		l.add(8);
		
		//check the size of the list
		assertEquals(4 , l.size());
		
		//clear the non canonical elements
		l.clearNonCanonical();
		
		//ensure the size is back to 1
		assertEquals(1, l.size());
	}
	
	
	/*
	 * a Junit test that check the size of the equivalence class
	 */
	
	@Test
	public void testSize() {
		//add canonical, should be 1 item in linked list
		l.clear();
		//test size of linked equivalence class 
		assertEquals(0, l.size());
		
		//test the size with one element on the canonical
		assertTrue(l.demoteAndSetCanonical(2));
		
		//test the size
		assertEquals(1 , l.size());
		
		//add more elements to the list
		assertTrue(l.add(4));
		assertTrue(l.add(6));
		
		//test the size
		assertEquals(3 , l.size());
		
		//clear the list and not the canonical
		l.clearNonCanonical();
		
		//check the size
		assertEquals(1 , l.size());
		
		//clear the rest and the canonical
		l.clear();
		
		//check the size again
		assertEquals(0 , l.size());
		
	}
	
	
	/*
	 * a Junit test to check that the add method works and add elements to the 
	 * equivalence class
	 */
	
	@Test
	public void testAdd() {
		
		//add an element to the canonical 
		assertTrue(l.demoteAndSetCanonical(2));
		
		//add elements to the rest of the list
		assertTrue(l.add(4));
		assertTrue(l.add(6));
		
		//add the element that is value of the canonical
		assertFalse(l.add(2));
		
		//test an element that does not belong to the set
		assertFalse(l.add(3));
		
		//add an element that is null
		assertFalse(l.add(null));
		
		//try add with an empty list to set the element to the canonical!!!
		
	}
	
	
	/* 
	 * a jUnit test that ensures the contains method works
	 */
	
	@Test
	public void testContains() {
		//test on an empty set
		//assertFalse(l.contains(1));
		
		//add elements to the set
		l.demoteAndSetCanonical(2);
		l.add(4);
		l.add(6);
		l.add(8);
		
		//verify that the size has increased
		//assertEquals(4 , l.size());
		
		//check that the list contains the element
		assertTrue(l.contains(4));
		
		//add another element to the list
		assertTrue(l.contains(6));
		
		//verify that the lsit contains the canonical element
		assertTrue(l.contains(2));
		
		//clear the list
		l.clearNonCanonical();
		
		//verify that the list contains the canonical
		assertTrue(l.contains(2));
		
		//clear the canonical element
		l.clear();
		
		//check that the element is no longer contained within the set
		assertFalse(l.contains(2));
		
		//test on a null element
		assertFalse(l.contains(null));
		
		
	}
	
	
	/*
	 * a Junit test that checks that the belong method
	 * works and informs the user when an element doesn't belong
	 * 
	 */
	
	
	@Test public void testBelongs() {
		
		//add a canonical element
		l.demoteAndSetCanonical(2);
		
		//test on an element that doesn't belong
		assertFalse(l.belongs(3));
		
		//test on an element that belongs
		assertTrue(l.belongs(4));
		
		//test on an element that is the canonical
		assertTrue(l.belongs(2));
		
		//change the canonical
		l.demoteAndSetCanonical(4);
		
		//test on an element that belongs
		assertTrue(l.belongs(8));
		
		//test on an element that doesnt belong
		assertFalse(l.belongs(9));
		
	
		
	}
	
	
	/*
	 * a jUnit test that ensures that an element is removed when the 
	 * function is called
	 */
	
	
	@Test
	public void testRemove() {
		//clear the list
		l.clear();
		//check that the list is empty
		assertTrue(l.isEmpty());
		
		//test on an empty list
		assertFalse(l.remove(2));
		
		
		//add elements to the list
		l.demoteAndSetCanonical(2);
		l.add(4);
		l.add(6);
		l.add(8);
		
		//remove an element
		assertTrue(l.remove(2));
		
		//check that a new canonical has been set to the next element in list
		assertEquals(4 , l.canonical());
		
		//remove an element that is the canonical
		assertTrue(l.remove(4));
		
		
		//remove an element
		assertTrue(l.remove(6));

		
		//check that the canonical is still there but the list is empty
		assertFalse(l.isEmpty());
		
		//double check the size
		assertEquals(1 , l.size());
		
		//try to remove the canonical
		//will return false since it is the last element in the list
		assertFalse(l.remove(8));
		
		//remove an element that is not in the list
		assertFalse(l.remove(2));
		
	}
	
	
	
	/*
	 * a Junit test that check that the canonical
	 * element is cleared when called
	 */
	
	
	@Test
	public void testRemoveCanonical() {
		//clear the list
		l.clear();
		//check that the list is empty
		assertTrue(l.isEmpty());
		
		//add elements to the list
		l.demoteAndSetCanonical(2);
		l.add(4);
		l.add(6);
		l.add(8);
		
		//check that the new canonical is 3
		assertEquals(2 , l.canonical());
		
		//remove the canonical
		assertTrue(l.removeCanonical());
		
		//check that the new canonical is 3
		assertEquals(4 , l.canonical());
		
		//remove the canonical again
		assertTrue(l.removeCanonical());
		
		//check that the new canonical is 3
		assertEquals(6 , l.canonical());
		
		//remove canonical again
		assertTrue(l.removeCanonical());
		
		//check that the canonical is the only one
		assertEquals(8 , l.canonical());
		
		//ensure the size
		assertEquals(1 , l.size());

		
	}
	
	
	/*
	 * a JUnit test that ensures that the canonical element is added to 
	 * the rest of the list and add the element from input as the canonical eleent
	 */
	
	@Test
	public void testDemoteAndSetCanonical() {
		
		//clear the list
		l.clear();
		
		//ensure the size is 0 and everything is empty
		assertEquals( 0, l.size());
		
		//add a canonical to the list
		assertTrue(l.demoteAndSetCanonical(2));
		
		//check that the canonical is 2
		assertEquals(2 ,l.canonical());
		
		//check with a canonical value that is not allowed and doesnt belong
		assertFalse(l.demoteAndSetCanonical(3));
		
		//demote the canonical and add element to the front
		assertTrue(l.demoteAndSetCanonical(4));
		
		//verify that the canonical is now 3
		assertEquals(4 , l.canonical());
		
		
		//try to add canonical that is of the same value as existing canonical
		assertFalse(l.demoteAndSetCanonical(4));
		
		//try to add a canonical that is null
		assertFalse(l.demoteAndSetCanonical(null));
		


	}
	
	
	
	/*
	 * a jUnit test that ensures the function of the method that will
	 * turn the equivalence class to a string
	 */
	
	@Test
	public void testToString() {
		//set initial canonical
		l.demoteAndSetCanonical(2);
		l.add(4);
		l.add(6);
		l.add(8);
		l.add(10);
		//check string
		assertEquals("{2 | 4 6 8 10 }", l.toString());
		l.demoteAndSetCanonical(4);
		//duplicate element removed when canonical switched
		assertEquals("{4 | 2 6 8 10 }", l.toString());
	}

}

