package com.book.microservice.bookticketservice.models;

import javax.validation.constraints.NotBlank;

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
public class PayloadModel {
	
	@JsonProperty("event_date")
	private String eventDate;
	
	@JsonProperty("event_time")
	private String eventTime;
	@NotBlank(message = "location is mandatory")
	@JsonProperty("location")
	private String location;
	@JsonProperty("items")
	private ItemsModel  item;
	
}
