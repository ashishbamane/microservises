package com.book.microservice.bookticketservice.daoImpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.book.microservice.bookticketservice.models.EventsModel;
import com.book.microservice.bookticketservice.models.PayloadModel;
import com.book.microservice.bookticketservice.dao.BookingDao;
import com.book.microservice.bookticketservice.entity.Events;
import com.book.microservice.bookticketservice.entity.Payload;
import com.book.microservice.bookticketservice.exception.EventNotFoundException;
import com.book.microservice.bookticketservice.repository.EventRepository;

//import com.book.microservice.bookticketservice.models.Events;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.result.DeleteResult;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Repository
public class BookingDaoImpl implements BookingDao {
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private MongoTemplate mongoTemplate;

	public BookingDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	public List<EventsModel> getEvents(String location) {
		EventsModel event = new EventsModel();
		List<EventsModel> eventModelList = new ArrayList<EventsModel>();

		try {

			List<Events> eventList = eventRepository.findByLocation(location);
			EventsModel eventsModel;

			eventModelList = eventList.stream().map(u -> {
				EventsModel vo = new EventsModel();

				vo = convertToDTO(u);
				return vo;
			}).collect(Collectors.toList());

			eventModelList.stream().forEach(o -> log.info(o.toString()));
			eventModelList.get(6);
		} catch (Exception e) {
			
		}
		//eventModelList.get(6);
		return eventModelList;
	}

	public EventsModel createEvent(EventsModel eventModel) {
		try {
			log.info(
					"Invoked dao layer event = " + eventModel.toString() + "\n Payload" + eventModel.getPayload().toString());
			Events event = new Events();

			event = convertToEntity(eventModel);
			event = eventRepository.insert(event);
		} catch (Exception e) {
			
		}

		return eventModel;
	}

	public EventsModel updateEvent(EventsModel eventModel) throws EventNotFoundException {

		Events event = new Events();

		log.info(
				"Invoked dao layer = " + eventModel.toString() + "\n Payload" + eventModel.getPayload().toString());
		Optional<Events> eventList = eventRepository.findById("63c3e51d4818d30dff324863");
		eventList = eventRepository.getEventsByeid(eventModel.getEid());
		if (!eventList.isPresent()) {
			throw new EventNotFoundException("Event Not Found", HttpStatus.NOT_FOUND);
		}
		try {
			if (eventList.isPresent()) {

				event = convertToEntity(eventModel);
				Query query = new Query();
				query.addCriteria(Criteria.where("eid").is(eventModel.getEid()));
				Update update = new Update();
				update.set("type", eventModel.getType());
				update.set("owner_id", eventModel.getOwnerId());
				event = mongoTemplate.findAndModify(query, update, Events.class);
			}

		} catch (Exception e) {
			log.error("Connect DB failed: {}", e.getMessage(), e);
			// throw new ConnectDBException(e);
		}

		return eventModel;
	}

	public EventsModel deleteEvent(EventsModel eventModel) {
		Events event = new Events();
		try {
			log.info("Invoked updating event = " + eventModel.toString() + "\n Payload"
					+ eventModel.getPayload().toString());

			Optional<Events> eventList = eventRepository.getEventsByeid(eventModel.getEid());
			if (eventList.isPresent()) {
				event = convertToEntity(eventModel);
				eventRepository.deleteById(event.getOwnerId());

				Query query = new Query();
				query.addCriteria(Criteria.where("eid").is(eventModel.getEid()));
				Update update = new Update();
				update.set("type", eventModel.getType());
				update.set("owner_id", eventModel.getOwnerId());
				mongoTemplate.remove(query, Events.class);
				// findAndModify(query, update, Events.class);
			} else {
				throw new EventNotFoundException("Event No "+eventModel.getEid()+" Not Found", HttpStatus.NOT_FOUND);
			}
		} catch (EventNotFoundException e) {
			throw new EventNotFoundException("Event No "+eventModel.getEid()+" Not Found", HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			
		}

		return eventModel;
	}

	private Events convertToEntity(EventsModel eventsModel) {
		log.info("DTO Object = {} ", eventsModel);

		Events events = modelMapper.map(eventsModel, Events.class);

		return events;
	}

	private EventsModel convertToDTO(Events events) {
		EventsModel eventsModel = modelMapper.map(events, EventsModel.class);
		return eventsModel;
	}
}
