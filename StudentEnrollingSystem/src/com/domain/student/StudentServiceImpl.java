package com.domain.student;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.domain.lecture.Lecture;

public class StudentServiceImpl implements StudentService {

	public static final int MAX_NUMBER_OF_LECTURES_JUNIOR = 5;
	public static final int MAX_NUMBER_OF_LECTURES_SENIOR = 6;

	public int findTotalCredits(List<Lecture> lectures) {
		int totalLectureCredits = 0;
		totalLectureCredits = Optional.ofNullable(lectures).isPresent()
				? lectures.stream().filter(distinctById(Lecture::getLectureId)).mapToInt(p -> p.getCredit()).sum()
				: 0;
//		totalLectureCredits = lectures.stream().mapToInt(p -> p.getCredit()).sum();
		return totalLectureCredits;

	}

	@Override
	public boolean checkAvailableCreditsWithTakenCredits(Student student, List<Lecture> takenLectures) {
//		System.out.println(Optional.ofNullable(takenLectures).isPresent() ? takenLectures : Collections.emptyList());
//		System.out.println(student.getAvailableCredit() >= findTotalCredits(Optional.ofNullable(takenLectures).isPresent() ? takenLectures : Collections.emptyList()) ? true : false);
//		System.out.println(findTotalCredits(Optional.ofNullable(takenLectures).isPresent() ? takenLectures : Collections.emptyList()));
		return student.getAvailableCredit() >= findTotalCredits(
				Optional.ofNullable(takenLectures).isPresent() ? takenLectures : Collections.emptyList()) ? true
						: false;
	}

	public int getNumberOfDistinctLectures(List<Lecture> lectures) {
		return Optional.ofNullable(lectures).isPresent()
				? lectures.stream().filter(distinctById(Lecture::getLectureId)).collect(Collectors.toList()).size()
				: 0;
	}

	public static <T> Predicate<T> distinctById(Function<? super T, Object> keyExtractor) {
		Set<Object> setObject = ConcurrentHashMap.newKeySet();
		return t -> setObject.add(keyExtractor.apply(t));
	}

	public boolean checkNumberOfLecturesLimitByStudent(Student student, List<Lecture> lectures) {
		return getNumberOfDistinctLectures(lectures) <= student
				.getMaxAvailableNumberOfDifferentLecturesByStudentLevels() ? true : false;
	}
}