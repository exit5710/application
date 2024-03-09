package config.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionLocator {
	Connection getConnection() throws SQLException;
}