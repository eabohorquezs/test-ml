package com.mercadolibre.ipmonitor.service;

import com.mercadolibre.ipmonitor.model.IPAddress;

public interface IpService {
	public IPAddress getIpInformation(String ipAddress) throws Exception;
    
    public void banIp(String ipAddress) throws Exception ;
}
