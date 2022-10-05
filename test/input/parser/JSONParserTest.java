package input.parser;


import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import input.components.ComponentNode;
import input.components.FigureNode;
import input.exception.ParseException;

/**
 * Test cases for parsing and unparsing JSON
 * @author Jace, Jack, and George
 * @Date 10-2-22
 *
 */
class JSONParserTest
{
	public static ComponentNode runFigureParseTest(String filename)
	{
		JSONParser parser = new JSONParser();

		String figureStr = utilities.io.FileUtilities.readFileFilterComments(filename);
		
		return parser.parse(figureStr);
	}
	
	
	@Test
	void invalid_json_string_test()
	{
		JSONParser parser = new JSONParser();
		//check empty string
		assertThrows(ParseException.class, () -> { parser.parse("{}"); });

		//check noDescription string
		String noDescription = utilities.io.FileUtilities.readFileFilterComments("JSON/NO_DESCRIPTION.json");
		ParseException exception = assertThrows(ParseException.class, () -> 
			{ parser.parse(noDescription);});
		assertEquals("Parse error: JSON Description Not Found", exception.getMessage());
		
		//check noPoints string
		String noPoints = utilities.io.FileUtilities.readFileFilterComments("JSON/NO_POINTS.json");
		exception = assertThrows(ParseException.class, () -> 
		{ parser.parse(noPoints);});
		assertEquals("Parse error: JSON Points Not Found", exception.getMessage());
		
		//check noSegments string
		String noSegments = utilities.io.FileUtilities.readFileFilterComments("JSON/NO_SEGMENTS.json");
		exception = assertThrows(ParseException.class, () -> 
		{ parser.parse(noSegments);});
		assertEquals("Parse error: JSON Segments Not Found", exception.getMessage());
	}

	@Test
	void single_triangle_test()
	{
		ComponentNode node = JSONParserTest.runFigureParseTest("JSON/single_triangle.json");

		assertTrue(node instanceof FigureNode);
		
		StringBuilder sb = new StringBuilder();
		node.unparse(sb, 0);
		System.out.println(sb.toString());
	}
	
	@Test
	void crossingSymmetricTriangleTest()
	{
		ComponentNode node = JSONParserTest.runFigureParseTest("JSON/crossing_symmetric_triangle.json");

		assertTrue(node instanceof FigureNode);
		
		StringBuilder sb = new StringBuilder();
		node.unparse(sb, 0);
		System.out.println(sb.toString());
	}
	
	
	@Test
	void FullyConnectedIrregularPolygonTest()
	{
		ComponentNode node = JSONParserTest.runFigureParseTest("JSON/fully_connected_irregular_polygon.json");

		assertTrue(node instanceof FigureNode);
		
		StringBuilder sb = new StringBuilder();
		node.unparse(sb, 0);
		System.out.println(sb.toString());
	}
	
	@Test
	void SquareTest()
	{
		ComponentNode node = JSONParserTest.runFigureParseTest("JSON/square.json");

		assertTrue(node instanceof FigureNode);
		
		StringBuilder sb = new StringBuilder();
		node.unparse(sb, 0);
		System.out.println(sb.toString());
	}
	
	
	
	@Test
	void ThirdQuadrantSquareTest()
	{
		ComponentNode node = JSONParserTest.runFigureParseTest("JSON/third_quadrant_square.json");

		assertTrue(node instanceof FigureNode);
		
		StringBuilder sb = new StringBuilder();
		node.unparse(sb, 0);
		System.out.println(sb.toString());
	}
	
	
	@Test
	void PentagonTest()
	{
		ComponentNode node = JSONParserTest.runFigureParseTest("JSON/pentagon.json");

		assertTrue(node instanceof FigureNode);
		
		StringBuilder sb = new StringBuilder();
		node.unparse(sb, 0);
		System.out.println(sb.toString());
	}
	
	
	
	
	@Test
	//TODO temp to get output from private functions
	void buildPointNodeDatabase() {
		ComponentNode node = JSONParserTest.runFigureParseTest("JSON/collinear_line_segments.json");
		node = JSONParserTest.runFigureParseTest("JSON/single_triangle.json");
	}
	
}
