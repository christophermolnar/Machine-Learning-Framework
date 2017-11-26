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
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import machineLearning.Example;

public class KNNView extends JFrame implements Observer {
	
	private JMenuItem  create, edit, calculate, testing, training, errorCalculation;
	private JPanel input, output;
	private JList<Example> list;
	private JLabel outputText;
	private boolean hasCreatedTraining, hasCreatedTesting;
	
	public KNNView(DefaultListModel<Example> m) {
		super("KNN");
		hasCreatedTraining = false;
		hasCreatedTesting = false;
		outputText = new JLabel();
		list = new JList<>(m);
		JMenuBar menuBar = new JMenuBar();
		JMenu menuCreate = new JMenu("Example");
		create = new JMenuItem("Create");
		JMenu addSubMenu = new JMenu("Add");
		
		edit = new JMenuItem("Edit");
		edit.setEnabled(true); //@TODO set disabled unless jlist entry is selected
		
		calculate = new JMenuItem("Calculate");
		calculate.setEnabled(false);
		
		errorCalculation = new JMenuItem("Error Calculation");
		errorCalculation.setEnabled(false);
		
		testing = new JMenuItem("Testing");
		training = new JMenuItem("Training");
		testing.setEnabled(false);
		training.setEnabled(false);
		addSubMenu.add(testing);
		addSubMenu.add(training);
		
		menuCreate.add(create);
		menuCreate.add(addSubMenu);
		menuCreate.add(edit);
		menuCreate.add(calculate);
		menuCreate.add(errorCalculation);
		
		menuBar.add(menuCreate);
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
		output.setPreferredSize(new Dimension(800,0));
		output.setPreferredSize(new Dimension(800,-200));
  		output.setBorder(BorderFactory.createTitledBorder("Output"));
  		add(input);
  		add(output);
  		output.add(outputText);
  		input.add(list);
	}
	
	/** update()		update state of UI
	 * 
	 * @param obs		Observer
	 * @param obj		Object
	 */
	public void update(Observable obs, java.lang.Object obj)
	{
		if (obj instanceof String) {
			String s = (String) obj;
			if (s.equals("create")) {
				training.setEnabled(true);
				testing.setEnabled(true);
			}
			else if (s.equals("testing")) {
				hasCreatedTraining = true;
				if (hasCreatedTesting)
				{
					calculate.setEnabled(true);
				}
			}
			else if (s.equals("training")) {
				hasCreatedTesting = true;
				if (hasCreatedTraining) {
					calculate.setEnabled(true);
				}
			}
			else if (s.equals("edit")) {
				
			}
			else {
				outputText.setText((String)obj);
				errorCalculation.setEnabled(true);
			}
		}
	}
	
	/** getJListIndex()		get list index
	 * 
	 * @return index		Selected index
	 */
	public int getJlistIndex(){
		return list.getSelectedIndex();
	}
	
	/** getselected() 		get selected example object
	 * 
	 * @return value		Selected value
	 */
	public Example getSelectedObject(){
		return list.getSelectedValue();
	}
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
}
