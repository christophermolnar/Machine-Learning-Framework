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
	
	//Calculate the distance between two Coordinates, assuming Lists are equal length
	public Double Distance(POINTclass compare){
		ArrayList<Double> a = compare.getNums();
		Double dist = 0.0;
		for(int i = 0; i < a.size(); i++){
			dist = dist + (Math.sqrt(Math.pow(a.get(i),2) + Math.pow(this.Nums.get(i),2)));
		}
		return round(dist,4);
	}
	
	//Rounds value to specified decimal precision (d)
	public static double round(double value, int d) {
		if (d < 0) throw new IllegalArgumentException();
	    return Math.round(value*Math.pow(10, d))/Math.pow(10, d);
	}
	
	public static void main (String args[])	{
		POINTclass test = new POINTclass("1,2,3,4,5");
		POINTclass  comp = new POINTclass("5,5,5,5,5");
		System.out.println(test.Distance(comp));
	}
}

