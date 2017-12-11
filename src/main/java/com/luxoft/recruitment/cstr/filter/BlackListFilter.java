package com.luxoft.recruitment.cstr.filter;

import com.luxoft.recruitment.cstr.datalayer.BlackListProvider;
import com.luxoft.recruitment.cstr.http.Request;

import java.util.List;

public class BlackListFilter {

	private final List<String> blackList;

	public BlackListFilter() {
		blackList = BlackListProvider.getInstance().getBlackList();
	}

	public boolean shouldBlock(Request request) {
		if(blackList.contains(request.getIpAddress())) {
			return true;
		}
		return false;
	}
}
