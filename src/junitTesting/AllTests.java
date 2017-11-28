package junitTesting;
import junit.framework.*;

public class AllTests extends TestSuite {
	public static void main(String[] args) {
		junit.textui.TestRunner.run(AllTests.suite());
	}
	
	public static Test suite(){
		TestSuite suite = new TestSuite();
		suite.addTest(new TestSuite(TestPointWithEuclidean.class));
		suite.addTest(new TestSuite(TestPointWithDifference.class));
		suite.addTest(new TestSuite(TestKeyAttribute.class));
		suite.addTest(new TestSuite(TestNumAttribute.class));
		suite.addTest(new TestSuite(TestScoreCalculationsWithTrainingExamples.class));
		return suite;
	}
}
