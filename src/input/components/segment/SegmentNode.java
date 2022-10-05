/**
 * A segment node for the Geometry Fact Computer
 * 
 * <p>Bugs: 
 * 
 * @author Jace Rettig and James ???
 * @Date 9-1-22
 */
package input.components.segment;

import input.components.point.PointNode;


/**
 * A utility class only for representing ONE segment
 */
public class SegmentNode
{
	protected PointNode _point1;
	protected PointNode _point2;
	
	public PointNode getPoint1() { return _point1; }
	public PointNode getPoint2() { return _point2; }
	
	public SegmentNode(PointNode pt1, PointNode pt2)
	{
		_point1 = pt1;
		_point2 = pt2;
	}

	@Override
	public boolean equals(Object obj)
	{
		//check if obj is null
		if(obj == null) return false;
		//check if obj is an instance of PointNode
		if (!(obj instanceof SegmentNode)) return false;
		
		//cast obj to segment data type
		SegmentNode that = (SegmentNode)obj;
		//check if segment points are identical
		if (!(this._point1.equals(that._point1) || this._point1.equals(that._point2))) return false;
		if (!(this._point2.equals(that._point2) || this._point2.equals(that._point1))) return false;
		
		//must be equal
		return true;
		
	}
	
	@Override
	public String toString() {
		return _point1.getName() + _point2.getName();
	}
}