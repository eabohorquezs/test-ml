package com.mercadolibre.ipmonitor.service;

import com.mercadolibre.ipmonitor.builder.IPAddressBuilder;
import com.mercadolibre.ipmonitor.model.IPAddress;

public class IpServiceImpl implements IpService {

	private IPAddressBuilder ipBuilder = new IPAddressBuilder();
	
	@Override
	public IPAddress getIpInformation(String ipAddress) throws Exception {
		return ipBuilder.build(ipAddress);		
	}

	@Override
	public void banIp(String ipAddress) throws Exception {
		ipBuilder.buildBannedIp(ipAddress);		
	}

}
