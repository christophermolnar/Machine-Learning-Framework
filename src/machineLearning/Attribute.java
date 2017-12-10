package machineLearning;

import java.io.Serializable;

/** Attribute 		Uses Template Pattern to provide children classes with calculation features
 * 
 * @author MZGA
 * @version 4.0
 *
 */ 
public abstract class Attribute implements Serializable{

	private static final long serialVersionUID = 1L;
	//Type of calculation to use when comparing, either 'Euclidean' or 'Difference'
	protected Calculation distanceSelection;
	
	/** setSelection()				Sets the calculation method for a given child class, is passed Calculation type �d� 
	 * 
	 * @param d						Calculation type to use, either 'Euclidean' or 'Difference'
	 */
	public void setSelection(Calculation d) {
		distanceSelection = d;
	}
	
	/** getSelection()				Returns an Attributes Calculation type
	 * 
	 * @return	distanceSelection	CalcType, either 'Euclidean' or 'Difference'
	 */
	public Calculation getSelection() {
		return distanceSelection;
	}
	
	/** getDistance()				Compares the numerical distance between 2 Attributes
	 * 								Abstract Method, implementation through child classes
	 * 
	 * @param compare				Attribute to compare against, compares with attribute which calls fcn
	 * @return distance				Numerical Distance value between 2 Attributes
	 */
	abstract double getDistance(Attribute compare);
	
	/** calculateTestValue()		Calculate the value of the testvalue located in the closestsK array
	 * 								At the given index
	 * 
	 * @param closestK				The array with the testvalue in it	
	 * @param index					The index of the testvalue
	 * 
	 * @return calculatedTestValue	The calculated testvalue
	 */
	abstract String calculateTestValue(Example[] closestK, int index); 
	
	/** getStringVal()				Returns the String value of the Attribute
	 * 
	 * @return AttributeValue		The String value of the attribute
	 */
	abstract String getStringVal();
	
	/** editedObject()				Changes an old Attribute to a new value
	 * 
	 * @param s						The value of the new Attribute		
	 * 
	 * @return newAttribute			The new Attribute with its new value
	 */
	abstract Attribute editedObject(String s);
}
