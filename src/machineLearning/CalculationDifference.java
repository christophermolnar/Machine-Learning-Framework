package machineLearning;


import java.util.ArrayList;


/** CalculationDifference 	Calculates the difference between Two Attributes using their absolute phsyical distances
 * 							//IMPLEMENTS// Calculation.java
 * @author MZGA
 * @version 2.0
 *
 */
public class CalculationDifference implements Calculation{

	/** calculate()			Responsible comparing 2 Attributes (Of same type [ie. Points]) from different examples
	 * 
	 * @param a				First Attribute to compare
	 * @param b				Second Attribute to compare
	 * @return distance		Difference between 2 Attributes
	 */
	public double calculate(Attribute a, Attribute b) {
		if (a instanceof Point && b instanceof Point){
			ArrayList<Double> A = ((Point)a).getNums();
			ArrayList<Double> B = ((Point)b).getNums();
			
			if(B.size() != A.size()){ //Check if Coordinates are of equal size
				System.out.print("Coordinate Dimensions Mismatch: ");
				return -2.0; //Caught by caller
			
			} 
			else { //Calculate the distance between two points by taking their absolute realitive distance
				Double dist = 0.0;
				for(int i = 0; i < A.size(); i++){
					dist = dist + Math.abs((A.get(i) - B.get(i)));
				}
				return round(dist, 4);
			}
		}
		else if (a instanceof Num && b instanceof Num){
			Double dist = 0.0;
			Num A = ((Num) a);
			Num B = ((Num) b);
			dist = Math.abs(A.getNum() - B.getNum());
			return round(dist, 4);
		}
		return -1.0; //Not a known type, catching method will prompt error
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
	
	/** toString()			Returns the current Calculation type of 'Difference'
	 *  @return CalcType	Returns 'Differnce' --> Current CalcType
	 */
	public String toString() {
		return "Difference";
	}
}
