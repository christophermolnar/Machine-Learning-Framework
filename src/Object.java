import java.util.*;
public class Object{
	String id;
	private ArrayList<Type> data;
	
	public Object ()
	{
		data = new ArrayList<>();
	}
	public Object (String s)
	{
		data = new ArrayList<>();
		id = s;
	}
	
	public Object (ArrayList<Type> data){
		this.data = data;
		
	}
	
	public void addType(Type t)
	{
		data.add(t);
	}
	public String getId()
	{
		return id;
	}
	
	public ArrayList<Type> getData(){
		return data;
	}

	public Object[] findClosestK(int k, ArrayList<Object> objectList)
	{
		objectList.remove(this);
		Object[] closest = new Object[k];
		Object[] list = new Object[objectList.size()];
		double[] arr = new double[objectList.size()];
		for (int p = 0; p < objectList.size(); p++) 
		{
			list[p] = objectList.get(p);
			arr[p] = this.calculateScore(objectList.get(p));
		}
		if (k < objectList.size())
		{
			 for (int i = 0; i < arr.length; i++) {
			        for (int j = i + 1; j < arr.length; j++) {
			            double tmp = 0;
			            Object temp;
			            if (arr[i] > arr[j]) {
			                tmp = arr[i];
			                arr[i] = arr[j];
			                arr[j] = tmp;
			                temp = list[i];
			                list[i] = list[j];
			                list[j] = temp;
			            }
			        }
			   }
		}
		for (int z = 0; z < closest.length; z++)
		{
			closest[z] = list[z];
		}
//		Object[] closest = new Object[k];
//		float dist = 0;
//		boolean added = false;
//		for (Object o : objectList)
//		{
//			added = false;
//			if (closest[0] == null)
//			{
//				closest[0] = o;
//				added = true;
//			}
//			else if (closest[1] == null)
//			{
//				closest[1] = o;
//				added = true;
//			}
//			for (int i = 0; i < closest.length; i++)
//			{
//				if (!added)
//				{
//					
//				}
//			}
//		}
//		objectList.add(this);
		return closest;
	}
	
	public double calculateScore(Object testObject){
		
		double totalScore = 0;
		int index = 0;
		for (Type t: this.getData()){
			totalScore += t.getDistance(testObject.getData().get(index));
			index++;
		}
		return totalScore;
	}
	
	public String toString()
	{
		String tmp = "";
		for (Type t : data)
		{
			tmp += t + " ";
		}
		return tmp;
	}
	
	public double calculateTotalScore(ArrayList<Object> testObjects) {
		return 0;
	}
//	public int compareTo(Object obj)
//	{
//		System.out.println(this.id + "comparing" + obj.getId());
//		int p = (int) this.calculateScore(obj);
//		System.out.println(p);
//		return p;
//	}
	
}
