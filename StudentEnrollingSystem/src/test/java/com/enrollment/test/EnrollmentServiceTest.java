package com.enrollment.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.domain.enrollment.EnrollmentService;
import com.domain.exception.DifferentNumberOfLecturesExceedLimitException;
import com.domain.exception.TakenCreditsExceedAvailableCreditsLimitException;
import com.domain.lecture.Lecture;
import com.domain.lecture.LectureService;
import com.domain.lecture.Lectures;
import com.student.domain.JuniorStudent;
import com.student.domain.SeniorStudent;
import com.student.domain.Student;
import com.student.domain.StudentService;
import com.student.domain.StudentServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class EnrollmentServiceTest {

	private StudentService studentService = new StudentServiceImpl();

	private EnrollmentService enrollmentService;

	private static final Lecture JAVA = Lectures.JAVA.getValue();
	private static final Lecture LOGIC_CIRCUITS = Lectures.LOGIC_CIRCUITS.getValue();
	private static final Lecture ARTIFICIAL_INTELLIGENCE = Lectures.ARTIFICIAL_INTELLIGENCE.getValue();
	private static final Lecture DEEP_LEARNING = Lectures.DEEP_LEARNING.getValue();
	private static final Lecture OOP_DESIGN = Lectures.OOP_DESIGN.getValue();
	private static final Lecture DESIGN_PATTERN = Lectures.DESIGN_PATTERN.getValue();
	private static final Lecture MICROSERVICE_ARCHITECTURE = Lectures.MICROSERVICE_ARCHITECTURE.getValue();
	public static final Lecture DUMMY_LECTURE_ONE = Lectures.DUMMY_LECTURE_ONE.getValue();
	public static final Lecture DUMMY_LECTURE_TWO = Lectures.DUMMY_LECTURE_TWO.getValue();
	public static final Lecture DUMMY_LECTURE_THREE = Lectures.DUMMY_LECTURE_THREE.getValue();

	private List<Lecture> EXISTED_LECTURES = new ArrayList<Lecture>();
	private List<Lecture> MOCKLECTURES_20_CREDITS = new ArrayList<Lecture>();
	private List<Lecture> MOCKLECTURES_25_CREDITS = new ArrayList<Lecture>();
	private List<Lecture> MOCKLECTURES_27_CREDITS = new ArrayList<Lecture>();
	private List<Lecture> MOCKLECTURES_7_DIFFERENT_LECTURES = new ArrayList<Lecture>();

	@Before
	public void setUpLectures() {
		
		EXISTED_LECTURES.add(JAVA);
		EXISTED_LECTURES.add(LOGIC_CIRCUITS);
		EXISTED_LECTURES.add(ARTIFICIAL_INTELLIGENCE);
		EXISTED_LECTURES.add(DEEP_LEARNING);
		EXISTED_LECTURES.add(OOP_DESIGN);
		EXISTED_LECTURES.add(DESIGN_PATTERN);
		EXISTED_LECTURES.add(MICROSERVICE_ARCHITECTURE);
		EXISTED_LECTURES.add(DUMMY_LECTURE_ONE);
		EXISTED_LECTURES.add(DUMMY_LECTURE_TWO);
		EXISTED_LECTURES.add(DUMMY_LECTURE_THREE);

		MOCKLECTURES_20_CREDITS.add(JAVA);
		MOCKLECTURES_20_CREDITS.add(LOGIC_CIRCUITS);
		MOCKLECTURES_20_CREDITS.add(ARTIFICIAL_INTELLIGENCE);
		MOCKLECTURES_20_CREDITS.add(OOP_DESIGN);

		MOCKLECTURES_25_CREDITS.add(JAVA);
		MOCKLECTURES_25_CREDITS.add(ARTIFICIAL_INTELLIGENCE);
		MOCKLECTURES_25_CREDITS.add(DEEP_LEARNING);
		MOCKLECTURES_25_CREDITS.add(OOP_DESIGN);
		MOCKLECTURES_25_CREDITS.add(DESIGN_PATTERN);

		MOCKLECTURES_27_CREDITS.add(JAVA);
		MOCKLECTURES_27_CREDITS.add(ARTIFICIAL_INTELLIGENCE);
		MOCKLECTURES_27_CREDITS.add(DEEP_LEARNING);
		MOCKLECTURES_27_CREDITS.add(OOP_DESIGN);
		MOCKLECTURES_27_CREDITS.add(DESIGN_PATTERN);
		MOCKLECTURES_27_CREDITS.add(MICROSERVICE_ARCHITECTURE);

		MOCKLECTURES_7_DIFFERENT_LECTURES.add(MICROSERVICE_ARCHITECTURE);
		MOCKLECTURES_7_DIFFERENT_LECTURES.add(DEEP_LEARNING);
		MOCKLECTURES_7_DIFFERENT_LECTURES.add(LOGIC_CIRCUITS);
		MOCKLECTURES_7_DIFFERENT_LECTURES.add(ARTIFICIAL_INTELLIGENCE);
		MOCKLECTURES_7_DIFFERENT_LECTURES.add(DUMMY_LECTURE_ONE);
		MOCKLECTURES_7_DIFFERENT_LECTURES.add(DUMMY_LECTURE_TWO);
		MOCKLECTURES_7_DIFFERENT_LECTURES.add(DUMMY_LECTURE_THREE);
		
	}

	/**
	 * @throws TakenCreditsExceedAvailableCreditsLimitException 
	 * when junior student has more than 20 credits.
	 */
	@Test(expected = TakenCreditsExceedAvailableCreditsLimitException.class)
	public void shouldThrowTakenCreditsExceedAvailableCreditsLimitException_whenCreditExceedsLimitForJunior()
			throws Exception {
		LectureService lectureService = Mockito.mock(LectureService.class);
		Student juniorStudent = new JuniorStudent(1, "Galip");
		enrollmentService = new EnrollmentService(studentService, lectureService);
		
		when(lectureService.getRandomLectures(any())).thenReturn(MOCKLECTURES_25_CREDITS);

		enrollmentService.enrollLectures(juniorStudent, EXISTED_LECTURES);
	}
	
	/**
	 * @throws DifferentNumberOfLecturesExceedLimitException
	 * when junior student adds more than 5 different lectures.
	 */
	@Test(expected = DifferentNumberOfLecturesExceedLimitException.class)
	public void shouldReturnTrue_whenJuniorAddsMoreThanAvailableLectures() throws Exception {
		LectureService lectureService = Mockito.mock(LectureService.class);
		Student juniorStudent = new JuniorStudent(1, "Galip");
		enrollmentService = new EnrollmentService(studentService, lectureService);
		
		when(lectureService.getRandomLectures(any())).thenReturn(MOCKLECTURES_7_DIFFERENT_LECTURES);
		
		enrollmentService.enrollLectures(juniorStudent, EXISTED_LECTURES);
		
	}
	
	/**
	 * @throws TakenCreditsExceedAvailableCreditsLimitException
	 * when senior student has more than 25 credits.
	 */
	@Test(expected = TakenCreditsExceedAvailableCreditsLimitException.class)
	public void shouldThrowTakenCreditsExceedAvailableCreditsLimitException_whenCreditExceedsLimitForSenior()
			throws Exception {
		LectureService lectureService = Mockito.mock(LectureService.class);
		Student seniorStudent = new SeniorStudent(1, "Joshua");
		enrollmentService = new EnrollmentService(studentService, lectureService);
		
		when(lectureService.getRandomLectures(any())).thenReturn(MOCKLECTURES_27_CREDITS);

		enrollmentService.enrollLectures(seniorStudent, EXISTED_LECTURES);
	}
	
	/**
	 * Rule: Senior student can have max. 6 different lectures.
	 * Case: When senior student tries to add 7 different lectures
	 * @throws DifferentNumberOfLecturesExceedLimitException
	 */
	@Test(expected = DifferentNumberOfLecturesExceedLimitException.class)
	public void shouldDifferentNumberOfLecturesExceedLimitException_whenSeniorAddsMoreThanAvailableLectures() throws Exception {
		LectureService lectureService = Mockito.mock(LectureService.class);
		Student seniorStudent = new SeniorStudent(1, "Joshua");
		enrollmentService = new EnrollmentService(studentService, lectureService);
		
		when(lectureService.getRandomLectures(any())).thenReturn(MOCKLECTURES_7_DIFFERENT_LECTURES);
		
		enrollmentService.enrollLectures(seniorStudent, EXISTED_LECTURES);
		
	}
	
	/**
	 * @throws Exception
	 * valid lectures to add for junior student
	 */
	@Test
	public void shouldReturnTrue_whenJuniorAddsAvailableLectures() throws Exception {
		LectureService lectureService = Mockito.mock(LectureService.class);
		StudentService studentService = Mockito.mock(StudentService.class);
		Student juniorStudent = new JuniorStudent(1, "Galip");
		enrollmentService = new EnrollmentService(studentService, lectureService);
		
		when(lectureService.getRandomLectures(any())).thenReturn(MOCKLECTURES_20_CREDITS);
		when(studentService.checkAvailableCreditsWithTakenCredits(any(), any())).thenReturn(true);
		when(studentService.checkNumberOfLecturesLimitByStudent(any(), any())).thenReturn(true);
		
		enrollmentService.enrollLectures(juniorStudent, EXISTED_LECTURES);
		
		assertThat(MOCKLECTURES_20_CREDITS).isEqualTo(juniorStudent.getLectures());
	}
	
	@Test
	public void shouldReturnTrue_whenSeniorAddsAvailableLectures() throws Exception {
		LectureService lectureService = Mockito.mock(LectureService.class);
		StudentService studentService = Mockito.mock(StudentService.class);
		Student seniorStudent = new SeniorStudent(1, "Joshua");
		enrollmentService = new EnrollmentService(studentService, lectureService);
		
		when(lectureService.getRandomLectures(any())).thenReturn(MOCKLECTURES_25_CREDITS);
		when(studentService.checkAvailableCreditsWithTakenCredits(any(), any())).thenReturn(true);
		when(studentService.checkNumberOfLecturesLimitByStudent(any(), any())).thenReturn(true);
		
		enrollmentService.enrollLectures(seniorStudent, EXISTED_LECTURES);
		
		assertThat(MOCKLECTURES_25_CREDITS).isEqualTo(seniorStudent.getLectures());
	}
	
}