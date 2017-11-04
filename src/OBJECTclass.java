import java.util.*;
public class OBJECTclass {
	ArrayList<NUMclass> nums;
	ArrayList<POINTclass> points;
	ArrayList<Enum> enums;
	public OBJECTclass ()
	{
		nums = new ArrayList<>();
		points = new ArrayList<>();
		enums = new ArrayList<>();
	}
	public void addNum(NUMclass n)
	{
		nums.add(n);
	}
	public void addPoint(POINTclass p)
	{
		points.add(p);
	}
	public void addEnum(Enum e)
	{
		enums.add(e);
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
