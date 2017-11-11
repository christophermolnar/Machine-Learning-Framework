import java.util.ArrayList;

/** Point 		Responsible for managing instances of coordinate values
 * 
 * @author MZGA
 * @version 1.0
 *
 */
public class Point extends Type{
	
	private ArrayList<Double> nums;
	private Calculation calcType;
	
	//Convert user entry to List of points 
	public Point(String corrds, Calculation calcType){ 
		nums = new ArrayList<>();
		String[] entry = corrds.split(","); 
		
		for(int i = 0; i < entry.length; i++){
			if (!isDouble(entry[i])) throw new IllegalArgumentException();
			nums.add(Double.valueOf(entry[i]));
		}
		this.calcType = calcType;
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
	
	//Return value of specified index, -1.0 if not in range
	public Double getValue(int i){
		if(i < this.nums.size())
			return this.nums.get(i);
		else
			return -1.0;
	}
	
	public ArrayList<Double> getNums(){
		return this.nums;
	}
	
	public void setCalc(Calculation calcType) {
		distanceSelection = calcType;
	}
	
	public Calculation getCalcType() {
		return this.calcType;
	}
	
	public Double getDistance(Type compare){
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
		return tmp +  "->" + distanceSelection + "| ";
	}
}

