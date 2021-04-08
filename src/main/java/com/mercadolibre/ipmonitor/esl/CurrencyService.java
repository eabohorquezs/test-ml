package com.mercadolibre.ipmonitor.esl;

import java.io.IOException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mercadolibre.ipmonitor.model.Currency;

import io.github.cdimascio.dotenv.Dotenv;

public class CurrencyService extends BasicExternalServiceConnector {

	final Dotenv dotenv = Dotenv.load();
	
	public Currency getCurrencyByCountryCode(String countryCode) throws IOException {
		String urlString = dotenv.get("CURRENCY_INFO_SERVICE").replace("<COUNTRY>", countryCode);

		String serviceResponse = getExternalServiceResponseSecured(urlString);
        
        JsonObject json = new JsonParser().parse(serviceResponse).getAsJsonObject();
        JsonElement jsonElm = json.get("currencies").getAsJsonArray().get(0);
        
        Currency currency = new Currency();
        currency.setCurrencyCode(jsonElm.getAsJsonObject().get("code").getAsString());
        currency.setCurrencyName(jsonElm.getAsJsonObject().get("name").getAsString());
        
		return currency;
	}

}
