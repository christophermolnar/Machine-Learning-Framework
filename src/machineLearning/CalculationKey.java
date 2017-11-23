package machineLearning;


/** CalculationKey 			Calculates if two keys are equal
 * 							//IMPLEMENTS// Calculation.java
 * @author MZGA
 * @version 2.0
 *
 */
public class CalculationKey implements Calculation{

	/** calculate()			Responsible comparing 2 Key(String) Attributes from different examples
	* 
	* @param a				First Attribute to compare
	* @param b				Second Attribute to compare
	* @return distance		Distance of 0 if the Attributes are equal, 1 otherwise
	*/
	public double calculate(Attribute a, Attribute b)
	{
		if (a instanceof Key && b instanceof Key){
			Key key1 = (Key) a;
			Key key2 = (Key) b;
			if (key1.getWord().equals(key2.getWord())) {
				return 0;
			}
		}
		return 1;
		
	}
}
