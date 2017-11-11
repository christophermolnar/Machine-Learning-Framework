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
	
	public ArrayList<Type> getData(){
		return data;
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
	
	public double calculateScore(Object testObject){
		
		double totalScore = 0;
		int index = 0;
		for (Type t: this.getData()){
			if (t instanceof Point){
				Point pointT = (Point) t;
				totalScore += pointT.getDistance(testObject.getData().get(index));
			}
			else if (t instanceof Key){
				Key keyT = (Key) t;
				totalScore += keyT.getDistance(testObject.getData().get(index));
			}
			else{
				System.out.println("OH NO :(");
			}
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
	
}
