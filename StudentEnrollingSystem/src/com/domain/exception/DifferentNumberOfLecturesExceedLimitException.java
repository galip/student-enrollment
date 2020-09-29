package com.domain.exception;

public class DifferentNumberOfLecturesExceedLimitException extends Exception {
	/**
	 * Each student can have specific number of lectures which is avbailable for them.
	 * Junior student can have 5 different lectures at most.
	 * Senior student can have 6 different lectures at most.
	 */
	private static final long serialVersionUID = 1L;

	public DifferentNumberOfLecturesExceedLimitException(String message) {
		super(message);
	}
}