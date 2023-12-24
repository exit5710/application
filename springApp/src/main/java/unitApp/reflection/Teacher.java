package unitApp.reflection;

import org.slf4j.Logger;

@SuppressWarnings("unused")
public class Teacher {
	private Logger logger;
	private String name;
	private int age;

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void teacherPrint(String name, int count) {
		logger.debug("the number of {}: {}", name, count);
	}

	public void meeting(Student student) {
		logger.debug("name of student to meeting: {}", student.getName());
	}
}
