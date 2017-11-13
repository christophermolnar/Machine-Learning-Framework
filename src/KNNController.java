import java.awt.event.*;
public class KNNController {
	KNNView v;
	Project p;
	public KNNController(KNNView v, Project p)
	{
		this.p = p;
		this.v = v;
//		v.setCreateActionListener(new create());
		v.setCreateTestingActionListener(new testing());
		v.setCreateTrainingActionListener(new training());
		v.setAddActionListener(new add());
		v.setCalculateActionListener(new calculate());
	}
//	class create implements ActionListener{
//		public void actionPerformed(ActionEvent e)
//		{
//			p.create();
//		}
//	}
	class testing implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			//p.create();
			p.testing();
			System.out.println("Testing 123");
		}
	}
	class training implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			p.create();
			System.out.println("Training 456");
		}
	}
	class add implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			p.add();
		}
	}
	class calculate implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			p.calculate();
		}
	}
}
