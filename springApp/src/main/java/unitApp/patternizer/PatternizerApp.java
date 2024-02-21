package unitApp.patternizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import java.util.ArrayList;
import java.util.List;

public class PatternizerApp {
	private static final Class<?> thisClass = MethodHandles.lookup().lookupClass();
	private static final Logger logger = LoggerFactory.getLogger(thisClass);

	public static void main(String[] args) {
		logger.info(String.valueOf(thisClass));

		List<String> list = new ArrayList<>();
		list.add("A.NAME = #{name}");
		list.add("<![CDATA[ A.AGE <= 19 ]]>");
		list.add("<if test='name != null and name != \"\"'>");
		list.add("</if>");
		list.add("<where>");
		list.add("</where>");
		list.add("/* this is comment */");
		list.add("/* this is comment \n" + "test 111 */");

		for (String element : list) {
			logger.debug(processRegex(element));
		}
	}

	// 문자열 처리 메서드
	public static String processRegex(String data) {
		// #{ } 패턴 처리
		data = data.replaceAll("#\\{\\w+}", "'1'");

		// <![CDATA[ ]]> 패턴 처리
		data = data.replaceAll("<!\\[CDATA\\[|]]>", "");

		// if 태그 패턴 처리
		data = data.replaceAll("<if[^>]*test='[^']*'[^>]*>", "");
		data = data.replaceAll("</if>", "");

		// where 태그 패턴 처리
		data = data.replaceAll("<where>", "WHERE 1 = 1");
		data = data.replaceAll("</where>", "");

		// 주석 패턴 처리
		data = data.replaceAll("//.*", "");
		data = data.replaceAll("\\s+", " ");
		data = data.replaceAll("/\\\\*.*?\\\\*/", "");

		return data;
	}
}
