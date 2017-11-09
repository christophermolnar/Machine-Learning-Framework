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
	
	public double CalculateScore(Object testObject){
		
		double totalScore = 0;
		
		for (int Geoff = 0; Geoff < this.data.size(); Geoff++){
			Type t = this.data.get(0);
		//for (Type t: this.getData()){
			if (t instanceof Point){
				//Point t2 = (Point) t;
				//for (int position = 0; position < t2.getNums().size(); position ++ ){
				//	System.out.println("Point " + t2.getNums().get(position) + " at position " + position);
				//}
			}
			
			else if (t instanceof Key){
				System.out.println(((Key) t).getWord());
			}
			
			else if (t instanceof Num){
				System.out.println("Num");
			}
			
			else{
				System.out.println("OH NO :(");
			}
		}
		
		return totalScore;
	}
	
}
