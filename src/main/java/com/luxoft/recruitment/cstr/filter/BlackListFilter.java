package com.luxoft.recruitment.cstr.filter;

import com.luxoft.recruitment.cstr.datalayer.BlackListProvider;
import com.luxoft.recruitment.cstr.http.Request;

import java.util.List;

public class BlackListFilter {

	private BlackListProvider blackListProvider;

	public BlackListFilter(BlackListProvider blackListProvider){
        this.blackListProvider = blackListProvider;
    }

	public boolean shouldBlock(Request request) {
	    List<String> blackList;
	    if(blackListProvider == null || (blackList = blackListProvider.getBlackList()) == null){
            return false;
        }
        else {
	        return blackList.contains(request.getIpAddress());
        }
	}
}
