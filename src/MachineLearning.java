import userInterface.KNNController;
import userInterface.KNNView;
import machineLearning.*;
public class MachineLearning {

	public static void main(String[] args) {
		Project p = new Project();
		KNNView v = new KNNView(p.getList());
		KNNController c = new KNNController(v, p);
		
		//UNCOMMENT TO AUTO FILL SOCCOR DATA
		//p.soccerScenario();
	}
	public static void importProject(Project p)
	{
		
	}
}
