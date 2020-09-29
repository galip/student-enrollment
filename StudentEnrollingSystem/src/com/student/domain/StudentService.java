package com.student.domain;

import java.util.List;

import com.lecture.domain.Lecture;

public interface StudentService {
	public int findTotalCredits(List<Lecture> lectures);
	public boolean checkAvailableCreditsWithTakenCredits(Student student, List<Lecture> lectures);
	public int getNumberOfDistinctLectures(List<Lecture> lectures);
	public boolean checkNumberOfLecturesLimitByStudent(Student student, List<Lecture> lectures);
}