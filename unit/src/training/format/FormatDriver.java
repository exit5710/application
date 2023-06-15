package training.format;

public class FormatDriver {
	public static void main(String[] args) {
		String str = "apple";

		String result = String.format("문자열 서식: %s, %S, %d", str, str, 11);
		System.out.println(result);

		String data = "firstName=111&lastName=22&gender=f&minutes=33&seconds=44&action=addRunner";
		data = "{" + data + "}";
		data = data.replaceAll("=", " : ");
		data = data.replaceAll("&", ", ");

		System.out.println(data);

	}
}