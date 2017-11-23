package junitTesting;
import junit.framework.*;
import machineLearning.*;

public class TestPointWithDifference extends TestCase{

	private Point coords1;
	private Point coords2;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestPointWithDifference.class);
	}
	
	public void setUp(){
		coords1 = new Point("10, 20", new CalculationDifference());
		coords2 = new Point("20, 20", new CalculationDifference());

	}
	
	public void testSize(){
		assertEquals("Difference distance between (10, 20) and (20, 20) should be 10.0", 10.0, coords1.getDistance(coords2));
	}
}
