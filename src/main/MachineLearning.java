package main;
import userInterface.KNNController;
import userInterface.KNNView;
import machineLearning.*;
public class MachineLearning {
	private static Project p;
	private static KNNView v;
	private static KNNController c;
	public static void main(String[] args) {
		p = new Project();
		v = new KNNView(p.getList());
		c = new KNNController(v, p);
		
		//UNCOMMENT TO AUTO FILL SOCCOR DATA
		//p.soccerScenario();
	}
	public static void importProject(Project newP)
	{
		v.close();
		p = newP;
		v = new KNNView(p.getList());
		c = new KNNController(v, p);
	}
}
