package machineLearning;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import main.MachineLearning;

import java.io.Serializable;
import userInterface.KNNController;
import userInterface.KNNView;
import main.MachineLearning;
/** Project			Main class, responsible for testing
 *  
 * @author MZGA
 * @version 2.0	
 *
 */
/**
 * @author Callum
 *
 */
/**
 * @author Callum
 *
 */
public class KNNModel extends Observable implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private char testvalueType;
	private String testvalueResult;
	private boolean distanceMetric = true;
	private boolean exampleAdded;
	private boolean hasCreated = false, hasTraining = false, hasTesting = false;
	
	//Create new Project
	public KNNModel()
	{
		pointChoice = new ArrayList<>();
		numChoice = new ArrayList<>();
		list = new DefaultListModel<>();
		examples = new ArrayList<>();
		exampleAdded = false;
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
					hasCreated = true;
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
	
	/** isNoneValue			Checks if a given value is 'testvalue' (set as unknown, to find)
	 * 
	 * @param s				String to check
	 * @return boolean		True if strings match, false otherwise
	 */
	private boolean isTestValue(String s){
		return (s.toLowerCase().compareTo(TESTVALUE) == 0);
	}
	
	/** isNoneValue			Checks if a given value is 'none' (set as unknown, negliab. by user)
	 * 
	 * @param s				String to check
	 * @return boolean		True if strings match, false otherwise
	 */
	private boolean isNoneValue(String s){
		return (s.toLowerCase().compareTo(NONE) == 0);
	}
	
	/** checkForTestValue()		Checks a given example to see if a 'testvalue' is indicated
	 * 
	 * @param testingExample	Example to check 
	 * @return boolean			True if matching string found, false otherwise
	 */
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
	
	
	/** getTestValuePosition()		Returns the index of a specified 'tetsvalue'
	 * 
	 * @param testingExample		Example containing unknown value
	 * @return int 					Index of test position
	 */
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
	
	/** testing()		Create a new tersting example consisting of x Nums, y Points, and z Text Values
	 * 
	 * @calcValue		Calc value is indicated by the user entering 'testvalue' in feild of unknown value (MAX 1)
	 * 
	 */
	public void testing(){
		Example tester;	
		String input;
		double numberInput;
		
		createTester: {
			
			tester = new Example();
			try {
				for (int i = 0; i < numOfNumbers; i++) {
					input = JOptionPane.showInputDialog("Please input number value");
					if (input != null) { //'OK' clicked
						if (isTestValue(input) || isNoneValue(input)){ //Check to see if testvalue or none was entered
							Attribute n = new Key(input.toLowerCase());
							tester.addAttribute(n);
							if (isTestValue(input)){
								testvalueType = 'n';
							}
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
							if (isTestValue(input)){
								testvalueType = 'p';
							}
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
							if (isTestValue(input)){
								testvalueType = 'k';
							}
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
				hasTesting = true;
				indexOfTestValue = getTestValuePosition(tester);
				list.addElement(tester);
				examples.add(tester);
				tester.setTestingObject(true);
				exampleAdded = true;
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
	
	
	/** training()		Create a new training example consisting of x Nums, y Points, and z Text Values
	 * 
	 */
	public void training(){
		Example training;	
		String input;
		double numberInput;
		
		createTrainer: {
			
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
			hasTraining = true;
			
			//Add training Example to the list
			exampleAdded = true;
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
							curr = currentData.get(index).getStringVal();
							s = JOptionPane.showInputDialog("Modify the selected value?", curr);
							if (s == null) 
							{
								break outerloop;
							}
							updatedData.addAttribute(currentData.get(index).editedObject(s));
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
					
					// Save the testValueResult
					setTestValueResult(s);				
          
					s += "\nClosest Objects:\n"; 

					for (int i = 0; i < closestK.length; i++)
					{
						s += closestK[i];
						if (i < closestK.length - 1) s += "\n";
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
	
	/** setTestValueResult  Set the testvalue result
	 * 
	 * @param result		String representation of calculated unknonn value
	 */
	public void setTestValueResult(String result){
		String[] newAttribute = result.split("= ");
		testvalueResult = newAttribute[newAttribute.length -1];
	}
	
	/** errorCalculation()		Displays the error between the calculated value and actual value specified by the user
	 * 
	 * @input actual			The expected value of 'testvalue' specified by user
	 * 
	 */
	public void errorCalculation(){
		String s = JOptionPane.showInputDialog("Enter the expected value for the testvalue: ");
		if(s != null){ //'OK' clicked
			double errorCalculationResult; 
			if (testvalueType == 'n'){
				Double numberInput = Double.parseDouble(s);
				Num realAnswer = new Num(numberInput);
				System.out.println(testvalueResult);
				numberInput  = Double.parseDouble(testvalueResult);
				Num calculatedAnswer= new Num(numberInput);
				if (distanceMetric)
					realAnswer.setSelection(new CalculationPolar());
				else
					realAnswer.setSelection(new CalculationDifference());
				errorCalculationResult = realAnswer.getDistance(calculatedAnswer);

			}
			else if (testvalueType == 'p'){
				Point realAnswer = new Point(s);
				String result = testvalueResult.substring(testvalueResult.indexOf("(") + 1, testvalueResult.indexOf(")"));
				System.out.println(result);
				Point calculatedAnswer = new Point(result);
				if (distanceMetric)
					realAnswer.setSelection(new CalculationEuclidean());
				else
					realAnswer.setSelection(new CalculationDifference());
				errorCalculationResult = realAnswer.getDistance(calculatedAnswer);

			}
			else{
				Key realAnswer = new Key(s);
				Key calculatedAnswer = new Key(testvalueResult);
				errorCalculationResult = realAnswer.getDistance(calculatedAnswer);
			}
			JOptionPane.showMessageDialog(null, "Calculated Answer: " + testvalueResult + "\n" + "Expected Answer: " + s + "\n" + "The calculated error is: " + errorCalculationResult, "Calculated Error", JOptionPane.PLAIN_MESSAGE);
			
		} 
		
	}
	
	/**	in()				Imports fileName, containing examples, attributes etc. into system.  Displays message on error
	 * 
	 * @param fileName		Name of file to import
	 * @creates newFile 	New file in program directory created with filename
	 */
	public void in(String fileName){
		try {
			MachineLearning.importProject(importKNNModel(fileName));
		 } catch (Exception e) {
			 if(fileName != null) 
				 JOptionPane.showMessageDialog(null, "Please Check File Name and File exists within the Source Directory", "Import Error!", JOptionPane.PLAIN_MESSAGE);
		 }
	}
	
	/**	out()				Exports filename, containing examples, attributes etc. Displays message on error
	 * 
	 * @param fileName		Name of file to export
	 */
	public void out(String fileName){
		try
		{
			exportKNNModel(fileName);
			JOptionPane.showMessageDialog(null, "Export Successful!", "Export Review", JOptionPane.PLAIN_MESSAGE);
		} catch(IOException e){
			if(fileName != null) 
				JOptionPane.showMessageDialog(null, "Export Failed!", "Export Review", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	/** importKNNModel			Imports specified file. Exceptions handled in in() method
	 * 
	 * @param fileName			Name of file to import
	 * @return importedProject	the imported KNNModel
	 * 
	 */
	public KNNModel importKNNModel(String fileName) throws IOException, ClassNotFoundException{
		FileInputStream streamIn = new FileInputStream(fileName);
		ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
		KNNModel importedProject = (KNNModel) objectinputstream.readObject();
		streamIn.close();
		return importedProject;
	}
	
	/** exportKNNModel		Exports specified file. Exceptions handled in out() method
	 * 
	 * @param fileName		Name of file to export
	 */
	public void exportKNNModel(String fileName) throws IOException{
		FileOutputStream fout = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(this);
		fout.close();
	}
	
	/**
	 * Loads in all of the training data from the excel sheet
	 */
	public void soccerScenario(){
		Attribute n1, n2, n3, n4, n5, n6, n7, n8, n9;
		Key k;
		Example training = new Example();
		numOfNumbers=8;
		numOfPoints=0;
		numOfKeys=1;
		numChoice.add(new CalculationDifference());
		numChoice.add(new CalculationPolar());
		numChoice.add(new CalculationDifference());
		numChoice.add(new CalculationPolar());
		numChoice.add(new CalculationDifference());
		numChoice.add(new CalculationPolar());
		numChoice.add(new CalculationDifference());
		numChoice.add(new CalculationPolar());
		numChoice.add(new CalculationKey());
		//ROW 1
		n1 = new Num(1.9);
		n1.setSelection(numChoice.get(0));
		training.addAttribute(n1);
		n2 = new Num(-167);
		n2.setSelection(numChoice.get(1));
		training.addAttribute(n2);
		n3 = new Num(63.8);
		n3.setSelection(numChoice.get(2));
		training.addAttribute(n3);
		n4 = new Num(31);
		n4.setSelection(numChoice.get(3));
		training.addAttribute(n4);
		n5 = new Num(39.1);
		n5.setSelection(numChoice.get(4));
		training.addAttribute(n5);
		n6 = new Num(-41);
		n6.setSelection(numChoice.get(5));
		training.addAttribute(n6);
		n7 = new Key("none");
		n7.setSelection(numChoice.get(6));
		training.addAttribute(n7);
		n8 = new Key("none");
		n8.setSelection(numChoice.get(7));
		training.addAttribute(n8);
		n9 = new Key("Kick");
		n9.setSelection(numChoice.get(8));
		training.addAttribute(n9);
		list.addElement(training);
		examples.add(training);
		//ROW 2
		training = new Example();
		n1 = new Num(1.9);
		n1.setSelection(numChoice.get(0));
		training.addAttribute(n1);
		n2 = new Num(50);
		n2.setSelection(numChoice.get(1));
		training.addAttribute(n2);
		n3 = new Num(63.8);
		n3.setSelection(numChoice.get(2));
		training.addAttribute(n3);
		n4 = new Num(31);
		n4.setSelection(numChoice.get(3));
		training.addAttribute(n4);
		n5 = new Num(39.1);
		n5.setSelection(numChoice.get(4));
		training.addAttribute(n5);
		n6 = new Num(-41);
		n6.setSelection(numChoice.get(5));
		training.addAttribute(n6);
		n7 = new Key("none");
		n7.setSelection(numChoice.get(6));
		training.addAttribute(n7);
		n8 = new Key("none");
		n8.setSelection(numChoice.get(7));
		training.addAttribute(n8);
		n9 = new Key("Kick");
		n9.setSelection(numChoice.get(8));
		training.addAttribute(n9);
		list.addElement(training);
		examples.add(training);
		//ROW 3
		training = new Example();
		n1 = new Num(1.8);
		n1.setSelection(numChoice.get(0));
		training.addAttribute(n1);
		n2 = new Num(2);
		n2.setSelection(numChoice.get(1));
		training.addAttribute(n2);
		n3 = new Num(61.9);
		n3.setSelection(numChoice.get(2));
		training.addAttribute(n3);
		n4 = new Num(-4);
		n4.setSelection(numChoice.get(3));
		training.addAttribute(n4);
		n5 = new Key("none");
		n5.setSelection(numChoice.get(4));
		training.addAttribute(n5);
		n6 = new Key("none");
		n6.setSelection(numChoice.get(5));
		training.addAttribute(n6);
		n7 = new Key("none");
		n7.setSelection(numChoice.get(6));
		training.addAttribute(n7);
		n8 = new Key("none");
		n8.setSelection(numChoice.get(7));
		training.addAttribute(n8);
		n9 = new Key("Kick");
		n9.setSelection(numChoice.get(8));
		training.addAttribute(n9);
		list.addElement(training);
		examples.add(training);
		//ROW 4
		training = new Example();
		n1 = new Num(1.8);
		n1.setSelection(numChoice.get(0));
		training.addAttribute(n1);
		n2 = new Num(-85);
		n2.setSelection(numChoice.get(1));
		training.addAttribute(n2);
		n3 = new Num(53.5);
		n3.setSelection(numChoice.get(2));
		training.addAttribute(n3);
		n4 = new Num(17);
		n4.setSelection(numChoice.get(3));
		training.addAttribute(n4);
		n5 = new Key("none");
		n5.setSelection(numChoice.get(4));
		training.addAttribute(n5);
		n6 = new Key("none");
		n6.setSelection(numChoice.get(5));
		training.addAttribute(n6);
		n7 = new Key("none");
		n7.setSelection(numChoice.get(6));
		training.addAttribute(n7);
		n8 = new Key("none");
		n8.setSelection(numChoice.get(7));
		training.addAttribute(n8);
		n9 = new Key("Kick");
		n9.setSelection(numChoice.get(8));
		training.addAttribute(n9);
		list.addElement(training);
		examples.add(training);
		//ROW 5
		training = new Example();
		n1 = new Num(19.2);
		n1.setSelection(numChoice.get(0));
		training.addAttribute(n1);
		n2 = new Num(1);
		n2.setSelection(numChoice.get(1));
		training.addAttribute(n2);
		n3 = new Num(24.6);
		n3.setSelection(numChoice.get(2));
		training.addAttribute(n3);
		n4 = new Num(-17);
		n4.setSelection(numChoice.get(3));
		training.addAttribute(n4);
		n5 = new Key("none");
		n5.setSelection(numChoice.get(4));
		training.addAttribute(n5);
		n6 = new Key("none");
		n6.setSelection(numChoice.get(5));
		training.addAttribute(n6);
		n7 = new Key("none");
		n7.setSelection(numChoice.get(6));
		training.addAttribute(n7);
		n8 = new Key("none");
		n8.setSelection(numChoice.get(7));
		training.addAttribute(n8);
		n9 = new Key("Dash");
		n9.setSelection(numChoice.get(8));
		training.addAttribute(n9);
		list.addElement(training);
		examples.add(training);
		//ROW 6
		training = new Example();
		n1 = new Num(15.9);
		n1.setSelection(numChoice.get(0));
		training.addAttribute(n1);
		n2 = new Num(1);
		n2.setSelection(numChoice.get(1));
		training.addAttribute(n2);
		n3 = new Num(22.3);
		n3.setSelection(numChoice.get(2));
		training.addAttribute(n3);
		n4 = new Num(-18);
		n4.setSelection(numChoice.get(3));
		training.addAttribute(n4);
		n5 = new Key("none");
		n5.setSelection(numChoice.get(4));
		training.addAttribute(n5);
		n6 = new Key("none");
		n6.setSelection(numChoice.get(5));
		training.addAttribute(n6);
		n7 = new Key("none");
		n7.setSelection(numChoice.get(6));
		training.addAttribute(n7);
		n8 = new Key("none");
		n8.setSelection(numChoice.get(7));
		training.addAttribute(n8);
		n9 = new Key("Dash");
		n9.setSelection(numChoice.get(8));
		training.addAttribute(n9);
		list.addElement(training);
		examples.add(training);
		//ROW 7
		training = new Example();
		n1 = new Num(14.5);
		n1.setSelection(numChoice.get(0));
		training.addAttribute(n1);
		n2 = new Num(1);
		n2.setSelection(numChoice.get(1));
		training.addAttribute(n2);
		n3 = new Num(20.7);
		n3.setSelection(numChoice.get(2));
		training.addAttribute(n3);
		n4 = new Num(-20);
		n4.setSelection(numChoice.get(3));
		training.addAttribute(n4);
		n5 = new Key("none");
		n5.setSelection(numChoice.get(4));
		training.addAttribute(n5);
		n6 = new Key("none");
		n6.setSelection(numChoice.get(5));
		training.addAttribute(n6);
		n7 = new Key("none");
		n7.setSelection(numChoice.get(6));
		training.addAttribute(n7);
		n8 = new Key("none");
		n8.setSelection(numChoice.get(7));
		training.addAttribute(n8);
		n9 = new Key("Dash");
		n9.setSelection(numChoice.get(8));
		training.addAttribute(n9);
		list.addElement(training);
		examples.add(training);
		//ROW 8
		training = new Example();
		n1 = new Num(11);
		n1.setSelection(numChoice.get(0));
		training.addAttribute(n1);
		n2 = new Num(1);
		n2.setSelection(numChoice.get(1));
		training.addAttribute(n2);
		n3 = new Key("none");
		n3.setSelection(numChoice.get(2));
		training.addAttribute(n3);
		n4 = new Key("none");
		n4.setSelection(numChoice.get(3));
		training.addAttribute(n4);
		n5 = new Num(44.8);
		n5.setSelection(numChoice.get(4));
		training.addAttribute(n5);
		n6 = new Num(-5);
		n6.setSelection(numChoice.get(5));
		training.addAttribute(n6);
		n7 = new Key("none");
		n7.setSelection(numChoice.get(6));
		training.addAttribute(n7);
		n8 = new Key("none");
		n8.setSelection(numChoice.get(7));
		training.addAttribute(n8);
		n9 = new Key("Dash");
		n9.setSelection(numChoice.get(8));
		training.addAttribute(n9);
		list.addElement(training);
		examples.add(training);
		//ROW 9
		training = new Example();
		n1 = new Num(10);
		n1.setSelection(numChoice.get(0));
		training.addAttribute(n1);
		n2 = new Num(1);
		n2.setSelection(numChoice.get(1));
		training.addAttribute(n2);
		n3 = new Num(61.3);
		n3.setSelection(numChoice.get(2));
		training.addAttribute(n3);
		n4 = new Num(-31);
		n4.setSelection(numChoice.get(3));
		training.addAttribute(n4);
		n5 = new Key("none");
		n5.setSelection(numChoice.get(4));
		training.addAttribute(n5);
		n6 = new Key("none");
		n6.setSelection(numChoice.get(5));
		training.addAttribute(n6);
		n7 = new Num(41.4);
		n7.setSelection(numChoice.get(6));
		training.addAttribute(n7);
		n8 = new Num(43);
		n8.setSelection(numChoice.get(7));
		training.addAttribute(n8);
		n9 = new Key("Dash");
		n9.setSelection(numChoice.get(8));
		training.addAttribute(n9);
		list.addElement(training);
		examples.add(training);
		//ROW 10
		training = new Example();
		n1 = new Num(45.7);
		n1.setSelection(numChoice.get(0));
		training.addAttribute(n1);
		n2 = new Num(1);
		n2.setSelection(numChoice.get(1));
		training.addAttribute(n2);
		n3 = new Num(96.6);
		n3.setSelection(numChoice.get(2));
		training.addAttribute(n3);
		n4 = new Num(2);
		n4.setSelection(numChoice.get(3));
		training.addAttribute(n4);
		n5 = new Num(55.6);
		n5.setSelection(numChoice.get(4));
		training.addAttribute(n5);
		n6 = new Num(-37);
		n6.setSelection(numChoice.get(5));
		training.addAttribute(n6);
		n7 = new Num(55.6);
		n7.setSelection(numChoice.get(6));
		training.addAttribute(n7);
		n8 = new Num(40);
		n8.setSelection(numChoice.get(7));
		training.addAttribute(n8);
		n9 = new Key("Dash");
		n9.setSelection(numChoice.get(8));
		training.addAttribute(n9);
		list.addElement(training);
		examples.add(training);
		//ROW 11
		training = new Example();
		n1 = new Num(50.4);
		n1.setSelection(numChoice.get(0));
		training.addAttribute(n1);
		n2 = new Num(-1);
		n2.setSelection(numChoice.get(1));
		training.addAttribute(n2);
		n3 = new Num(101.5);
		n3.setSelection(numChoice.get(2));
		training.addAttribute(n3);
		n4 = new Num(14);
		n4.setSelection(numChoice.get(3));
		training.addAttribute(n4);
		n5 = new Num(75.4);
		n5.setSelection(numChoice.get(4));
		training.addAttribute(n5);
		n6 = new Num(-24);
		n6.setSelection(numChoice.get(5));
		training.addAttribute(n6);
		n7 = new Num(46.2);
		n7.setSelection(numChoice.get(6));
		training.addAttribute(n7);
		n8 = new Num(40);
		n8.setSelection(numChoice.get(7));
		training.addAttribute(n8);
		n9 = new Key("Turn");
		n9.setSelection(numChoice.get(8));
		training.addAttribute(n9);
		list.addElement(training);
		examples.add(training);
		//ROW 12
		training = new Example();
		n1 = new Num(41.4);
		n1.setSelection(numChoice.get(0));
		training.addAttribute(n1);
		n2 = new Num(0);
		n2.setSelection(numChoice.get(1));
		training.addAttribute(n2);
		n3 = new Num(90.1);
		n3.setSelection(numChoice.get(2));
		training.addAttribute(n3);
		n4 = new Num(18);
		n4.setSelection(numChoice.get(3));
		training.addAttribute(n4);
		n5 = new Num(65.1);
		n5.setSelection(numChoice.get(4));
		training.addAttribute(n5);
		n6 = new Num(-27);
		n6.setSelection(numChoice.get(5));
		training.addAttribute(n6);
		n7 = new Key("none");
		n7.setSelection(numChoice.get(6));
		training.addAttribute(n7);
		n8 = new Key("none");
		n8.setSelection(numChoice.get(7));
		training.addAttribute(n8);
		n9 = new Key("Turn");
		n9.setSelection(numChoice.get(8));
		training.addAttribute(n9);
		list.addElement(training);
		examples.add(training);
		//ROW 13
		training = new Example();
		n1 = new Num(14.5);
		n1.setSelection(numChoice.get(0));
		training.addAttribute(n1);
		n2 = new Num(15);
		n2.setSelection(numChoice.get(1));
		training.addAttribute(n2);
		n3 = new Num(60.1);
		n3.setSelection(numChoice.get(2));
		training.addAttribute(n3);
		n4 = new Num(27);
		n4.setSelection(numChoice.get(3));
		training.addAttribute(n4);
		n5 = new Key("none");
		n5.setSelection(numChoice.get(4));
		training.addAttribute(n5);
		n6 = new Key("none");
		n6.setSelection(numChoice.get(5));
		training.addAttribute(n6);
		n7 = new Key("none");
		n7.setSelection(numChoice.get(6));
		training.addAttribute(n7);
		n8 = new Key("none");
		n8.setSelection(numChoice.get(7));
		training.addAttribute(n8);
		n9 = new Key("Turn");
		n9.setSelection(numChoice.get(8));
		training.addAttribute(n9);
		list.addElement(training);
		examples.add(training);
		//ROW 14
		training = new Example();
		n1 = new Num(41.4);
		n1.setSelection(numChoice.get(0));
		training.addAttribute(n1);
		n2 = new Num(3);
		n2.setSelection(numChoice.get(1));
		training.addAttribute(n2);
		n3 = new Num(94.7);
		n3.setSelection(numChoice.get(2));
		training.addAttribute(n3);
		n4 = new Num(4);
		n4.setSelection(numChoice.get(3));
		training.addAttribute(n4);
		n5 = new Num(55.1);
		n5.setSelection(numChoice.get(4));
		training.addAttribute(n5);
		n6 = new Num(-36);
		n6.setSelection(numChoice.get(5));
		training.addAttribute(n6);
		n7 = new Num(53.5);
		n7.setSelection(numChoice.get(6));
		training.addAttribute(n7);
		n8 = new Num(43);
		n8.setSelection(numChoice.get(7));
		training.addAttribute(n8);
		n9 = new Key("Turn");
		n9.setSelection(numChoice.get(8));
		training.addAttribute(n9);
		list.addElement(training);
		examples.add(training);
		//ROW 15
		training = new Example();
		n1 = new Num(23.2);
		n1.setSelection(numChoice.get(0));
		training.addAttribute(n1);
		n2 = new Num(0);
		n2.setSelection(numChoice.get(1));
		training.addAttribute(n2);
		n3 = new Num(76.9);
		n3.setSelection(numChoice.get(2));
		training.addAttribute(n3);
		n4 = new Num(2);
		n4.setSelection(numChoice.get(3));
		training.addAttribute(n4);
		n5 = new Key("none");
		n5.setSelection(numChoice.get(4));
		training.addAttribute(n5);
		n6 = new Key("none");
		n6.setSelection(numChoice.get(5));
		training.addAttribute(n6);
		n7 = new Key("none");
		n7.setSelection(numChoice.get(6));
		training.addAttribute(n7);
		n8 = new Key("none");
		n8.setSelection(numChoice.get(7));
		training.addAttribute(n8);
		n9 = new Key("Turn");
		n9.setSelection(numChoice.get(8));
		training.addAttribute(n9);
		list.addElement(training);
		examples.add(training);
		//ROW 16
		training = new Example();
		n1 = new Num(12);
		n1.setSelection(numChoice.get(0));
		training.addAttribute(n1);
		n2 = new Num(24);
		n2.setSelection(numChoice.get(1));
		training.addAttribute(n2);
		n3 = new Key("none");
		n3.setSelection(numChoice.get(2));
		training.addAttribute(n3);
		n4 = new Key("none");
		n4.setSelection(numChoice.get(3));
		training.addAttribute(n4);
		n5 = new Key("none");
		n5.setSelection(numChoice.get(4));
		training.addAttribute(n5);
		n6 = new Key("none");
		n6.setSelection(numChoice.get(5));
		training.addAttribute(n6);
		n7 = new Num(42.7);
		n7.setSelection(numChoice.get(6));
		training.addAttribute(n7);
		n8 = new Num(-40);
		n8.setSelection(numChoice.get(7));
		training.addAttribute(n8);
		n9 = new Key("Turn");
		n9.setSelection(numChoice.get(8));
		training.addAttribute(n9);
		list.addElement(training);
		examples.add(training);
		//ROW 17
		training = new Example();
		n1 = new Key("none");
		n1.setSelection(numChoice.get(0));
		training.addAttribute(n1);
		n2 = new Key("none");
		n2.setSelection(numChoice.get(1));
		training.addAttribute(n2);
		n3 = new Num(26.3);
		n3.setSelection(numChoice.get(2));
		training.addAttribute(n3);
		n4 = new Num(2);
		n4.setSelection(numChoice.get(3));
		training.addAttribute(n4);
		n5 = new Key("none");
		n5.setSelection(numChoice.get(4));
		training.addAttribute(n5);
		n6 = new Key("none");
		n6.setSelection(numChoice.get(5));
		training.addAttribute(n6);
		n7 = new Key("none");
		n7.setSelection(numChoice.get(6));
		training.addAttribute(n7);
		n8 = new Key("none");
		n8.setSelection(numChoice.get(7));
		training.addAttribute(n8);
		n9 = new Key("Turn");
		n9.setSelection(numChoice.get(8));
		training.addAttribute(n9);
		list.addElement(training);
		examples.add(training);
		//ROW 18
		training = new Example();
		n1 = new Num(3.5);
		n1.setSelection(numChoice.get(0));
		training.addAttribute(n1);
		n2 = new Num(1);
		n2.setSelection(numChoice.get(1));
		training.addAttribute(n2);
		n3 = new Num(56.1);
		n3.setSelection(numChoice.get(2));
		training.addAttribute(n3);
		n4 = new Num(4);
		n4.setSelection(numChoice.get(3));
		training.addAttribute(n4);
		n5 = new Key("none");
		n5.setSelection(numChoice.get(4));
		training.addAttribute(n5);
		n6 = new Key("none");
		n6.setSelection(numChoice.get(5));
		training.addAttribute(n6);
		n7 = new Key("none");
		n7.setSelection(numChoice.get(6));
		training.addAttribute(n7);
		n8 = new Key("none");
		n8.setSelection(numChoice.get(7));
		training.addAttribute(n8);
		n9 = new Key("Dash");
		n9.setSelection(numChoice.get(8));
		training.addAttribute(n9);
		list.addElement(training);
		examples.add(training);
		//ROW 18
		setChanged();
		notifyObservers("create");
		setChanged();
		notifyObservers("training");
	}
	
	/** getList()		Returns the current lis of examples and attributes
	 * 
	 * @return list 	Current list of examples
	 */
	public DefaultListModel<Example> getList()
	{
		return list;
	}
	
	
	/** getCreated()		Returns if a Example containing x Nums, y Points, and z Text Attributes has been created
	 * 
	 * @return hasCReated	True if an exmaple has been created, false otherwise
	 */
	public boolean getCreated(){
		return hasCreated;
	}
	
	/** getTraining()		Returns if a example has been created
	 * 
	 * @return hasTraining	True if an training exmaple has been created, false otherwise
	 */
	public boolean getTraining()
	{
		return hasTraining;
	}
	
	/** getTesting()		Returns if a example has been created
	 * 
	 * @return hasTesting	True if an testing exmaple has been created, false otherwise
	 */
	public boolean getTesting()
	{
		return hasTesting;
	}
}
