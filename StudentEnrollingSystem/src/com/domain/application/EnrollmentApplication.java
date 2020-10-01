package com.domain.application;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import com.domain.enrollment.EnrollmentService;
import com.domain.lecture.Lecture;
import com.domain.lecture.LectureService;
import com.domain.lecture.LectureServiceImpl;
import com.domain.lecture.Lectures;
import com.domain.student.JuniorStudent;
import com.domain.student.SeniorStudent;
import com.domain.student.Student;
import com.domain.student.StudentService;
import com.domain.student.StudentServiceImpl;

public class EnrollmentApplication {

	private static final Lecture JAVA = Lectures.JAVA.getValue();
	private static final Lecture LOGIC_CIRCUITS = Lectures.LOGIC_CIRCUITS.getValue();
	private static final Lecture ARTIFICIAL_INTELLIGENCE = Lectures.ARTIFICIAL_INTELLIGENCE.getValue();
	private static final Lecture DEEP_LEARNING = Lectures.DEEP_LEARNING.getValue();
	private static final Lecture OOP_DESIGN = Lectures.OOP_DESIGN.getValue();
	private static final Lecture DESIGN_PATTERN = Lectures.DESIGN_PATTERN.getValue();
	private static final Lecture MICROSERVICE_ARCHITECTURE = Lectures.MICROSERVICE_ARCHITECTURE.getValue();
	private static final Lecture DUMMY_LECTURE_ONE = Lectures.DUMMY_LECTURE_ONE.getValue();
	private static final Lecture DUMMY_LECTURE_TWO = Lectures.DUMMY_LECTURE_TWO.getValue();
	private static final Lecture DUMMY_LECTURE_THREE = Lectures.DUMMY_LECTURE_THREE.getValue();

	StudentService studentService = new StudentServiceImpl();
	LectureService lectureService = new LectureServiceImpl();
	EnrollmentService enrollmentService = new EnrollmentService(studentService, lectureService);

	public static void main(String args[]) throws Exception {

		EnrollmentApplication app = new EnrollmentApplication();

		List<Lecture> existedLectures = createLectures();

		Student student = app.getRandStudent(getRandNumber());
		app.enrollLectureToStudent(student, existedLectures);
	}

	public static List<Lecture> createLectures() {

		List<Lecture> existedLectures = new ArrayList<Lecture>();

		existedLectures.add(JAVA);
		existedLectures.add(LOGIC_CIRCUITS);
		existedLectures.add(ARTIFICIAL_INTELLIGENCE);
		existedLectures.add(DEEP_LEARNING);
		existedLectures.add(OOP_DESIGN);
		existedLectures.add(DESIGN_PATTERN);
		existedLectures.add(MICROSERVICE_ARCHITECTURE);
		existedLectures.add(DUMMY_LECTURE_ONE);
		existedLectures.add(DUMMY_LECTURE_TWO);
		existedLectures.add(DUMMY_LECTURE_THREE);

		return existedLectures;
	}

	/**
	 * @return int 0 or 1
	 */
	public static int getRandNumber() {
		Random r = new Random();
		return r.nextInt(2);
	}

	/**
	 * @param id
	 * @return student
	 */
	public Student getRandStudent(int id) {
		Student student = null;
		if (id == 0) {
			student = new JuniorStudent(1, "Galip");
		} else {
			student = new SeniorStudent(2, "Joshua");
		}
		return student;
	}

	public void enrollLectureToStudent(Student student, List<Lecture> existedLectures) throws Exception {

		enrollmentService.enrollLectures(student, existedLectures);

		System.out.println("Lectures are added successfully!");
		System.out.println("Student name : " + student.getStudentName() + " Level: " + student.getLevel());

		Consumer<List<Lecture>> lectureListPrint = list -> list.stream().sorted(Comparator.comparingInt(Lecture::getLectureId)).forEach(l -> System.out
				.println("Lecture Id: " + l.getLectureId() + ", Name: " + l.getName() + ", Credit: " + l.getCredit()));

		lectureListPrint.accept(student.getLectures());
	}

}