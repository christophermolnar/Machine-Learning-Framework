/** House		House Class, example class of data objects
 *  
 * @author MZGA
 * @version 1.0	
 *
 */
public class House {

	private NUMclass price;
	private Age age;
	private POINTclass coordinates;
	private NUMclass sqfoot; 
	
	//Create new house
	public House(POINTclass coordinates, NUMclass sqfoot, Age age, NUMclass price){
		this.price = price;
		this.age = age;
		this.sqfoot = sqfoot;
		this.coordinates = coordinates;
	}

	/** getPrice() 		Return Price of specified House class
	 * 
	 * @return price	Price of House
	 */
	public NUMclass getPrice() {
		return price;
	}

	/** getAge() 		Return Age of specified House class
	 * 
	 * @return age		Age of House
	 */
	public Age getAge() {
		return age;
	}

	/** getLocation() 		Return Location of specified House class
	 * 
	 * @return  coordinates	Location of House
	 */
	public POINTclass getCoordinates() {
		return coordinates;
	}

	/** getSqrft() 		Return Size of specified House class in feet
	 * 
	 * @return sqfoot	Size of House
	 */
	public NUMclass getSqrft() {
		return sqfoot;
	}
}