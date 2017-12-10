package machineLearning;
import java.io.FileInputStream;
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
 * @version 4.0
 * 
 */
public class KNNModel extends Observable implements Serializable{
	
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
	
	/** InvalidInputMessage()				Return an Error message with the passed message text
	 * 
	 * @param message						The message you want to display to the user
	 */
	private void InvalidInputMessage(String message){
		JOptionPane.showMessageDialog(null, message, "Input Error", JOptionPane.ERROR_MESSAGE);
	}
	
	
	/** getTestValuePosition()				Returns the index of a specified 'tetsvalue'
	 * 
	 * @param testingExample				Example containing unknown value
	 * @return int 							Index of test position
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
	*@param editable		the testing or training example to edit
	*@param index			the index of what is being edited 
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
	public void calculate(){
		try {
			Example testing = null;
			String s = "";
			Example[] closestK = null;
			Attribute t;
			int n = Integer.parseInt(JOptionPane.showInputDialog("Please input amount of nearest neighbours: "));
			if (n <= examples.size() - 1) {
				double val = 0;
				for (Example o : examples) {
					if (o.getIsTesting()) {
						closestK = o.findClosestK(n, examples);
						if (closestK == null){
							InvalidInputMessage("Point Dimension Mismatch - Please Check Entries");
							return; //Halt Calculation
						}
						break;
					}
				}
				for (Example o : examples) {
					if (!o.getIsTesting()) {
						testing = o;
					}
				}
				if (testing != null && closestK != null) {
					//if all the values are correct
					t = testing.getValueAtIndex(indexOfTestValue);
					s = t.calculateTestValue(closestK, indexOfTestValue);
					
					// Save the testValueResult
					setTestValueResult(s);				
          
					s += "\nClosest Objects:\n"; 

					for (int i = 0; i < closestK.length; i++) {
						s += closestK[i];
						if (i < closestK.length - 1) s += "\n";
					}
					setChanged();
					notifyObservers(s);
				}
			}
			else {
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
	
	/**	in()				Imports filename, containing examples, attributes etc. into system
	 * 
	 * @param filename		Name of file to import
	 * @creates newFile 	New file in program directory created with filename
	 */
	public void in(String filename){
		try {
			FileInputStream streamIn = new FileInputStream(filename);
			ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
			KNNModel importedProject = (KNNModel) objectinputstream.readObject();
			MachineLearning.importProject(importedProject);
			streamIn.close();
		 } catch (Exception e) {
			 if(filename != null) 
				 JOptionPane.showMessageDialog(null, "Please Check File Name and File exists within the Source Directory", "Import Error!", JOptionPane.PLAIN_MESSAGE);
		 }
	}
	
	/**	out()				Imports filename, containing examples, attributes etc. into system
	 * 
	 * @param filename		Name of file to import
	 */
	public void out(String filename){
		try
		{
			FileOutputStream fout = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(this);
			JOptionPane.showMessageDialog(null, "Export Successful!", "Export Review", JOptionPane.PLAIN_MESSAGE);
			fout.close();
		} catch(IOException e){
			if(filename != null) 
				JOptionPane.showMessageDialog(null, "Export Failed!", "Export Review", JOptionPane.PLAIN_MESSAGE);
		}
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
