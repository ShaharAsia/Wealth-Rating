package com.microservice.wealthrating.Logic;

import java.util.List;

import com.microservice.wealthrating.Boundaries.PersonDataBoundary;
import com.microservice.wealthrating.Data.RichPersonEntity;

//This is the interface for the micro service
public interface WealthRatingService {
	
	public RichPersonEntity handleWealtRating(PersonDataBoundary personDetails);
	public List<RichPersonEntity> getAllRichPeople();
	public RichPersonEntity getRichById(Long id);

}
