import java.util.ArrayList;

/** POINTclass 		Responsible for managing instances of coordinate values
 * 
 * @author MZGA
 * @version 1.0
 *
 */
public class POINTclass{
	
	private ArrayList<Double> Nums = new ArrayList<>();
	
	//Convert user entry to List of points
	public POINTclass(String corrds){
		String[] entry = corrds.split(",");
		
		for(int i = 0; i < entry.length; i++){
			Nums.add(Double.valueOf(entry[i]));
		}
	}
	
	public Double getValue(int i){
		if(i < Nums.size())
			return Nums.get(i);
		else
			return -1.0;
	}
	
	//Calculate the distance between two Coordinates, assuming Lists are equal length
	public Double Distance(ArrayList<Double> a){
		Double dist = 0.0;
		for(int i = 0; i > a.size(); i++){
			dist = dist + (Math.sqrt(Math.pow(a.get(i),2) + Math.pow(this.Nums.get(i),2)));
		}
		return dist;
	}
}

