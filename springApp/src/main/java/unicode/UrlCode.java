package unicode;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class UrlCode {
	public static void main(String[] args) {
		String text = "가나다라123apple&=";

		String encodeResult = URLEncoder.encode(text, StandardCharsets.UTF_8);
		System.out.println(encodeResult);

		String decodeResult = URLDecoder.decode(encodeResult, StandardCharsets.UTF_8);
		System.out.println(decodeResult);
	}
}