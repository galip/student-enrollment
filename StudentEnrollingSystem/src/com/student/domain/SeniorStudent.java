package com.student.domain;

import com.credit.domain.CREDIT;

public class SeniorStudent extends Student {
	
	private static final String SENIOR_LEVEL = "Senior";
	
	public SeniorStudent(int studentId, String studentName) {
		super(studentId, studentName, SENIOR_LEVEL);
	}

	@Override
	public int getAvailableCredit() {
			maxAvailableCredit = CREDIT.SENIOR_MAX_CREDIT.getCredit();
			return maxAvailableCredit;
	}
	
	@Override
	public int getMaxAvailableNumberOfDifferentLecturesByStudentLevels() {
		maxAvailableNumberOfDifferentLectures = CREDIT.SENIOR_MAX_DIFFERENT_LECTURES_NUMBERS.getCredit();
		return maxAvailableNumberOfDifferentLectures;
	}
}