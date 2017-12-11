package com.luxoft.recruitment.cstr;

import com.luxoft.recruitment.cstr.datalayer.BlackListProvider;
import com.luxoft.recruitment.cstr.http.Response;
import com.luxoft.recruitment.cstr.filter.BlackListFilter;
import com.luxoft.recruitment.cstr.http.Request;
import com.luxoft.recruitment.cstr.service.HealthCheckService;

import java.util.logging.Logger;

import static com.luxoft.recruitment.cstr.http.HttpStatus.BAD_REQUEST;
import static com.luxoft.recruitment.cstr.http.HttpStatus.OK;

public class SuperApiController {

	//private static final Logger LOGGER = Logger.getLogger(SuperApiController.class.getName());

	private BlackListFilter blackListBlocker;
	private HealthCheckService healthCheckService;

	public SuperApiController() {
		this.blackListBlocker = new BlackListFilter();
		this.healthCheckService = new HealthCheckService();
	}

	public Response healthCheck(Request request) {
		System.out.println("Starting healthCheck request.");
		if (blackListBlocker.shouldBlock(request)) {
			return new Response(BAD_REQUEST);
		}

		healthCheckService.longOperation();

		System.out.println("Finishing healthCheck request.");
		return new Response(OK);
	}
}
