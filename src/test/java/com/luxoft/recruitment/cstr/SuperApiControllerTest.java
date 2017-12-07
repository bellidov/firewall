package com.luxoft.recruitment.cstr;

import com.luxoft.recruitment.cstr.http.Response;
import com.luxoft.recruitment.cstr.http.Request;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.luxoft.recruitment.cstr.http.HttpStatus.OK;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class SuperApiControllerTest {
	private SuperApiController superApiController = new SuperApiController();

	@Test
	public void test1() {
		Request request = new Request("74.123.224.72");

		Response response = superApiController.healthCheck(request);

		assertEquals(response.getCode(), OK);
	}
}
