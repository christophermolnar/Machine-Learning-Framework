package machineLearning;


import java.util.ArrayList;


/** CalculationEuclidean 	Calculates the difference between Two Attributes using their euclidean distances
 * 							//IMPLEMENTS// Calculation.java
 * @author MZGA
 * @version 3.0
 *
 */

public class CalculationEuclidean implements Calculation{
	
	//Calculate the distance between two Coordinates, test if Lists are equal length
	/** calculate()			Responsible comparing 2 Attributes (Of same type [ie. Points]) from different examples
	 * 
	 * @param a				First Attribute to compare
	 * @param b				Second Attribute to compare
	 * @return distance		Difference between 2 Attributes
	 */
	public double calculate(Attribute a, Attribute b){
		if (a instanceof Point && b instanceof Point){
			ArrayList<Double> A = ((Point)a).getNums();
			ArrayList<Double> B = ((Point)b).getNums();
			
			if(B.size() != A.size()){ //Check if Coordinates are of equal size, exit if false
				System.out.print("Coordinate Dimensions Mismatch: ");
				return -2.0; //Caught by caller
			
			} else { //Calculate the distance between two points by taking their calculated distance
				Double dist = 0.0;
				for(int i = 0; i < A.size(); i++){
					dist = dist + (Math.pow(A.get(i) - B.get(i), 2));
				}
				dist = Math.sqrt(dist);
				return round(dist, 4);
			}
		}
		return -1.0; //Not known type
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
	
	/** toString()			Returns the current Calculation type of 'Euclidean'
	 *  @return CalcType	Returns 'Differnce' --> Current CalcType
	 */
	public String toString() {
		return "Euclidean";
	}
}
