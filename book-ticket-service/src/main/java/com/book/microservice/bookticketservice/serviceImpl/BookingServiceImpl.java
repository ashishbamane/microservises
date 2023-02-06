package com.book.microservice.bookticketservice.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.microservice.bookticketservice.models.EventsModel;
import com.book.microservice.bookticketservice.dao.BookingDao;
import com.book.microservice.bookticketservice.service.BookingService;
//import com.book.microservice.bookticketservice.models.Events;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Service
public class BookingServiceImpl implements BookingService {
	@Autowired
	private BookingDao bookingDao;

	public List<EventsModel> getEvents(String location) {

		return bookingDao.getEvents(location);
	}

	public EventsModel createEvent(EventsModel event) {
		event = bookingDao.createEvent(event);
		return event;
	}

	public EventsModel updateEvent(EventsModel event) {

		event = bookingDao.updateEvent(event);

		return event;
	}

	public EventsModel deleteEvent(EventsModel event) {

		event = bookingDao.deleteEvent(event);

		return event;
	}

}
