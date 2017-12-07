package com.luxoft.recruitment.cstr.filter;

import com.luxoft.recruitment.cstr.http.Request;

import java.util.ArrayList;
import java.util.List;

public class BlackListFilter {

	private final List<String> blackList;

	public BlackListFilter() {
		blackList = new ArrayList<>();
		blackList.add("74.125.224.72");
	}

	public boolean shouldBlock(Request request) {
		if(blackList.contains(request.getIpAddress())) {
			return true;
		}
		return false;
	}
}
