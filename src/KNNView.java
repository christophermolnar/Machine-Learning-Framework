import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
public class KNNView extends JFrame implements Observer{
	JMenuItem create, add, calculate;
	JPanel input, output;
	JList<Object> list;
	public KNNView(DefaultListModel<Object> m)
	{
		super("KNN");
		list = new JList<>(m);
		JMenuBar menuBar = new JMenuBar();
		JMenu menuCreate = new JMenu("Example");
		create = new JMenuItem("Create");
		add = new JMenuItem("Add");
		calculate = new JMenuItem("Calculate");
		calculate.setEnabled(false);
//		add.setEnabled(false);
		menuCreate.add(create);
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
  		output.add(new JLabel("test"));
  		add.setEnabled(true);
  		input.add(list);
	}
	public void update(Observable obs, java.lang.Object obj)
	{
		
	}
	public void setCreateActionListener(ActionListener a)
	{
		create.addActionListener(a);
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
