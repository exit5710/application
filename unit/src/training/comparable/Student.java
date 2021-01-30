package training.comparable;

import java.util.Arrays;
import java.util.Comparator;

public class Student implements Comparable<Student> {
	private String name;
	private int studentNo;
	private double score;

	public Student(String name, int studentNo, double score) {
		this.name = name;
		this.studentNo = studentNo;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public int getStudentNo() {
		return studentNo;
	}

	public double getScore() {
		return score;
	}

	@Override
	public int compareTo(Student student) {
		return Integer.compare(getStudentNo(), student.getStudentNo());
	}

	@Override
	public String toString() {
		return getName() + " : " + getStudentNo() + " : " + getScore();
	}

	public static void main(String[] args) {
		Student[] student = {new Student("Apple", 202010001, 4.1), new Student("banana", 202020001, 4.2)
				, new Student("cake", 201910013, 3.9), new Student("doughnut", 201820009, 4.1)};

		Arrays.sort(student, new Comparator<Student>() {
			@Override
			public int compare(Student s1, Student s2) {
				if (s1.getScore() == s2.getScore()) {
					// 오름차순
					return Integer.compare(s1.getStudentNo(), s2.getStudentNo());
				}

				// 내림차순
				return Double.compare(s2.getScore(), s1.getScore());
			}
		});

		for (Student students : student) {
			System.out.println(students);
		}
	}
}