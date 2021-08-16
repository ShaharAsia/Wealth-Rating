package com.microservice.wealthrating.Logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.wealthrating.Boundaries.PersonDataBoundary;
import com.microservice.wealthrating.Data.RichPeopleDAO;
import com.microservice.wealthrating.Data.RichPersonEntity;

//This class is the implementation for the micro service interface
@Service
public class WealthRatingServiceImplementation implements WealthRatingService{
	
	@Autowired
	private RichPeopleDAO richPeopleDAO;
	private RestTemplate restTemplate;
	private final String URI_AST_EVAL = "http://central-bank/regional-info/evaluate?city=";
	private final String URI_THRESHOLD = "http://central-bank/wealth-threshold";
	
	public WealthRatingServiceImplementation() {
		this.restTemplate = new RestTemplate();
	}

	@Override
	public RichPersonEntity handleWealtRating(PersonDataBoundary personDetails) {
		RichPersonEntity entity = boundaryToEntity(personDetails);
		
		int numberOfAssets = personDetails.getFinancialInfo().getNumberOfAssets();
		Long cash = entity.getFortune();
		
		//to use central-bank's API:
		//String evaluateResponse = restTemplate.getForObject(URI_AST_EVAL, String.class);
		//String thresholdResponse = restTemplate.getForObject(URI_THRESHOLD, String.class);
		//extract threshold & evaluate values from the jsons
		
		//the  central-bank's API is unavailable , set default values for now:
		Long threshold = 10000000L; //STUB value
		int evaluate = 5000000;  //STUB value
		
		Long fortune = cash + numberOfAssets * evaluate;
		if ( fortune > threshold) //save in DB if the person is considered rich
			this.richPeopleDAO.save(entity);
		return entity;
		
	}

	//get all rich people from the DB
	@Override
	public List<RichPersonEntity> getAllRichPeople() {
		Iterable<RichPersonEntity> entitiesIterable = this.richPeopleDAO.findAll();
		List<RichPersonEntity> entitiesList = new ArrayList<RichPersonEntity>();
		
		for (RichPersonEntity entity : entitiesIterable) {	
			entitiesList.add(entity);
		}		
		return entitiesList;
	}

	//get rich from the DB using id
	@Override
	public RichPersonEntity getRichById(Long id) {
		Optional<RichPersonEntity> rich = this.richPeopleDAO.findById(id);
		if (rich.isPresent()) { //if found by id
			return rich.get();
		} else {
			return null;
		}
	}
	
	//this function converts a person's boundary into entity
	private RichPersonEntity boundaryToEntity(PersonDataBoundary personDetails) {
		RichPersonEntity entity = new RichPersonEntity();
		entity.setID(personDetails.getId());
		entity.setFirstName(personDetails.getPersonalInfo().getFirstName());
		entity.setLastName(personDetails.getPersonalInfo().getLastName());
		entity.setFortune(personDetails.getFinancialInfo().getCash());
		return entity;
	}
}
