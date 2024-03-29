package unitApp.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Set;

public class ResourceRead {
	private static final Class<?> thisClass = MethodHandles.lookup().lookupClass();
	private static final Logger logger = LoggerFactory.getLogger(thisClass);

	public static void main(String[] args) {
		logger.info(String.valueOf(thisClass));
		// C:\java\IdeaProjects\webApp\out\artifacts\spring5fs_war_exploded\WEB-INF\classes\databaseInfo.properties
		// String path = ResourceRead.class.getResource("databaseInfo.properties").getPath();
		String path = thisClass.getResource("/databaseInfo.properties").getPath();

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

			Set<String> keys = properties.stringPropertyNames();
			for (String key : keys) {
				logger.info(key);
			}

			throw new SQLException();
		} catch (IOException | SQLException e) {
			logger.debug(e.toString());
		} catch (Exception e) {
			logger.error(e.toString());
		}
	}
}