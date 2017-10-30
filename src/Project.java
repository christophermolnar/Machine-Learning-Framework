import java.util.ArrayList;

public class Project {
	public static ArrayList<VideoGames> videogames = new ArrayList<>();
	public static ArrayList<House> house_list = new ArrayList<>();
	
	public static ArrayList<POINTclass> house_coordinates = new ArrayList<>();
	public static ArrayList<NUMclass> house_sqrft = new ArrayList<>();
	public static ArrayList<Age> house_age = new ArrayList<>();
	public static ArrayList<NUMclass> house_price = new ArrayList<>();
	
	public static ArrayList<NUMclass> videogames_yearReleased = new ArrayList<>();
	public static ArrayList<NUMclass> videogames_hours = new ArrayList<>();
	public static ArrayList<Age> videogames_age = new ArrayList<>();
	public static ArrayList<NUMclass> videogames_price = new ArrayList<>();
	
	public static void main (String args[])
	{
		House h1 = new House(new POINTclass(12,25), new NUMclass(1200), Age.NEW, new NUMclass(500000));
		House h2 = new House(new POINTclass(10,50), new NUMclass(1000), Age.OLD, new NUMclass(300000));
		House h3 = new House(new POINTclass(30,100), new NUMclass(800), Age.NEW, new NUMclass(400000));
		House h4 = new House(new POINTclass(15,20), new NUMclass(1000), Age.NEW, null);
		
		videogames.add(new VideoGames(new NUMclass(80), Age.NEW, new NUMclass(120), new NUMclass(2017)));
		videogames.add(new VideoGames(new NUMclass(20), Age.NEW, new NUMclass(30), new NUMclass(2015)));
		videogames.add(new VideoGames(new NUMclass(40), Age.OLD, new NUMclass(50), new NUMclass(2012)));
		VideoGames testVideoGame = new VideoGames(null, Age.NEW, new NUMclass(70), new NUMclass(2013)); 
		house_list.add(h1);
		house_list.add(h2);
		house_list.add(h3);
		
		for (House h : house_list){
			house_coordinates.add(h.getCoordinates());
			house_sqrft.add(h.getSqrft());
			house_age.add(h.getAge());
			house_price.add(h.getPrice());
		}
		for (VideoGames v : videogames){
			videogames_yearReleased.add(v.getYearReleased());
			videogames_hours.add(v.getHoursOfGameplay());
			videogames_age.add(v.getAge());
			videogames_price.add(v.getPrice());
		}
		int bestHours = testVideoGame.getHoursOfGameplay().findClosest(videogames_hours);
		int bestAge = testVideoGame.getAge().findClosest(videogames_age);
		int bestYear = testVideoGame.getYearReleased().findClosest(videogames_yearReleased);
		
		int best_coordinates = h4.getCoordinates().findClosest(house_coordinates);
		int best_sqrft = h4.getSqrft().findClosest(house_sqrft);
		//int best_age = h4.getAge().findClosest(house_age, h4.getAge()); 
		
		float videogamePrice = (videogames_price.get(bestHours).getNUM() + videogames_price.get(bestYear).getNUM() + videogames_price.get(bestAge).getNUM())/3;
		float price = (house_price.get(best_coordinates).getNUM() + house_price.get(best_sqrft).getNUM())/2;
		
		System.out.println("bc " + best_coordinates);
		System.out.println("bs " + best_sqrft);
		System.out.println("Final Price of h4 should be $" + price);
		System.out.println("The price of the test videogame is $" + videogamePrice);
	}
}
