/**
 * A Database to store PointNodes 
 * 
 * <p>Bugs: 
 * 
 * @author Jace Rettig and James ???
 * @Date 9-1-22
 */
package input.components.point;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import input.components.ComponentNode;
import utilities.io.StringUtilities;

public class PointNodeDatabase implements ComponentNode {
	protected Set<PointNode> _points;

	/**
	 * Creates a empty set of pointNodes
	 */
	public PointNodeDatabase() {
		_points = new LinkedHashSet<PointNode>();
	}

	/**
	 * Creates a new set of pointNodes with an existing 
	 * list of pointNodes
	 * @param l- a list of PointNodes
	 */
	public PointNodeDatabase(List<PointNode> l) {
		_points = new LinkedHashSet<PointNode>(l);
	}

	/**
	 * Adds a pointNode to the database
	 * @param p- the pointNode to add
	 */
	public void put(PointNode p) {
		//if item already in set
		_points.add(p);
	}

	/**
	 * Checks if the set contains a specific pointNode
	 * @param p- the pointNode in question
	 * @return true if in database otherwise false
	 */
	public boolean contains(PointNode p) {
		return _points.contains(p);
	}

	/**
	 * Checks if a set contains a specific set of
	 * Coordinates as a node
	 * @param x- x coordinate
	 * @param y- y coordinate
	 * @return true if in database otherwise false
	 */
	public boolean contains(double x, double y) {
		//create a point object with coordinates then compare
		//PointNode p = new PointNode(x, y)
		//compare
		return _points.contains(new PointNode(x, y));
	}

	/**
	 * Gets the name of a pointNode
	 * @param p- the desired pointNode
	 * @return the name as a string
	 */
	public String getName(PointNode p) {
		//find ACTUAL name of corresponding point
		//a(0,0)
		//DATABASE b(0,0)
		//return name b

		//CALL getName with p x and y
		return this.getName(p.getX(), p.getY());
	}

	/**
	 * Gets the name of a pointNode if it exists, otherwise creates
	 * a new pointNode and returns the default name of that point
	 * @param x- x coordinate
	 * @param y- y coordinate
	 * @return the name as a string
	 */
	public String getName(double x, double y) {
		//create a new node with inputs
		PointNode newP = new PointNode(x, y);
		//if set already contains node
		if (!(_points.contains(newP))) return newP.getName(); //if not containment

		//convert set to array and find point in array, then return point's name
		for(PointNode point:_points) {
			if (point.equals(newP)) {
				return ((PointNode) point).getName();
			}
		}
		//return default name
		return newP.getName();
	}

	/**
	 * Gets a point Node
	 * @param p- the Node to get
	 * @return the retrieved node
	 */
	public PointNode getPoint(PointNode p) {
		//find ACTUAL point obj with those coords in database
		//call getPoint(x, y)
		return this.getPoint(p.getX(), p.getY());
	}
	/**
	 * Gets a point Node with specified coordinates
	 * @param x
	 * @param y
	 * @return
	 */
	public PointNode getPoint(double x, double y) {
		//create a new node with inputs
		PointNode newP = new PointNode(x, y);
		//if set does not contain newP
		if (!(_points.contains(newP))) return newP;
		
		//convert set to array and find point in array, then return point
		for(Object point:_points.toArray()) {
			if (point.equals(newP)) {
				return (PointNode) point;
			}
		}
		//return newP.
		return newP;
	}
	
	/**
	 * Returns a string representation of the pointNodeDatabase
	 */
	@Override
	public String toString() {
		String pointString = "EMPTY";
		for (PointNode point:_points) {
			pointString = point.toString() + "\n";
		}
		return pointString;
	}
	
	public void unparse(StringBuilder sb, int level) {
		sb.append(StringUtilities.indent(level - 1));
		sb.append("Points: \n");
		sb.append(StringUtilities.indent(level - 1));
		sb.append("{\n");
		
		for (PointNode p: _points) {
			p.unparse(sb, level);
			sb.append("\n");
		}
		
		sb.append(StringUtilities.indent(level - 1));
		sb.append("}\n");
	}


}
