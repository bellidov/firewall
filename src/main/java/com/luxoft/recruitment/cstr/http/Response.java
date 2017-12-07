package com.luxoft.recruitment.cstr.http;

public class Response {
	private final HttpStatus code;

	public Response(HttpStatus code) {
		this.code = code;
	}

	public HttpStatus getCode() {
		return code;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
