package com.domain.lecture;

import com.domain.constants.Constant;

public enum Lectures {

	JAVA(new Lecture(1, Constant.JAVA, 30, Credit.JAVA.getCredit())),
	LOGIC_CIRCUITS(new Lecture(2, Constant.LOGIC_CIRCUITS, 35, Credit.LOGIC_CIRCUITS.getCredit())),
	ARTIFICIAL_INTELLIGENCE(new Lecture(3, Constant.ARTIFICIAL_INTELLIGENCE, 20, Credit.ARTIFICIAL_INTELLIGENCE.getCredit())),
	DEEP_LEARNING(new Lecture(4, Constant.DEEP_LEARNING, 25, Credit.DEEP_LEARNING.getCredit())),
	OOP_DESIGN(new Lecture(5, Constant.OOP_DESIGN, 30, Credit.OOP_DESIGN.getCredit())),
	DESIGN_PATTERN(new Lecture(6, Constant.DESIGN_PATTERN, 30, Credit.DESIGN_PATTERN.getCredit())),
	MICROSERVICE_ARCHITECTURE(new Lecture(7, Constant.MICROSERVICE_ARCHITECTURE, 30, Credit.MICROSERVICE_ARCHITECTURE.getCredit())),
	DUMMY_LECTURE_ONE(new Lecture(8, Constant.DUMMY_LECTURE_ONE, 30, Credit.DUMMY_LECTURE_ONE.getCredit())),
	DUMMY_LECTURE_TWO(new Lecture(9, Constant.DUMMY_LECTURE_TWO, 30, Credit.DUMMY_LECTURE_TWO.getCredit())),
	DUMMY_LECTURE_THREE(new Lecture(10, Constant.DUMMY_LECTURE_THREE, 30, Credit.DUMMY_LECTURE_THREE.getCredit()));

	private Lecture lecture;

	public Lecture getValue() {
		return lecture;
	}

	Lectures(Lecture lecture) {
		this.lecture = lecture;
	}

}