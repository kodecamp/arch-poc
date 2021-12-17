package com.example.demo.person;

import com.example.demo.service.BaseDto;
import lombok.Builder;
import lombok.Getter;

/**
* PersonDto
*/
@Builder
@Getter
public class PersonDto implements BaseDto {
	private final String id;
	private final String name;

}
