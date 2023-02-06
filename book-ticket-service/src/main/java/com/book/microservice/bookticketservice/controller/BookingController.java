package com.book.microservice.bookticketservice.controller;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.book.microservice.bookticketservice.models.DataResponse;
import com.book.microservice.bookticketservice.models.EventsModel;
import com.book.microservice.bookticketservice.models.Header;
import com.book.microservice.bookticketservice.service.BookingService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@Data
public class BookingController {
	@Autowired
	private BookingService bookingService;

	
	@Validated
	@GetMapping("/events")
	public ResponseEntity<DataResponse> getEvents(@Valid @RequestParam("location") String location) {
		log.info("Request get - /events/ "+ location);
		DataResponse dataResponse = new DataResponse();
		List<EventsModel> eventModelList = bookingService.getEvents(location);
		dataResponse.setPayload((List<EventsModel>) eventModelList);
		
		var headers = new HttpHeaders();
		headers.add("Responded", "BookingController");
		log.info("Response  get - /events/ " +  dataResponse);
		return ResponseEntity.accepted().headers(headers).body(dataResponse);

	}

	@GetMapping("/test")
	public String getTests() {

		return "test";
	}

	@PostMapping("/events")
	@Validated
	public ResponseEntity<DataResponse> createEvent(@RequestBody @Valid EventsModel event) {
		log.info("Request create - /events/ "+ event.toString());
		DataResponse dataResponse = new DataResponse();
		dataResponse.setPayload((EventsModel) bookingService.createEvent(event));
		var headers = new HttpHeaders();
		headers.add("Responded", "BookingController");
		log.info("Response  create - /events/ " +  dataResponse);
		return ResponseEntity.accepted().headers(headers).body(dataResponse);
		// return bookingService.createEvent(event);
	}

	@PutMapping("/events/{id}")
	public ResponseEntity<DataResponse> updateEvent(@RequestBody EventsModel event, @PathVariable("id") int id) {
		log.info("Request update - /events/ "+ id);
		DataResponse dataResponse = new DataResponse();
		event.setEid(id);
		dataResponse.setPayload((EventsModel) bookingService.updateEvent(event));

		var headers = new HttpHeaders();
		headers.add("Responded", "BookingController");
		log.info("Response  update - /events/ " +  dataResponse);
		
		return ResponseEntity.accepted().headers(headers).body(dataResponse);
	}

	@DeleteMapping("/events")
	public ResponseEntity<DataResponse> deleteEvent(@RequestBody EventsModel event) {
		log.info("Request delete - /events/ "+ event.toString());
		DataResponse dataResponse = new DataResponse();
		dataResponse.setPayload((EventsModel) bookingService.deleteEvent(event));

		var headers = new HttpHeaders();
		headers.add("Responded", "BookingController");
		log.info("Response  delete - /events/ " +  dataResponse);
		return ResponseEntity.accepted().headers(headers).body(dataResponse);
	}

	/*
	 * public String getTraceId() { Span span = tracer.currentSpan(); return
	 * span.context().traceId(); }
	 */
}
