package com.mercadolibre.ipmonitor.esl;

import java.io.IOException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mercadolibre.ipmonitor.model.Country;

import io.github.cdimascio.dotenv.Dotenv;

public class CountryService extends BasicExternalServiceConnector {
	
	final Dotenv dotenv = Dotenv.load();
	
	public Country getCountryByIp(String ip) throws IOException {
		String urlString = dotenv.get("COUNTRY_INFO_SERVICE").replace("<IP_ADDRESS>", ip);
        
		String serviceResponse = getExternalServiceResponseSecured(urlString);
        
        JsonObject json = new JsonParser().parse(serviceResponse).getAsJsonObject();
        
        Country country = new Country();
        country.setCountryCode(json.get("countryCode3").getAsString());
        country.setCountryName(json.get("countryName").getAsString());
        
		return country;
	}
	
}
