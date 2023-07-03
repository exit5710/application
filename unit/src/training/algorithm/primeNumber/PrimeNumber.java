package training.algorithm.primeNumber;

import java.util.Scanner;

public class PrimeNumber {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int number = scanner.nextInt();
		boolean isPrimeNumber = true;

		for (int i = 2; i <= number / 2; i++) {
			if (number % i == 0) {
				isPrimeNumber = false;
				break;
			}
		}

		if (isPrimeNumber) {
			System.out.println("소수 입니다.");
		} else {
			System.out.println("소수가 아닙니다.");
		}
	}
}