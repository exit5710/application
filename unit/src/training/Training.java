package training;

import java.util.ArrayList;

public class Training {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void sayHello() {
		System.out.println("this.name " + this.name);
		System.out.println("this.getName() " + this.getName());
	}

	public static void main(String[] args) {
		Training training = new Training();
		training.setName("kiYoung");
		System.out.println(training.getName());
		training.sayHello();

		ArrayList<String> strList = new ArrayList<String>();
		strList.add("kiYoung");
		strList.add("java");
		strList.add("dream");

		for (String str : strList) {
			System.out.println(str);
		}

		System.out.println("apple\"");
	}
}