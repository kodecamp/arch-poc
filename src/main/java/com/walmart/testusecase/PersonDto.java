package com.walmart.testusecase;

import com.walmart.commons.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
* PersonDto
*/
@Getter
@AllArgsConstructor
public class PersonDto implements BaseDto {
	private final String id;
	private final String name;
	private final int age;

	static public PersonDto from(String id, String name, int age) {
		return new PersonDto(id, name, age);
	};
}
