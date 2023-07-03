package training.algorithm.studentSearch;

import java.util.ArrayList;
import java.util.Scanner;

public class SearchDriver {
	public static void main(String[] args) {
		Student s1 = new Student("홍길동", 123456789);
		Student s2 = new Student("둘리", 283456779);
		Student s3 = new Student("루피", 921416712);

		ArrayList<Student> arrayList = new ArrayList<>();
		arrayList.add(s1);
		arrayList.add(s2);
		arrayList.add(s3);

		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.print("검색하시려면 y, 종료하시려면 n을 입력하세요 : ");

			String input = scanner.next();

			if (input.equals("n")) {
				System.out.println("프로그램이 종료되었습니다.");
				break;
			}

			System.out.print("이름을 입력하세요 : ");
			String name = scanner.next();
			boolean flag = false;

			for(Student student : arrayList) {
				if (name.equals(student.getName())) {
					System.out.println("이름 : " + student.getName() + " / 학번 : " + student.getNo() + "\n");
					flag = true;
				}
			}

			if (!flag) {
				System.out.println("해당하는 이름의 학생은 없습니다.\n");
			}
		}
	}
}