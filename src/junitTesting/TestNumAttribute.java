package junitTesting;
import junit.framework.*;
import machineLearning.*;

public class TestNumAttribute extends TestCase{

	private Num num1;
	private Num num2;
	private Num num3;
	private Num num4;
	private Num num5;
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
		num4 = new Num(350, new CalculationPolar());
		num5 = new Num(10, new CalculationPolar());

	}
	
	public void testSize(){
		assertEquals("Arithmetic distance between 2 and 5 should be 3.0", 3.0, num1.getDistance(num2));
		assertEquals("Arithmetic distance between 2 and -4 should be 6.0", 6.0, num1.getDistance(num3));
		assertEquals("Angle distance between 350 and 10 should be 20", 20.0, num4.getDistance(num5));
	}
}
