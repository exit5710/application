package training.algorithm.game369;

// 1 2 * 4 5 * 7 8 * 10 11 12 * 14 15 * 17 18 * 20 21 ..... 27 28 * * * * **(33) * * **(36) * * **(39) 40
public class Game369 {
	private void strLength(int num) {
		for (int i = 1; i <= num; i++) {
			String strNumber = String.valueOf(i);
			StringBuilder star = new StringBuilder();
			boolean bool = true;

			for (int j = 0; j < strNumber.length(); j++) {
				if (strNumber.charAt(j) == '3' || strNumber.charAt(j) == '6' || strNumber.charAt(j) == '9') {
					star.append("*");
					bool = false;
				}
			}

			String format = "%" + (int) (Math.log10(num) + 2) + "s";
			if (bool) {
				System.out.printf(format, strNumber);
			} else {
				System.out.printf(format, star.toString());
			}

			if (i % 50 == 0) {
				System.out.println();
			}
		}
	}

	private void calculation(int num) {
		for (int i = 1; i <= num; i++) {
			int oneDigits = i % 10;
			int tenDigits = i / 10;

			if ((oneDigits == 3 || oneDigits == 6 || oneDigits == 9) && (tenDigits == 3 || tenDigits == 6 || tenDigits == 9)) {
				System.out.printf("%4s", "**");
			} else if ((oneDigits == 3 || oneDigits == 6 || oneDigits == 9) || (tenDigits == 3 || tenDigits == 6 || tenDigits == 9)) {
				System.out.printf("%4s", "*");
			} else {
				System.out.printf("%4d", i);
			}

			if (i % 50 == 0) {
				System.out.println();
			}
		}
	}

	public static void main(String[] args) {
		Game369 game369 = new Game369();
		int numLength = 100;
		game369.strLength(numLength);
		System.out.println("\n");
		game369.calculation(numLength);
	}

	// 일단위 3 6 9 체크  23, 26, 69 % 10 % 3이 0이면 *
	// System.out.println(23 % 10 % 3);
	// 십단위 3 6 9 체크  31, 62, 91 / 10 % 3이 0이면 *
	// System.err.println(31 / 10 % 3);
}