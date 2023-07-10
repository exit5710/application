import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.sql.SQLException;
import java.util.Properties;

public class ResourceRead {
	private static final Class<?> clazz = MethodHandles.lookup().lookupClass();
	private static final Logger logger = LoggerFactory.getLogger(clazz);

	public static void main(String[] args) {
		logger.info(String.valueOf(clazz));
		// C:\java\IdeaProjects\webApp\out\artifacts\spring5fs_war_exploded\WEB-INF\classes\dataBaseInfo.properties
		// String path = Test.class.getResource("dataBaseInfo.properties").getPath();
		String path = clazz.getResource("dataBaseInfo.properties").getPath();

		try {
			FileReader resources = new FileReader(path);
			Properties properties = new Properties();
			properties.load(resources);

			String url = properties.getProperty("mariaDbUrl");
			String user = properties.getProperty("mariaDbUser");
			String password = properties.getProperty("mariaDbPassword");

			logger.info("path : " + path);
			logger.info("url : " + url);
			logger.info("user : " + user);
			logger.info("password : " + password);

			throw new SQLException();
		} catch (IOException | SQLException e) {
			logger.debug(e.toString());
		} catch (Exception e) {
			logger.error(e.toString());
		}
	}
}