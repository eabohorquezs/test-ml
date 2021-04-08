package com.mercadolibre.ipmonitor.dao;

import com.mercadolibre.ipmonitor.model.Currency;

public class CurrencyDao extends BasicDao {

	public Currency getCurrencyByCode(String currencyCode) {
		return datastore.find(Currency.class).filter("currencyCode", currencyCode).get();
	}
	
	public void saveCurrency(Currency currency) {
		datastore.save(currency);
	}
}
