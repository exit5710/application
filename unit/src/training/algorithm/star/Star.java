package training.algorithm.star;

public class Star {
	private int number;

	private Star(int number) {
		this.number = number;
	}

	private void setStar(int kind) {
		switch (kind) {
			case 1:
				for (int i = 0; i < this.number; i++) {
					for (int j = 0; j <= i; j++) {
						System.out.print("*");
					}
					System.out.println();
				}
				break;
			case 2:
				for (int i = 0; i < this.number; i++) {
					for (int j = 1; j <= this.number; j++) {
						if (i < j) {
							System.out.print("*");
						} else {
							System.out.print(" ");
						}
					}
					System.out.println();
				}
				break;
			case 3:
				for (int i = 0; i < this.number; i++) {
					for (int j = this.number - 1; j >= 0; j--) {
						if (i >= j) {
							System.out.print("*");
						} else {
							System.out.print(" ");
						}
					}
					System.out.println();
				}
				break;
		}
	}

	public static void main(String[] args) {
		Star star = new Star(5);

		int kind = 3;
		star.setStar(kind);
	}
}