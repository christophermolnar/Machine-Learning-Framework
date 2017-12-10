package machineLearning;

import java.io.Serializable;

/** Key 				Responsible for managing instances of String attributes
 * 
 * @author MZGA
 * @version 4.0
 *
 */
public class Key extends Attribute implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String word;
	
	//Create new Key
	public Key(String w) {
		word = w;
	}
	
	/** getWord()			get String value of Key
	 * 
	 * @return String		String value of key
	 */
	public String getWord() {
		return word;
	}
	
	/** toString()			yield String value of key
	 * 
	 * @return String		String value of key
	 */
	public String toString() {
		return (word + " | ");
	}
	
	/** getDistance()			Gets distance between 2 keys (0 or 1)
	 * 
	 * @param compare			The key value to be compared to
	 * @return distance			0 if same Key value, 1 if different
	 */
	public double getDistance(Attribute compare){
		return (new CalculationKey()).calculate(this, compare);
	}
	
	/** getVal()				Gets String value of Key
	 * 
	 * @return value			String value of Key					
	 */
	public String getVal() {
		return word;
	}
	
	/** calculateTestValue()		Calculate the value of the testvalue located in the closestsK array
	 * 								At the given index
	 * 
	 * @param closestK				The array with the testvalue in it	
	 * @param index					The index of the testvalue
	 * 
	 * @return calculatedTestValue	The calculated testvalue
	 */
	public String calculateTestValue(Example[] closestK, int index) {
		return "Testvalue = " + closestK[0].getValueAtIndex(index) + " ";
	}
	
	/** getStringVal()				Returns the String value Key
	 * 
	 * @return StringKeyValue		The String value of the Key
	 */
	public String getStringVal() {
		return word;
	}
	
	/** editedObject()				Changes an old Key to a new value
	 * 
	 * @param s						The value of the new Key	
	 * 
	 * @return newKey				The new Key with its new value
	 */
	public Attribute editedObject(String s)	{
		return new Key(s);
	}
}
