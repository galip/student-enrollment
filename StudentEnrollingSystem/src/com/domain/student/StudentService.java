package com.domain.student;

import java.util.List;

import com.domain.lecture.Lecture;

public interface StudentService {
	public int findTotalCredits(List<Lecture> lectures);
	public boolean checkAvailableCreditsWithTakenCredits(Student student, List<Lecture> lectures);
	public int getNumberOfDistinctLectures(List<Lecture> lectures);
	public boolean checkNumberOfLecturesLimitByStudent(Student student, List<Lecture> lectures);
}