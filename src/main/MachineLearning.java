package main;
import userInterface.KNNController;
import userInterface.KNNView;
import machineLearning.*;
public class MachineLearning {
	private static KNNModel p;
	private static KNNView v;
	private static KNNController c;
	public static void main(String[] args) {
		p = new KNNModel();
		v = new KNNView(p.getList());
		c = new KNNController(v, p);
		
		//UNCOMMENT TO AUTO FILL SOCCOR DATA
		//p.soccerScenario();
	}
	public static void importProject(KNNModel newP)
	{
		v.close();
		p = newP;
		v = new KNNView(p.getList());
		c = new KNNController(v, p);
		if (p.getCreated())
		{
			v.created();
		}
		if (p.getTraining())
		{
			v.training();
		}
		if (p.getTesting())
		{
			v.tested();
		}
	}
}
