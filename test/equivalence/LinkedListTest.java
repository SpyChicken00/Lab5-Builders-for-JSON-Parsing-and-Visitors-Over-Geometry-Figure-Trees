package equivalence;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import equivalence.LinkedList;

class LinkedListTest {
	private LinkedList<String> l;
	private LinkedList<String> l2;
	private LinkedList<String> emptyList;

	public LinkedListTest() {
		l = new LinkedList<String>();
		l2 = new LinkedList<String>();
		emptyList = new LinkedList<String>();
		l.addToBack("1");
		l.addToBack("2");
		l.addToBack("3");
		l.addToBack("4");
		l.addToBack("5");
		l.addToBack("6");
		l.addToFront("0");
		l2.addToBack("A");
		l2.addToBack("B");
		l2.addToBack("C");
		l2.addToBack("D");
	}
	
	@Test
	public void testClear() {
		emptyList.addToBack("empty");
		emptyList.clear();
		assertTrue(emptyList.isEmpty());
	}
	
	@Test
	public void testIsEmpty() {
		assertFalse(l.isEmpty());
		assertTrue(emptyList.isEmpty());
	}
	
	@Test
	public void testAddToFrontAddtoBack() {
		//TODO more insert node tests?
		assertEquals("", emptyList.toString());
		assertEquals("0 1 2 3 4 5 6 ", l.toString());
		assertEquals("A B C D ", l2.toString());
	}
	
	@Test
	public void testContains() {
		//does not contain target
		//does contain target 
		//list is empty
		assertTrue(l.contains("1"));
		assertTrue(l.contains("2"));
		assertFalse(l.contains("10"));
		
		emptyList.clear();
		emptyList.addToBack("empty");
		emptyList.clear();
		assertTrue(emptyList.isEmpty());
		assertFalse(emptyList.contains("0"));
		assertFalse(emptyList.contains("1"));
		assertFalse(emptyList.contains("12"));
	}
	
	@Test
	public void testRemove() {
		//try to remove last node
		//try empty
		//try 1 element
		//try non-existent element
		//ensure size adjusts accordingly
		
		assertEquals(4, l2.size());
		assertTrue(l2.remove("D"));
		assertFalse(l2.contains("D"));
		assertEquals("A B C ", l2.toString());
		assertEquals(3, l2.size());
		
		//remove non-existent element
		assertFalse(l2.remove("D"));
		//remove empty
		assertFalse(emptyList.remove("empty"));
		
		l2.remove("A");
		assertFalse(l2.contains("A"));
		assertEquals("B C ", l2.toString());
		assertEquals(2, l2.size());
		
		l2.addToBack("RAWR");
		l2.addToBack("(*_*)");
		assertEquals(4, l2.size());
		l2.remove("B");
		l2.remove("C");
		assertFalse(l2.contains("B"));
		assertFalse(l2.contains("C"));
		assertEquals("RAWR (*_*) ", l2.toString());
		assertEquals(2, l2.size());
	}
	
	@Test
	public void testPrevious() {
		//does not contain target
		//does contain target
		//list is empty
		//tests work fine but not public method
		/*
		System.out.println(l.previous("0"));
		System.out.println(l.previous("1"));
		System.out.println(l.previous("2"));
		System.out.println(l.previous("6"));
		System.out.println(l.previous("7"));
		System.out.println(l.previous("8"));
		System.out.println(emptyList.previous("5"));
		*/
	}
	
	@Test
	public void testReverse() {
		//TODO more tests?
		l.reverse();
		assertEquals("6 5 4 3 2 1 0 ", l.toString());
		
		//check different list
		l2.reverse();
		assertEquals("D C B A ", l2.toString());
		
		//check 1 and 2 items
		emptyList.addToBack("Test");
		emptyList.reverse();
		assertEquals("Test ", emptyList.toString());
		
		emptyList.addToBack("Test2");
		emptyList.reverse();
		assertEquals("Test2 Test ", emptyList.toString());
		emptyList.clear();
		assertEquals(0, emptyList.size());
		
	}
	
	@Test
	public void testGetIndex() {
		assertEquals("0", l.getIndex(0));
		assertEquals("1", l.getIndex(1));
		assertEquals("2", l.getIndex(2));
		assertEquals("6", l.getIndex(6));
		
	}
	
}
