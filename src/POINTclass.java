import java.util.ArrayList;
import java.util.List;

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
	public Double Distance(ArrayList<Double> a, ArrayList<Double> b){
		Double dist = 0.0;
		for(int i = 0; i > a.size(); i++){
			dist = dist + (Math.sqrt(Math.pow(a.get(i),2) + Math.pow(b.get(i),2)));
		}
		return dist;
	}
	
	/** getX() 			Return X position of POINT
	 * 
	 * @return x 		The decimal value of position X
	 
	public double getX() {
		return x;
	}
	*/

	/** getY() 			Return Y position of POINT
	 * 
	 * @return y		The decimal value of position Y
	 
	public double getY() {
		return y;
	}
	*/
	
	/** toString() 		Returns the X and Y pairs as a String value
	 * 
	 * @return String	Coordinate pairs X and Y as String
	 
	public String toString(){
		return ("(" + x + "," + y + ")");
	}
	*/


	/** findClosest() 	Returns POINT closest to the object being compared
	 * 
	 * @param Objects 	ArrayList of all current POINTS to compare
	 * @calls distance
	 * @return int		The index in ArrayList of closest point
	 
	public int findClosest(ArrayList<POINTclass> Objects){
		POINTclass closest = null;
		int i = 0;
		int closestIndex = 0;
		
		for(POINTclass o : Objects){
			
			if(closest == null){
				closest = o;
				closestIndex = i;
			}
			if(distance(Objects.get(i), this) < distance(closest, this)){
				closest = o;
				closestIndex = i;
			}
			i++;
		}
		return closestIndex;
	}
	*/
	
	/** position() 		Returns Euclidean Distance of point X and Y
	 * 
	 * @return double 	Euclidean Distance of points X and Y
	 
	public double position(){
		return (this.getX()+this.getY())/2;
	}
	*/
	
	/** distance()		Returns absolute distance between two points
	 * 
	 * @param o			First POINT to compare 
	 * @param p			Second POINT to compare
	 * @calls position	
	 * @return double	Absolute distance between POINT 'o' AND POINT 'p'
	 
	public double distance(POINTclass o, POINTclass p){
		return Math.abs(o.position() - p.position());
	}
	*/
	
	/** equals()		(OVERRIDE) Determines if two POINTS are equal
	 * 
	 * @param p			POINT to check
	 * @return			TRUE if POINTS are equal, FALSE otherwise
	 
	public Boolean equals(POINTclass p){
		if(this.getX() == p.getX() && this.getY() == p.getY())
			return true;
		return false; 
	}
	*/
}

