package training.algorithm.averageRatio;

import config.Common;

public class AverageRatio {
	public static void main(String[] args) {
		String[] listValue = Common.setInputArray();
		int[] arrayNumber = Common.getArrayNumber(listValue);

		double sum = 0;
		for (int value : arrayNumber) {
			sum += value;
		}

		double average = sum / arrayNumber.length;

		int count = 0;
		for (int value : arrayNumber) {
			if (value >= average) {
				count++;
			}
		}

		double ratio = count / (double) arrayNumber.length * 100;

		System.out.println("average : " + average);
		System.out.println("ratio : " + ratio);
	}
}