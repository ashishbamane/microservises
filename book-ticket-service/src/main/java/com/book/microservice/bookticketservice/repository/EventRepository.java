package com.book.microservice.bookticketservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.book.microservice.bookticketservice.entity.Events;
@Repository
public interface EventRepository extends MongoRepository<Events, String> {

  //public Customer findByFirstName(String firstName);
  public List<Events> findByOwnerId(String ownerId);
  //public List<Events> findByeId(int eId);
  @Query("{eid : ?0}")                  // SQL Equivalent : SELECT * FROM BOOK where author = ?
  Optional<Events> getEventsByeid(int eId);
  
  @Query(value = "{ 'payload.location' : ?0 }")
  List<Events> findByLocation(String location);
}