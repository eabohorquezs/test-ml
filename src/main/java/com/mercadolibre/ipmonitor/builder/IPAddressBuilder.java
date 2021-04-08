package com.mercadolibre.ipmonitor.builder;

import java.time.LocalDate;

import com.mercadolibre.ipmonitor.dao.CountryDao;
import com.mercadolibre.ipmonitor.dao.CurrencyDao;
import com.mercadolibre.ipmonitor.dao.IPAddressDao;
import com.mercadolibre.ipmonitor.esl.CountryService;
import com.mercadolibre.ipmonitor.esl.CurrencyService;
import com.mercadolibre.ipmonitor.esl.RateService;
import com.mercadolibre.ipmonitor.model.Country;
import com.mercadolibre.ipmonitor.model.Currency;
import com.mercadolibre.ipmonitor.model.IPAddress;

public class IPAddressBuilder {
	
	private IPAddressDao ipDao = new IPAddressDao();
	private CountryDao countryDao = new CountryDao();
	private CurrencyDao currencyDao = new CurrencyDao();
	
	private CountryService countryService = new CountryService();
	private CurrencyService currencyService = new CurrencyService();
	private RateService rateService = new RateService();
	
	public IPAddressBuilder() {
		
	}
	
	public IPAddress build(String ip) throws Exception {
		return buildIp(ip);
	}
	
	public IPAddress buildIp(String ip) throws Exception {
		IPAddress ipAddress = ipDao.getIpAddressById(ip);
		
		if(ipAddress == null) {
			ipAddress = new IPAddress();
			ipAddress.setIp(ip);
			ipAddress.setBanned(false);	
			
		} else {
			if (ipAddress.getIsBanned()) {
				throw new Exception("The IP address is banned to be consulted.");
			}
		}
		
		ipAddress.setCountry(buildCountry(ipAddress));
		
		ipDao.saveIPAddress(ipAddress);
		
		return ipAddress;
	}
	
	public Country buildCountry(IPAddress ipAddress) throws Exception {	
		Country country = ipAddress.getCountry();
		
		if(country == null) {
			Country serviceCountry = countryService.getCountryByIp(ipAddress.getIp());
			country = countryDao.getCountryByCountryCode(serviceCountry.getCountryCode());
			
			if(country == null) {
				country = serviceCountry;
			}
		} 
		
		country.setCurrency(buildCurrency(country));
		
		countryDao.saveCountry(country);
		
		return country;
	}
	
	public Currency buildCurrency(Country country) throws Exception {	
		Currency currency = country.getCurrency();
		
		if(currency == null) {
			Currency serviceCurrency = currencyService.getCurrencyByCountryCode(country.getCountryCode());
			Currency dbCurrency = currencyDao.getCurrencyByCode(serviceCurrency.getCurrencyCode());
			
			if(dbCurrency == null) {
				currency = serviceCurrency;
			}
		}
		
		if(currency.getRatesDate() == null || currency.getRatesDate().isBefore(LocalDate.now())) {
			currency = rateService.getRatesByCurrency(currency);
		}
		
		currencyDao.saveCurrency(currency);
		
		return currency;
	}
	
	public IPAddress buildBannedIp(String ip) throws Exception {
		IPAddress ipAddress = ipDao.getIpAddressById(ip);
		
		if(ipAddress == null) {
			ipAddress = new IPAddress();
			ipAddress.setIp(ip);
		} 
		
		ipAddress.setBanned(true);
		
		ipDao.saveIPAddress(ipAddress);
		
		return ipAddress;
	}

}

