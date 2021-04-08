package com.mercadolibre.ipmonitor.model;

import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;


@Entity
public class Country {
	/*
	 
	"countryName": "Colombia", // FROM SERVICE 1
	"currency": [{ // FROM SERVICE 2
            "code": "USD",
            "name": "United States dollar"
            
        }
    ],
	"date": "2021-03-24", // RATES DATE
	"rates": {
	"USD": 1.184968,
	"EUR": 1,
	"COP": 4288.989874
	}
	*/
	@Id
	private String countryCode;
	private String countryName;
	@Reference
	private Currency currency;
	
	public String getCountryCode() {
		return countryCode;
	}
	
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	public String getCountryName() {
		return countryName;
	}
	
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
}
