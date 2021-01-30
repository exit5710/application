package training.format;

public class FormatDriver {
	public static void main(String[] args) {
		String str = "apple";

		String result = String.format("문자열 서식: %s, %S, %d", str, str, 11);
		System.out.println(result);
	}
}