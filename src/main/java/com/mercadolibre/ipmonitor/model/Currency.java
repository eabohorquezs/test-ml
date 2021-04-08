package com.mercadolibre.ipmonitor.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Currency {
	@Id
	private String currencyCode;
	private String currencyName;
	private LocalDate ratesDate;
	private BigDecimal usdRate;
	private BigDecimal eurRate;
	
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	public LocalDate getRatesDate() {
		return ratesDate;
	}
	public void setRatesDate(LocalDate ratesDate) {
		this.ratesDate = ratesDate;
	}
	public BigDecimal getUsdRate() {
		return usdRate;
	}
	public void setUsdRate(BigDecimal usdRate) {
		this.usdRate = usdRate;
	}
	public BigDecimal getEurRate() {
		return eurRate;
	}
	public void setEurRate(BigDecimal eurRate) {
		this.eurRate = eurRate;
	}
	
}
