package training.algorithm.binaryCoded;

import java.util.ArrayList;
import java.util.Scanner;

public class BinaryDriver {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int quotient = scanner.nextInt();

		ArrayList<Integer> binaryList = new ArrayList<>();

		while (quotient > 0) {
			binaryList.add(quotient % 2);
			quotient = quotient / 2;
		}

		for (int i = binaryList.size(); i > 0; i--) {
			System.out.print(binaryList.get(i - 1));
		}
	}
}