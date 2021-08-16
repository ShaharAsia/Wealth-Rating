package com.microservice.wealthrating;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import com.microservice.wealthrating.Boundaries.PersonDataBoundary;
import com.microservice.wealthrating.Data.RichPersonEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) //to run this test on random port
class WealthRatingApplicationTests {
	
	private int port; 
	private String url;
	private RestTemplate restTemplate;
	
	@LocalServerPort // inject port number
	public void setPort(int port) {
		this.port = port;
		this.restTemplate = new RestTemplate();
	}

	// init url after constructing the object
	@PostConstruct
	public void init() {
		this.url = "http://localhost:" + this.port + "/wealthRating"; //build the prefix to the requests
	}

	@Test //make sure that "/handle" return status 2xx
	public void testHandleReturnsStatus2XX() throws Exception{ 
		
		//create boundary
		PersonDataBoundary boundary = new PersonDataBoundary(123456789L);
		boundary.getPersonalInfo().setFirstName("Bill");
		boundary.getPersonalInfo().setLastName("Gates");
		boundary.getPersonalInfo().setCity("Washington");
		boundary.getFinancialInfo().setCash(16000000000L);
		boundary.getFinancialInfo().setNumberOfAssets(50);
		
		//perform the query:
		PersonDataBoundary response = this.restTemplate.postForObject(this.url + "/handle", boundary ,PersonDataBoundary.class);
		
		//make sure it returns the same boundary
		assertThat(response.getId() == 123456789L);
	}
	
	@Test //make sure that "getRichByID/{personID}" works fine
	public void testGetRichById() throws Exception{ 
		
		//create boundary
		PersonDataBoundary boundary = new PersonDataBoundary(987654321L);
		boundary.getPersonalInfo().setFirstName("Shahar");
		boundary.getPersonalInfo().setLastName("Asia");
		boundary.getPersonalInfo().setCity("Petach Tikva");
		boundary.getFinancialInfo().setCash(20000000000L);
		boundary.getFinancialInfo().setNumberOfAssets(100);
		
		//handle the man
		this.restTemplate.postForObject(this.url + "/handle", boundary ,PersonDataBoundary.class);
		
		//find the man in DB by id:
		RichPersonEntity response = 
				this.restTemplate.getForObject(
						this.url + "/getRichByID/{personID}",RichPersonEntity.class,boundary.getId());
		
		//make sure it finds the rich person
		assertThat(response.getID() == 987654321L);
	}
	
	@Test //make sure that "/getAllrich" works fine
	public void testGetAllRich() throws Exception{ 
		
		//create Rich man boundary
		PersonDataBoundary boundary1 = new PersonDataBoundary(111111111L);
		boundary1.getPersonalInfo().setFirstName("Shahar");
		boundary1.getPersonalInfo().setLastName("Asia");
		boundary1.getPersonalInfo().setCity("Petach Tikva");
		boundary1.getFinancialInfo().setCash(20000000000L);
		boundary1.getFinancialInfo().setNumberOfAssets(100);
		
		//create Rich man boundary
		PersonDataBoundary boundary2 = new PersonDataBoundary(222222222L);
		boundary2.getPersonalInfo().setFirstName("Shahar");
		boundary2.getPersonalInfo().setLastName("Asia");
		boundary2.getPersonalInfo().setCity("Petach Tikva");
		boundary2.getFinancialInfo().setCash(100L);
		boundary2.getFinancialInfo().setNumberOfAssets(2);
		
		//handle both
		this.restTemplate.postForObject(this.url + "/handle", boundary1 ,PersonDataBoundary.class);
		this.restTemplate.postForObject(this.url + "/handle", boundary2 ,PersonDataBoundary.class);
		
		//get all Rich people from DB
		RichPersonEntity[] response = 
				this.restTemplate.getForObject
							(this.url + "/getAllrich",RichPersonEntity[].class);
		
		//make sure there is only boundary1
		assertThat(response.length == 1);
		assertThat(response[0].getID() == 111111111L);
	}

}
