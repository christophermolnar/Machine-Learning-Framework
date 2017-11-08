import java.util.ArrayList;

/** Point 		Responsible for managing instances of coordinate values
 * 
 * @author MZGA
 * @version 1.0
 *
 */
public class Point extends Type{
	
	private ArrayList<Double> Nums = new ArrayList<>();
	
	//Convert user entry to List of points
	public Point(String corrds){
		String[] entry = corrds.split(",");
		
		for(int i = 0; i < entry.length; i++){
			if (!isDouble(entry[i])) throw new IllegalArgumentException();
			Nums.add(Double.valueOf(entry[i]));
		}
	}
	
	//Check if (user) String entry, is numeric
	public boolean isDouble(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	//Return value of specified index, -1.0 if not in range
	public Double getValue(int i){
		if(i < this.Nums.size())
			return this.Nums.get(i);
		else
			return -1.0;
	}
	
	public ArrayList<Double> getNums(){
		return this.Nums;
	}
}

