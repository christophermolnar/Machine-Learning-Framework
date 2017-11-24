package junitTesting;
import java.util.ArrayList;

import junit.framework.*;
import machineLearning.*;

public class TestCalculationsWithTrainingExamples extends TestCase{

	private Example house1; 
	private Example house2;
	private Key key1;
	private Key key2;
	private Point pointD1; //Difference
	private Point pointD2;
	private Point pointE1; //Euclidean
	private Point pointE2;
	private Num num1;
	private Num num2;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestCalculationsWithTrainingExamples.class);
	}
	
	public void setUp(){
		key1 = new Key("NEW");
		key2 = new Key("OLD");
		pointD1 = new Point("2,2", new CalculationDifference());
		pointD2 = new Point("4,4", new CalculationDifference());
		pointE1 = new Point("2,2", new CalculationEuclidean());
		pointE2 = new Point("4,4", new CalculationEuclidean());
		num1 = new Num(10);
		num2 = new Num(20);
		house1 = new Example();
		house2 = new Example();
		house1.addType(key1);
		house2.addType(key2);
		house1.addType(pointD1);
		house2.addType(pointD2);
		house1.addType(pointE1);
		house2.addType(pointE2);
		house1.addType(num1);
		house2.addType(num2);
	}
	
	public void testSize(){
		assertEquals("Score calculation between the same example items should return 0.0", 0.0, house1.calculateScore(house1));
		assertEquals("Score calculation between two example items should return 6.8284", 0, house1.calculateScore(house2));
	}
}
