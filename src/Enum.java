import java.util.ArrayList;

public enum Enum {
	NEW("new"), 
	OLD("old");

	private String enums;

	/** Age()		Sets the value of Age passed by user
	 * 
	 * @param age	Age passed by user ('new' OR 'old')
	 */
	Enum(String enums){
		this.enums = enums;
	}
}
