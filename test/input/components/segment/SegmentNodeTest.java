/**
 * Tests for SegmentNode
 * 
 * <p>Bugs: 
 * 
 * @author Jace Rettig and James ???
 * @Date 9-1-22
 */
package input.components.segment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import input.components.point.PointNode;

class SegmentNodeTest {
	private PointNode _nodeA;
	private PointNode _nodeB;
	private PointNode _nodeC;
	private PointNode _nodeD;
	private PointNode _nodeE;
	private PointNode _nodeF; 
	private SegmentNode _segment1;
	private SegmentNode _segment1A;
	private SegmentNode _segment2;
	private SegmentNode _segment2A;
	private SegmentNode _segment3;
	private SegmentNode _segment3A;

	
	public SegmentNodeTest() {
		_nodeA = new PointNode("A", 0, 0);
		_nodeB = new PointNode("B", 1, 1);
		_nodeC = new PointNode("C", 2, 2);
		_nodeD = new PointNode("D", 3, 3);
		_nodeE = new PointNode("E", 4, 4);
		_nodeF = new PointNode("F", 5, 5);
		_segment1 = new SegmentNode(_nodeA, _nodeB);
		_segment2 = new SegmentNode(_nodeC, _nodeD);
		_segment3 = new SegmentNode(_nodeE, _nodeF);
		_segment1A = new SegmentNode(_nodeB, _nodeA);
		_segment2A = new SegmentNode(_nodeC, _nodeD);
		_segment3A = new SegmentNode(_nodeE, _nodeF);
		
	}

	/**
	 * test equals method
	 */
	@Test
	public void testEqualsMethod() {
		assertTrue(_segment1.equals(_segment1A));
		assertTrue(_segment2.equals(_segment2A));
		assertTrue(_segment3.equals(_segment3A));
		assertFalse(_segment1.equals(_segment2));
		assertFalse(_segment2.equals(_segment3));
		assertFalse(_segment3.equals(_segment1));
	}
	
	/**
	 * test toString method
	 */
	@Test
	public void testToString() {
		assertEquals("AB", _segment1.toString());
		assertEquals("CD", _segment2.toString());
		assertEquals("EF", _segment3.toString());
	}

}
