package com.luxoft.recruitment.cstr;

import com.luxoft.recruitment.cstr.http.Response;
import com.luxoft.recruitment.cstr.filter.BlackListFilter;
import com.luxoft.recruitment.cstr.http.Request;
import com.luxoft.recruitment.cstr.service.HealthCheckService;

import static com.luxoft.recruitment.cstr.http.HttpStatus.BAD_REQUEST;
import static com.luxoft.recruitment.cstr.http.HttpStatus.OK;

class SuperApiController {

	private BlackListFilter blackListBlocker;
	private HealthCheckService healthCheckService;

	SuperApiController() {
		this.blackListBlocker = new BlackListFilter();
		this.healthCheckService = new HealthCheckService();
	}

	Response healthCheck(Request request) {
		if (blackListBlocker.shouldBlock(request)) {
			return new Response(BAD_REQUEST);
		}

		healthCheckService.longOperation();
		return new Response(OK);
	}
}
