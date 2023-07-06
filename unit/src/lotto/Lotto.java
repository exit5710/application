package lotto;

import java.util.ArrayList;

public class Lotto {
	private ArrayList<Integer> lottoNumber = new ArrayList<>();

	private void setLottoNumber() {
		while (true) {
			double randomNumber = Math.random();
			int lottoNumber = (int) (randomNumber * 45) + 1;

			if (!checkOverlap(lottoNumber)) {
				this.lottoNumber.add(lottoNumber);
			}

			if (this.lottoNumber.size() == 6) {
				this.lottoNumber.sort(null);

				break;
			}
		}
	}

	private boolean checkOverlap(int lottoNumber) {
		for (Integer integer : this.lottoNumber) {
			if (integer == lottoNumber) {
				return true;
			}
		}

		return false;
	}

	private void printLottoNumber() {
		StringBuilder lottoNumber = new StringBuilder();

		for (Integer integer : this.lottoNumber) {
			lottoNumber.append(integer).append(" ");
		}

		this.lottoNumber.clear();
		System.out.print(lottoNumber.toString().trim() + "\n");
	}

	public static void main(String[] args) {
		Lotto lotto = new Lotto();

		for (int i = 0; i <= 4; i++) {
			lotto.setLottoNumber();
			lotto.printLottoNumber();
		}
	}
}