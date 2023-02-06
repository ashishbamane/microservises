package com.book.microservice.bookticketservice.models;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemsModel {
	
	@JsonProperty("event_id")	
	private String eventId;
	@JsonProperty("event_name")
	private String eventName;
	@JsonProperty("event_title")
	private String eventTitle;
	@JsonProperty("event_description")
	private String eventDescription;

}
