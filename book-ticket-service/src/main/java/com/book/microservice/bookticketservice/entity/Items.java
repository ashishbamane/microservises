package com.book.microservice.bookticketservice.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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
@Document
public class Items {
	@Field("event_id")
	private String eventId;
	@Field("event_name")
	private String eventName;
	@Field("event_title")
	private String eventTitle;
	@Field("event_description")
	private String eventDescription;
}
