import java.util.*;
public class OBJECTclass {
	ArrayList<NUMclass> nums;
	ArrayList<POINTclass> points;
	ArrayList<Age> ages;
	
	public OBJECTclass ()
	{
		nums = new ArrayList<>();
		points = new ArrayList<>();
		ages = new ArrayList<>();
	}
	
	public OBJECTclass (ArrayList<NUMclass> nums, ArrayList<POINTclass> points, ArrayList<Age> ages){
		this.nums = nums;
		this.points = points;
		this.ages = ages;
	}
	
	public void addNum(NUMclass n)
	{
		nums.add(n);
	}
	public void addPoint(POINTclass p)
	{
		points.add(p);
	}
	public void addAge(Age a)
	{
		ages.add(a);
	}
	public ArrayList<OBJECTclass> findClosestK(int k, ArrayList<OBJECTclass> objectList)
	{
		ArrayList<OBJECTclass> closest = new ArrayList<>();
		float dist;
		for (OBJECTclass o : objectList)
		{
			
		}
		return closest;
	}
}
