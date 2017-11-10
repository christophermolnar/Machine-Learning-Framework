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
//		setLayout (new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)); 
		add(list);
		setSize(800,800);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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
