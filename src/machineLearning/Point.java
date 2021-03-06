package machineLearning;
import java.io.Serializable;
import java.util.ArrayList;


/** Point 		Responsible for managing instances of coordinate values
 * 
 * @author MZGA
 * @version 4.0
 *
 */
public class Point extends Attribute implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Double> nums;
	
	//Convert user entry to List of points 
	public Point(String corrds, Calculation calcType){ 
		nums = new ArrayList<>();
		String[] entry = corrds.split(","); 
		
		for(int i = 0; i < entry.length; i++){
			if (!isDouble(entry[i])) throw new IllegalArgumentException();
			nums.add(Double.valueOf(entry[i]));
		}
		distanceSelection = calcType;
	}
	
	public Point(String corrds) {
		nums = new ArrayList<>();
		String[] entry = corrds.split(","); 
		for(int i = 0; i < entry.length; i++){
			nums.add(Double.valueOf(entry[i]));
		}
	}
	
	/**	isDouble					Checks if string is number
	 * 
	 * @param str					String to be checked
	 * @return	true/false			returns true if String is number
	 */
	public boolean isDouble(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	/** getVal()					Getter for nums
	 * 
	 * @return nums					Array of nums
	 */
	public ArrayList<Double> getVal() {
		return nums;
	}
	
	/** getValue()					Returns the value at a specified index or -1.0 if not in range
	 * 
	 * @param i						The index that you want the value for
	 * 
	 * @return numValue				The value of num at i
	 */
	public double getValue(int i){
		if(i < this.nums.size())
			return this.nums.get(i);
		else
			return -1.0;
	}
	
	/** getNums()					Get the ArrayList of Points
	 * 
	 * @return numArrayList			Return the ArrayList of Points
	 */
	public ArrayList<Double> getNums(){
		return this.nums;
	}
	
	/** getCoords()v				Gets String of coordinates of point
	 * 
	 * @return coords				String representation of coordinates
	 */
	public String getCoords(){
		String coords = "";
		for(int i=0; i<nums.size()-1; i++){
			coords = coords + nums.get(i) + ", ";
		}
		coords = coords + nums.get(nums.size()-1);
		return coords;
	}
	public String getStringVal()
	{
		return this.getCoords();
	}
	
	/** getCalcType					Getter for calcType
	 * 
	 * @return distanceSelection	Type of distance metric (euclidean, difference)
	 */
	public Calculation getCalcType(){ //@ToDo This needs to be reworked
		return distanceSelection;
	}
	
	/** getDistance				Calculates distance between two points using chosen metric
	 * 
	 * 
	 * @return distance			distance between points
	 */
	public double getDistance(Attribute compare){
		return distanceSelection.calculate(this, compare);
	}
	
	/** toString()			Returns the point as a String value
	 * 
	 * @return String		point as a String value
	 */
	public String toString() {
		String tmp = "(";
		int index = 0;
		for (Double d : nums)
		{
			tmp += d;
			if ((index + 1) != nums.size())
			{
				tmp += ", ";
			}
			index++;
		}
		tmp += ")";
		return tmp +  "->" + distanceSelection + " | ";
	}
	
	/** getStringVal()				Returns the String value of the Point
	 * 
	 * @return AttributeValue		The String value of the Point
	 */
	public String calculateTestValue(Example[] closestK, int index) {
		String result = ""; 
		double pointCount = 0;
		double[] listOfPointValues = new double[((Point) this).getNums().size()];
		for (int i = 0; i < closestK.length; i++) {
			for (int j = 0; j < listOfPointValues.length; j++) {
				listOfPointValues[j] += ((Point) closestK[i].getValueAtIndex(index)).getNums().get(j);
			}
			pointCount++;
		}
		for (int i = 0; i < listOfPointValues.length; i++) {
			listOfPointValues[i] /= pointCount;
		}
		result += "Testvalue = (";
		for (int i = 0; i < listOfPointValues.length; i++) {
			result += listOfPointValues[i];
			if (i < listOfPointValues.length - 1) result += ", ";
		}
		result += ")";
		return result;
	}
	
	/** editedObject()				Changes an old Point to a new value
	 * 
	 * @param s						The value of the new Point		
	 * 
	 * @return newPoint			The new Point with its new value
	 */
	public Attribute editedObject(String s)
	{
		return new Point(s, distanceSelection);
	}
}

