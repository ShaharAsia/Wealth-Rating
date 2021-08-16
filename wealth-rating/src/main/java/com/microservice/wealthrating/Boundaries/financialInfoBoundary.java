package com.microservice.wealthrating.Boundaries;

//This boundary will be used for "/handle" request from client
public class financialInfoBoundary {

	private Long cash;
	private int numberOfAssets;
	
	public Long getCash() {
		return cash;
	}
	public void setCash(Long cash) {
		this.cash = cash;
	}
	public int getNumberOfAssets() {
		return numberOfAssets;
	}
	public void setNumberOfAssets(int numberOfAssets) {
		this.numberOfAssets = numberOfAssets;
	}
	
	
}

