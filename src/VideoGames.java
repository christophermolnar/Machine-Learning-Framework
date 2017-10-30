
public class VideoGames {
	private NUMclass price;
	private Age age;
	private NUMclass hoursOfGameplay;
	private NUMclass yearReleased;
	public VideoGames(NUMclass price, Age age, NUMclass hoursOfGameplay, NUMclass yearReleased)
	{
		this.price = price;
		this.age = age;
		this.hoursOfGameplay = hoursOfGameplay;
		this.yearReleased = yearReleased;
	}
	public NUMclass getPrice() {
		return price;
	}
	public void setPrice(NUMclass price) {
		this.price = price;
	}
	public Age getAge() {
		return age;
	}
	public void setAge(Age age) {
		this.age = age;
	}
	public NUMclass getHoursOfGameplay() {
		return hoursOfGameplay;
	}
	public void setHoursOfGameplay(NUMclass hoursOfGameplay) {
		this.hoursOfGameplay = hoursOfGameplay;
	}
	public NUMclass getYearReleased() {
		return yearReleased;
	}
	public void setYearReleased(NUMclass yearReleased) {
		this.yearReleased = yearReleased;
	}
}