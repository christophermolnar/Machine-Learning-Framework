import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
public class KNNView extends JFrame implements Observer{
	JMenuItem  add, calculate, testing, training;
	//JMenuItem create;
	JPanel input, output;
	JList<Object> list;
	JLabel outputText;
	public KNNView(DefaultListModel<Object> m)
	{
		super("KNN");
		outputText = new JLabel();
		list = new JList<>(m);
		JMenuBar menuBar = new JMenuBar();
		JMenu menuCreate = new JMenu("Example");
		JMenu createSubMenu = new JMenu("Create");
		add = new JMenuItem("Add");
		calculate = new JMenuItem("Calculate");
//		calculate.setEnabled(false);
//		add.setEnabled(false);
		
		testing = new JMenuItem("Testing");
		training = new JMenuItem("Training");
		
		createSubMenu.add(testing);
		createSubMenu.add(training);
		
		menuCreate.add(createSubMenu);
		menuCreate.add(add);
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
  		add.setEnabled(true);
  		input.add(list);
	}
	public void update(Observable obs, java.lang.Object obj)
	{
		try
		{
			String s = "Closest Objects: ";
			int n = Integer.parseInt(JOptionPane.showInputDialog("Please input amount of nearest neighbours"));
			ArrayList<Object> l = (ArrayList<Object>) obj;
			Object[] closestK = list.getSelectedValue().findClosestK(n, l);
			for (int i = 0; i < closestK.length; i++)
			{
				s += closestK[i];
				if (i < closestK.length - 1) s += ", ";
			}
			outputText.setText(s);
		} catch(NumberFormatException e){	
		}
	}
//	public void setCreateActionListener(ActionListener a)
//	{
//		create.addActionListener(a);
//	}
	public void setCreateTestingActionListener(ActionListener a)
	{
		testing.addActionListener(a);
	}
	public void setCreateTrainingActionListener(ActionListener a)
	{
		training.addActionListener(a);
	}
	public void setAddActionListener(ActionListener a)
	{
		add.addActionListener(a);
	}
	public void setCalculateActionListener(ActionListener a)
	{
		calculate.addActionListener(a);
	}
}
