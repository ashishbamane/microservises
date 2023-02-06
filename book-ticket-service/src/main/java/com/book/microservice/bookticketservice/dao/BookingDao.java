package com.book.microservice.bookticketservice.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.book.microservice.bookticketservice.exception.EventNotFoundException;
import com.book.microservice.bookticketservice.models.EventsModel;
//import com.book.microservice.bookticketservice.models.Events;
import com.fasterxml.jackson.databind.ObjectMapper;

//@Component
public interface BookingDao {
	
	public List<EventsModel> getEvents(String location);
	public EventsModel createEvent(EventsModel event);
	public EventsModel deleteEvent(EventsModel event);
	public EventsModel updateEvent(EventsModel event) throws EventNotFoundException;
	

}
