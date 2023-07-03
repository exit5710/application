package training.algorithm.commonDivisor;

import java.util.Scanner;

public class CommonDivisorDriver {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		String[] listValue = input.split(" ");

		int small;
		int big;

		if (Integer.parseInt(listValue[0]) > Integer.parseInt(listValue[1])) {
			small = Integer.parseInt(listValue[1]);
			big = Integer.parseInt(listValue[0]);
		} else {
			small = Integer.parseInt(listValue[0]);
			big = Integer.parseInt(listValue[1]);
		}

		int divisor = 1;

		for (int i = 1; i <= small; i++) {
			if (small % i == 0 && big % i == 0) {
				divisor = i;
			}
		}

		System.out.println("divisor : " + divisor);
	}
}