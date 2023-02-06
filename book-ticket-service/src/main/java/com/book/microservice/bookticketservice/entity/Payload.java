package com.book.microservice.bookticketservice.entity;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class Payload {
	
	@Field("event_date")
	private String eventDate;
	@Field("event_time")
	private String eventTime;
	@Field("location")
	private String location;
	@Field("items")
	private Items  item;
	}
