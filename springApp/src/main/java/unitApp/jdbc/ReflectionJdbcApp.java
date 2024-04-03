package unitApp.jdbc;

import config.component.Utils;
import config.database.reflection.ReflectionDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Map;

public class ReflectionJdbcApp {
	private static final Class<?> thisClass = MethodHandles.lookup().lookupClass();
	private static final Logger logger = LoggerFactory.getLogger(thisClass);

	public static void main(String[] args) throws Throwable {
		logger.info("ReflectionJdbcApp main /");

		ReflectionDao reflectDao = ReflectionDao.getInstance();

		String tableSchema = "model2";

		StringBuilder query = new StringBuilder();
		query.append("SELECT ID");
		query.append("       , NAME");
		query.append("  FROM MEMBER");

		Object reflectionLoopImpl = Utils.createInstance("config.database.reflection.ReflectionLoopImpl");

		List<Map<String, Object>> memberList = reflectDao.execute(tableSchema, query.toString(), reflectionLoopImpl, "memberList");
		for (Map<String, Object> map : memberList) {
			System.out.println(map.get("id") + " / " + map.get("name"));
		}

		query.setLength(0);

		/*
		String tableSchema = "validate";

		StringBuilder query = new StringBuilder();
		query.append("SELECT ORIGINAL_CONTENT");
		query.append("       , REGEXP_CONTENT");
		query.append("  FROM VALIDATE");

		Object reflectionLoopImpl = Utils.createInstance("config.database.reflection.ReflectionLoopImpl");

		List<ValidateVo> list = reflectDao.regexpContentList(tableSchema, query.toString(), reflectionLoopImpl, "loop");
		System.out.println(list.get(0).getOriginalContent());
		System.out.println(list.get(0).getRegexpContent());

		List<Map<String, Object>> listMap = reflectDao.regexpContentList(tableSchema, query.toString(), reflectionLoopImpl, "loopMap");
		System.out.println(listMap.get(0).get("originalContent"));
		System.out.println(listMap.get(0).get("regexpContent"));
		*/
	}
}