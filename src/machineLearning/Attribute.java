package machineLearning;

/** Attribute 		Uses Template Pattern to provide children classes with calculation features
 * 
 * @author MZGA
 * @version 3.0
 *
 */ 
public abstract class Attribute {
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
	abstract String calculateTestValue(Example[] closestK, int index); 
	abstract String getStringVal();
}
