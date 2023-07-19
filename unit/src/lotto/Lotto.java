package lotto;

import java.util.ArrayList;

public class Lotto {
	private ArrayList<Integer> lottoNumber = new ArrayList<>();

	//lottoNumber 번호 add
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

	// 중복번호 체크
	private boolean checkOverlap(int lottoNumber) {
		for (Integer integer : this.lottoNumber) {
			if (integer == lottoNumber) {
				return true;
			}
		}

		return false;
	}

	// 번호 출력
	private void printLottoNumber() {
		StringBuilder lottoNumber = new StringBuilder();

		for (Integer integer : this.lottoNumber) {
			lottoNumber.append(integer).append(" ");
		}

		this.lottoNumber.clear();
		System.out.print(lottoNumber.toString().trim() + "\n");
	}

	// main method
	public static void main(String[] args) {
		Lotto lotto = new Lotto();

		for (int i = 0; i <= 4; i++) {
			lotto.setLottoNumber();
			lotto.printLottoNumber();
		}
	}
}