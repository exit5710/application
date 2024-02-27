package training.algorithm.addCycle;

import config.Common;

public class AddCycle {
	public static void main(String[] args) {
		int number = Common.setInputInteger();
		int temp = number;
		int count = 1;

		while (true) {
			int oneDigits = temp % 10;
			int tenDigits = temp / 10;
			int addNumber = oneDigits + tenDigits;
			int cycleNumber = oneDigits * 10 + addNumber % 10;

			if (number == cycleNumber) {
				break;
			}

			temp = cycleNumber;
			count++;
		}

		System.out.println("addCycle : " + count);
	}
}