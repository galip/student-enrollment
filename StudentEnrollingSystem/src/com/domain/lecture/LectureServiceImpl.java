package com.domain.lecture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.domain.student.Student;

public class LectureServiceImpl implements LectureService {

	@Override
	public long getLecturesWithMoreThanGivenCredit(List<Lecture> lectures, int credit) {
		return lectures.stream().filter(lecture -> lecture.getCredit() == credit).count();
	}

	public static Predicate<Lecture> isMoreThanGivenCapacity(int capacity) {
		return p -> p.getCapacity() > capacity;
	}

	public int getSumOfLectureCreditsByStudent(Student student) {
		return student.getLectures().stream().map(x -> x.getCredit()).collect(Collectors.summingInt(Integer::intValue));

	}

	public int findTotalCredits(List<Lecture> lectures) {
		int totalLectureCredits = 0;
		totalLectureCredits = Optional.ofNullable(lectures).isPresent()
				? lectures.stream().filter(distinctById(Lecture::getLectureId)).mapToInt(p -> p.getCredit()).sum()
				: 0;
//		totalLectureCredits = lectures.stream().mapToInt(p -> p.getCredit()).sum();
		return totalLectureCredits;

	}

	public static <T> Predicate<T> distinctById(Function<? super T, Object> keyExtractor) {
		Set<Object> setObject = ConcurrentHashMap.newKeySet();
		return t -> setObject.add(keyExtractor.apply(t));
	}

	public List<Lecture> getRandomLectures(List<Lecture> existedLectures) {
		List<Lecture> selectedRandomLectures = new ArrayList<Lecture>();
		int numberOfLectureToBeCreated = getRandomNumberOfLecturesToBeCreated(4, 9);
		Collections.shuffle(existedLectures);
		for (int i = 0; i < numberOfLectureToBeCreated; i++) {
			selectedRandomLectures.add(i, existedLectures.get(i));
		}
		return selectedRandomLectures;
	}

	public static int getRandomNumberOfLecturesToBeCreated(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

}