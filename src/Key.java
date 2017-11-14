
public class Key extends Attribute{
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
	
	public double getDistance(Attribute compare){
		return (new CalculationKey()).calculate(this, compare);
	}
	public String getVal()
	{
		return word;
	}
}
