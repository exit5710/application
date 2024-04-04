package unitApp.patternizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpression {
	private static final Class<?> thisClass = MethodHandles.lookup().lookupClass();
	private static final Logger logger = LoggerFactory.getLogger(thisClass);

	public static void main(String[] args) {
		logger.info("RegularExpression main /");

		// ^[0-9]*$			숫자만
		// ^[a-zA-Z]*$		문자만
		// ^[0-9|a-zA-Z]*$
		String pattern = "^[0-9|a-zA-Zㄱ-ㅎ가-힣\\s]*$";
		String val = "apple1 가 1";

		boolean bool = Pattern.matches(pattern, val);
		logger.debug(String.valueOf(bool));

		String source = "";
		// source = "apple-banana-cherry";
		// source = "<include id='memberListSql' />";
		// source = "<select id='boardList'>SELECT ID, NAME FROM BOARD</select>";
		source = "<select id='memberList' parameterType='hashMap' />";

		// Matcher matcher = Pattern.compile("<select id='(.*?)'.*?/*>", Pattern.DOTALL).matcher(source);
		// Matcher matcher = Pattern.compile("<select .*>(.*?)</select>", Pattern.DOTALL).matcher(source);
		Matcher matcher = Pattern.compile("<select\\s+id\\s*='(.*?)'\\s+parameterType='(.*?)'.*?/*>", Pattern.DOTALL).matcher(source);

		if (matcher.find()) {
			System.out.println(matcher.group());
			System.out.println(matcher.group(1));
			System.out.println(matcher.group(2));
		}

		// String data = "";
		// pattern = "";
		//
		// data = "<if test='id != null and id != \"\"'>a11</if>\r\n" + "<if test=\"memPwd != null and memPwd != ''\">b22</if>";
		// data = "<if test=\"contMngrTpCdList != null and contMngrTpCdList.size() > 0\">\r\n" + "a\r\n" + "</if>\r\n" + "<if test='id != null and id != \"\"'>b</if>";
		// data = "<if test=\"contMngrTpCdList != null and contMngrTpCdList.size() > 0\">test /* 주석 */ </if>";
		//
		// logger.debug("----- replaceAll --------------------");
		// logger.debug(data);
		// logger.debug(replaceRegexp(data).trim() + "\n");
		//
		// logger.debug("----- matcher --------------------");
		// data = "<select id='memberListMap' parameterType='java.util.Map'>";
		//
		// pattern = "<select id='(.*?)'>"; // '와 '사이의 모든 문자이기때문에 memberListMap 부터 resultType='java.util.Map 까지 출력된다.
		// pattern = "<select id='(.*?)'\\s*.*?>"; // id만 출력
		// // id, parameterType, resultType와 공백처리
		// pattern = "<select\\s+id\\s*=\\s*'(.*?)'\\s*parameterType\\s*=\\s*'(.*?)'\\s*>";
		//
		// matcher = Pattern.compile(pattern, Pattern.DOTALL).matcher(data);
		//
		// try {
		// 	if (matcher.find()) {
		// 		logger.debug(matcher.group());
		// 		logger.debug("id: {}", matcher.group(1));
		// 		logger.debug("parameterType: {}", matcher.group(2) + "\n");
		// 	}
		// } catch (IndexOutOfBoundsException e) {
		// 	logger.error(e.getMessage());
		// }
		//
		// data = "<include refid=\"com.springVaf.mappers.commonMapper.selectMember\" />";
		// pattern = "<include\\s+refid=\"(.*?)\".*?/\\s*>";
		//
		// matcher = Pattern.compile(pattern, Pattern.DOTALL).matcher(data);
		//
		// if (matcher.find()) {
		// 	logger.debug(matcher.group());
		// 	logger.debug(matcher.group(1));
		// }

		System.exit(0);
		String sql = "<select id=\"memberListMap\" parameterType=\"java.util.Map\" resultType=\"java.util.Map\">\n" +
				"\t\t/* memberMapper.memberListMap */\n" +
				"\t\t<![CDATA[ ]]>\n" +
				"\t\t<include refid=\"memberListSql\" />\n" +
				"\t\t<where>\n" +
				"\t\t<if test='id != null and id != \"\"'>\n" +
				"\t\t   AND id = #{id}\n" +
				"\t\t</if>\n" +
				"\t\t<if test='name != null and name != \"\"'>\n" +
				"\t\t   AND name = #{name}\n" +
				"\t\t</if>\n" +
				"\t\t<if test='gender != null and gender != \"\"'>\n" +
				"\t\t   AND gender = #{gender}\n" +
				"\t\t</if>\n" +
				"\t\t</where>\n" +
				"\t</select>";
		String include = "<sql id=\"memberListSql\">\n" +
				"\t\t/* memberMapper.memberListSql */\n" +
				"\t\tSELECT id\n" +
				"\t\t       , name\n" +
				"\t\t       , gender\n" +
				"\t\t       , birthday\n" +
				"\t\t       , blood_type\n" +
				"\t\t       , etc\n" +
				"\t\t       , register_date\n" +
				"\t\t  FROM member\n" +
				"\t</sql>";
		Map<String, String> map = new HashMap<>();
		map.put("memberListSql", include);

		String tag = "include";
		matcher = Pattern.compile("<" + tag + "\\s+refid=\"(.*?)\".*?/\\s*>", Pattern.DOTALL).matcher(sql);

		if (matcher.find()) {
			String refId = matcher.group(1);
			String content = matcher.group();

			if (map.get(refId) != null) {
				sql = sql.replaceAll(content, map.get(refId));
			}
		}

		System.out.println(sql);

		Map<String, String> mapTest = new HashMap<>();
		mapTest.put("a", "a1");
		mapTest.put("b", "b1");

		for (Map.Entry<String, String> entry : mapTest.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			System.out.println("Key: " + key + ", Value: " + value);
		}
	}

	private static String replaceRegexp(String data) {
		// 주석 패턴 처리
		data = data.replaceAll("//.*", "");
		// data = data.replaceAll("\\s+", " ");
		data = data.replaceAll("/\\*.*?\\*/", "");
		data = data.replaceAll("<!--[^>]*-->", "");

		// sql 태그 패턴 처리
		data = data.replaceAll("<select[^>]*>|</select>", "");
		data = data.replaceAll("<insert[^>]*>|</insert>", "");
		data = data.replaceAll("<update[^>]*>|</update>", "");
		data = data.replaceAll("<delete[^>]*>|</delete>", "");
		data = data.replaceAll("<foreach[^>]*>|</foreach>", "");
		data = data.replaceAll("<if[^>]*>|</if>", "");

		// #{ } 패턴 처리
		data = data.replaceAll("#\\{\\w+}", "'1'");

		// <![CDATA[ ]]> 패턴 처리
		data = data.replaceAll("<!\\[CDATA\\[|]]>", "");

		// where 태그 패턴 처리
		data = data.replaceAll("<where>", "WHERE 1 = 1");
		data = data.replaceAll("</where>", "");

		// set 태그 패턴 처리
		data = data.replace("<set>", "set").replace("</set>", "");
		data = data.replaceFirst(",\\s*WHERE", " WHERE");

		// ETC
		data = data.replaceAll("0\">", "");

		return data;
	}
}