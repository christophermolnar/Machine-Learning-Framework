import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/** Project			Main class, responsible for testing
 *  
 * @author MZGA
 * @version 1.0	
 *
 */
public class Project {
	String[] options = {"Euclidean", "Difference"};
	DefaultListModel<Object> list;
	int numOfNumbers;
	int numOfPoints;
	int numOfEnums;
	public Project()
	{
		list = new DefaultListModel<>();
	}
	public void create()
	{
		boolean isCorrect;
		do
		{
			isCorrect = true;
			try
			{
				numOfNumbers = Integer.parseInt(JOptionPane.showInputDialog("How many number values do you have"));
				numOfPoints = Integer.parseInt(JOptionPane.showInputDialog("How many points values do you have"));
				numOfEnums = Integer.parseInt(JOptionPane.showInputDialog("How many enum values do you have"));
			}catch (NumberFormatException e){
				isCorrect = false;
				JOptionPane.showMessageDialog(null, "Please input only integer values", "Input Error", JOptionPane.ERROR_MESSAGE);
			}
		} while (!isCorrect);
	}
	public void add()
	{
		float f;
		String s = "";
		Object o = new Object();
		Calculation c;
		for (int i = 0; i < numOfNumbers; i++)
		{
			f = Float.parseFloat(JOptionPane.showInputDialog("Please input number value"));
			Num n = new Num(f);
			o.addType(n);
		}
		for (int i = 0; i < numOfPoints; i++)
		{
			s = JOptionPane.showInputDialog("Please input point value");
			int index = JOptionPane.showOptionDialog(null, "Please pick a comparison metric", "title", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
			if (options[index].equals("Euclidean"))
			{
				c = new CalculationEuclidean();
			}
			else
			{
				c = new CalculationDifference();
			}
			Point n = new Point(s);
			n.setCalc(c);
			o.addType(n);
		}
		for (int i = 0; i < numOfEnums; i++)
		{
			f = Float.parseFloat(JOptionPane.showInputDialog("Please input enum value"));
			Num n = new Num(f);
			o.addType(n);
		}
		list.addElement(o);
	}
	public DefaultListModel<Object> getList()
	{
		return list;
	}
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
		//Point location1 = new Point("12,25");
		//Point location4 = new Point("15,25");
		Num sqft1 = new Num(1200);
		Num sqft4 = new Num(1000);
		Key key1 = new Key("new");
		Key key4 = new Key("new");
		Num price1 = new Num(500000);
		Num price4 = null;
		
		Object house1 = new Object();
		//house1.addType(location1);
		house1.addType(sqft1);
		house1.addType(key1);
		house1.addType(price1);
		
		Object house4 = new Object();
		//house4.addType(location4);
		house4.addType(sqft4);
		house4.addType(key4);
		house4.addType(price4);
		
		//GEOFF BRANCH
		Point sqCallum = new Point("1200,300", new CalculationDifference());
		Point sqCallum2 = new Point("1000,200", new CalculationDifference());
		Object H1 = new Object();
		Object H2 = new Object();
		H1.addType(sqCallum);
		H2.addType(sqCallum2);
		
		System.out.println(H1.calculateScore(H2));
		
		//GEOFF BRANCH
		
		//house1.CalculateScore(house4);
		
		
//		for (Type t: house1.getData()){
//			if (t instanceof Point){
//				Point t2 = (Point) t;
//				for (int position = 0; position < t2.getNums().size(); position ++ ){
//					System.out.println("Point " + t2.getNums().get(position) + " at position " + position);
//				}
//			}	
//		}
		Project p = new Project();
		KNNView v = new KNNView(p.getList());
		KNNController c = new KNNController(v, p);
	}
}
