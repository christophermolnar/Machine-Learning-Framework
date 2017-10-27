import java.util.ArrayList;

public class Project {
	
	public static ArrayList<House> house_list = new ArrayList<>();
	public static ArrayList<POINTclass> house_coordinates = new ArrayList<>();
	public static ArrayList<NUMclass> house_sqrft = new ArrayList<>();
	public static ArrayList<Age> house_age = new ArrayList<>();
	public static ArrayList<NUMclass> house_price = new ArrayList<>();
	
	public static void main (String args[])
	{
		House h1 = new House(new POINTclass(12,25), new NUMclass(1200), Age.NEW, new NUMclass(500000));
		House h2 = new House(new POINTclass(10,50), new NUMclass(1000), Age.OLD, new NUMclass(300000));
		House h3 = new House(new POINTclass(30,100), new NUMclass(800), Age.NEW, new NUMclass(400000));
		House h4 = new House(new POINTclass(15,20), new NUMclass(1000), Age.NEW, null);
		
		house_list.add(h1);
		house_list.add(h2);
		house_list.add(h3);
		
		for (House h : house_list){
			house_coordinates.add(h.getCoordinates());
			house_sqrft.add(h.getSqrft());
			house_age.add(h.getAge());
			house_price.add(h.getPrice());
		}
		
		int best_coordinates = h4.getCoordinates().findClosest(house_coordinates);
		int best_sqrft = h4.getSqrft().findClosest(house_sqrft);
		//int best_age = h4.getAge().findClosest(house_age, h4.getAge()); 
		
		float price = (house_price.get(best_coordinates).getNUM() + house_price.get(best_sqrft).getNUM())/2;
		
		System.out.println("bc " + best_coordinates);
		System.out.println("bs " + best_sqrft);
		System.out.println("Final Price of h4 should be $" + price);
		
	}
}
