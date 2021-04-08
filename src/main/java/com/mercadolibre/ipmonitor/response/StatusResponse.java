package com.mercadolibre.ipmonitor.response;

public enum StatusResponse {
	ERROR("Error"),
	SUCCESS("Success");
 
    private String status;       
    
    private StatusResponse(String status) {
    	this.status = status;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
