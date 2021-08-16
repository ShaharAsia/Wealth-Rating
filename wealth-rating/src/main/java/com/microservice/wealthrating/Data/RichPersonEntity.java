package com.microservice.wealthrating.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


//This object is the way we save rich person is the DB
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RichPersonEntity {
	
	@Id
	private Long ID;
	private String firstName;
	private String lastName;
	private Long fortune;
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Long getFortune() {
		return fortune;
	}
	public void setFortune(Long fortune) {
		this.fortune = fortune;
	}

	
}
