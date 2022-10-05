/**
 * Tests for SegmentNodeDatabase
 * 
 * <p>Bugs: 
 * 
 * @author Jace Rettig and James Crawford
 * @Date 9-1-22
 */
package input.components.segment;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import input.components.point.PointNode;

class SegmentNodeDatabaseTest {
	
	/**
	 * Builds a complex triangle to facilitate advanced testing
	 * @return segmentNodeDatabase of a complicated triangle
	 */
	public SegmentNodeDatabase build()
    {
    	//      A                                 
    	//     / \                                
    	//    B___C                               
    	//   / \ / \                              
    	//  /   X   \ 
    	// D___/_\___E
    	//
		//
    	PointNode a = new PointNode("A", 3, 6);
    	PointNode b = new PointNode("B", 2, 4);
    	PointNode c = new PointNode("C", 4, 4);

    	PointNode d = new PointNode("D", 0, 0);
    	PointNode e = new PointNode("E", 6, 0);
    	PointNode x = new PointNode("X", 3, 3);

    	SegmentNodeDatabase db = new SegmentNodeDatabase();
    	  	
    	db.addUndirectedEdge(a, b);
    	db.addUndirectedEdge(a, c);
    	db.addUndirectedEdge(b, c);
    	db.addUndirectedEdge(b, x);
    	db.addUndirectedEdge(b, d);
    	db.addUndirectedEdge(c, x);
    	db.addUndirectedEdge(c, e);
    	db.addUndirectedEdge(x, d);
    	db.addUndirectedEdge(x, e);
    	db.addUndirectedEdge(d, e);
    	
    	return db;
    }
	
	/**
	 * Builds a simple triangle to facilitate testing
	 * @return segmentNodeDatabase for triangle
	 */
	public SegmentNodeDatabase buildTriangle () {
		//       A
		//      / \
		//	   B___C
		//
		PointNode a = new PointNode("A", 1, 2);
    	PointNode b = new PointNode("B", 0, 0);
    	PointNode c = new PointNode("C", 2, 0);
		
		SegmentNodeDatabase db = new SegmentNodeDatabase();
		
		db.addUndirectedEdge(a, b);
    	db.addUndirectedEdge(a, c);
    	db.addUndirectedEdge(b, c);
    	
		return db;
	}
	
	public SegmentNodeDatabase testConstructor2() {
		//TODO need to test 2nd constructor
		return new SegmentNodeDatabase();
	}
	
	/**
	 * Converts the list of segmentNodes into a string of the segmentedNodes
	 * @param list of segmentNodes
	 * @return the list in string form
	 */
	//TODO MAKE STATIC OR PUT IN TEST CLASS
	//placement does not make sense
	public String segmentListToString(List<SegmentNode> list) {
		String segList ="";
		if (list.size() < 0) return null;
		
		for (SegmentNode sn : list)
		{
			segList += sn.toString() + " ";
		}
		return segList;
	}
	
	//TODO build square/ square with x to test too?
	
	/**
	 * tests the numUndirectedEdges method
	 */
	@Test
	void testNumUndirectedEdges()
	{
		SegmentNodeDatabase db = build();
		SegmentNodeDatabase triangle = buildTriangle();
		SegmentNodeDatabase empty = new SegmentNodeDatabase();
		
		
		assertEquals(10, db.numUndirectedEdges());
		assertEquals(3, triangle.numUndirectedEdges());
		//empty database/no edges
		assertEquals(0, empty.numUndirectedEdges());
	}
	/**
	 * tests addUndirectedEdge specifically
	 */
	@Test
	public void testAddUndirectedEdge() {
		//TODO test adding undirected edges 
	}
	
	
	/**
	 * tests for addAdjacencyList
	 */
	@Test
	public void testAddAdjacencyList() {
		PointNode a1 = new PointNode("A", 1, 2);
    	PointNode b1 = new PointNode("B", 0, 0);
    	PointNode c1 = new PointNode("C", 2, 0);
    	
    	
		SegmentNodeDatabase newTriangle = new SegmentNodeDatabase();
		SegmentNodeDatabase newComplexTriangle = new SegmentNodeDatabase();
		
		//create lists 
		List<PointNode> listA = new ArrayList<PointNode>();
		List<PointNode> listB = new ArrayList<PointNode>();
		List<PointNode> listC = new ArrayList<PointNode>();
		
		
		//add points to lists
		listA.add(b1);
		listA.add(c1);
		listB.add(a1);
		listB.add(c1);
		listC.add(b1);
		listC.add(a1);
		
		//add adjacency lists
		newTriangle.addAdjacencyList(a1, listA);
		newTriangle.addAdjacencyList(b1, listB);
		newTriangle.addAdjacencyList(c1, listC);
		
		
		assertEquals(3, newTriangle.numUndirectedEdges());
		
		
		
		//TEST FOR COMPLEX TRIANGLE//
		listA.clear();
		listB.clear();
		listC.clear();
		
		List<PointNode> listD = new ArrayList<PointNode>();
		List<PointNode> listE = new ArrayList<PointNode>();
		List<PointNode> listX = new ArrayList<PointNode>();
		
		PointNode a = new PointNode("A", 3, 6);
    	PointNode b = new PointNode("B", 2, 4);
    	PointNode c = new PointNode("C", 4, 4);

    	PointNode d = new PointNode("D", 0, 0);
    	PointNode e = new PointNode("E", 6, 0);
    	PointNode x = new PointNode("X", 3, 3);
		
		listA.add(b);
		listA.add(c);
		
		listB.add(a);
		listB.add(c);
		listB.add(x);
		listB.add(d);
		
		listC.add(b);
		listC.add(a);
		listC.add(x);	
		listC.add(e);
		
		listX.add(b);
		listX.add(c);
		listX.add(d);
		listX.add(e);
		
		listD.add(b);
		listD.add(x);
		listD.add(e);
		
		listE.add(d);
		listE.add(x);
		listE.add(c);
		
		newComplexTriangle.addAdjacencyList(a, listA);
		newComplexTriangle.addAdjacencyList(b, listB);
		newComplexTriangle.addAdjacencyList(c, listC);
		newComplexTriangle.addAdjacencyList(x, listX);
		newComplexTriangle.addAdjacencyList(d, listD);
		newComplexTriangle.addAdjacencyList(e, listE);
		
		assertEquals(10, newComplexTriangle.numUndirectedEdges());
	}
	
	
	
	@Test
	public void testDirectedEdgeThrow() {
		try {
			
			SegmentNodeDatabase db = build();
			PointNode a = new PointNode("A", 3, 6);
			//code to throw exception
			db.addUndirectedEdge(a, a);
			
			
			fail();
		} catch (ArithmeticException ex) {
			assertEquals("Invalid Edge", ex.getMessage());
		}
	}
	
	
	@Test
	public void testAsSegmentEdge() {
		SegmentNodeDatabase db = buildTriangle();
		
		assertEquals("AB AC BA BC CA CB ", segmentListToString(db.asSegmentList()));
	}
	
	/**
	 * tests the asUniqueSegmentList method
	 */
	@Test
	public void testasUniqueSegmentList() {
		SegmentNodeDatabase db = buildTriangle();
		
		assertEquals("AB AC BC ", segmentListToString(db.asUniqueSegmentList()));
	}
	
}
