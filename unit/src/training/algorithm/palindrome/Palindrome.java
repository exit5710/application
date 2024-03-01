package training.algorithm.palindrome;

import config.Common;

public class Palindrome {
	public static void main(String[] args) {
		String value = Common.setInputString();
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = value.length() - 1; i >= 0; i--) {
			stringBuilder.append(value.charAt(i));
		}

		if (value.equals(stringBuilder.toString())) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
	}
}