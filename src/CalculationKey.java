
public class CalculationKey implements Calculation{

	public double calculate(Attribute a, Attribute b)
	{
		//Key wordA = (Key) a;
		if (a.equals(b)) return 0;
		else return 1;
	}
	public String toString()
	{
		return "Enum";
	}
}
