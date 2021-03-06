package machineLearning;
import java.io.Serializable;

/** Calculation 	//INTERFACE// Uses Strategy Pattern to provide multiple methods to compare
 * 
 * @author MZGA
 * @version 4.0
 *
 */
public interface Calculation extends Serializable{
	
	/** calculate()			Responsible comparing 2 Attributes (Of same type [ie. Points]) from different objects
	 * 
	 * @param a				First Attribute to compare
	 * @param b				Second Attribute to compare
	 * @return				Difference between 2 Attributes
	 */
	public double calculate(Attribute a, Attribute b);
}

