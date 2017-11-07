import java.util.*;
public class OBJECTclass {
	
	private ArrayList<Type> data;
	
	public OBJECTclass ()
	{
		data = new ArrayList<>();
	}
	
	public OBJECTclass (ArrayList<Type> data){
		this.data = data;
		
	}
	
	public void addType(Type t)
	{
		data.add(t);
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
