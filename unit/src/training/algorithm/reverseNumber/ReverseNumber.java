package training.algorithm.reverseNumber;

import common.Common;

public class ReverseNumber {
	private int getReverse(int number) {
		int result = 0;

		while (number != 0) {
			result = result * 10 + number % 10;
			number = number / 10;
		}

		return result;
	}
	public static void main(String[] args) {
		ReverseNumber reverseNumber = new ReverseNumber();

		String[] listValue = Common.setInputArray();
		int[] arrayNumber = Common.getArrayNumber(listValue);

		int firstNumber = reverseNumber.getReverse(arrayNumber[0]);
		int secondNumber = reverseNumber.getReverse(arrayNumber[1]);

		System.out.println("reverseMaxNumber : " + Math.max(firstNumber, secondNumber));
	}
}