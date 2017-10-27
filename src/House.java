
public class House {

	private INTclass price;
	private ENUMclass age;
	private POINTclass coordinates;
	private INTclass sqfoot; 
	
	public House(POINTclass coordinates, INTclass sqfoot, ENUMclass age, INTclass price){
		this.price = price;
		this.age = age;
		this.sqfoot = sqfoot;
		this.coordinates = coordinates;
	}

	public INTclass getPrice() {
		return price;
	}


	public ENUMclass getAge() {
		return age;
	}


	public POINTclass getCoordinates() {
		return coordinates;
	}


	public INTclass getSqfoot() {
		return sqfoot;
	}

	
}