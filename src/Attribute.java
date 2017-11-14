 
public abstract class Attribute {
	protected Calculation distanceSelection;
	public void setSelection(Calculation d)
	{
		distanceSelection = d;
	}
	public Calculation getSelection()
	{
		return distanceSelection;
	}
	abstract double getDistance(Attribute compare);
	//abstract java.lang.Object getVal();
}
