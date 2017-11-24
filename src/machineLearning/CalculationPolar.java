package machineLearning;


public class CalculationPolar implements Calculation {

	
	public double calculate(Attribute a, Attribute b) {
		double distance = -1;
		if (a instanceof Num && b instanceof Num){
			
			Num numberA = ((Num) a);
			Num numberB = ((Num) b);
			
			double numberValueA = convertToPolar(numberA);
			double numberValueB = convertToPolar(numberB);
			
			distance = Math.min(Math.abs(numberValueA - numberValueB), Math.abs(numberValueB - numberValueA));
			
			return round(distance, 4);
		}
		
		return -1; // Unknown type
	}
	
	private boolean isPolar(double currentNumber){
		if (0 <= currentNumber && currentNumber <= 359){
			return true;
		}
		return false;
	}
	
	private double convertToPolar(Num currentNum){
		double currentNumberValue = currentNum.getNum();
		while (!isPolar(currentNumberValue)){
			if (currentNumberValue < 0){
				currentNumberValue += 360;
			}
			else if (currentNumberValue > 359){
				currentNumberValue -= 360;
			}
		}
		return currentNumberValue;
	}
	
	public static double round(double value, int d) {
		if (d < 0) throw new IllegalArgumentException();
	    return Math.round(value*Math.pow(10, d))/Math.pow(10, d);
	}

	public String toString() {
		return "Polar";
	}
	
	
}
