package com.student.domain;

public class JuniorStudent extends Student {
	
	private static final String JUNIOR_LEVEL = "Junior";
	
	public JuniorStudent(int studentId, String studentName) {
		super(studentId, studentName, JUNIOR_LEVEL);
	}

	@Override
	public int getAvailableCredit() {
			maxAvailableCredit = CreditLimit.JUNIOR.getCredit();
			return maxAvailableCredit;
	}
	

	@Override
	public int getMaxAvailableNumberOfDifferentLecturesByStudentLevels() {
		maxAvailableNumberOfDifferentLectures = LectureLimit.JUNIOR.getCredit();
		return maxAvailableNumberOfDifferentLectures;
	}
}