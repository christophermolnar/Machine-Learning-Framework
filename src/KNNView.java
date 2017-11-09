import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
public class KNNView extends JFrame implements Observer{
	JMenuItem create, add, calculate;
	JPanel input, output;
	ArrayList<JTextField> txt;
	ArrayList<JComboBox> combo;
	ArrayList<JComboBox> calcChoice;
	ArrayList<String> txtTitle;
	ArrayList<String[]> comboTitle;
	static String[] diffChoice = {"Euclidean", "Difference"};
	public KNNView()
	{
		super("KNN");
		txt = new ArrayList<>();
		combo = new ArrayList<>();
		txtTitle = new ArrayList<>();
		comboTitle = new ArrayList<>();
		calcChoice = new ArrayList<>();
		JMenuBar menuBar = new JMenuBar();
		JMenu menuCreate = new JMenu("Example");
		create = new JMenuItem("Create");
		add = new JMenuItem("Add");
		calculate = new JMenuItem("Calculate");
		calculate.setEnabled(false);
		add.setEnabled(false);
		menuCreate.add(create);
		menuCreate.add(add);
		menuCreate.add(calculate);
		menuBar.add(menuCreate);
		setJMenuBar(menuBar);
		setLayout (new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)); 
		this.addTextField("Coordinates");
		this.addDropdown(new String[]{"Dank", "Not Dank"});
		this.addTextField("sq. ft");
		this.addDropdown(new String[]{"New", "Old"});
		addNewExample();
		addNewExampleCase();
		addNewExampleCase();
		addNewExampleCase();
		addNewExampleCase();
		addNewExampleCase();
		addNewExampleCase();
		addNewExampleCase();
		addNewExampleCase();
		addNewExampleCase();
		addNewExampleCase();
		addNewExampleCase();
		addNewExampleCase();
		setSize(800,800);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void update(Observable obs, java.lang.Object obj)
	{
		
	}
	public void addNewExample()
	{
		input = new JPanel();
		input.setPreferredSize(new Dimension(800,200));
//		input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));
		input.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		output = new JPanel();
		output.setPreferredSize(new Dimension(800,-200));
		output.setBorder(BorderFactory.createTitledBorder("Output"));
		add(input);
		add(output);
		output.add(new JLabel("test"));
		add.setEnabled(true);
	}
	public void addNewExampleCase()
	{
//		String[] age = {"New", "Old"};
		FlowLayout flow = new FlowLayout();
		flow.setHgap(5);
//		JTextField f1 = new JTextField("Coordinates");
//		JTextField f2 = new JTextField("sq. ft");
//		JTextField f3 = new JTextField("Price");
//		f1.setPreferredSize(new Dimension(100, 20));
//		f2.setPreferredSize(new Dimension(100, 20));
//		f3.setPreferredSize(new Dimension(100, 20));
//		JComboBox combo = new JComboBox(age);
		JPanel p = new JPanel();
		p.setLayout(flow);
//		p.add(f1);
//		p.add(f2);
//		p.add(combo);
//		p.add(f3);
		for (String str : txtTitle)
		{
//			JTextField f = (JTextField) z;
//			if (z instanceof JTextField)
//			{
//				System.out.println("s");
//				z.setPreferredSize(new Dimension(100, 20));
//			}
			JLabel l = new JLabel(str);
			JTextField f = new JTextField();
			JComboBox j = new JComboBox(diffChoice);
			txt.add(f);
			calcChoice.add(j);
			f.setPreferredSize(new Dimension(75, 20));
			p.add(l);
			p.add(f);
			p.add(j);
		}
		for (String[] str : comboTitle)
		{
			JComboBox b = new JComboBox(str);
			combo.add(b);
			p.add(b);
		}
		p.setBorder(BorderFactory.createLineBorder(Color.black));
		input.add(p);
		calculate.setEnabled(true);
	}
	public void addTextField(String s)
	{
//		JTextField f = new JTextField(s);
//		f.setPreferredSize(new Dimension(100, 20));
//		comp.add(f);
		txtTitle.add(s);
	}
	public void addDropdown(String[] s)
	{
//		JComboBox c = new JComboBox(s);
//		comp.add(c);
		comboTitle.add(s);
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
