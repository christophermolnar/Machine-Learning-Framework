import java.util.ArrayList;

public class CalculationDifference implements Calculation{

	public double calculate(Attribute a, Attribute b)
	{
		if (a instanceof Point && b instanceof Point){
			ArrayList<Double> A = ((Point)a).getNums();
			ArrayList<Double> B = ((Point)b).getNums();
			
			if(B.size() != A.size()){
				System.out.print("Coordinate Dimensions Mismatch: ");
			} else {
				Double dist = 0.0;
				for(int i = 0; i < A.size(); i++){
					dist = dist + Math.abs((A.get(i) - B.get(i)));
				}
				return round(dist, 4);
			}
		}
		else if (a instanceof Num && b instanceof Num){
			Double dist = 0.0;
			Num A = ((Num) a);
			Num B = ((Num) b);
			dist = Math.abs(A.getNum() - B.getNum());
			return round(dist, 4);
		}
		return -1.0; //Not known type
	}
	
	//Rounds value to specified decimal precision (d)
	public static double round(double value, int d) {
		if (d < 0) throw new IllegalArgumentException();
	    return Math.round(value*Math.pow(10, d))/Math.pow(10, d);
	}
	public String toString()
	{
		return "Difference";
	}
}
