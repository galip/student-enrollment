package com.domain.enrollment;

import java.util.List;
import java.util.function.Consumer;

import com.domain.exception.DifferentNumberOfLecturesExceedLimitException;
import com.domain.exception.TakenCreditsExceedAvailableCreditsLimitException;
import com.lecture.domain.Lecture;
import com.lecture.domain.LectureService;
import com.student.domain.Student;
import com.student.domain.StudentService;

public class EnrollmentService {

	private StudentService studentService;
	private LectureService lectureService;

	public EnrollmentService(StudentService studentService, LectureService lectureService) {
		super();
		this.studentService = studentService;
		this.lectureService = lectureService;
	}

	public void enrollLectures(Student student, List<Lecture> exiestedLectures) throws Exception {
		List<Lecture> selectedRandomLectures = lectureService.getRandomLectures(exiestedLectures);
		boolean result = studentService.checkAvailableCreditsWithTakenCredits(student, selectedRandomLectures);
		if (result == false) {
			Consumer<List<Lecture>> lectureListPrint = list -> selectedRandomLectures.stream().forEach(l -> System.out
					.println("Lecture Id: " + l.getLectureId() + ", Name: " + l.getName() + ", Credit: " + l.getCredit()));
			lectureListPrint.accept(student.getLectures());
			throw new TakenCreditsExceedAvailableCreditsLimitException("Taken credits exceed available credits limit! Level: " + student.getLevel() + " student" );
		}
		result = studentService.checkNumberOfLecturesLimitByStudent(student, selectedRandomLectures);
		if (result == false) {
			Consumer<List<Lecture>> lectureListPrint = list -> selectedRandomLectures.stream().forEach(l -> System.out
					.println("Lecture Id: " + l.getLectureId() + ", Name: " + l.getName() + ", Credit: " + l.getCredit()));
			lectureListPrint.accept(student.getLectures());
			throw new DifferentNumberOfLecturesExceedLimitException("Different number of lectures exceed limit! Level : " + student.getLevel() + " student");
		}
		student.addLectures(selectedRandomLectures);
	}
}