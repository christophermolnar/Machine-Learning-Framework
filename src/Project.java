import java.util.ArrayList;

/** Project			Main class, responsible for testing
 *  
 * @author MZGA
 * @version 1.0	
 *
 */
public class Project {

	/** Main()		Test program
	 * 
	 * @case1		Create a list of houses with various attributes, predict the missing attribute of 'h4'
	 * 				(price) by comparing it's known attributes with the attributes of the other houses
	 * 
	 * @case2		Create a catalogue of video games with various attributes, predict the missing value
	 * 				of 'testVideoGame' by comparing known attributes with other Video Game attributes
	 */
	
	public static void main (String args[])
	{
		Point location1 = new Point("2,1");
		Point location2 = new Point("2,3");
		Key key1 = new Key("Geoff");
		Object house1 = new Object();
		house1.addType(location1);
		house1.addType(location2);
		house1.addType(key1);
		
		for (Type t: house1.getData()){
			if (t instanceof Point){
				Point t2 = (Point) t;
				for (int position = 0; position < t2.getNums().size(); position ++ ){
					System.out.println("Point " + t2.getNums().get(position) + " at position " + position);
				}
			}
			
			
		}	
	}
}
