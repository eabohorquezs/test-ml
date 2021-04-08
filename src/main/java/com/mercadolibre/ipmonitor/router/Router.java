package com.mercadolibre.ipmonitor.router;

import static spark.Spark.after;
import static spark.Spark.before;

import com.mercadolibre.ipmonitor.filter.BasicAuthenticationFilter;

import io.github.cdimascio.dotenv.Dotenv;

import com.mercadolibre.ipmonitor.filter.AuthenticationDetails;

public class Router {
	
	final Dotenv dotenv = Dotenv.load();

	protected Router() {
		before(new BasicAuthenticationFilter("/ip/*", new AuthenticationDetails(dotenv.get("EXPECTED_USER"), dotenv.get("EXPECTED_PASSWD"))));

		after((request, response) -> {
			response.type("application/json");
		});
	}

}
