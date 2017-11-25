package machineLearning;


/** Key 				Responsible for managing instances of String attributes
 * 
 * @author MZGA
 * @version 2.0
 *
 */
public class Key extends Attribute{
	private String word;
	
	//Create new Key
	public Key(String w)
	{
		word = w;
	}
	
	/** getWord()			get String value of Key
	 * 
	 * @return String		String value of key
	 */
	public String getWord()
	{
		return word;
	}
	
	/** toString()			yield String value of key
	 * 
	 * @return String		String value of key
	 */
	public String toString()
	{
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
	public String getVal()
	{
		return word;
	}
	
	public String calculateTestValue(Example[] closestK, int index)
	{
		return "Testvalue = " + closestK[0].getValueAtIndex(index) + " ";
	}
}
