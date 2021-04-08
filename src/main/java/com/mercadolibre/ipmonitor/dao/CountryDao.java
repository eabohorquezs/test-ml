package com.mercadolibre.ipmonitor.dao;

import com.mercadolibre.ipmonitor.model.Country;

public class CountryDao extends BasicDao {

	public Country getCountryByCountryCode(String countryCode) {
		return datastore.find(Country.class).filter("countryCode", countryCode).get();
	}
	
	public void saveCountry(Country country) {
		datastore.save(country);
	}

}
