package junitTesting;
import junit.framework.*;
import machineLearning.*;

public class TestNumAttribute extends TestCase{

	private Num num1;
	private Num num2;
	private Num num3;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestNumAttribute.class);
	}
	
	public void setUp(){
		num1 = new Num(2, new CalculationDifference());
		num2 = new Num(5, new CalculationDifference());
		num3 = new Num(-4, new CalculationDifference());

	}
	
	public void testSize(){
		assertEquals("Arithmetic distance between 2 and 5 should be 3.0", 3.0, num1.getDistance(num2));
		assertEquals("Arithmetic distance between 2 and -4 should be 6.0", 6.0, num1.getDistance(num3));
	}
}
