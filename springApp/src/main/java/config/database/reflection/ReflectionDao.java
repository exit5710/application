package config.database.reflection;

import config.component.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReflectionDao {
	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;

	public static ReflectionDao getInstance() {
		return ReflectDaoHelper.INSTANCE;
	}

	private static class ReflectDaoHelper {
		private static final ReflectionDao INSTANCE = new ReflectionDao();
	}

	public <T> List<T> execute(String tableSchema, String query, Object object, String methodName) throws Throwable {
		List<T> list = new ArrayList<>();
		try {
			connection = ReflectionConnector.getConnection(tableSchema);
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();

			Object[] paramValues = {rs, list};
			Class<?>[] paramTypes = {ResultSet.class, List.class};

			Utils.invokeMethod(object, methodName, paramValues, paramTypes);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Utils.databaseClose(rs, ps, connection);
		}

		return list;
	}
}