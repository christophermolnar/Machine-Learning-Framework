package machineLearning;

import java.io.Serializable;
import java.util.ArrayList;


/** NUMclass 				Responsible for managing instances of integer and floating point values
 * 
 * @author MZGA
 * @version 3.0
 *
 */
public class Num extends Attribute implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double num;
	
	
	//Create new NUM
	public Num(double num){
		this.num = num;
	}
	
	//Create new NUM with type of calculation specified
	public Num(double num, Calculation distanceSelection){
		this.num = num;
		this.distanceSelection = distanceSelection;
	}
	
	
	/** findClosest()			Finds the closest number given an ArrayList of numbers
	 * 
	 * @param compareList 		An ArrayList that stores all of the NUM values 
	 * @param compare			The value to compare with the ArrayList
	 * @return best_position	The position of the closest numbers
	 */
	public int findClosest(ArrayList<Num> compareList){
		double closest_distance;
		int best_position;
		double temp_distance;
		
		closest_distance = Math.abs(num - compareList.get(0).getNum());
		best_position = 0;
		for (int x = 1; x < compareList.size(); x++){
			temp_distance = Math.abs(num - compareList.get(x).getNum());
			if (temp_distance < closest_distance){
				closest_distance = temp_distance;
				best_position = x;
			}			
		}
		return best_position;
	}
	
	/** getNUM()			Return the float value of NUM
	 * 
	 * @return num			Float value of NUM
	 */
	public double getNum() {
		return num;
	}
	
	public double getVal() {
		return num;
	}
	
	public double getDistance(Attribute compare){
		return distanceSelection.calculate(this, compare);
	}
	
	
	/** toString()			Returns the num as a String value
	 * 
	 * @return String		Coordinate pairs X and Y as String
	 */
	public String toString(){
		return ("" + num + " | ");
	}
	
	public String calculateTestValue(Example[] closestK, int index) {
		double val = 0;
		int count = 0;
		for (int i = 0; i < closestK.length; i++) {
			if (!closestK[i].getIsTesting()) {
				if (!(closestK[i].getValueAtIndex(index) instanceof Key))
				{
					val += ((Num) closestK[i].getValueAtIndex(index)).getVal();
					count++;
				}
			}
		}
		val /= count;
		if (distanceSelection instanceof CalculationPolar)
		{
			val %= 180;
		}
		return "Testvalue = " + val + " ";
	}
	public String getStringVal()
	{
		return Double.toString(num);
	}
	public Attribute editedObject(String s)
	{
		return new Num(Double.parseDouble(s), distanceSelection);
	}
}
