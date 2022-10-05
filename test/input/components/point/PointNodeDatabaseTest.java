/**
 * Test cases for PointNodeDatabase 
 * 
 * <p>Bugs: 
 * 
 * @author Jace Rettig and James ???
 * @Date 9-1-22
 */
package input.components.point;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class PointNodeDatabaseTest {
	
	private PointNode _nodeA;
	private PointNode _nodeB;
	private PointNode _nodeC;
	private PointNode _nodeD;
	private PointNode _nodeE;
	private PointNode _nodeF;
	private PointNodeDatabase _database1;
	private PointNodeDatabase _database2;
	

	public PointNodeDatabaseTest() {
		_nodeA = new PointNode("A", 0, 0);
		_nodeB = new PointNode("B", 1, 1);
		_nodeC = new PointNode("C", 2, 2);
		_nodeD = new PointNode("D", 3, 3);
		_nodeE = new PointNode("E", 4, 4);
		_nodeF = new PointNode("F", 5, 5);
		_database1 = new PointNodeDatabase();
		
		PointNode[] pointArray = {_nodeA, _nodeB, _nodeC, _nodeD};
		_database2 = new PointNodeDatabase(Arrays.asList(pointArray));				
	}
	
	/**
	 * test put method
	 */
	@Test
	public void testPut() {
		//check adding items to empty set
		_database1.put(_nodeA);
		_database1.put(_nodeB);
		_database1.put(_nodeC);
		_database1.put(_nodeD);
		assertTrue(_database1.contains(_nodeA));
		assertTrue(_database1.contains(_nodeB));
		assertTrue(_database1.contains(_nodeC));
		assertTrue(_database1.contains(_nodeD));
		
		//check adding item to populated set
		_database2.put(_nodeA);
		_database2.put(_nodeB);
		_database2.put(_nodeC);
		_database2.put(_nodeD);
		_database2.put(_nodeE);
		_database2.put(_nodeF);
		
		assertTrue(_database2.contains(_nodeA));
		assertTrue(_database2.contains(_nodeB));
		assertTrue(_database2.contains(_nodeC));
		assertTrue(_database2.contains(_nodeD));
		assertTrue(_database2.contains(_nodeE));
		assertTrue(_database2.contains(_nodeF));
		
		//check adding duplicate item (but functionality built into set?

	}
	
	/**
	 * Test contains methods
	 */
	@Test
	public void testContains() {
		//check if contains w/ node and coordinates returns true
		assertTrue(_database2.contains(_nodeA));
		assertTrue(_database2.contains(0, 0));
		
		assertFalse(_database1.contains(_nodeA));
		assertFalse(_database1.contains(0, 0));
		
		//check if node not present is false
		assertFalse(_database1.contains(_nodeF));
		assertFalse(_database1.contains(5, 5)); 
		
		assertFalse(_database2.contains(_nodeF));
		assertFalse(_database2.contains(5, 5));
	}
	
	/**
	 * Test getName and getNode methods
	 */
	@Test
	public void testGetName() {
		//get node name
		assertEquals("A", _database2.getName(_nodeA));
		assertEquals("B", _database2.getName(_nodeB));
		assertEquals("C", _database2.getName(_nodeC));
		assertEquals("D", _database2.getName(3,3));
		assertEquals("__UNNAMED", _database2.getName(4,4));
		assertEquals("__UNNAMED", _database2.getName(5,5));
		
		//should be default name if has to create a new node
		assertEquals("__UNNAMED", _database2.getName(6,6));
		
		//get name from node in database
		assertEquals("A", _database2.getPoint(_nodeA).getName());
		assertEquals("B", _database2.getPoint(_nodeB).getName());
		assertEquals("C", _database2.getPoint(_nodeC).getName());
		assertEquals("D", _database2.getPoint(3,3).getName());
		assertEquals("__UNNAMED", _database2.getPoint(4,4).getName());
		assertEquals("__UNNAMED", _database2.getPoint(5,5).getName());
	}

}
