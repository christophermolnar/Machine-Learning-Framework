import java.util.ArrayList;

public enum Age {
	NEW("new"),
	OLD("old");

	private String age;

	Age(String age){
		this.age = age;
	}
	
	//Findest first occurrence of matching 'Age' attrubute
	public int findClosest(ArrayList<Age> Objects){
		for(Age a : Objects){
			int i = 0;
			if(this.getAge().equals(a.getAge())){
				return i; 
			}
		}
		return -1; //No matching attributes
	}
	
	public String getAge() {
        return age;
    }
	
	public String toString(){
		return this.age;
	}
}

