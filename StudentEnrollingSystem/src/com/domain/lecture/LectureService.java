package com.domain.lecture;

import java.util.List;

import com.domain.student.Student;

public interface LectureService {
	public long getLecturesWithMoreThanGivenCredit(List<Lecture> lectures, int credit);
	public int getSumOfLectureCreditsByStudent(Student student);
	public List<Lecture> getRandomLectures(List<Lecture> existedLectures);
}