package com.luxoft.recruitment.cstr;

import com.luxoft.recruitment.cstr.filter.BlackListFilter;
import com.luxoft.recruitment.cstr.http.Response;
import com.luxoft.recruitment.cstr.http.Request;
import com.luxoft.recruitment.cstr.service.HealthCheckService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.luxoft.recruitment.cstr.http.HttpStatus.BAD_REQUEST;
import static com.luxoft.recruitment.cstr.http.HttpStatus.OK;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SuperApiControllerTest {

	@Mock
	private HealthCheckService healthCheckService;

	@Mock
	private BlackListFilter blackListFilter;

	@InjectMocks
	private SuperApiController superApiController;

	@Test
	public void should_return_ok_http_status() {
		when(blackListFilter.shouldBlock(any(Request.class))).thenReturn(false);

		Request request = new Request("74.123.224.72");
		Response response = superApiController.healthCheck(request);

		assertEquals(response.getCode(), OK);
	}

	@Test
	public void should_return_bad_request_http_status() {
		when(blackListFilter.shouldBlock(any(Request.class))).thenReturn(true);

		Request request = new Request("74.123.224.72");
		Response response = superApiController.healthCheck(request);

		assertEquals(response.getCode(), BAD_REQUEST);
	}
}
