package com.mercadolibre.ipmonitor.dao;

import com.mercadolibre.ipmonitor.model.IPAddress;

public class IPAddressDao extends BasicDao {
	
	public IPAddress getIpAddressById(String ip) {
		return datastore.find(IPAddress.class).filter("ip", ip).get();
	}
	
	public void saveIPAddress(IPAddress ip) {
		datastore.save(ip);
	}
	
}
