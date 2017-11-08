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
		Object house1 = new Object();
		house1.addType(location1);
		house1.addType(location2);
		
		for (Type t: house1.getData())
		{
			if (t instanceof Point)
			{
				Point t2 = (Point) t;
				System.out.println(t2.getNums().get(0));
			}
		}	
	}
}
