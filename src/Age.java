
public enum Age {
	NEW("new"),
	OLD("old");

	private String age;

	Age(String age){
		this.age = age;
	}
	
	public String getAge() {
        return age;
    }
	
	public String toString(){
		return this.age;
	}
}

