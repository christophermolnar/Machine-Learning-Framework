/** VideoGames		Video Game Class, example class of data objects
 *  
 * @author MZGA
 * @version 1.0	
 *
 */
public class VideoGames {
	private NUMclass price;
	private Age age;
	private NUMclass hoursOfGameplay;
	private NUMclass yearReleased;
	
	//Create new video game
	public VideoGames(NUMclass price, Age age, NUMclass hoursOfGameplay, NUMclass yearReleased){
		this.price = price;
		this.age = age;
		this.hoursOfGameplay = hoursOfGameplay;
		this.yearReleased = yearReleased;
	}
	
	/** getPrice() 			Return Price of specified video game
	 * 
	 * @return price		Price of Video Game
	 */
	public NUMclass getPrice() {
		return price;
	}
	
	/** setPrice(NUMclass price)	Set price of specified video game
	 * 
	 * @param price					Price of video game
	 */
	public void setPrice(NUMclass price) {
		this.price = price;
	}
	
	/** getAge() 			Return Age of specified video game
	 * 
	 * @return age			Age of Video Game
	 */
	public Age getAge() {
		return age;
	}
	
	/** setAge(Age age) 	Set age of specified video game
	 * 
	 * @param age			Age of Video Game
	 */
	public void setAge(Age age) {
		this.age = age;
	}
	
	/** getHoursOfGameplay()	Return hoursOfGameplay of specified video game	
	 * 
	 * @return hoursOfGameplay	Hours of estimated gameplay time for a specified video game	
	 */
	public NUMclass getHoursOfGameplay() {
		return hoursOfGameplay;
	}
	
	/** setHoursOfGameplay(NUMclass hoursOfGameplay)	Set hoursOfGameplay for a specified video game
	 * 
	 * @param hoursOfGameplay							Hours of estimated gameplay time for a specified video game
	 */
	public void setHoursOfGameplay(NUMclass hoursOfGameplay) {
		this.hoursOfGameplay = hoursOfGameplay;
	}
	
	/** getYearReleased()		Return yearReleased for a specified video game
	 * 
	 * @return yearReleased		Year of release for a video game
	 */
	public NUMclass getYearReleased() {
		return yearReleased;
	}
	
	/** setYearReleased(NUMclass yearReleased) 		Set yearReleaed for a specified video game
	 * 
	 * @param yearReleased 							Year of release for a video game
	 */
	public void setYearReleased(NUMclass yearReleased) {
		this.yearReleased = yearReleased;
	}
}