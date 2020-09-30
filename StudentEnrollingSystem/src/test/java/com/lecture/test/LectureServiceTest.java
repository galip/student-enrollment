package com.lecture.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.domain.lecture.Lecture;
import com.domain.lecture.LectureService;
import com.domain.lecture.LectureServiceImpl;
import com.domain.lecture.Lectures;

public class LectureServiceTest {

	private static final Lecture JAVA = Lectures.JAVA.getValue();
	private static final Lecture LOGIC_CIRCUITS = Lectures.LOGIC_CIRCUITS.getValue();
	private static final Lecture ARTIFICIAL_INTELLIGENCE = Lectures.ARTIFICIAL_INTELLIGENCE.getValue();
	private static final Lecture DEEP_LEARNING = Lectures.DEEP_LEARNING.getValue();
	private static final Lecture OOP_DESIGN = Lectures.OOP_DESIGN.getValue();
	private static final Lecture DESIGN_PATTERN = Lectures.DESIGN_PATTERN.getValue();
	private static final Lecture MICROSERVICE_ARCHITECTURE = Lectures.MICROSERVICE_ARCHITECTURE.getValue();

	List<Lecture> lectures = new ArrayList<Lecture>();

	@Before
	public void setUpLectures() {

		lectures.add(JAVA);
		lectures.add(LOGIC_CIRCUITS);
		lectures.add(ARTIFICIAL_INTELLIGENCE);
		lectures.add(DEEP_LEARNING);
		lectures.add(OOP_DESIGN);
	}

	/**
	 * JAVA and ARTIFICIAL_INTELLIGENCE are 5 credits.
	 */
	@Test
	public void shouldReturnCountsOfLectures_whenGivenCredit() {
		LectureService lectureService = new LectureServiceImpl();
		long count = lectureService.getLecturesWithMoreThanGivenCredit(lectures, 5);

		assertThat(count).isEqualTo(2L);
	}
}