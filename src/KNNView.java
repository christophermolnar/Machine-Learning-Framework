import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
public class KNNView extends JFrame implements Observer{
	JMenuItem  add, create, calculate, testing, training;
	//JMenuItem create;
	JPanel input, output;
	JList<Object> list;
	JLabel outputText;
	boolean hasCreatedTraining, hasCreatedTesting;
	public KNNView(DefaultListModel<Object> m)
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
		add = new JMenuItem("Add");
		JMenu addSubMenu = new JMenu("Add");
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
			else
			{
				outputText.setText((String)obj);
			}
		}
		else
		{
			outputText.setText("lol");
//			try//REMOVE THIS AND PUT THE CODE INTO PROJECT !!IMPORTANT!!
//			{
//				String s = "Closest Objects: ";
//				int n = Integer.parseInt(JOptionPane.showInputDialog("Please input amount of nearest neighbours"));
//				ArrayList<Object> l = (ArrayList<Object>) obj;
//				Object[] closestK = list.getSelectedValue().findClosestK(n, l);
//				for (int i = 0; i < closestK.length; i++)
//				{
//					s += closestK[i];
//					if (i < closestK.length - 1) s += ", ";
//				}
//				outputText.setText(s);
//			} catch(NumberFormatException e){	
//			}
		}
	}
	public void setCreateActionListener(ActionListener a)
	{
		create.addActionListener(a);
	}
	public void setCreateTestingActionListener(ActionListener a)
	{
		testing.addActionListener(a);
	}
	public void setCreateTrainingActionListener(ActionListener a)
	{
		training.addActionListener(a);
	}
//	public void setAddActionListener(ActionListener a)
//	{
//		add.addActionListener(a);
//	}
	public void setCalculateActionListener(ActionListener a)
	{
		calculate.addActionListener(a);
	}
}
