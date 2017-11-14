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
	int numOfNumbers, numOfPoints, numOfEnums;
	int tempNum, tempPoint, tempEnum;
	int indexOfTestValue;
	
	ArrayList<Calculation> pointChoice;
	ArrayList<Object> objects;
	
	Object testObject;
	String TESTVALUE = "testvalue";
	
	public Project()
	{
		pointChoice = new ArrayList<>();
		list = new DefaultListModel<>();
		objects = new ArrayList<>();
	}
	
	public void create(){
		boolean isCorrect;
		String s = "";
		
		outerloop:
		do {
			isCorrect = true;
			try {
				
				s = JOptionPane.showInputDialog("How many 'Single Number' values do you have");
				if(s != null){ //'OK' clicked
					tempNum = Integer.parseInt(s);
				}
				else { //'Cancel' Clicked	
					isCorrect = false;
					break outerloop;
				}
				
				s = JOptionPane.showInputDialog("How many 'Coordinate Point' values do you have");
				if(s != null){ //'OK' clicked
					tempPoint = Integer.parseInt(s);
				} 
				else { //'Cancel' Clicked
					isCorrect = false;
					break outerloop;
				}
				
				s = JOptionPane.showInputDialog("How many 'Text' values do you have");
				if(s != null){ //'OK' clicked
					tempEnum = Integer.parseInt(s);
				} 
				else { //'Cancel' Clicked
					isCorrect = false;
					break outerloop;
				}
				
				for (int i = 0; i < tempPoint; i++) {
					int index = JOptionPane.showOptionDialog(null, "Please pick a comparison metric for 'Point " + (i + 1) + "'", "title", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
					if (index != -1){
						if (options[index].equals("Euclidean")) {
							pointChoice.add(new CalculationEuclidean());
						} 
						else if (options[index].equals("Difference")){
							pointChoice.add(new CalculationDifference());
						}
					}
					else {
						//pointChoice.add(new CalculationDifference());
						isCorrect = false;
						break outerloop;
					}
				}
				if(isCorrect){
					numOfNumbers=tempNum;
					numOfPoints=tempPoint;
					numOfEnums=tempEnum;
				}
			}catch (NumberFormatException e){
				isCorrect = false;
				JOptionPane.showMessageDialog(null, "Please input only integer values", "Input Error", JOptionPane.ERROR_MESSAGE);
			}
		} while (!isCorrect);
		
		if (isCorrect){
			setChanged();
			notifyObservers("create");
		}
	}
	
	private boolean isTestValue(String s){
		return (s.toLowerCase().compareTo(TESTVALUE) == 0);
	}
	
	public void testing(){
		double d;
		String s = "";
		Object o;
		boolean isCorrect;
		boolean testvalueSet = false;
		indexOfTestValue = 0;
		int counter = 0;
		outerloop:
		do { 
			isCorrect = true;
			o = new Object();
			try {
				for (int i = 0; i < numOfNumbers; i++) {
					s = JOptionPane.showInputDialog("Please input number value");
					if (s != null) { //'OK' clicked
						if (isTestValue(s) && !testvalueSet){
							Type n = new Key(s.toLowerCase());
							o.addType(n);
							testvalueSet = true;
							indexOfTestValue = counter;
						}
						else{
							counter++;
							d = Double.parseDouble(s);
							Num n = new Num(d);
							o.addType(n);
						}
					}
					else { //'Cancel' Clicked
						break outerloop;
					}
				} 

				for (int i = 0; i < numOfPoints; i++) {
					s = JOptionPane.showInputDialog("Please input point value");
					
					if (s != null) { //'OK' clicked
						if (isTestValue(s) && !testvalueSet){
							Type n = new Key(s.toLowerCase());
							o.addType(n);
							testvalueSet = true;
							indexOfTestValue = counter;
						}
						else{
							counter++;
							Point n = new Point(s);
							n.setSelection(pointChoice.get(i));
							o.addType(n);
						};
					} else { //'Cancel' Clicked
						break outerloop;
					}
				} 

				for (int i = 0; i < numOfEnums; i++) {
					s = JOptionPane.showInputDialog("Please input enum value");
					if (s == ""){ //Nothing Entered --> 'OK' clicked
						throw new Exception();
					}
					else if (s != null){ //Something Entered --> 'OK' clicked
						if (isTestValue(s) && !testvalueSet){
							Type n = new Key(s.toLowerCase());
							o.addType(n);
							testvalueSet = true;
							indexOfTestValue = counter;
						}
						else{
							counter++;
							Type n = new Key(s);
							o.addType(n);
						}
					}
					else{ //'Cancel' clicked
						break outerloop;
					}
				} 

			} 
			catch(Exception e){
				isCorrect = false;
				JOptionPane.showMessageDialog(null, "Input is invalid", "Input Error", JOptionPane.ERROR_MESSAGE);
			}	
		} while (!isCorrect);
		
		if (testvalueSet) { //The user has not requested to cancel, thus all dialogs have been filled
			//testObjet set to o
			list.addElement(o);
			objects.add(o);
			o.setTestingObject(true);
			setChanged();
			notifyObservers("testing");
			//DONT ALLOW THEM TO ADD ANY MORE TESTING EXAMPLES
		}
		else{
			JOptionPane.showMessageDialog(null, "No Attribute was set to testvalue", "Testvalue not Set", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void training() {
		double d;
		String s = "";
		Object o = new Object();
		boolean isCorrect;
		
		outerloop:
		do { 
			isCorrect = true;
			o = new Object();
			try {
				for (int i = 0; i < numOfNumbers; i++) {
					s = JOptionPane.showInputDialog("Please input number value");
					if (s != null) { //'OK' clicked
						d = Double.parseDouble(s);
						Num n = new Num(d);
						o.addType(n);
					} 
					else { //'Cancel' Clicked
						isCorrect = false;
						break outerloop;
					}
				} 
				for (int i = 0; i < numOfPoints; i++) {
					s = JOptionPane.showInputDialog("Please input 'Coordinate Point' value");
					if (s != null) { //'OK' clicked
						Point n = new Point(s.toString());
						n.setSelection(pointChoice.get(i));
						o.addType(n);
					} 
					else { //'Cancel' Clicked
						isCorrect = false;
						break outerloop;
					}
				} 
				for (int i = 0; i < numOfEnums; i++) {
					s = JOptionPane.showInputDialog("Please input 'Text' value");
					if (s == ""){ //Nothing Entered --> 'OK' clicked
						throw new Exception();
					}
					else if (s != null){ //Something Entered --> 'OK' clicked
						Type n = new Key(s);
						o.addType(n);
					}
					else{ //'Cancel' clicked
						isCorrect = false;
						break outerloop;
					}
				} 
			} 
			catch(Exception e){
				isCorrect = false;
				JOptionPane.showMessageDialog(null, "Input is invalid", "Input Error", JOptionPane.ERROR_MESSAGE);
			}
		} while (!isCorrect);
		
		if (isCorrect) { //The user has not requested to cancel, thus all dialogs have been filled
			list.addElement(o);
			objects.add(o);
			setChanged();
			notifyObservers("training");
		}
	}
	
	public void edit(Object editable, int Index){
		String s = "";
		String curr = "";
		Object updatedData = new Object();
		ArrayList<Type> currentData = editable.getData();
			
		boolean isCorrect;
			
		outerloop:
		do { 
			isCorrect = true;
				for(int index=0; index<currentData.size(); index++){
					try {
						if (currentData.get(index) instanceof Num){
							curr = Double.toString((((Num) currentData.get(index)).getNum()));
							s = JOptionPane.showInputDialog("Modify the selected value?", curr);
							updatedData.addType(new Num(Double.parseDouble(s)));
							
						}else if(currentData.get(index) instanceof Point){
							curr = (((Point) currentData.get(index)).getCoords());
							s = JOptionPane.showInputDialog("Modify the selected value?", curr);
							updatedData.addType(new Point(s, ((Point) currentData.get(index)).getCalcType()));
						
						} else { //instanceof Key
							curr = ((Key)currentData.get(index)).getWord();
							s = JOptionPane.showInputDialog("Modify the selected value?", curr);
							updatedData.addType(new Key(s));
						}
						
					}catch(Exception e){
							isCorrect = false;
							JOptionPane.showMessageDialog(null, "Input is invalid", "Input Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			
		} while (!isCorrect);
		
		if (isCorrect) { //The user has not requested to cancel, thus all dialogs have been filled
			list.remove(Index);
			list.add(Index, updatedData);
			objects.remove(Index);
			objects.add(Index, updatedData);
			setChanged();
		}
	}
	
	public void calculate()
	{
		try
		{
			Object testing = null;
			String s = "";
			Object[] closestK = null;
			Type t;
			int n = Integer.parseInt(JOptionPane.showInputDialog("Please input amount of nearest neighbours"));
			double val = 0;
			for (Object o : objects)
			{
				if (o.getIsTesting())
				{
					closestK = o.findClosestK(n, objects);
					break;
				}
			}
			for (Object o : objects)
			{
				if (!o.getIsTesting())
				{
					testing = o;
				}
			}
			if (testing != null && closestK != null)
			{
				//if all values are correct
				t = testing.getValueAtIndex(indexOfTestValue);
				if (t instanceof Num)
				{
					for (int i = 0; i < closestK.length; i++)
					{
						if (!closestK[i].getIsTesting())
						{
							val += ((Num) closestK[i].getValueAtIndex(indexOfTestValue)).getVal();
						}
					}
					val /= closestK.length;
					s += "Testvalue = " + val + " ";
				}
				else if (t instanceof Point)
				{
					//CALCULATION FOR POINT TEST VALUE
					double pointCount = 0;
					double[] listOfPointValues = new double[((Point) t).getNums().size()];
					for (int i = 0; i < closestK.length; i++)
					{
						for (int j = 0; j < listOfPointValues.length; j++)
						{
							listOfPointValues[j] += ((Point) closestK[i].getValueAtIndex(indexOfTestValue)).getNums().get(j);
						}
						pointCount++;
					}
					for (int i = 0; i < listOfPointValues.length; i++)
					{
						listOfPointValues[i] /= pointCount;
					}
					s += "Testvalue = (";
					for (int i = 0; i < listOfPointValues.length; i++)
					{
						s += listOfPointValues[i];
						if (i < listOfPointValues.length - 1) s += ", ";
					}
					s += ")";
				}
				else
				{
					//CALCULATION FOR KEY TEST VALUE
					s += "Testvalue = " + closestK[0].getValueAtIndex(indexOfTestValue) + " ";
				}
				s += "Closest Objects:"; 
				for (int i = 0; i < closestK.length; i++)
				{
					s += closestK[i];
					if (i < closestK.length - 1) s += ", ";
				}
				setChanged();
				notifyObservers(s);
			}
		} catch(NumberFormatException e){	
		}
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
		Object o1 = new Object();
		o1.addType(o1Pt);
		o1.addType(o1Str);
		o1.addType(o1Num);
		Object o2 = new Object();
		o2.addType(o2Pt);
		o2.addType(o2Str);
		o2.addType(o2Num);
		Object o3 = new Object();
		o3.addType(o3Pt);
		o3.addType(o3Str);
		o3.addType(o3Num);
		Object o4 = new Object();
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
	}
}
