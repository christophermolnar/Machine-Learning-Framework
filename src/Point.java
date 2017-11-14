import java.util.ArrayList;

/** Point 		Responsible for managing instances of coordinate values
 * 
 * @author MZGA
 * @version 1.0
 *
 */
public class Point extends Attribute{
	
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
	public Point(String corrds)
	{
		nums = new ArrayList<>();
		String[] entry = corrds.split(","); 
		for(int i = 0; i < entry.length; i++){
			//if (!isDouble(entry[i])) throw new IllegalArgumentException();
			nums.add(Double.valueOf(entry[i]));
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
	public ArrayList<Double> getVal()
	{
		return nums;
	}
	
	//Return value of specified index, -1.0 if not in range
	public double getValue(int i){
		if(i < this.nums.size())
			return this.nums.get(i);
		else
			return -1.0;
	}
	
	public ArrayList<Double> getNums(){
		return this.nums;
	}
	
	public String getCoords(){
		String coords = "";
		for(int i=0; i<nums.size()-1; i++){
			coords = coords + nums.get(i) + ", ";
		}
		coords = coords + nums.get(nums.size()-1);
		return coords;
	}
	
//	public void setCalc(Calculation calcType) {
//		this.distanceSelection = calcType;
//	}
	
	public Calculation getCalcType(){ //@ToDo This needs to be reworked
		return distanceSelection;
	}
	
	public double getDistance(Attribute compare){
		return distanceSelection.calculate(this, compare);
	}
	
	public String toString()
	{
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
}

