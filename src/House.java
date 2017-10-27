
public class House {

	private NUMclass price;
	private ENUMclass age;
	private POINTclass coordinates;
	private NUMclass sqfoot; 
	
	public House(POINTclass coordinates, NUMclass sqfoot, ENUMclass age, NUMclass price){
		this.price = price;
		this.age = age;
		this.sqfoot = sqfoot;
		this.coordinates = coordinates;
	}

	public NUMclass getPrice() {
		return price;
	}


	public ENUMclass getAge() {
		return age;
	}


	public POINTclass getCoordinates() {
		return coordinates;
	}


	public NUMclass getSqfoot() {
		return sqfoot;
	}

	
}