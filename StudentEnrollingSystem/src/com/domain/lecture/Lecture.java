package com.domain.lecture;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Lecture {
	private int lectureId;
	private String name;
	private int capacity;
	private int credit;
}