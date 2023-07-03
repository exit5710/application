package training.algorithm.multiplication;

import java.util.Scanner;

public class Multiplication {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		String[] listValue = input.split(" ");

		for (int i = 1; i <= 9; i++) {
			for (String number : listValue) {
				System.out.printf("%1d * %1d = %2d   ", Integer.parseInt(number), i, Integer.parseInt(number) * i);
			}

			System.out.println("\b\b\b");
		}
	}
}