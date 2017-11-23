package junitTesting;
import junit.framework.*;

public class TestExampleWithEuclidean extends TestCase{
	private Example house1 = new Example();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestExampleWithEuclidean.class);
	}
}
