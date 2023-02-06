package com.book.microservice.bookticketservice.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.data.mongodb.core.mapping.Unwrapped;

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
@Document("event")
public class Events {
	@Id
	@Field("_id")
	@MongoId(value = FieldType.OBJECT_ID)
	private String id;
	
	@Field("eid")
	private int eid;
	private String type;
	
	@Field("owner_id")
	private String ownerId;
	@Field("payload")
	//@DBRef	
	private Payload  payload;
	
	@Field("published_at")
	private  String publishedAt;
	}
