package com.microservice.wealthrating.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.wealthrating.Boundaries.PersonDataBoundary;
import com.microservice.wealthrating.Data.RichPersonEntity;
import com.microservice.wealthrating.Logic.WealthRatingService;

@RestController
@RequestMapping("/wealthRating") //prefix for all requests
public class WealthRatingController {

	@Autowired
	private WealthRatingService wealthRatingService;
	
	//Handle a PersonDataBoundary json received from cllient 
	@RequestMapping(
			path = "/handle",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public RichPersonEntity handleRating(@RequestBody PersonDataBoundary personDetails) {
		return wealthRatingService.handleWealtRating(personDetails);
	}
	
	//Returns an array of all the rich people in the DB
	@RequestMapping(
			path = "/getAllrich",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public RichPersonEntity[] getAllrich() {
		return wealthRatingService.getAllRichPeople().toArray(new RichPersonEntity[0]);
	}
	
	//Returns a rich man by id
	@RequestMapping(
			path = "/getRichByID/{personID}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public RichPersonEntity getByID(@PathVariable("personID") Long personID) {
		return wealthRatingService.getRichById(personID);
	}
	
}
