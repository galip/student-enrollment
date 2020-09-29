package com.student.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.lecture.domain.Lecture;
import com.lecture.domain.Lectures;
import com.student.domain.JuniorStudent;
import com.student.domain.SeniorStudent;
import com.student.domain.Student;
import com.student.domain.StudentService;
import com.student.domain.StudentServiceImpl;

public class StudentServiceTest {

	private static final int JUNIOR_LEVEL_MAX_CREDIT = 20;
	private static final int SENIOR_LEVEL_MAX_CREDIT = 25;

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

	private StudentService studentService = new StudentServiceImpl();
	
	/**
	 * Rule: findTotalCredits(List<Lecture> lectures) returns sum of lectures.
	 * Case: Lecture list is null, sum of it should return 0.
	 */
	@Test
	public void shouldReturnZero_whenLecturesNotExist() {
		int totalCreditsTaken;
		List<Lecture> takenLectures = new ArrayList<Lecture>();

		totalCreditsTaken = studentService.findTotalCredits(takenLectures);

		assertThat(totalCreditsTaken).isEqualTo(0);
	}
	
	/**
	 * Rule: findTotalCredits(List<Lecture> lectures) returns sum of lectures.
	 * Case: 3 lectures in the list, sum of it should return 16.
	 * JAVA is 5 credit, ARTIFICIAL_INTELLIGENCE is 6 credit, OOP_DESIGN is 6 credit.
	 */
	@Test
	public void shouldReturnSumOfLecturesCredit_whenGivenLecturesAreUnique() {
		int totalCreditsTaken;
		List<Lecture> takenLectures = new ArrayList<Lecture>();
		takenLectures.add(JAVA);
		takenLectures.add(ARTIFICIAL_INTELLIGENCE);
		takenLectures.add(OOP_DESIGN);

		totalCreditsTaken = studentService.findTotalCredits(takenLectures);

		assertThat(totalCreditsTaken).isEqualTo(16);
	}
	
	/**
	 * Rule: findTotalCredits(List<Lecture> lectures) returns sum of lectures.
	 * Case: 5 lectures in the list, sum of distinct list should return 16.
	 * JAVA is 5 credit, ARTIFICIAL_INTELLIGENCE is 6 credit, OOP_DESIGN is 6 credit.
	 * OOP_DESIGN and JAVA are duplicate.
	 */
	@Test
	public void shouldReturnSumOfLecturesCredit_whenGivenLecturesIncludeDuplicateLectures() {
		int totalCreditsTaken;
		List<Lecture> takenLectures = new ArrayList<Lecture>();
		takenLectures.add(JAVA);
		takenLectures.add(ARTIFICIAL_INTELLIGENCE);
		takenLectures.add(OOP_DESIGN);
		takenLectures.add(OOP_DESIGN);
		takenLectures.add(JAVA);

		totalCreditsTaken = studentService.findTotalCredits(takenLectures);

		assertThat(totalCreditsTaken).isEqualTo(16);
	}
	
	/**
	 * Rule: Unique lectures should be counted only.
	 * Case: 6 lectures in the list but 4 lectures are unique.
	 */
	@Test
	public void shouldReturnUniqueLectureCount_whenLecturesAreAdded() throws Exception {
		List<Lecture> takenLectures = new ArrayList<Lecture>();
		takenLectures.add(JAVA);
		takenLectures.add(ARTIFICIAL_INTELLIGENCE);
		takenLectures.add(OOP_DESIGN);
		takenLectures.add(DUMMY_LECTURE_ONE);
		takenLectures.add(ARTIFICIAL_INTELLIGENCE);
		takenLectures.add(OOP_DESIGN);
		
		int actualCount = studentService.getNumberOfDistinctLectures(takenLectures);
		assertThat(actualCount).isEqualTo(4);
	}

	/**
	 * Rule: Available credit for Junior student is 20.
	 * Case: Available credit for Junior student is 20.
	 */
	@Test
	public void shouldReturnTrue_whenAvailableCreditEqualsToJuniorMaxCredit() {
		Student student = new JuniorStudent(1, "Galip");
		int availabeCredit = student.getAvailableCredit();

		assertThat(availabeCredit).isEqualTo(JUNIOR_LEVEL_MAX_CREDIT);
	}

	/**
	 * Rule: Max. available credit for Junior student is 20.
	 * Case: Junior student adds less than 20 credits.
     */
	@Test
	public void shouldReturnTrue_whenJuniorStudentAddsLessCreditsThanMaxAvailableCredit() {
		List<Lecture> takenLectures = new ArrayList<Lecture>();
		takenLectures.add(JAVA);
		takenLectures.add(ARTIFICIAL_INTELLIGENCE);
		takenLectures.add(OOP_DESIGN);

		Student juniorStudent = new JuniorStudent(1, "Galip");
		boolean actual = studentService.checkAvailableCreditsWithTakenCredits(juniorStudent,
				takenLectures);
		assertTrue(actual);

	}
	
	/**
	 * Rule: Max. Available Credit for Junior student is 20.
	 * Case: Junior student adds 20 credits.
	 */
	@Test
	public void shouldReturnTrue_whenJuniorStudentAddsEqualCreditsToMaxAvailableCredits() {
		List<Lecture> takenLectures = new ArrayList<Lecture>();
		takenLectures.add(JAVA);
		takenLectures.add(LOGIC_CIRCUITS);
		takenLectures.add(ARTIFICIAL_INTELLIGENCE);
		takenLectures.add(OOP_DESIGN);


		Student juniorStudent = new JuniorStudent(1, "Galip");
		boolean actual = studentService.checkAvailableCreditsWithTakenCredits(juniorStudent,
				takenLectures);
		assertTrue(actual);

	}

	/**
	 * Rule: Max. available credit for Junior student is 20.
	 * Case: Junior student adds more than 20 credits.
	 */
	@Test
	public void shouldReturnFalse_whenJuniorStudentAddsMoreCreditsThanAvailableCredits() {
		List<Lecture> takenLectures = new ArrayList<Lecture>();
		takenLectures.add(JAVA);
		takenLectures.add(ARTIFICIAL_INTELLIGENCE);
		takenLectures.add(DESIGN_PATTERN);
		takenLectures.add(OOP_DESIGN);
		takenLectures.add(MICROSERVICE_ARCHITECTURE);


		Student juniorStudent = new JuniorStudent(1, "Galip");
		boolean actual = studentService.checkAvailableCreditsWithTakenCredits(juniorStudent,
				takenLectures);
		assertFalse(actual);

	}

	/**
	 * Rule: Junior student can have max. 5 different lectures.
	 * Case: Junior student adds 4 different lectures.
	 */
	@Test
	public void shouldReturnTrue_whenJuniorAddsLessThanLectureLimit() throws Exception {
		List<Lecture> takenLectures = new ArrayList<Lecture>();
		takenLectures.add(JAVA);
		takenLectures.add(ARTIFICIAL_INTELLIGENCE);
		takenLectures.add(OOP_DESIGN);
		takenLectures.add(DUMMY_LECTURE_ONE);
		
		Student juniorStudent = new JuniorStudent(1, "Galip");
		
		boolean actual = studentService.checkNumberOfLecturesLimitByStudent(juniorStudent,
				takenLectures);
		assertTrue(actual);
	}
	
	/**
	 * Rule: Junior student can have max. 5 different lectures.
	 * Case: Junior student adds 5 different lectures.
	 */
	@Test
	public void shouldReturnTrue_whenJuniorAddsEqualNumberOfDifferentLecturesToLectureLimit() throws Exception {
		List<Lecture> takenLectures = new ArrayList<Lecture>();
		takenLectures.add(JAVA);
		takenLectures.add(ARTIFICIAL_INTELLIGENCE);
		takenLectures.add(OOP_DESIGN);
		takenLectures.add(DUMMY_LECTURE_ONE);
		takenLectures.add(DUMMY_LECTURE_TWO);
		
		Student juniorStudent = new JuniorStudent(1, "Galip");
		
		boolean actual = studentService.checkNumberOfLecturesLimitByStudent(juniorStudent,
				takenLectures);
		assertTrue(actual);
	}
	
	/**
	 * Rule: Junior student can have max. 5 different lectures.
	 * Case: Junior student adds 6 different lectures.
	 */
	@Test
	public void shouldReturnFalse_whenJuniorAddsMoreThanLectureLimit() throws Exception {
		List<Lecture> takenLectures = new ArrayList<Lecture>();
		takenLectures.add(JAVA);
		takenLectures.add(ARTIFICIAL_INTELLIGENCE);
		takenLectures.add(OOP_DESIGN);
		takenLectures.add(DUMMY_LECTURE_ONE);
		takenLectures.add(DUMMY_LECTURE_TWO);
		takenLectures.add(DUMMY_LECTURE_THREE);
		
		Student juniorStudent = new JuniorStudent(1, "Galip");
		
		boolean actual = studentService.checkNumberOfLecturesLimitByStudent(juniorStudent,
				takenLectures);
		assertFalse(actual);
	}

	/**
	 * Rule: Available credit for Senior student is 25.
	 * Case: Available credit for Senior student is 25.
	 */
	@Test
	public void shouldReturnTrue_whenAvailableCreditEqualsToSeniorMaxCredit() { 
		Student student = new SeniorStudent(1, "Joshua");
		int availableCredit = student.getAvailableCredit();

		assertThat(availableCredit).isEqualTo(SENIOR_LEVEL_MAX_CREDIT);
	}
	
	/**
	 * Rule: Max. available credit for Senior student is 25.
	 * Case: Senior student adds less than 25 credits.
     */
	@Test
	public void shouldReturnTrue_whenSeniorStudentAddsLessCreditsThanMaxAvailableCredit() {
		List<Lecture> takenLectures = new ArrayList<Lecture>();
		takenLectures.add(JAVA);
		takenLectures.add(ARTIFICIAL_INTELLIGENCE);
		takenLectures.add(OOP_DESIGN);

		Student seniorStudent = new SeniorStudent(1, "Galip");
		boolean actual = studentService.checkAvailableCreditsWithTakenCredits(seniorStudent,
				takenLectures);
		assertTrue(actual);

	}

	/**
	 * Rule: Max. Available Credit for Senior student is 25.
	 * Case: Senior student adds 25 credit.
	 */
	@Test
	public void shouldReturnTrue_whenSeniorStudentAddsEqualCreditsToAvailableCredits() {
		List<Lecture> takenLectures = new ArrayList<Lecture>();
		takenLectures.add(JAVA);
		takenLectures.add(LOGIC_CIRCUITS);
		takenLectures.add(ARTIFICIAL_INTELLIGENCE);
		takenLectures.add(DEEP_LEARNING);
		takenLectures.add(OOP_DESIGN);
		takenLectures.add(MICROSERVICE_ARCHITECTURE);

		Student seniorStudent = new SeniorStudent(1, "Galip");
		boolean actual = studentService.checkAvailableCreditsWithTakenCredits(seniorStudent,
				takenLectures);
		assertTrue(actual);
	}

	/**
	 * Rule: Max. available credit for Junior student is 25.
	 * Case: Junior student adds more than 28 credits.
	 */
	@Test
	public void shouldReturnFalse_whenSeniorStudentAddsMoreThanAvailableCredits() {
		List<Lecture> takenLectures = new ArrayList<Lecture>();
		takenLectures.add(JAVA);
		takenLectures.add(ARTIFICIAL_INTELLIGENCE);
		takenLectures.add(DESIGN_PATTERN);
		takenLectures.add(OOP_DESIGN);
		takenLectures.add(MICROSERVICE_ARCHITECTURE);
		takenLectures.add(LOGIC_CIRCUITS);

		Student seniorStudent = new SeniorStudent(1, "Galip");
		
		boolean actual = studentService.checkAvailableCreditsWithTakenCredits(seniorStudent,
				takenLectures);
		assertFalse(actual);

	}

	/**
	 * Rule: Senior student can have max. 6 different lectures.
	 * Case: Senior student adds 5 different lectures.
	 */
	@Test
	public void shouldReturnTrue_whenSeniorAddsLessThanLectureLimit() throws Exception {
		List<Lecture> takenLectures = new ArrayList<Lecture>();
		takenLectures.add(JAVA);
		takenLectures.add(ARTIFICIAL_INTELLIGENCE);
		takenLectures.add(OOP_DESIGN);
		takenLectures.add(DUMMY_LECTURE_ONE);
		takenLectures.add(DUMMY_LECTURE_TWO);
		
		Student seniorStudent = new SeniorStudent(1, "Galip");
		
		boolean actual = studentService.checkNumberOfLecturesLimitByStudent(seniorStudent,
				takenLectures);
		assertTrue(actual);
	}
	
	/**
	 * Rule: Junior student can have max. 6 different lectures.
	 * Case: Junior student adds 6 different lectures.
	 */
	@Test
	public void shouldReturnTrue_whenSeniorAddsEqualNumberOfDifferentLecturesToLectureLimit() throws Exception {
		List<Lecture> takenLectures = new ArrayList<Lecture>();
		takenLectures.add(JAVA);
		takenLectures.add(ARTIFICIAL_INTELLIGENCE);
		takenLectures.add(OOP_DESIGN);
		takenLectures.add(DUMMY_LECTURE_ONE);
		takenLectures.add(DUMMY_LECTURE_TWO);
		takenLectures.add(DUMMY_LECTURE_THREE);
		
		Student seniorStudent = new SeniorStudent(1, "Galip");
		
		boolean actual = studentService.checkNumberOfLecturesLimitByStudent(seniorStudent,
				takenLectures);
		assertTrue(actual);
	}
	
	/**
	 * Rule: Senior student can have max. 6 different lectures.
	 * Case: Senior student adds 7 different lectures.
	 */
	@Test
	public void shouldReturnFalse_whenSeniorAddsMoreThanLectureLimit() throws Exception {
		List<Lecture> takenLectures = new ArrayList<Lecture>();
		takenLectures.add(JAVA);
		takenLectures.add(ARTIFICIAL_INTELLIGENCE);
		takenLectures.add(OOP_DESIGN);
		takenLectures.add(DUMMY_LECTURE_ONE);
		takenLectures.add(DUMMY_LECTURE_TWO);
		takenLectures.add(DUMMY_LECTURE_THREE);
		takenLectures.add(DEEP_LEARNING);
		
		Student seniorStudent = new SeniorStudent(1, "Galip");
		
		boolean actual = studentService.checkNumberOfLecturesLimitByStudent(seniorStudent,
				takenLectures);
		assertFalse(actual);
	}

}