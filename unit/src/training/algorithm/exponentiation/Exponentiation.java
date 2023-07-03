package training.algorithm.exponentiation;

import java.util.Scanner;

public class Exponentiation {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		String[] listValue = input.split(" ");

		int number = Integer.parseInt(listValue[0]);
		int exponentiation = Integer.parseInt(listValue[1]);
		int result = 1;

		for (int i = 0; i < exponentiation; i++) {
			result *= number;
		}

		System.out.println("exponentiation : " + result);
	}
}