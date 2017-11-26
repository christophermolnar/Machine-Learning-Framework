package machineLearning;

import java.util.ArrayList;


/** Example 	Represents a physical entity with Attributes
 * 
 * @author MZGA
 * @version 3.0
 *
 */
public class Example {
	
	private ArrayList<Attribute> data;
	private boolean isTestingObject; //Checks to see if a 'unknown' varaible has been enetered into the system
	
	//Creates new EMPTY (no attribute) example
	public Example () {
		data = new ArrayList<>();
	}
	
	//Creates new example with Attibutes 'data'
	public Example (ArrayList<Attribute> data){
		this.data = data;
	}
	
	/** addType()			Add attibute to Object
	 * 
	 * @param t				Attribute to add
	 */
	public void addType(Attribute t) {
		data.add(t);
	}
	
	/** getData()				Returns Attributes of object
	 * 
	 * @return ArrayList		All attributes of a given object
	 */
	public ArrayList<Attribute> getData(){
		return data;
	}
	
	/** getValueAtIndex()		Returns the Attibute at a given index
	 * 
	 * @param index				Index of value to get
	 * @return Attribute		Attribute at given index
	 */
	public Attribute getValueAtIndex(int index) {
		return data.get(index);
	}

	/** findClosest() 			Returns list of k closest object, in order of proximity
	 * 
	 * @param k					Number of neighbors to find
	 * @param objectList		List of all current Examples (Objects with Attributes)
	 * @return	ListofExamples	List of k closest Examples
	 */
	public Example[] findClosestK(int k, ArrayList<Example> objectList) {
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
		if (k <= objectList.size())//if k is small enough
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
		return closest;
	}
	
	/** calculateScore				Returns teh score of a given Example, comparing with 'unknown' object
	 * 
	 * @param testObject			Object to compare
	 * @return Score				The deteremined score of a given example
	 * @calls getDistance			Calculates the individual score of a given attribute (Key, Point, Num)
	 * @Todo 						Add weighting
	 */
	public double calculateScore(Example testObject){
		double totalScore = 0;
		int index = 0;
		for (Attribute t: this.getData()){
			totalScore += t.getDistance(testObject.getData().get(index));
			index++;
		}
		return CalculationDifference.round(totalScore, 4);
		
	}
	
	/** toString()			Returns all attributes as a String value
	 * 
	 * @return String		Coordinate 
	 */
	public String toString() {
		String tmp = "";
		for (Attribute t : data)
		{
			tmp += t + " ";
		}
		return tmp;
	}
	
	//Sets a selected example to a testingexample
	public void setTestingObject(boolean b) {
		isTestingObject = true;
	}
	
	//Returns if testingExample
	public boolean getIsTesting() {
		return isTestingObject;
	}
}
