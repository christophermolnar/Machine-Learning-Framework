import java.awt.event.*;
public class KNNController {
	KNNView v;
	public KNNController(KNNView v)
	{
		this.v = v;
		v.setCreateActionListener(new create());
		v.setAddActionListener(new add());
		v.setCalculateActionListener(new calculate());
	}
	class create implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("create");
		}
	}
	class add implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("add");
		}
	}
	class calculate implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("calculate");
		}
	}
}
