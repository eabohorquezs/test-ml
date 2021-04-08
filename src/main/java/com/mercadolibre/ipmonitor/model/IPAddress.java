package com.mercadolibre.ipmonitor.model;

import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class IPAddress {
	
	@Id
	private String ip;
	private boolean isBanned;
	@Reference
	private Country country;
	
	public String getIp() {
		return ip;
	}	
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public boolean getIsBanned() {
		return isBanned;
	}
	public void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}
	
}
