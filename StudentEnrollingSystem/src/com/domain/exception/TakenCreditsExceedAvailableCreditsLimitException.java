package com.domain.exception;

public class TakenCreditsExceedAvailableCreditsLimitException extends Exception {
	/**
	 * Junior student can have 20 credits at most.
	 * Senior student can have 25 credits at most.
	 */
	private static final long serialVersionUID = 1L;

	public TakenCreditsExceedAvailableCreditsLimitException(String message) {
		super(message);
	}
}