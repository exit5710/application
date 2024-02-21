package unitApp.patternizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class PatternizerApp {
	private static final Class<?> thisClass = MethodHandles.lookup().lookupClass();
	private static final Logger logger = LoggerFactory.getLogger(thisClass);

	public static void main(String[] args) {
		logger.info(String.valueOf(thisClass));

		String dataFrom1 = "A.NAME = #{name}";
		String dataFrom2 = "<![CDATA[ A.AGE <= 19 ]]>";
		String dataFrom3 = "<if test='name != null and name != \"\"'>";
		String dataFrom4 = "/* this is comment */";
		String dataFrom5 = "/* this is comment \n" + "test 111 */";

		String regexData1 = processRegex(dataFrom1);
		String regexData2 = processRegex(dataFrom2);
		String regexData3 = processRegex(dataFrom3);
		String regexData4 = processRegex(dataFrom4);
		String regexData5 = processRegex(dataFrom5);

		logger.debug(regexData1);
		logger.debug(regexData2);
		logger.debug(regexData3);
		logger.debug(regexData4);
		logger.debug(regexData5);
	}

	// 문자열 처리 메서드
	public static String processRegex(String data) {
		// #{ } 패턴 처리
		data = data.replaceAll("#\\{\\w+}", "'1'");

		// <![CDATA[ ]]> 패턴 처리
		data = data.replaceAll("<!\\[CDATA\\[|]]>", "");

		// if 태그 패턴 처리
		data = data.replaceAll("<if[^>]*test='[^']*'[^>]*>", "");

		// 주석 패턴 처리
		data = data.replaceAll("//.*", "");
		data = data.replaceAll("\\s+", " ");
		data = data.replaceAll("/\\\\*.*?\\\\*/", "");

		return data;
	}
}
