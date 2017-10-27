import java.util.ArrayList;

public enum Age {
	NEW("new"),
	OLD("old");

	private String age;

	Age(String age){
		this.age = age;
	}
	
	public int findClosest(ArrayList<Age> Objects){
		
		return 0;
	}
	
	public String getAge() {
        return age;
    }
	
	public String toString(){
		return this.age;
	}
}

