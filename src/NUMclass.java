import java.util.ArrayList;

public class NUMclass{

	private float num;
	

	public NUMclass(float num){
		this.num = num;
	}
	
	
	/*
	 * findClosest()
	 * -Finds the closest number given an ArrayList of numbers
	 * Parameters:
	 * -compareList: An ArrayList that stores all of the NUM values 
	 * -compare: The value I want to compare with the ArrayList
	 * Return:
	 * -best_position: The position of the closest number
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
	
	
	/*
	 * getNUM()
	 * -gets the float value of NUM
	 * No Parameters
	 * Returns:
	 * -num: the float value of NUM 
	 */
	public float getNUM(){
		return num;
	}
	
	
	/*
	 * toString()
	 * -returns the num as a String value
	 * No Parameters
	 * Returns:
	 * -num as a String
	 */
	public String toString(){
		return ("" + num + "");
	}
	
}
