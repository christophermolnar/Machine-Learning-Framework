import java.util.ArrayList;

public class NUMclass implements Attributes {

	private float num;
	
	public NUMclass(float num){
		this.num = num;
	}
	
	public int CompareTo(ArrayList<NUMclass> compareList, float compare ){
		float closest_distance;
		int best_position;
		float temp_distance;
		
		closest_distance = Math.abs(compare - compareList.get(0).getNUM());
		best_position = 0;
		for (int x = 1; x < compareList.size(); x++){
			temp_distance = Math.abs(compare - compareList.get(x).getNUM());
			if (temp_distance < closest_distance){
				closest_distance = temp_distance;
				best_position = x;
			}			
		}
		
		return best_position;
	}
	
	public float getNUM(){
		return num;
	}
	
	public String toString(){
		return ("" + num + "");
	}
	
}
