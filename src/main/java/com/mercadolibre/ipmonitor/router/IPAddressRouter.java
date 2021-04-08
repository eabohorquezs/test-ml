package com.mercadolibre.ipmonitor.router;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mercadolibre.ipmonitor.response.Response;
import com.mercadolibre.ipmonitor.response.StatusResponse;
import com.mercadolibre.ipmonitor.service.IpServiceImpl;
import com.mercadolibre.ipmonitor.utils.LocalDateGsonAdapter;

import static spark.Spark.get;
import static spark.Spark.post;

import java.time.LocalDate;

public class IPAddressRouter extends Router {
	
	public IPAddressRouter(IpServiceImpl service) {
		get("/ip/info/:ip", (request, response) -> {
            try {
            	Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateGsonAdapter()).create();
            	
                return new Gson().toJson(
                  new Response(StatusResponse.SUCCESS,
                		  gson.toJsonTree(service.getIpInformation(request.params(":ip")))));
                
            } catch (Exception ex) {
            	response.status(400);
                return new Gson().toJson(new Response(StatusResponse.ERROR, ex.getMessage()));
            }
        });
		
		post("/ip/ban/:ip", (request, response) -> {
            try {
            	service.banIp(request.params(":ip"));

            	return new Gson().toJson(new Response(StatusResponse.SUCCESS, "IP address correctly banned"));
            } catch (Exception ex) {
            	response.status(400);
            	return new Gson().toJson(new Response(StatusResponse.ERROR, ex.getMessage()));
            }
        });
    }
}
