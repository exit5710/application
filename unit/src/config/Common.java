package config;

import java.util.Scanner;

public class Common {
	public static String setInputString() {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		scanner.close();

		return input;
	}

	public static int setInputInteger() {
		Scanner scanner = new Scanner(System.in);
		int input = scanner.nextInt();
		scanner.close();

		return input;
	}

	public static String[] setInputArray() {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		scanner.close();

		return input.split(" ");
	}

	public static int getMaxValue(int[] valueList) {
		int maxValue = 0;

		for (int value : valueList) {
			if (value > maxValue) {
				maxValue = value;
			}
		}

		return maxValue;
	}

	private static boolean isNumeric(String value) {
		// value.matches("[+-]?\\d*(\\.\\d+)?");
		boolean bool = true;

		try {
			Integer.parseInt(value);
		} catch (NumberFormatException e) {
			bool = false;
		}

		return bool;
	}

	public static int[] getArrayNumber(String[] listValue) {
		int[] arrayNumber = new int[listValue.length];
		int count = 0;

		for (String value : listValue) {
			if (isNumeric(value)) {
				arrayNumber[count] = Integer.parseInt(value);
				count++;
			}
		}

		int[] newArray = new int[count];
		System.arraycopy(arrayNumber, 0, newArray, 0, count);

		return newArray;
	}
}