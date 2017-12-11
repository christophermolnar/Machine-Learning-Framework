package main;
import userInterface.KNNController;
import userInterface.KNNView;
import machineLearning.*;

/** MachineLearning 		The main class for our project
 * 
 *@author MZGA
 *@version 4.0
 *
 */
public class MachineLearning {
	private static KNNModel p;
	private static KNNView v;
	private static KNNController c;
	public static void main(String[] args) {
		p = new KNNModel();
		v = new KNNView(p.getList());
		c = new KNNController(v, p);
	}
	
	/** importProject			Load a previous saved file
	 * 
	 * @param newP				The new file to load in
	 */
	public static void loadProject(KNNModel newP) {
		v.close();
		p = newP;
		v = new KNNView(p.getList());
		c = new KNNController(v, p);
		if (p.getCreated()) {
			v.created();
		}
		if (p.getTraining()) {
			v.training();
		}
		if (p.getTesting()) {
			v.tested();
		}
	}
}
