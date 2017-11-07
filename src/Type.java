
public abstract class Type {
	private DistanceCalculation distanceSelection;
	public void setSelection(DistanceCalculation d)
	{
		distanceSelection = d;
	}
	public DistanceCalculation getSelection()
	{
		return distanceSelection;
	}
}
