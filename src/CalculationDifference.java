import java.util.ArrayList;

public class CalculationDifference implements Calculation{

	public double calculate(Type a, Type b)
	{
		if (a instanceof Point && b instanceof Point){
			ArrayList<Double> A = ((Point)a).getNums();
			ArrayList<Double> B = ((Point)b).getNums();
			
			if(B.size() != A.size()){
				System.out.print("Coordinate Dimensions Mismatch: ");
			} else {
				Double dist = 0.0;
				for(int i = 0; i < A.size(); i++){
					dist = dist + (A.get(i) - B.get(i));
				}
				dist = Math.sqrt(dist);
				return round(dist, 4);
			}
		}
		return -1.0; //Not known type
	}
	
	//Rounds value to specified decimal precision (d)
	public static double round(double value, int d) {
		if (d < 0) throw new IllegalArgumentException();
	    return Math.round(value*Math.pow(10, d))/Math.pow(10, d);
	}
}
