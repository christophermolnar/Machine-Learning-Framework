package junitTesting;
import java.io.IOException;

import machineLearning.*;
import junit.framework.TestCase;

public class TestImportExport extends TestCase{
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
	KNNModel model;
	KNNModel importedModel;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestImportExport.class);
	}
	
	public void setUp() throws IOException, ClassNotFoundException{
		key1 = new Key("NEW");
		key2 = new Key("OLD");
		pointD1 = new Point("2,2", new CalculationDifference());
		pointD2 = new Point("4,4", new CalculationDifference());
		pointE1 = new Point("2,2", new CalculationEuclidean());
		pointE2 = new Point("4,4", new CalculationEuclidean());
		num1 = new Num(10, new CalculationDifference());
		num2 = new Num(20, new CalculationDifference());
		house1 = new Example();
		house2 = new Example();
		house1.addAttribute(key1);
		house2.addAttribute(key2);
		house1.addAttribute(pointD1);
		house2.addAttribute(pointD2);
		house1.addAttribute(pointE1);
		house2.addAttribute(pointE2);
		house1.addAttribute(num1);
		house2.addAttribute(num2);
		model = new KNNModel();
		model.addExample(house1);
		model.addExample(house2);
		model.exportKNNModel("junitTest.ser");
		importedModel = model.importKNNModel("junitTest.ser");
	}
	
	public void testSize(){
		assertEquals("Exporting and importing a KNNModel should yield a KNNModel", model instanceof KNNModel, importedModel instanceof KNNModel);
	}
}
