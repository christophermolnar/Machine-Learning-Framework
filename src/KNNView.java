import javax.swing.*;
import java.awt.*;
import java.util.*;
public class KNNView extends JFrame implements Observer{
	JMenuItem create, add;
	JPanel input, output;
	ArrayList<JTextField> txt;
	ArrayList<JComboBox> combo;
	ArrayList<String> txtTitle;
	ArrayList<String[]> comboTitle;
	public KNNView()
	{
		super("KNN");
		txt = new ArrayList<>();
		combo = new ArrayList<>();
		txtTitle = new ArrayList<>();
		comboTitle = new ArrayList<>();
		JMenuBar menuBar = new JMenuBar();
		JMenu menuCreate = new JMenu("Example");
		create = new JMenuItem("Create");
		add = new JMenuItem("Add");
		add.setEnabled(false);
		menuCreate.add(create);
		menuCreate.add(add);
		menuBar.add(menuCreate);
		setJMenuBar(menuBar);
		setLayout (new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)); 
		this.addTextField("Coordinates");
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
		output.setPreferredSize(new Dimension(800,0));
		output.setBorder(BorderFactory.createTitledBorder("Output"));
		add(input);
		add(output);
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
			f.setPreferredSize(new Dimension(60, 20));
			p.add(l);
			p.add(f);
			p.add(new JComboBox(new String[]{"Euclidean", "Difference"}));
		}
		for (String[] str : comboTitle)
		{
			JComboBox b = new JComboBox(str);
			p.add(b);
		}
		input.add(p);
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
}
