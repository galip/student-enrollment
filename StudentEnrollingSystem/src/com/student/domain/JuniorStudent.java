package com.student.domain;

import com.credit.domain.CREDIT;

public class JuniorStudent extends Student {
	
	private static final String JUNIOR_LEVEL = "Junior";
	
	public JuniorStudent(int studentId, String studentName) {
		super(studentId, studentName, JUNIOR_LEVEL);
	}

	@Override
	public int getAvailableCredit() {
			maxAvailableCredit = CREDIT.JUNIOR_MAX_CREDIT.getCredit();
			return maxAvailableCredit;
	}
	

	@Override
	public int getMaxAvailableNumberOfDifferentLecturesByStudentLevels() {
		maxAvailableNumberOfDifferentLectures = CREDIT.JUNIOR_MAX_DIFFERENT_LECTURES_NUMBERS.getCredit();
		return maxAvailableNumberOfDifferentLectures;
	}
}