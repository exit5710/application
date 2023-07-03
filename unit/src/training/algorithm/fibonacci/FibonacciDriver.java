package training.algorithm.fibonacci;

public class FibonacciDriver {
	// 1 1 2 3 5 8 13 21 34 55 ...
	// An = An-1 + An+2 ...
	public static void main(String[] args) {
		// type1
		int[] fibonacci = new int[30];
		fibonacci[1] = 1;
		fibonacci[2] = 1;

		for (int i = 3; i < fibonacci.length; i++) {
			fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
		}

		for (int i = 1; i < fibonacci.length; i++) {
			System.out.print(fibonacci[i] + " ");
		}

		// type2
		int firstNum = 1;
		int secondNum = 1;

		System.out.print("\n" + firstNum + " " + secondNum + " ");

		for (int i = 3; i < 30; i++) {
			int currentNum = firstNum + secondNum;
			System.out.print(currentNum + " ");

			firstNum = secondNum;
			secondNum = currentNum;
		}
	}
}