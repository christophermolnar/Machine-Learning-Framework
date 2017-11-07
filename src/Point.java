import java.util.ArrayList;

/** Point 		Responsible for managing instances of coordinate values
 * 
 * @author MZGA
 * @version 1.0
 *
 */
public class Point{
	
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
	
	//Calculate the distance between two Coordinates, test if Lists are equal length
	public Double Distance(Point compare){
		ArrayList<Double> a = compare.getNums();
		if(this.getNums().size() != a.size()){
			System.out.print("Coordinate Dimensions Mismatch: ");
			return -1.0;
		}
		Double dist = 0.0;
		for(int i = 0; i < a.size(); i++){
			dist = dist + (Math.pow(a.get(i) - this.Nums.get(i), 2));
		}
		dist = Math.sqrt(dist);
		return round(dist,4);
	}
	
	//Rounds value to specified decimal precision (d)
	public static double round(double value, int d) {
		if (d < 0) throw new IllegalArgumentException();
	    return Math.round(value*Math.pow(10, d))/Math.pow(10, d);
	}
	
	public static void main (String args[])	{
		Point test = new Point("5,8,6");
		Point  comp1 = new Point("10,6,3");
		//Point  comp2 = new Point("5,5,5,5");
		System.out.println(test.Distance(comp1));
		//System.out.println(test.Distance(comp2));
	}
}

