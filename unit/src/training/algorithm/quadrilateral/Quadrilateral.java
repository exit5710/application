package training.algorithm.quadrilateral;

public class Quadrilateral {
	private int number;
	private int[][] array;

	private Quadrilateral(int number) {
		this.number = number;
		this.array = new int[number][number];
	}

	private void printQuadrilateral() {
		for (int i = 1; i <= this.number * this.number; i++) {
			System.out.printf("%4d", i);

			if (i % this.number == 0) {
				System.out.println();
			}
		}
	}

	private void printArray() {
		for (int[] array : this.array) { /* for (int i = 0; i < this.array.length; i++) { */
			for (int i = 0; i < this.array[0].length; i++) {
				System.out.printf("%4d", array[i]);
			}
			System.out.println();
		}
	}

	private void setArray(int type) {
		int count = 1;

		switch (type) {
			case 1:
				this.printQuadrilateral();
				break;

			case 2:
				// [0][0] = 1
				// [0][1] = 2
				// [0][2] = 3 ...
				// [1][4] = 6
				// [1][3] = 7
				// [1][2] = 8 ...
				for (int i = 0; i < this.number; i++) {
					if (i % 2 == 0) {
						for (int j = 0; j < this.number; j++) {
							this.array[i][j] = count;
							count++;
						}
					} else {
						for (int j = this.number - 1; j >= 0; j--) {
							this.array[i][j] = count;
							count++;
						}
					}
				}
				this.printArray();
				break;

			case 3:
				// [0][0] = 1
				// [1][0] = 2
				// [2][0] = 3
				// [3][0] = 4
				// [4][0] = 5
				// [0][1] = 6
				// [1][1] = 7
				// [2][1] = 8
				for (int i = 0; i < this.number; i++) {
					for (int j = 0; j < this.number; j++) {
						this.array[j][i] = count;
						count++;
					}
				}
				this.printArray();
				break;

			case 4:
				// [0][0] = 1
				// [0][1] = 2
				// [0][2] = 3
				// [0][3] = 4
				// [0][4] = 5
				// [1][0] = 2
				// [1][1] = 4
				// [1][2] = 6
				for (int i = 0; i < this.number; i++) {
					for (int j = 0; j < this.number; j++) {
						this.array[i][j] = (i + 1) * (j + 1);
					}
				}
				this.printArray();
				break;
		}
	}

	public static void main(String[] args) {
		Quadrilateral quadrilateral = new Quadrilateral(5);

		int type = 2;
		quadrilateral.setArray(type);
	}
}