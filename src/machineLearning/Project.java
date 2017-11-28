package machineLearning;

import java.util.ArrayList;
import java.util.Observable;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import userInterface.KNNController;
import userInterface.KNNView;
/** Project			Main class, responsible for testing
 *  
 * @author MZGA
 * @version 2.0	
 *
 */
public class Project extends Observable{
	private String[] optionsPoint = {"Euclidean", "Difference"};
	private String[] optionsNum = {"Difference", "Polar"};
	private DefaultListModel<Example> list;
	private int numOfNumbers, numOfPoints, numOfKeys;
	private int tempNum, tempPoint, tempKey;
	private int indexOfTestValue;
	private ArrayList<Calculation> pointChoice;
	private ArrayList<Calculation> numChoice;
	private ArrayList<Example> examples;
	private Example testObject;
	public static final String TESTVALUE = "testvalue";
	public static final String NONE = "none";
	
	//Create new Project
	public Project()
	{
		pointChoice = new ArrayList<>();
		numChoice = new ArrayList<>();
		list = new DefaultListModel<>();
		examples = new ArrayList<>();
	}
	
	/** create() 		create the format for example
	*
	*/
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
					tempKey = Integer.parseInt(s);
				} 
				else { //'Cancel' Clicked
					isCorrect = false;
					break outerloop;
				}
				
				for (int i = 0; i < tempPoint; i++) {
					int index = JOptionPane.showOptionDialog(null, "Please pick a comparison metric for 'Point " + (i + 1) + "'", "title", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionsPoint, null);
					if (index != -1){
						if (optionsPoint[index].equals("Euclidean")) {
							pointChoice.add(new CalculationEuclidean());
						} 
						else if (optionsPoint[index].equals("Difference")){
							pointChoice.add(new CalculationDifference());
						}
					}
					else {
						//pointChoice.add(new CalculationDifference());
						isCorrect = false;
						break outerloop;
					}
				}
				
				for (int i = 0; i < tempNum; i++) {
					int index = JOptionPane.showOptionDialog(null, "Please pick a comparison metric for 'Num " + (i + 1) + "'", "title", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionsNum, null);
					if (index != -1){
						if (optionsNum[index].equals("Polar")) {
							numChoice.add(new CalculationPolar());
						} 
						else if (optionsNum[index].equals("Difference")){
							numChoice.add(new CalculationDifference());
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
					numOfKeys=tempKey;
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
	
	private boolean isNoneValue(String s){
		return (s.toLowerCase().compareTo(NONE) == 0);
	}
	
	private boolean checkForTestValue(Example testingExample){
		ArrayList<Attribute> testingExampleArray = testingExample.getData();
		int testingValue = 0;
		for (int position = 0; position < testingExampleArray.size(); position++){
			if (testingExampleArray.get(position) instanceof Key){
				Key value = ((Key)testingExampleArray.get(position));
				if (isTestValue(value.getVal())){
					testingValue++;
				}
			}
		}
		if (testingValue == 1){
			return true;
		}
		return false;
	}
	
	private void InvalidInputMessage(String message){
		JOptionPane.showMessageDialog(null, message, "Input Error", JOptionPane.ERROR_MESSAGE);
	}
	
	private int getTestValuePosition(Example testingExample){
		ArrayList<Attribute> testingExampleArray = testingExample.getData();
		for (int position = 0; position < testingExampleArray.size(); position++){
			if (testingExampleArray.get(position) instanceof Key){
				Key value = ((Key)testingExampleArray.get(position));
				if (isTestValue(value.getVal())){
					return position;
				}
			}
		}
		return 0;
	}
	
	
	public void testing(){
		Example tester;	
		String input;
		double numberInput;
		
		createTester: { //Subroutine??
			
			tester = new Example();
			try {
				for (int i = 0; i < numOfNumbers; i++) {
					input = JOptionPane.showInputDialog("Please input number value");
					if (input != null) { //'OK' clicked
						if (isTestValue(input) || isNoneValue(input)){ //Check to see if testvalue or none was entered
							Attribute n = new Key(input.toLowerCase());
							tester.addAttribute(n);
						}
						else{
							numberInput = Double.parseDouble(input);
							Num n = new Num(numberInput);
							n.setSelection(numChoice.get(i));
							tester.addAttribute(n);
						}
					}
					else { //'Cancel' Clicked
						break createTester;
					}
				} 

				for (int i = 0; i < numOfPoints; i++) {
					input = JOptionPane.showInputDialog("Please input point value");
					
					if (input != null) { //'OK' clicked
						if (isTestValue(input) || isNoneValue(input)){ //Check to see if testvalue or none was entered
							Attribute n = new Key(input.toLowerCase());
							tester.addAttribute(n);
						}
						else{
							Point n = new Point(input);
							n.setSelection(pointChoice.get(i));
							tester.addAttribute(n);
						};
					} else { //'Cancel' Clicked
						break createTester;
					}
				} 

				for (int i = 0; i < numOfKeys; i++) {
					input = JOptionPane.showInputDialog("Please input enum value");
					if (input != null){ //Something Entered --> 'OK' clicked
						if (isTestValue(input) || isNoneValue(input)){ //Check to see if testvalue or none was entered
							Attribute n = new Key(input.toLowerCase());
							tester.addAttribute(n);
						}
						else{
							Attribute n = new Key(input);
							tester.addAttribute(n);
						}
					}
					else{ //'Cancel' clicked
						break createTester;
					}
				} 
			} 
			catch(Exception e){
				InvalidInputMessage("Input is invalid");
				break createTester;
			}	
			
			if (checkForTestValue(tester)) { //Check to see if the user entered everything properly
				indexOfTestValue = getTestValuePosition(tester);
				list.addElement(tester);
				examples.add(tester);
				tester.setTestingObject(true);
				setChanged();
				notifyObservers("testing");
				//DONT ALLOW THEM TO ADD ANY MORE TESTING EXAMPLES
				//Block the creation of testing
			}
			else{
				InvalidInputMessage("No Attribute was set to testvalue");
			}
		
		} //End of createTester
	}
	
	public void training(){
		Example training;	
		String input;
		double numberInput;
		
		createTrainer: { //Subroutine??
			
			training = new Example();
			try {
				for (int i = 0; i < numOfNumbers; i++) {
					input = JOptionPane.showInputDialog("Please input number value");
					if (input != null) { //'OK' clicked
						if (isNoneValue(input)){ //Check to see if none was entered
							Attribute n = new Key(input.toLowerCase());
							training.addAttribute(n);
						}
						else{
							numberInput = Double.parseDouble(input);
							Num n = new Num(numberInput);
							n.setSelection(numChoice.get(i));
							training.addAttribute(n);
						}
					}
					else { //'Cancel' Clicked
						break createTrainer;
					}
				} 

				for (int i = 0; i < numOfPoints; i++) {
					input = JOptionPane.showInputDialog("Please input point value");
					
					if (input != null) { //'OK' clicked
						if (isNoneValue(input)){ //Check to see if none was entered
							Attribute n = new Key(input.toLowerCase());
							training.addAttribute(n);
						}
						else{
							Point n = new Point(input);
							n.setSelection(pointChoice.get(i));
							training.addAttribute(n);
						};
					} else { //'Cancel' Clicked
						break createTrainer;
					}
				} 

				for (int i = 0; i < numOfKeys; i++) {
					input = JOptionPane.showInputDialog("Please input enum value");
					if (input != null){ //Something Entered --> 'OK' clicked
						if (isNoneValue(input)){ //Check to see if none was entered
							Attribute n = new Key(input.toLowerCase());
							training.addAttribute(n);
						}
						else{
							Attribute n = new Key(input);
							training.addAttribute(n);
						}
					}
					else{ //'Cancel' clicked
						break createTrainer;
					}
				} 
			} 
			catch(Exception e){
				InvalidInputMessage("Input is invalid");
				break createTrainer;
			}	
			
			//Add training Example to the list
			list.addElement(training);
			examples.add(training);
			setChanged();
			notifyObservers("training");
		
		} //End of createTrainer
	}
	
	/** edit()			Allows the user to edit testing and training examples
	*
	 @param editable		the testing or training example to edit
	 @param index			the index of what is being edited 
	*/
	public void edit(Example editable, int Index){
		String s = "";
		String curr = "";
		Example updatedData = new Example();
		ArrayList<Attribute> currentData = editable.getData();
			
		boolean isCorrect;
			
		outerloop:
		{
			do { 
				isCorrect = true;
					for(int index=0; index<currentData.size(); index++){
						try {
							if (currentData.get(index) instanceof Num){
								curr = Double.toString((((Num) currentData.get(index)).getNum()));
								s = JOptionPane.showInputDialog("Modify the selected value?", curr);
								if(s==null)
									break outerloop; //Cancel Pressed
								updatedData.addAttribute(new Num(Double.parseDouble(s), ((Num) currentData.get(index)).getSelection()));
								
							}else if(currentData.get(index) instanceof Point){
								curr = (((Point) currentData.get(index)).getCoords());
								s = JOptionPane.showInputDialog("Modify the selected value?", curr);
								if(s==null)
									break outerloop; //Cancel Pressed
								updatedData.addAttribute(new Point(s, ((Point) currentData.get(index)).getCalcType()));
							
							} else { //instanceof Key
								curr = ((Key)currentData.get(index)).getWord();
								s = JOptionPane.showInputDialog("Modify the selected value?", curr);
								if(s==null)
									break outerloop; //Cancel Pressed
								updatedData.addAttribute(new Key(s));
							}
						}catch(Exception e){
								//isCorrect = false; //No longer needed, reprompts errored value
								JOptionPane.showMessageDialog(null, "Input is invalid", "Input Error", JOptionPane.ERROR_MESSAGE);
								index--; //Reprompt Value
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
	}
	
	/** calculate()			calculate the score for each training example and find the testing examples kNN
	*
	*/
	public void calculate()
	{
		try
		{
			Example testing = null;
			String s = "";
			Example[] closestK = null;
			Attribute t;
			int n = Integer.parseInt(JOptionPane.showInputDialog("Please input amount of nearest neighbours: "));
			if (n <= examples.size() - 1)
			{
				double val = 0;
				for (Example o : examples)
				{
					if (o.getIsTesting())
					{
						closestK = o.findClosestK(n, examples);
						if (closestK == null){
							InvalidInputMessage("Point Dimension Mismatch - Please Check Entries");
							return; //Halt Calculation
						}
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
					//if all the values are correct
					t = testing.getValueAtIndex(indexOfTestValue);
					s = t.calculateTestValue(closestK, indexOfTestValue);
					s += "Closest Objects:"; 
					for (int i = 0; i < closestK.length; i++)
					{
						s += closestK[i];
						if (i < closestK.length - 1) s += ", ";
					}
					setChanged();
					notifyObservers(s);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null,
					    "Too many neighbours.",
					    "K Value Error",
					    JOptionPane.ERROR_MESSAGE);
			}
		} catch(NumberFormatException e){	
		}
	}
	
	public void errorCalculation(){
		String s = JOptionPane.showInputDialog("Enter the expected value for the testvalue: ");
		if(s != null){ //'OK' clicked
			System.out.println("Calculating");
		} 
		System.out.println("Error Calculation");
	}
	
	public DefaultListModel<Example> getList()
	{
		return list;
	}
}
