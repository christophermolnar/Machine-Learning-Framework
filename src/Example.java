import java.util.*;
public class Example{
	private ArrayList<Attribute> data;
	private boolean isTestingObject;
	public Example ()
	{
		data = new ArrayList<>();
	}
	
	public Example (ArrayList<Attribute> data){
		this.data = data;
		
	}
	
	public void addType(Attribute t)
	{
		data.add(t);
	}
	
	public ArrayList<Attribute> getData(){
		return data;
	}
	public Attribute getValueAtIndex(int index)
	{
		return data.get(index);
	}

	public Example[] findClosestK(int k, ArrayList<Example> objectList)
	{
		objectList.remove(this);
		Example[] closest = new Example[k];
		Example[] list = new Example[objectList.size()];
		double[] arr = new double[objectList.size()];
		for (int p = 0; p < objectList.size(); p++) 
		{
			list[p] = objectList.get(p);
			arr[p] = this.calculateScore(objectList.get(p));
			System.out.println(list[p] + "score" + arr[p]);
		}
		if (k <= objectList.size())
		{
			 for (int i = 0; i < arr.length; i++) {
			        for (int j = i + 1; j < arr.length; j++) {
			            double tmp = 0;
			            Example temp;
			            System.out.println("asfasf");
			            if (arr[i] > arr[j]) {
			            	System.out.println("arri" + arr[i] + "arrj" + arr[j]);
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
			System.out.println(closest[z]);
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
	
	public double calculateScore(Example testObject){
		
		double totalScore = 0;
		int index = 0;
		for (Attribute t: this.getData()){
			totalScore += t.getDistance(testObject.getData().get(index));
			index++;
		}
		System.out.println(totalScore);
		return totalScore;
	}
	
	public String toString()
	{
		String tmp = "";
		for (Attribute t : data)
		{
			tmp += t + " ";
		}
		return tmp;
	}
	
	public double calculateTotalScore(ArrayList<Example> testObjects) {
		return 0;
	}
	public void setTestingObject(boolean b)
	{
		isTestingObject = true;
	}
	public boolean getIsTesting()
	{
		return isTestingObject;
	}
//	public int compareTo(Object obj)
//	{
//		System.out.println(this.id + "comparing" + obj.getId());
//		int p = (int) this.calculateScore(obj);
//		System.out.println(p);
//		return p;
//	}
	
}
