package com.luxoft.recruitment.cstr.filter;

import com.luxoft.recruitment.cstr.datalayer.BlackListProvider;
import com.luxoft.recruitment.cstr.http.Request;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BlackListFilter {

	private BlackListProvider blackListProvider;

	public BlackListFilter(BlackListProvider blackListProvider){
        this.blackListProvider = blackListProvider;
        list = new ArrayList<>(blackListProvider.getBlackList());
    }

    private List<String> list;

    public boolean addIp(String ip){
		//String ip = request.getIpAddress();
		list.add(ip);
		return true;
	}

	public boolean shouldBlock(Request request) {
	    List<String> blackList;
	    if(blackListProvider == null || list == null){
            return false;
        }
        else {
	        return list.contains(request.getIpAddress());
        }
	}
}
