package com.domain.lecture;

public enum Credit {

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

	Credit(int credit) {
		this.credit = credit;
	}

	public int getCredit() {
		return this.credit;
	}

}
