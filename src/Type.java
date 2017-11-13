
public abstract class Type {
	protected Calculation distanceSelection;
	public void setSelection(Calculation d)
	{
		distanceSelection = d;
	}
	public Calculation getSelection()
	{
		return distanceSelection;
	}
	abstract double getDistance(Type compare);
}
