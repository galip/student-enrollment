package com.domain.student;

public enum LectureLimit {

	/**
	 * Max. number of different lectures for student levels.
	 */
	JUNIOR(5),
	SENIOR(6);

	private final int credit;

	LectureLimit(int credit) {
		this.credit = credit;
	}

	public int getCredit() {
		return this.credit;
	}
	
}
