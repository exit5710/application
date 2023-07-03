package training.algorithm.digit;

public class DigitAdd {
	public static void main(String[] args) {
		int number = 1242;
		int numberAdd = 0;

		for (int i = 0; i < String.valueOf(number).length(); i++) {
			numberAdd += Integer.parseInt( String.valueOf(number).charAt(i) + "");
		}

		System.out.println(numberAdd);
	}
}