package config.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Utils {
	private static final Class<?> thisClass = MethodHandles.lookup().lookupClass();
	private static final Logger logger = LoggerFactory.getLogger(thisClass);

	// close
	public static void databaseClose(ResultSet rs, PreparedStatement ps, Connection connection) {
		try {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
			if (connection != null) connection.close();
		} catch (SQLException e) {
			logger.error("Error database close: {}", e.getMessage());
		}
	}
}