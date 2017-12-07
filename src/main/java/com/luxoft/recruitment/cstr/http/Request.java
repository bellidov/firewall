package com.luxoft.recruitment.cstr.http;

public class Request {
	private final String ipAddress;

	public Request(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getIpAddress() {
		return ipAddress;
	}
}
