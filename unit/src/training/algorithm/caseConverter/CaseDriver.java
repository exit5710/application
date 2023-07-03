package training.algorithm.caseConverter;

public class CaseDriver {
	public static void main(String[] args) {
		String input = "aPpLe";
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < input.length(); i++) {
			if (Character.isLowerCase(input.charAt(i))) {
				stringBuilder.append(String.valueOf(input.charAt(i)).toUpperCase());
			} else {
				stringBuilder.append(String.valueOf(input.charAt(i)).toLowerCase());
			}
		}

		System.out.println(stringBuilder.toString());
	}
}