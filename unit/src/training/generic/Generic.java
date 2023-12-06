package training.generic;

class Man<T> {
	private T temp;

	public T getTemp() {
		return temp;
	}

	public void setTemp(T temp) {
		this.temp = temp;
	}
}

public class Generic {
	public static void main(String[] args) {
		Man<String> manString = new Man<>();
		manString.setTemp("String");
		System.out.println(manString.getTemp());

		Man<Integer> manInteger = new Man<>();
		manInteger.setTemp(20);
		System.out.println(manInteger.getTemp());
	}
}