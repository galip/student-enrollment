package com.student.domain;

public class SeniorStudent extends Student {
	
	private static final String SENIOR_LEVEL = "Senior";
	
	public SeniorStudent(int studentId, String studentName) {
		super(studentId, studentName, SENIOR_LEVEL);
	}

	@Override
	public int getAvailableCredit() {
			maxAvailableCredit = CreditLimit.SENIOR.getCredit();
			return maxAvailableCredit;
	}
	
	@Override
	public int getMaxAvailableNumberOfDifferentLecturesByStudentLevels() {
		maxAvailableNumberOfDifferentLectures = LectureLimit.SENIOR.getCredit();
		return maxAvailableNumberOfDifferentLectures;
	}
}