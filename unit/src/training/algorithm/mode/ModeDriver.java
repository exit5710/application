package training.algorithm.mode;

import java.util.ArrayList;
import java.util.Scanner;

public class ModeDriver {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		String[] listValue = input.split(" ");
		ArrayList<Integer> integerList = new ArrayList<>();

		int maxValue = 0;
		for (String value : listValue) {
			try {
				int getValue = Integer.parseInt(value);
				if (maxValue < getValue) {
					maxValue = getValue;
				}
				integerList.add(getValue);
			} catch (NumberFormatException e) {
				e.fillInStackTrace();
			}
		}

		int[] mode = new int[maxValue + 1];
		for (Integer integer : integerList) {
			mode[integer]++;
		}

		int modeValue = 0;
		int count = 0;
		for (int i = 0; i < mode.length; i++) {
			if (count < mode[i]) {
				count = mode[i];
				modeValue = i;
			}
		}

		System.out.println("mode : " + modeValue + ", count : " + count);
	}
}