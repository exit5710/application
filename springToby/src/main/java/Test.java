import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.sql.SQLException;

public class Test {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public static void main(String[] args) {
		logger.info(String.valueOf(MethodHandles.lookup().lookupClass()));
		// C:\java\IdeaProjects\webApp\out\artifacts\spring5fs_war_exploded\WEB-INF\classes\dataBaseInfo.properties
		System.out.println(Test.class.getResource("dataBaseInfo.properties"));

		try {
			throw new SQLException();
		} catch (ArithmeticException e) {
			System.out.println("ArithmeticException");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("ArrayIndexOutOfBoundsException");
		} catch (Exception e) {
			System.out.println("Exception");
		}
	}
}