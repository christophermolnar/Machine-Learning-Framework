import java.util.ArrayList;

public class POINTclass implements Attributes {
	private final double x;
	private final double y;
	
	//Create new POINT
	public POINTclass(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	//Return X position of POINT
	public double getX() {
		return x;
	}

	//Return Y position of POINT
	public double getY() {
		return y;
	}
	
	//Convert POINT to String
	public String toString(){
		return ("(" + x + "," + y + ")");
	}

	//Returns POINT closest to Compare
	public POINTclass findClosest(ArrayList<POINTclass> Objects, POINTclass Compare){
		POINTclass closest = null;
		int i = 0;
		int closestIndex = 0;
		
		for(POINTclass o : Objects){
			i++;
			if(closest == null){
				closest = o;
				closestIndex = i;
			}
			if(distance(Objects.get(i), Compare) < distance(closest, Compare)){
				closest = o;
				closestIndex = i;
			}
		}
		return closest;
		//return closestIndex;
	}
	
	//Returns linear position of a point; sums x and y
	public double position(){
		return (this.getX()+this.getY())/2;
	}
	
	//Returns absolute distance between two points
	public double distance(POINTclass o, POINTclass p){
		return Math.abs(o.position() - p.position());
	}
	
	public Boolean equals(POINTclass p){
		if(this.getX() == p.getX() && this.getY() == p.getY())
			return true;
		return false; 
	}
}
