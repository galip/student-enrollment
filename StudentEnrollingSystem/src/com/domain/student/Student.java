package com.domain.student;

import java.util.ArrayList;
import java.util.List;

import com.domain.lecture.Lecture;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class Student {
	
	private int studentId;
	private String studentName;
	private String level;
	private int lectureId;
	protected int maxAvailableCredit;
	protected int maxAvailableNumberOfDifferentLectures;
	
	private List<Lecture> lectures = new ArrayList<Lecture>();
	
	public Student(int studentId, String studentName, String level) {
		this.studentId = studentId;
		this.studentName = studentName;
		this.level = level;
	}
	
	public abstract int getAvailableCredit();
	public abstract int getMaxAvailableNumberOfDifferentLecturesByStudentLevels();
	
	public void addLecture(Lecture lecture) {
		lectures.add(lecture);
	}
	
	public void addLectures(List<Lecture> lectures1) {
		lectures.addAll(lectures1);
	}
	
}