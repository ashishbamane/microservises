package com.book.microservice.bookticketservice.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DataResponse<T> {
	private Header header;
	private T payload; // any object or JSON can be sent through this generic Object
	private List<ErrorDetails> errorList;
	private List<String> errors;
}
