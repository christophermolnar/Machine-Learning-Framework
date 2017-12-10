package userInterface;
/** KNNView				Responsible for creating the user interface
 * 
 * @author MZGA
 * @version 2.0
 *
 */

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import machineLearning.Example;

public class KNNView extends JFrame implements Observer {
	
	private JMenuItem  create, edit, calculate, testing, training, errorCalculation;
	private JMenuItem in, out;
	private JPanel input, output;
	private JList<Example> list;
	private JTextArea outputText;
	private boolean hasCreatedTraining, hasCreatedTesting;
	
	
	/** KNNView		Generate user interface
	 * 
	 * @param listData	Existing data to add to the List
	 */
	public KNNView(DefaultListModel<Example> listData) {
		super("KNN");
		hasCreatedTraining = false;
		hasCreatedTesting = false;
		outputText = new JTextArea();
		
		//List to represent data models
		list = new JList<>(listData);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//Add program options/functionality
		JMenuBar menuBar = new JMenuBar();
		JMenu menuCreate = new JMenu("Example");
		JMenu menuFile = new JMenu("File");
		create = new JMenuItem("Create");
		JMenu addSubMenu = new JMenu("Add");
		in = new JMenuItem("Import");
		out = new JMenuItem("Export");

		
		edit = new JMenuItem("Edit");
		edit.setEnabled(false);
		
		calculate = new JMenuItem("Calculate");
		calculate.setEnabled(false); 
		
		errorCalculation = new JMenuItem("Error Calculation");
		errorCalculation.setEnabled(false);
		
		testing = new JMenuItem("Testing");
		training = new JMenuItem("Training");
		testing.setEnabled(false); //Disabled until data is added
		training.setEnabled(false); //Disabled until data is added
		addSubMenu.add(testing);
		addSubMenu.add(training);
		
		menuCreate.add(create);
		menuCreate.add(addSubMenu);
		menuCreate.add(edit);
		menuCreate.add(calculate);
		menuCreate.add(errorCalculation);
		
		menuFile.add(in);
		menuFile.add(out);
		
		menuBar.add(menuCreate);
		menuBar.add(menuFile);
		setJMenuBar(menuBar);
		setLayout (new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)); 
		setSize(800,800);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addInputOutput();
	}
	
	/** addInputOutput		Add IO to user interface
	 * 
	 */
	public void addInputOutput() {
		input = new JPanel();
		input.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
  		output = new JPanel();
  		input.setPreferredSize(new Dimension(800,400));
		output.setPreferredSize(new Dimension(800,200));
  		output.setBorder(BorderFactory.createTitledBorder("Output"));
  		add(input);
  		add(output);
  		output.add(outputText);
  		input.add(list);
	}
	
	/** update()		update state of UI
	 * 
	 * @param obs		Observer	Current Model
	 * @param obj		Object
	 */
	public void update(Observable obs, Object obj)
	{
		if (obj instanceof String) {
			String s = (String) obj;
			if (s.equals("create")) {
				created();
			}
			else if (s.equals("testing")) {
				tested();
			}
			else if (s.equals("training")) {
				training();
			}
			else if (s.equals("edit")) {
				
			}
			else {
				outputText.setText((String)obj);
				errorCalculation.setEnabled(true);
			}
		}
	}
	
	public void close()
	{
		dispose();
	}
	
	public void created()
	{
		training.setEnabled(true);
		testing.setEnabled(true);
		edit.setEnabled(true);
	}
	
	public void tested()
	{
		hasCreatedTraining = true;
		create.setEnabled(false);
		if (hasCreatedTesting)
		{
			calculate.setEnabled(true);
		}
	}
	
	public void training()
	{
		hasCreatedTesting = true;
		create.setEnabled(false);
		if (hasCreatedTraining) {
			calculate.setEnabled(true);
		}
	}
	
	/** getJListIndex()		get list index of JList
	 *  
	 * @return index		Selected index
	 */
	public int getJlistIndex(){
		return list.getSelectedIndex();
	}
	
	/** getselected() 		get selected example object from JList
	 * 
	 * @return value		Selected value
	 */
	public Example getSelectedObject(){
		return list.getSelectedValue();
	}
	
	//ACTION LISTENERS -- Functionality of Buttons
	
	public void setCreateActionListener(ActionListener a) {
		create.addActionListener(a);
	}
	public void setTestingActionListener(ActionListener a) {
		testing.addActionListener(a);
	}
	public void setTrainingActionListener(ActionListener a) {
		training.addActionListener(a);
	}
	public void setEditActionListener(ActionListener a) {
		edit.addActionListener(a);
	}	
	public void setCalculateActionListener(ActionListener a) {
		calculate.addActionListener(a);
	}
	public void setErrorCalculationActionListener(ActionListener a) {
		errorCalculation.addActionListener(a);
	}
	public void setImportActionListener(ActionListener a) {
		in.addActionListener(a);
	}
	public void setExportActionListener(ActionListener a) {
		out.addActionListener(a);
	}
}
