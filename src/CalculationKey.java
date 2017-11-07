
public class CalculationKey implements Calculation{
	public CalculationKey()
	{
	}
	public double calculate(Type a, Type b)
	{
		//Key wordA = (Key) a;
		if (a.equals(b)) return 0;
		else return 1;
	}
}
