import java.util.ArrayList;
import java.util.Observable;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/** Project			Main class, responsible for testing
 *  
 * @author MZGA
 * @version 1.0	
 *
 */
public class Project extends Observable{
	String[] options = {"Euclidean", "Difference"};
	DefaultListModel<Object> list;
	int numOfNumbers;
	int numOfPoints;
	int numOfEnums;
	ArrayList<Calculation> pointChoice;
	ArrayList<Object> objects;
	public Project()
	{
		pointChoice = new ArrayList<>();
		list = new DefaultListModel<>();
		objects = new ArrayList<>();
	}
	public void create()
	{
		boolean isCorrect;
		do
		{
			isCorrect = true;
			try
			{
				numOfNumbers = Integer.parseInt(JOptionPane.showInputDialog("How many 'Single Number' values do you have"));
				numOfPoints = Integer.parseInt(JOptionPane.showInputDialog("How many 'Coordinate Point' values do you have"));
				numOfEnums = Integer.parseInt(JOptionPane.showInputDialog("How many 'Text' values do you have"));
				for (int i = 0; i < numOfPoints; i++)
				{
					int index = JOptionPane.showOptionDialog(null, "Please pick a comparison metric for 'Point " + (i + 1) + "'", "title", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
					if (options[index].equals("Euclidean"))
					{
						pointChoice.add(new CalculationEuclidean());
					} 
					else
					{
						pointChoice.add(new CalculationDifference());
					}
				}
			}catch (NumberFormatException e){
				isCorrect = false;
				JOptionPane.showMessageDialog(null, "Please input only integer values", "Input Error", JOptionPane.ERROR_MESSAGE);
			}
		} while (!isCorrect);
	}
	public void testing(){
		
	}
	
	public void add()
	{
		float f;
		String s = "";
		Object o = new Object();
		boolean isCorrect;
		boolean cancel;
		do
		{
			isCorrect = true;
			cancel = false;
			o = new Object();
			try
			{
				if (!cancel) {
					for (int i = 0; i < numOfNumbers; i++) {
						s = JOptionPane.showInputDialog("Please input number value");
						if (s != null) { //'OK' clicked
							f = Float.parseFloat(s);
							Num n = new Num(f);
							o.addType(n);
						} else { //'Cancel' Clicked
							cancel = true;
							break;
						}
					} 
				}
				if (!cancel) {
					for (int i = 0; i < numOfPoints; i++) {
						s = JOptionPane.showInputDialog("Please input point value");
						if (s != null) { //'OK' clicked
							Point n = new Point(s);
							n.setCalc(pointChoice.get(i));
							o.addType(n);
						} else { //'Cancel' Clicked
							cancel = true;
							break;
						}
					} 
				}
				if (!cancel) { 
					for (int i = 0; i < numOfEnums; i++) {
						s = JOptionPane.showInputDialog("Please input enum value");
						if (s == ""){ //Nothing Entered --> 'OK' clciked
							throw new Exception();
						}else if (s != null){ //Something Entered --> 'OK' clicked
							Type n = new Key(s);
							o.addType(n);
						}else{ //'Cancel' clicked
							cancel = true;
							break;
						}
					} 
				}
			} catch(Exception e)
			{
				isCorrect = false;
				JOptionPane.showMessageDialog(null, "Input is invalid", "Input Error", JOptionPane.ERROR_MESSAGE);
			}
		} while (!isCorrect);
		
		if (!cancel) { //The user has not requested to cancel, thus all dialogs have been filled
			list.addElement(o);
			objects.add(o);
			setChanged();
		}
	}
	public void calculate()
	{
		notifyObservers(objects);
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
		
		//TERMINAL OUTPUT TESTING | START
		
		//CalculationDifference attributes
		Point sqCallum = new Point("1200,300", new CalculationDifference());
		Point sqCallum2 = new Point("1000,200", new CalculationDifference());
		
		//CalculationEuclidean attributes
		Point sqGeoff = new Point("1200,300", new CalculationEuclidean());
		Point sqGeoff2 = new Point("1000,200", new CalculationEuclidean());
		
		//CalculationKey attributes
		Key k1 = new Key("OLD");
		Key k2 = new Key("NEW");
		
		//House objects
		Object H1 = new Object();
		Object H2 = new Object();
		Object H3 = new Object();
		Object H4 = new Object();
		Object H5 = new Object();
		Object H6 = new Object();
		Object H7 = new Object();
		Object H8 = new Object();
		
		H1.addType(sqCallum);
		H2.addType(sqCallum2);
		H3.addType(sqGeoff);
		H4.addType(sqGeoff2);
		H5.addType(k1);
		H6.addType(k2);
		H7.addType(sqCallum); //H7 and H8 have 3 attributes
		H7.addType(sqGeoff);
		H7.addType(k1);
		H8.addType(sqCallum2);
		H8.addType(sqGeoff2);
		H8.addType(k2);
		
		//Diff of Callums + Eucl of Geoffs
//		System.out.println("Calc Difference = " + H1.calculateScore(H2));
//		System.out.println("Calc Euclidean = " +H3.calculateScore(H4));
//		System.out.println("Calc Key = " +H5.calculateScore(H6));
//		System.out.println("Total Score = " +H7.calculateScore(H8));
		
		ArrayList<Object> lol = new ArrayList<>();
		Type o1Pt = new Point("10,10", new CalculationEuclidean());
		Type o2Pt = new Point("2,6", new CalculationEuclidean());
		Type o3Pt = new Point("3,5", new CalculationEuclidean());
		Type o4Pt = new Point("7,1", new CalculationEuclidean());
		Type o1Str = new Key("NEW");
		Type o2Str = new Key("OLD");
		Type o3Str = new Key("NEW");
		Type o4Str = new Key("OLD");
		Type o1Num = new Num(10);//10
		Type o2Num = new Num(5);
		Type o3Num = new Num(2);
		Type o4Num = new Num(7);//7
		Object o1 = new Object("o1");
		o1.addType(o1Pt);
		o1.addType(o1Str);
		o1.addType(o1Num);
		Object o2 = new Object("o2");
		o2.addType(o2Pt);
		o2.addType(o2Str);
		o2.addType(o2Num);
		Object o3 = new Object("o3");
		o3.addType(o3Pt);
		o3.addType(o3Str);
		o3.addType(o3Num);
		Object o4 = new Object("o4");
		o4.addType(o4Pt);
		o4.addType(o4Str);
		o4.addType(o4Num);
		lol.add(o1);
		lol.add(o2);
		lol.add(o3);
		lol.add(o4);
		Object[] f = o4.findClosestK(2, lol);
		for (int i = 0; i < f.length; i++)
		{
			System.out.println(f[i]);
		}
		//TERMINAL OUTPUT TESTING | END
		
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
		p.addObserver(v);
	}
}
