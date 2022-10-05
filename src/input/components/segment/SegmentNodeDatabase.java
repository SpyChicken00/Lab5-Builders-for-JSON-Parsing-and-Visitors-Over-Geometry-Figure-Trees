package input.components.segment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.*;

import input.components.ComponentNode;
import input.components.point.*;
import utilities.io.StringUtilities;

/**
 * A database to store segment nodes
 * 
 * <p>Bugs: Constructor SegmentNodeDatabase taking in a pre-existing map may not function properly
 * 
 * @author Jace Rettig and James Crawford
 * @Date 9-1-22
 */
public class SegmentNodeDatabase implements ComponentNode {
	private Map<PointNode, Set<PointNode>> _adjLists;
	
	public SegmentNodeDatabase() {
		_adjLists = new HashMap<PointNode, Set<PointNode>>();
	}
	
	public SegmentNodeDatabase(Map<PointNode, Set<PointNode>> m) {
		//TODO make copy of m
		//_adjLists = m;
		
		//makes a copy of m so that data will not be altered
		//MAY NOT WORK PROPERLY
		for (Map.Entry<PointNode, Set<PointNode>> OuterPoint:m.entrySet()) {
				_adjLists.put(OuterPoint.getKey(), OuterPoint.getValue());		
		}
	}
	
	/**
	 * Calculates the number of undirectedEdges in the database
	 * @return the number of undirected edges
	 */
	public int numUndirectedEdges() {
		int numUndirectedEdges = 0;
		//count number of pairs in adjLists
		//returns set of every entry pair
		for (Map.Entry<PointNode, Set<PointNode>> OuterPoint: _adjLists.entrySet()) {
			for (PointNode value: OuterPoint.getValue()) {
				if (_adjLists.get(value).contains(OuterPoint.getKey())) {
					numUndirectedEdges += 1;
				}	
			}
		}
		//divide by 2 to remove duplicates
		numUndirectedEdges = numUndirectedEdges / 2;
		return numUndirectedEdges;
	}
	
	/**
	 * Helper method to add a one way/directed edge from two given points
	 * @param pt1
	 * @param pt2
	 * @throws Exception 
	 */
	//TODO this needs comments
	private void addDirectedEdge(PointNode pt1, PointNode pt2)  {
		//fresh adj list for pt1
		if (_adjLists.get(pt1) == null) {
			Set<PointNode> nodeSet = new HashSet<PointNode>();
			_adjLists.put(pt1, nodeSet);
			_adjLists.get(pt1).add(pt2);
		} 
		//adjList already contains point/point is identical
		else if (_adjLists.get(pt1).contains(pt2) || pt1.equals(pt2)) {
			//TODO Dr Alvin: why is this throwing an exception
			throw new ArithmeticException("Invalid Edge");
		}
		//add pt2 to pt1 adj list
		else {
			_adjLists.get(pt1).add(pt2);
		}
	}
	
	/**
	 * Adds an undirectedEdge from two given points
	 * @param pt1
	 * @param pt2
	 */
	public void addUndirectedEdge(PointNode pt1, PointNode pt2) {
		//Call directed edge twice, makes 1 directed edge
		addDirectedEdge(pt1, pt2);
		addDirectedEdge(pt2, pt1);
			//pt1 -> pt2 and pt1 <- pt2
			//pt1 <-> pt2
	}
	
	/**
	 * Helper method to turn this list into set
	 * @param list of pointNodes
	 * @return set of PointNodes
	 */
	private Set<PointNode> listToSet(List<PointNode> list) {
		if (list == null) return null;
		Set<PointNode> nodeSet = new HashSet<PointNode>();
		for (PointNode node: list) {
			nodeSet.add(node);
		}
		return nodeSet;
	}
	
	/**
	 * Adds a new adjacencyList to adjLists
	 * @param point- point to add
	 * @param list- list of points that point is next to
	 */
	public void addAdjacencyList(PointNode point, List<PointNode> list) {
		//turn into set
		//Add point as the key as d list as the "value" pair to _adjLists
		_adjLists.put(point, this.listToSet(list));
	}
	
	
	/**
	 * Creates a list of segmentNodes based on the adjacency lists, including duplicates
	 * @return segmentList
	 */
	public List<SegmentNode> asSegmentList() {
		List<SegmentNode> segmentList = new ArrayList<SegmentNode>();
		//loop through each list in adjLists
		for (Map.Entry<PointNode, Set<PointNode>> OuterPoint: _adjLists.entrySet()) {
			//loop through list values
			for (PointNode value: OuterPoint.getValue()) {
				SegmentNode tempSegment = new SegmentNode(OuterPoint.getKey(), value);
				segmentList.add(tempSegment);
			}
		}
		return segmentList;
		
	}
	/**
	 * Helper Method to check if a segment's compliment exists in the list 
	 * @param start of segment
	 * @param end of segment
	 * @param segmentList
	 * @return True if the segmentList contains the reversed segment
	 */
	private boolean hasDirectedSegment(PointNode start, PointNode end, List<SegmentNode> segmentList) {
		SegmentNode segmentReversed = new SegmentNode(end, start);
		//check if compliment is in list
		if (segmentList.contains(segmentReversed)) return true;
		//if not false
		return false;
	}
	
	/**
	 * Creates a list of unique segmentNodes based on the adjacency lists
	 * @return a unique segmentList
	 */
	public List<SegmentNode> asUniqueSegmentList() {
		//unique, CANNOT have duplicate segments
		List<SegmentNode> segmentList = new ArrayList<SegmentNode>();
		//loop through each list in adjLists
		for (Map.Entry<PointNode, Set<PointNode>> OuterPoint: _adjLists.entrySet()) {
			//loop through list values
			for (PointNode value: OuterPoint.getValue()) {
				SegmentNode tempSegment = new SegmentNode(OuterPoint.getKey(), value);
				if (!(this.hasDirectedSegment(OuterPoint.getKey(), value, segmentList))) {
					segmentList.add(tempSegment);
				}
			}
		}
		return segmentList;
	}
	
	
	/**
	 * Unparse the SegmentNodeDatabase: glorified toString ;n; 
	 */
	public void unparse(StringBuilder sb, int level) 
	{
		sb.append(StringUtilities.indent(level - 1));
		sb.append("Segments: \n");
		sb.append(StringUtilities.indent(level - 1));
		sb.append("{\n");
		
		//loop over the segments
		for (Map.Entry<PointNode, Set<PointNode>> key: _adjLists.entrySet()) {
			sb.append(StringUtilities.indent(level));
			sb.append(key.getKey().getName() + " : ");
			for (PointNode value: key.getValue()) {
				sb.append(value.getName() + "  ");
			}
			sb.append("\n");
		}
		
		sb.append(StringUtilities.indent(level - 1));
		sb.append("}\n");
	}
	
	
	
	
	
	
}
