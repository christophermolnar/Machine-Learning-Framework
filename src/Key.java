
public class Key extends Type{
	private String word;
	public Key(String w)
	{
		word = w;
	}
	public String getWord()
	{
		return word;
	}
	public String toString()
	{
		return word;
	}
	
	public double getDistance(Type compare){
		return (new CalculationKey()).calculate(this, compare);
	}
	public String getVal()
	{
		return word;
	}
}
