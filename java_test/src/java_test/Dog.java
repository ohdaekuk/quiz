package java_test;

public class Dog {
	
	private String name;
	private int age;
	
	public Dog(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public void bark() {
		for(int i = 0; i <10; i++) {
			System.out.println("멍멍멍");
		}
	}
	
	
}
