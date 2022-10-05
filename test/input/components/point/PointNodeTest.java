/**
 * Test cases for PointNode
 * 
 * <p>Bugs: 
 * 
 * @author Jace Rettig and James ???
 * @Date 9-1-22
 */
package input.components.point;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PointNodeTest {

	private PointNode _nodeA;
	private PointNode _nodeA2;
	private PointNode _nodeB;
	
	public PointNodeTest() {
		_nodeA = new PointNode("A", 0, 0);
		_nodeA2 = new PointNode("A", 0, 0);
		_nodeB = new PointNode("B", 1, 1);
	}
	
	
	/**
	 * test if two notes have same point
	 */
	@Test
	public void testEqualsMethod() {
		assertTrue(_nodeA.equals(_nodeA2));
		assertFalse(_nodeA.equals(_nodeB));
	}
	/**
	 * test toString method
	 */
	@Test
	public void testToString() {
		assertEquals("A(0.0, 0.0)", _nodeA.toString());
	}
	

	
	

}
