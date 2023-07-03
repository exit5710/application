package training.algorithm.numberCount;

public class NumberCount {
	public static void main(String[] args) {
		int[] numberCount = new int[10];
		String number = "123415684156123011869567890";

		for (int i = 0; i < number.length(); i++) {
			for (int j = 0; j <= 9; j++) {
				if (String.valueOf(number.charAt(i)).equals(String.valueOf(j))) {
					numberCount[j]++;
				}
			}
		}

		for (int i = 0; i <= 9; i++) {
			System.out.println(i + " : " + numberCount[i]);
		}
	}
}