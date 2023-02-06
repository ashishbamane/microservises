package com.book.microservice.bookticketservice.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.data.mongodb.core.mapping.Unwrapped;

import com.book.microservice.bookticketservice.models.PayloadModel;
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
public class EventsModel {
	@JsonProperty("_id")
	private String id;
	@JsonProperty("eid")
	private int eid;
	
	@JsonProperty("type")
	private String type;
	@Size(min=2, max=30)
	@NotNull
	@JsonProperty("owner_id")
	private String ownerId;
	@JsonProperty("payload")
	private PayloadModel  payload;
	@JsonProperty("published_at")
	private  String publishedAt;
	
}
