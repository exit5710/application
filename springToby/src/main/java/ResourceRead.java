import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.lang.invoke.MethodHandles;
import java.sql.SQLException;
import java.util.Properties;

public class ResourceRead {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public static void main(String[] args) {
		logger.info(String.valueOf(MethodHandles.lookup().lookupClass()));
		// C:\java\IdeaProjects\webApp\out\artifacts\spring5fs_war_exploded\WEB-INF\classes\dataBaseInfo.properties
		// Class<?> c = MethodHandles.lookup().lookupClass();
		// String path = Test.class.getResource("dataBaseInfo.properties").getPath();
		String path = MethodHandles.lookup().lookupClass().getResource("dataBaseInfo.properties").getPath();

		try {
			FileReader resources = new FileReader(path);
			Properties properties = new Properties();
			properties.load(resources);

			String url = properties.getProperty("mariaDbUrl");
			String user = properties.getProperty("mariaDbUser");
			String password = properties.getProperty("mariaDbPassword");

			System.out.println("path : " + path);
			System.out.println("url : " + url);
			System.out.println("user : " + user);
			System.out.println("password : " + password);

			throw new SQLException();
		} catch (ArithmeticException e) {
			System.err.println("ArithmeticException");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("ArrayIndexOutOfBoundsException");
		} catch (Exception e) {
			logger.debug(e.toString());
		}
	}
}