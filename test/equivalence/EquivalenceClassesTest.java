package equivalence;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import equivalence.EquivalenceClasses;


/**
 * Runs Junit tests on the methods of Equivalence class to ensure
 * capability and works on a variety of test cases.
 * @author Sally Stahl and Jace Rettig
 * @version September 17, 2022
 */



class EquivalenceClassesTest {

	/*
	 * A test array that takes integers 
	 */
	EquivalenceClasses<Integer> _ec;
	
	/*
	 * A comparator that will compare integers and see 
	 * if they are even or odd
	 */
	Comparator<Integer> c;



	public EquivalenceClassesTest(){

		//create the test equivalence classes
		c = new Comparator<Integer>() {
			// All even integers are 'equivalent' // All odd integers are 'equivalent'
			public int compare(Integer x, Integer y) {
				return x * 5 % 2 == y * 5 % 2 ? 0 : 1; }
		};

		_ec = new EquivalenceClasses<Integer>(c);
	}



	/**
	 * Uses jUnit testing to test the the add method will add an
	 * element to the an euivalence class
	 */

	@Test
	void testAdd() {

		//test with a null element
		assertFalse(_ec.add(null));

		//add an element
		assertTrue(_ec.add(2));

		//check that it is contains within that
		assertTrue(_ec.contains(2));

		//add another element
		assertTrue(_ec.add(4));

		//check that new element is contains
		assertTrue(_ec.contains(4));

		//add an element that already exists in the class
		assertTrue(_ec.add(2));

		//check what the equivalence class is 
		assertEquals("{2 | 4 }\n", _ec.toString());

		//add an element that is in a different equivalence 
		assertTrue(_ec.add(3));

		//add an element that is in this new equivalence class
		assertTrue(_ec.add(5));

		//verify that this has been added to new class
		assertEquals("{2 | 4 }\n{3 | 5 }\n", _ec.toString());

	}


	/**
	 * Uses jUnit testing to test that an element
	 * added to the array exists in that array
	 */


	@Test
	void testContains() {

		//test with a null element
		assertFalse(_ec.contains(null));

		//add an element
		assertTrue(_ec.add(2));

		//check that it is contains within that
		assertTrue(_ec.contains(2));

		//add another element
		assertTrue(_ec.add(4));

		//check that new element is contains
		assertTrue(_ec.contains(4));

		//test with an element that is not in the list
		assertFalse(_ec.contains(6));

		//add an element that is in a different equivalence 
		assertTrue(_ec.add(3));

		//check this element is in the set
		assertTrue(_ec.contains(3));

		//add an element that is in this new equivalence class
		assertTrue(_ec.add(5));

		//check that this element is in the set
		assertTrue(_ec.contains(5));


	}



	/**
	 * Uses jUnit testing to test the size of 
	 * the array by summing all the elements in each
	 * of the equivalence classes
	 */


	@Test
	void testSize() {


		//test with no elements in the set
		assertEquals(0 , _ec.size());

		//add an element
		assertTrue(_ec.add(2));

		//check the size of the class
		assertEquals(1 , _ec.size());

		//add another element
		assertTrue(_ec.add(4));

		//check the size 
		assertEquals( 2 , _ec.size());

		//add elements to a new equivalence class
		assertTrue(_ec.add(3));
		assertTrue(_ec.add(5));
		assertTrue(_ec.add(7));

		//check the size of the set
		assertEquals( 5 , _ec.size());

	}


	
	/**
	 * Uses jUnit testing to test how many equivalence
	 * classes there are in the array
	 */
	@Test
	void testNumClasses() {
		
		//check 0 num of classes
		assertEquals(0 , _ec.numClasses());

		//add elements to a class 
		_ec.add(2);
		_ec.add(4);

		//check the number of classes
		assertEquals(1, _ec.numClasses());

		//add elements to a different set of classes
		assertTrue(_ec.add(3));
		assertTrue(_ec.add(5));

		//check the number of classes
		assertEquals(2 , _ec.numClasses());

		//add an element to the first class
		assertTrue(_ec.add(6));

		//check the number of classes
		assertEquals(2 , _ec.numClasses());

	}


	/**
	 * Uses jUnit testing find the index of the class
	 * that a target element is in
	 */

	@Test
	void testIndexOfClasses() {
		
		//test on an element that is not in the set
		assertEquals( -1 , _ec.indexOfClass(1));

		//add elements to a class
		assertTrue(_ec.add(2));
		assertTrue(_ec.add(4));

		//find the index of class it is in
		assertEquals(0, _ec.indexOfClass(2));
		assertEquals(0, _ec.indexOfClass(4));

		//add elements to another class
		assertTrue(_ec.add(3));
		assertTrue(_ec.add(5));
		assertTrue(_ec.add(7));

		//find the index of class it is in
		assertEquals(1, _ec.indexOfClass(3));
		assertEquals(1, _ec.indexOfClass(5));
		
		//add an element to the orginial class
		assertTrue(_ec.add(6));
		
		//find the index of the class
		assertEquals(0 , _ec.indexOfClass(6));
		
		//add an element to the second class
		assertTrue(_ec.add(1));
		
		//check the index of the class
		assertEquals(1 , _ec.indexOfClass(1));


	}


	/**
	 * Uses jUnit testing to test the method that turns the elements
	 * in the equivalence classes to a string
	 */

	@Test
	void testToString() {
		//add elements to an equivalence class
		assertTrue(_ec.add(2));
		assertTrue(_ec.add(4));

		//verify that these elements are in there
		assertEquals("{2 | 4 }\n", _ec.toString());

		//add another element
		assertTrue(_ec.add(6));

		//check the list again
		assertEquals("{2 | 4 6 }\n", _ec.toString());

		//add elements that are in a different equivalence class
		assertTrue(_ec.add(3));
		assertTrue(_ec.add(5));
		assertTrue(_ec.add(7));

		//check that there are two separate equivalence classes created
		assertEquals("{2 | 4 6 }\n{3 | 5 7 }\n", _ec.toString());

	}


}
