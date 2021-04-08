package com.mercadolibre.ipmonitor.esl;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mercadolibre.ipmonitor.model.Currency;

import io.github.cdimascio.dotenv.Dotenv;

public class RateService extends BasicExternalServiceConnector {
	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd";
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
	
	private final Dotenv dotenv = Dotenv.load();
	
	public Currency getRatesByCurrency(Currency currency) throws IOException {
		String urlString = dotenv.get("RATE_INFO_SERVICE").replace("<ACCESS_KEY>", dotenv.get("RATE_INFO_SERVICE_ACCESS_KEY").replace("<CURRENCY>", currency.getCurrencyCode()));
        
		String serviceResponse = getExternalServiceResponse(urlString);
        
        JsonObject json = new JsonParser().parse(serviceResponse).getAsJsonObject();
        
        currency = getCurrencyRatesByEurRates(
        		currency, json.get("rates").getAsJsonObject().get("USD").getAsString(), 
        		json.get("rates").getAsJsonObject().get(currency.getCurrencyCode()).getAsString()
        );
        currency = getRatesDate(currency, json.get("date").getAsString());
        
		return currency;
	}
	
	private Currency getCurrencyRatesByEurRates(Currency currencyObj, String eurEquivalenceInUsdStr, String eurEquivalenceInCurrencyStr) {
		BigDecimal eurEquivalenceInUsd = new BigDecimal(eurEquivalenceInUsdStr);
		BigDecimal eurEquivalenceInCurrency = new BigDecimal(eurEquivalenceInCurrencyStr);
		
		currencyObj.setEurRate(new BigDecimal(1).divide(eurEquivalenceInCurrency, 10, RoundingMode.HALF_UP));
		currencyObj.setUsdRate(eurEquivalenceInUsd.divide(eurEquivalenceInCurrency, 10, RoundingMode.HALF_UP));
		
		return currencyObj;
	}
	
	private Currency getRatesDate(Currency currencyObj, String ratesDateStr) {		
		currencyObj.setRatesDate(LocalDate.parse(ratesDateStr, formatter));
		
		return currencyObj;
	}
}
