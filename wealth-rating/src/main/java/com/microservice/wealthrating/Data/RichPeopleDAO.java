package com.microservice.wealthrating.Data;

import org.springframework.data.repository.CrudRepository;

//Data Access Object to rich people DB
public interface RichPeopleDAO extends CrudRepository<RichPersonEntity, Long> {

	
}