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
	DefaultListModel<Example> list;
	int numOfNumbers, numOfPoints, numOfEnums;
	int tempNum, tempPoint, tempEnum;
	int indexOfTestValue;
	
	ArrayList<Calculation> pointChoice;
	ArrayList<Example> examples;
	
	Example testObject;
	String TESTVALUE = "testvalue";
	
	public Project()
	{
		pointChoice = new ArrayList<>();
		list = new DefaultListModel<>();
		examples = new ArrayList<>();
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
		Example o;
		boolean isCorrect;
		boolean testvalueSet = false;
		indexOfTestValue = 0;
		int counter = 0;
		outerloop:
		do { 
			isCorrect = true;
			o = new Example();
			try {
				for (int i = 0; i < numOfNumbers; i++) {
					s = JOptionPane.showInputDialog("Please input number value");
					if (s != null) { //'OK' clicked
						if (isTestValue(s) && !testvalueSet){
							Attribute n = new Key(s.toLowerCase());
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
							Attribute n = new Key(s.toLowerCase());
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
							Attribute n = new Key(s.toLowerCase());
							o.addType(n);
							testvalueSet = true;
							indexOfTestValue = counter;
						}
						else{
							counter++;
							Attribute n = new Key(s);
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
			examples.add(o);
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
		Example o = new Example();
		boolean isCorrect;
		
		outerloop:
		do { 
			isCorrect = true;
			o = new Example();
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
						Attribute n = new Key(s);
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
			examples.add(o);
			setChanged();
			notifyObservers("training");
		}
	}
	
	public void edit(Example editable, int Index){
		String s = "";
		String curr = "";
		Example updatedData = new Example();
		ArrayList<Attribute> currentData = editable.getData();
			
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
			examples.remove(Index);
			examples.add(Index, updatedData);
			setChanged();
		}
	}
	
	public void calculate()
	{
		try
		{
			Example testing = null;
			String s = "";
			Example[] closestK = null;
			Attribute t;
			int n = Integer.parseInt(JOptionPane.showInputDialog("Please input amount of nearest neighbours"));
			double val = 0;
			for (Example o : examples)
			{
				if (o.getIsTesting())
				{
					closestK = o.findClosestK(n, examples);
					break;
				}
			}
			for (Example o : examples)
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
						System.out.println(closestK[i]);
						if (!closestK[i].getIsTesting())
						{
							System.out.println("g" + closestK[i]);
							val += ((Num) closestK[i].getValueAtIndex(indexOfTestValue)).getVal();
							System.out.println(val);
						}
					}
					System.out.println("a" + val);
					val /= closestK.length;
					System.out.println("a" + val);
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
	public DefaultListModel<Example> getList()
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

		Project p = new Project();
		KNNView v = new KNNView(p.getList());
		KNNController c = new KNNController(v, p);
	}
}
