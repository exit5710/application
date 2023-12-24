package unitApp.reflection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Reflection {
	private static final Class<?> thisClass = MethodHandles.lookup().lookupClass();
	private static final Logger logger = LoggerFactory.getLogger(thisClass);

	private static Object createInstance(String className) throws Exception {
		// 클래스 동적 로드
		Class<?> clazz = Class.forName(className);

		// 생성자 로드
		Constructor<?> constructor = clazz.getDeclaredConstructor(); // String.class, int.class

		// 인스턴스 생성
		return constructor.newInstance(); // "kiYoung", 23
	}

	//private static Object invokeMethod(Object instance, String methodName, Class<?>[] paramTypes, Object... paramValues) throws Throwable {
	//invokeMethod(teacherInstance, "setName", new Class<?>[]{String.class}, "kiYoung");

	private static Object invokeMethod(Object instance, String methodName, Object[] paramValues, Class<?>... paramTypes) throws Throwable {
		Method method = instance.getClass().getDeclaredMethod(methodName, paramTypes);
		MethodHandle methodHandle = MethodHandles.lookup().unreflect(method);

		return methodHandle.invokeWithArguments(concat(instance, paramValues));
	}

	private static Object[] concat(Object instance, Object[] paramValues) {
		Object[] arguments = new Object[paramValues.length + 1];
		arguments[0] = instance;
		System.arraycopy(paramValues, 0, arguments, 1, paramValues.length);

		return arguments;
	}

	public static void main(String[] args) throws Throwable {
		logger.info(String.valueOf(thisClass));

		// 클래스 생성
		Object teacherInstance = createInstance("unitApp.reflection.Teacher");
		Object studentInstance = createInstance("unitApp.reflection.Student");

		// 메소드 동적 호출
		// teacherInstance setter
		invokeMethod(teacherInstance, "setLogger", new Logger[]{logger}, Logger.class);
		invokeMethod(teacherInstance, "setName", new Object[]{"tom"}, String.class);
		invokeMethod(teacherInstance, "setAge", new Object[]{22}, int.class);

		// studentInstance setter
		invokeMethod(studentInstance, "setName", new Object[]{"jim"}, String.class);

		// 메소드 동적 호출
		logger.debug("teacherName: {}", invokeMethod(teacherInstance, "getName", new Object[]{})); //(Class<?>[]) null
		logger.debug("teacherAge: {}", invokeMethod(teacherInstance, "getAge", new Object[]{}));

		Object[] paramValues = {"teachers", 1}; // new Object[]{"teachers", 1};
		Class<?>[] paramTypes = {String.class, int.class}; // new Class<?>[]{String.class, int.class};
		invokeMethod(teacherInstance, "teacherPrint", paramValues, paramTypes);
		invokeMethod(teacherInstance, "meeting", new Object[]{studentInstance}, Student.class);
	}
}