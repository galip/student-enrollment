package com.lecture.domain;

import java.util.List;

import com.student.domain.Student;

public interface LectureService {
	public long getLecturesWithMoreThanGivenCredit(List<Lecture> lectures, int credit);
	public int getSumOfLectureCreditsByStudent(Student student);
	public List<Lecture> getRandomLectures(List<Lecture> existedLectures);
}