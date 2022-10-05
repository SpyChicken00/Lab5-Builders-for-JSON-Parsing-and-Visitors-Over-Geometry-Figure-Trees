/**
 * A PointNode object that stores x and y coordinates and a name
 * 
 * <p>Bugs: 
 * 
 * @author Jace Rettig and James ???
 * @Date 9-1-22
 */
package input.components.point;

import input.components.ComponentNode;
import utilities.io.StringUtilities;
import utilities.math.MathUtilities;

/**
 * A 2D Point (x, y).
 */
public class PointNode implements ComponentNode
{
	protected static final String ANONYMOUS = "__UNNAMED";

	protected double _x;
	public double getX() { return this._x; }

	protected double _y; 
	public double getY() { return this._y; }

	protected String _name; 
	public String getName() { return _name; }

	/**
	 * Create a new Point with the specified coordinates.
	 * @param x The X coordinate
	 * @param y The Y coordinate
	 */
	public PointNode(double x, double y)
	{
		_x = x;
		_y = y;
		_name = ANONYMOUS;
	}

	/**
	 * Create a new Point with the specified coordinates.
	 * @param name -- The name of the point. (Assigned by the UI)
	 * @param x -- The X coordinate
	 * @param y -- The Y coordinate
	 */
	public PointNode(String name, double x, double y)
	{
		_x = x;
		_y = y;
		_name = name;
	}

	@Override
	public int hashCode()
	{
		return Double.valueOf(_x).hashCode() + Double.valueOf(_y).hashCode();
	}
	

	@Override
    public boolean equals(Object obj)
	{
		//check if obj is null
		if(obj == null) return false;
		//check if obj is an instance of PointNode
		if (!(obj instanceof PointNode)) return false;
		
		//cast obj to PointNode data type
		PointNode that = (PointNode)obj;
		if (!(MathUtilities.doubleEquals(this._x, that._x))) return false;
		if (!(MathUtilities.doubleEquals(this._y, that._y))) return false;
		
		return true;
	}

    @Override
    public String toString()
    {
    	return _name + "(" + _x + ", " + _y + ")";
	}

	@Override
	public void unparse(StringBuilder sb, int level) {
		sb.append(StringUtilities.indent(level));
		sb.append("Point(" + _name + ")(" + _x + ", " + _y + ")");
		
	}
    
    
}