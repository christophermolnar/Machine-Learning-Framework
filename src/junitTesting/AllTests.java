package junitTesting;
import junit.framework.*;

public class AllTests extends TestSuite {
	public static void main(String[] args) {
		junit.textui.TestRunner.run(AllTests.suite());
	}
	
	public static Test suite(){
		TestSuite suite = new TestSuite();
		return suite;
	}
}
