package config.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Utils {
	private static final Class<?> thisClass = MethodHandles.lookup().lookupClass();
	private static final Logger logger = LoggerFactory.getLogger(thisClass);

	// createInstance
	public static Object createInstance(String className) throws Exception {
		Class<?> clazz = Class.forName(className);
		Constructor<?> constructor = clazz.getDeclaredConstructor();

		return constructor.newInstance();
	}

	// invokeMethod
	public static Object invokeMethod(Object instance, String methodName, Object[] paramValues, Class<?>... paramTypes) throws Throwable {
		Method method = instance.getClass().getDeclaredMethod(methodName, paramTypes);
		MethodHandle methodHandle = MethodHandles.lookup().unreflect(method);

		return methodHandle.invokeWithArguments(concat(instance, paramValues));
	}

	// concat
	private static Object[] concat(Object instance, Object[] paramValues) {
		Object[] arguments = new Object[paramValues.length + 1];
		arguments[0] = instance;
		System.arraycopy(paramValues, 0, arguments, 1, paramValues.length);

		return arguments;
	}

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