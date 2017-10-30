import java.util.ArrayList;

/** Age(ENUM) 		Responsible for managing instances of Age String Attributes
 * 
 * @author MZGA
 * @version 1.0
 *
 */
public enum Age {
	NEW("new"),
	OLD("old");

	private String age;

	/** Age()		Sets the value of Age passed by user
	 * 
	 * @param age	Age passed by user ('new' OR 'old')
	 */
	Age(String age){
		this.age = age;
	}
	
	/** findClosest() 	Returns first occurrence of matching 'Age' attrubute
	 * 
	 * @param Objects 	ArrayList of all current AGES to compare
	 * @return int		The index in ArrayList of the first matching attribute, -1 if none match
	 */
	public int findClosest(ArrayList<Age> Objects){
		for(Age a : Objects){
			int i = 0;
			if(this.getAge().equals(a.getAge())){
				return i; 
			}
		}
		return -1; //No matching attributes
	}
	
	/** getAge()		Return the string value of age
	 * 
	 * @return String	Return age
	 */
	public String getAge() {
        return age;
    }
	
	/** toString()		Return the string value of age
	 * 
	 * 	@return			Return age
	 */	 
	public String toString(){
		return this.age;
	}
}

