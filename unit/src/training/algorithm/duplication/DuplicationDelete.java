package training.algorithm.duplication;

import config.Common;

public class DuplicationDelete {
	public static void main(String[] args) {
		String[] listValue = Common.setInputArray();
		int[] arrayNumber = Common.getArrayNumber(listValue);
		int maxValue = Common.getMaxValue(arrayNumber);
		Boolean[] arrayBoolean = new Boolean[maxValue + 1];

		for (int number : arrayNumber) {
			arrayBoolean[number] = true;
		}

		for (int i = 0; i < arrayBoolean.length; i++) {
			if (arrayBoolean[i] != null && arrayBoolean[i]) {
				System.out.printf("%3d", i);
			}
		}
	}
}