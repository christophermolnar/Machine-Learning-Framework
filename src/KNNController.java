import java.awt.event.*;
public class KNNController {
	KNNView v;
	Project p;
	public KNNController(KNNView v, Project p)
	{
		this.p = p;
		this.v = v;
		this.p.addObserver(this.v);
		v.setCreateActionListener(new create());
		v.setTestingActionListener(new testing());
		v.setTrainingActionListener(new training());
		v.setEditActionListener(new edit());
		v.setCalculateActionListener(new calculate());
	}
	class create implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			p.create();
		}
	}
	class testing implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			p.testing();
		}
	}
	class training implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			p.training();
		}
	}
	class edit implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			Example selected = v.getSelectedObject();
			int selectedIndex = v.getJlistIndex();
			if(v.getJlistIndex()>=0)
				p.edit(selected, selectedIndex);
			//Else Nothing Selected
		}
	}
	class calculate implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			p.calculate();
		}
	}
}
