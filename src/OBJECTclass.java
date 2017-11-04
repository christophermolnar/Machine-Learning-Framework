import java.util.*;
public class OBJECTclass {
	ArrayList<NUMclass> nums;
	ArrayList<POINTclass> points;
	Enum enu;
	public OBJECTclass ()
	{
		nums = new ArrayList<>();
		points = new ArrayList<>();
	}
	public void addNum(NUMclass n)
	{
		nums.add(n);
	}
	public void addPoint(POINTclass p)
	{
		points.add(p);
	}
	public void setEnum(Enum enu)
	{
		this.enu = enu;
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
