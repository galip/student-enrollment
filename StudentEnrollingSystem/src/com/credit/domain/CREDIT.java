package com.credit.domain;

public enum CREDIT {
	
	/**
	 * Max. available credit for levels.
	 */
	JUNIOR_MAX_CREDIT(20), 
	SENIOR_MAX_CREDIT(25),
	
	/**
	 * Max. number of different lectures for student levels.
	 */
	JUNIOR_MAX_DIFFERENT_LECTURES_NUMBERS(5),
	SENIOR_MAX_DIFFERENT_LECTURES_NUMBERS(6),

	/**
	 * Credits of each lectures.
	 */
	JAVA(6), 
	LOGIC_CIRCUITS(4), 
	ARTIFICIAL_INTELLIGENCE(5), 
	DEEP_LEARNING(3), 
	OOP_DESIGN(5), 
	DESIGN_PATTERN(6),
	MICROSERVICE_ARCHITECTURE(2),
	DUMMY_LECTURE_ONE(1),
	DUMMY_LECTURE_TWO(1),
	DUMMY_LECTURE_THREE(1);

	private final int credit;

	CREDIT(int credit) {
		this.credit = credit;
	}

	public int getCredit() {
		return this.credit;
	}

}
