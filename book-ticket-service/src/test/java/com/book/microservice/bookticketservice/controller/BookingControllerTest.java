package com.book.microservice.bookticketservice.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.mockito.BDDMockito.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.book.microservice.bookticketservice.exception.EventNotFoundException;
import com.book.microservice.bookticketservice.models.EventsModel;
import com.book.microservice.bookticketservice.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
public class BookingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookingService bookingService;

	@Autowired
	private ObjectMapper objectMapper;

	private EventsModel musicalEvent;

	private EventsModel technicalcalEvent;

	@BeforeEach
	void init() {
		musicalEvent = new EventsModel();
		musicalEvent.setType("created");
		musicalEvent.setOwnerId("102");

		technicalcalEvent = new EventsModel();
		technicalcalEvent.setType("updated");
		technicalcalEvent.setOwnerId("412");

	}

	@Test
	void shouldFetchAllEvents() throws Exception {

		List<EventsModel> list = new ArrayList<>();
		list.add(musicalEvent);
		list.add(technicalcalEvent);

		when(bookingService.getEvents("pune")).thenReturn(list);

		this.mockMvc.perform(get("/events?location=pune")).andExpect(status().is(202))
				// .andExpect(jsonPath("$.events").exists());
				.andExpect(jsonPath("$.payload[0]").exists());

		// .andExpect(jsonPath("$.size()", is(list.size())));
	}

	@Test
	public void givenUpdatedEventThenReturnUpdatedEventObject() throws Exception {
		

		EventsModel musicEvent = new EventsModel();
		musicEvent.setType("created");
		musicEvent.setOwnerId("102");
		musicEvent.setEid(909);
	
		given(bookingService.updateEvent(any(EventsModel.class))).willAnswer((invocation) -> invocation.getArgument(0));

		// when - action or the behaviour that we are going test
		ResultActions response = mockMvc.perform(put("/events/{id}", 909).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(musicEvent)));

		// then - verify the output
		response.andExpect(status().is(202))
				// .andDo(print())
				.andExpect(jsonPath("$.payload").exists()).andExpect(jsonPath("$.payload.eid").exists())
				.andExpect(jsonPath("$.payload.eid", is(musicEvent.getEid())));
		// .andExpect(jsonPath("$.lastName", is(updatedEmployee.getLastName())))
		// .andExpect(jsonPath("$.email", is(updatedEmployee.getEmail())));
	}

	@Test
    public void givenInvalidIDForUpdateEventThenThrowException() throws Exception{
        
		EventsModel musicEvent = new EventsModel();
		musicEvent.setType("created");
		musicEvent.setOwnerId("102");
		musicEvent.setEid(909);
        //given(bookingService.getEmployeeById(employeeId)).willReturn(Optional.of(technicalcalEvent));
		when(bookingService.updateEvent(any())).thenThrow(EventNotFoundException.class);

		this.mockMvc.perform(put("/events/{id}", 1890)).andExpect(status().is(400));
				
    }
}
