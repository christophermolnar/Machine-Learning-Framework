package junitTesting;
import junit.framework.*;
import machineLearning.*;

public class TestKeyAttribute extends TestCase{


	private Key key1;
	private Key key2;
	private Key key3;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestKeyAttribute.class);
	}
	
	public void setUp(){
		key1 = new Key("NEW");
		key2 = new Key("OLD");
		key3 = new Key("NEW");

	}
	
	public void testSize(){
		assertEquals("Distance between keys with different values should return 1", 1.0, key1.getDistance(key2));
		assertEquals("Distance between keys with same values should return 0", 0.0, key1.getDistance(key3));
	}
}
