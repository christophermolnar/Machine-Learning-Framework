import java.util.*;
public class Object {
	
	private ArrayList<Type> data;
	
	public Object ()
	{
		data = new ArrayList<>();
	}
	
	public Object (ArrayList<Type> data){
		this.data = data;
		
	}
	
	public void addType(Type t)
	{
		data.add(t);
	}

	public ArrayList<Object> findClosestK(int k, ArrayList<Object> objectList)
	{
		ArrayList<Object> closest = new ArrayList<>();
		float dist;
		for (Object o : objectList)
		{
			
		}
		return closest;
	}
}
