package machineLearning;

/** CalculationPolar		Calculates the difference between two angles
 * 							//IMPLEMENTS// Calculation.java
 * @author MZGA
 * @version 3.0
 *
 */
public class CalculationPolar implements Calculation {

	
	public double calculate(Attribute a, Attribute b) {
		double distance;
		if (a instanceof Num && b instanceof Num){
			
			Num numberA = ((Num) a);
			Num numberB = ((Num) b);
			
			double numberValueA = convertToPolar(numberA);
			double numberValueB = convertToPolar(numberB);
			
			//Return the minimum distance between two angles
			distance = Math.min(Math.abs(numberValueA - numberValueB), Math.abs(360- (numberValueA - numberValueB)));
			return round(distance, 4);
		}
		
		return -1; //Not a known type OR Type Mismatch, catching method will prompt error
	}
	
	/** isPolar					Determines if a passed number is wihtin polar retrictions or not
	 * 
	 * @param currentNumber		Number to test
	 * @return 					true if within bounds of 0 and 359, false otherwise
	 */
	private boolean isPolar(double currentNumber){
		if (0 <= currentNumber && currentNumber <= 359){
			return true;
		}
		return false;
	}
	
	/** convertToPolar			Converts a given number to polar coordinates (within the bounds of 0 and 360) 
	 * 
	 * @param currentNum		Number to convert to polar cords
	 * @return polarNum			Result of conversion
	 */
	private double convertToPolar(Num currentNum){
		double currentNumberValue = currentNum.getNum();
		while (!isPolar(currentNumberValue)){
			if (currentNumberValue < 0){
				currentNumberValue += 360;
			}
			else if (currentNumberValue > 359){
				currentNumberValue -= 360;
			}
		}
		return currentNumberValue;
	}
	
	/** round()				Rounds a passed numerical value to a specified precision (d)
	 * 
	 * @param value			Value to be rounded
	 * @param d				Precision to round to, checks if less that 0
	 * @return roundVal		Rounded Value
	 */
	public static double round(double value, int d) {
		if (d < 0) throw new IllegalArgumentException();
	    return Math.round(value*Math.pow(10, d))/Math.pow(10, d);
	}

	/** toString()			Returns the current Calculation type of 'Polar'
	 *  @return NumType		Returns 'Polar' --> Current Numeric-Type
	 */
	public String toString() {
		return "Polar";
	}
	
	
}
