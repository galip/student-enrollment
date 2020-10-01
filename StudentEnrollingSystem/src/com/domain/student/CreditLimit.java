package com.domain.student;

public enum CreditLimit {

	/**
	 * Max. available credit for levels.
	 */
	JUNIOR(20), 
	SENIOR(25);

	private final int credit;

	CreditLimit(int credit) {
		this.credit = credit;
	}

	public int getCredit() {
		return this.credit;
	}
}
