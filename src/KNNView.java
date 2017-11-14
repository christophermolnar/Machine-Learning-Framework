import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.util.List;
public class KNNView extends JFrame implements Observer{
	JMenuItem  create, edit, calculate, testing, training;
	//JMenuItem create;
	JPanel input, output;
	JList<Example> list;
	JLabel outputText;
	boolean hasCreatedTraining, hasCreatedTesting;
	public KNNView(DefaultListModel<Example> m)
	{
		super("KNN");
		hasCreatedTraining = false;
		hasCreatedTesting = false;
		outputText = new JLabel();
		list = new JList<>(m);
		JMenuBar menuBar = new JMenuBar();
		JMenu menuCreate = new JMenu("Example");
		create = new JMenuItem("Create");
		//JMenu createSubMenu = new JMenu("Create");
		JMenu addSubMenu = new JMenu("Add");
		
		edit = new JMenuItem("Edit");
		edit.setEnabled(true); //@TODO set disabled unless jlist entry is selected
		
		calculate = new JMenuItem("Calculate");
		calculate.setEnabled(false);
		
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
		
		menuBar.add(menuCreate);
		setJMenuBar(menuBar);
		setLayout (new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)); 
		setSize(800,800);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addInputOutput();
	}
	public void addInputOutput()
	{
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
	public void update(Observable obs, java.lang.Object obj)
	{
		if (obj instanceof String)
		{
			String s = (String) obj;
			if (s.equals("create"))
			{
				training.setEnabled(true);
				testing.setEnabled(true);
			}
			else if (s.equals("testing"))
			{
				hasCreatedTraining = true;
				if (hasCreatedTesting)
				{
					calculate.setEnabled(true);
				}
			}
			else if (s.equals("training"))
			{
				hasCreatedTesting = true;
				if (hasCreatedTraining)
				{
					calculate.setEnabled(true);
				}
			}
			else if (s.equals("edit"))
			{
				
			}
			else
			{
				outputText.setText((String)obj);
			}
		}
	}
	
	public int getJlistIndex(){
		return list.getSelectedIndex();
	}
	
	public Example getSelectedObject(){
		return list.getSelectedValue();
	}
	
	public void setCreateActionListener(ActionListener a)
	{
		create.addActionListener(a);
	}
	public void setTestingActionListener(ActionListener a)
	{
		testing.addActionListener(a);
	}
	public void setTrainingActionListener(ActionListener a)
	{
		training.addActionListener(a);
	}
	public void setEditActionListener(ActionListener a)
	{
		edit.addActionListener(a);
	}	
	public void setCalculateActionListener(ActionListener a)
	{
		calculate.addActionListener(a);
	}
}
