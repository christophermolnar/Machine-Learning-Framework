import java.util.ArrayList;

/** NUMclass 				Responsible for managing instances of integer and floating point values
 * 
 * @author MZGA
 * @version 1.0
 *
 */
public class NUMclass{

	private float num;
	

	public NUMclass(float num){
		this.num = num;
	}
	
	
	/** findClosest()		Finds the closest number given an ArrayList of numbers
	 * 
	 * @param compareList 	An ArrayList that stores all of the NUM values 
	 * @param compare		The value to compare with the ArrayList
	 * @return best_positionThe position of the closest numbers
	 */
	public int findClosest(ArrayList<NUMclass> compareList){
		float closest_distance;
		int best_position;
		float temp_distance;
		
		closest_distance = Math.abs(num - compareList.get(0).getNUM());
		best_position = 0;
		for (int x = 1; x < compareList.size(); x++){
			temp_distance = Math.abs(num - compareList.get(x).getNUM());
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
	public float getNUM(){
		return num;
	}
	
	
	/** toString()			Returns the num as a String value
	 * 
	 * @return String		Coordinate pairs X and Y as String
	 */
	public String toString(){
		return ("" + num + "");
	}
	
}
