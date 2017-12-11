package com.luxoft.recruitment.cstr.filter;

import com.luxoft.recruitment.cstr.datalayer.BlackListProvider;
import com.luxoft.recruitment.cstr.http.Request;

import java.util.ArrayList;
import java.util.List;

public class BlackListFilter {

	private BlackListProvider blackListProvider;
	private List<String> list;

	public BlackListFilter(BlackListProvider blackListProvider){
        this.blackListProvider = blackListProvider;
    }
    
    public void addIp(String ip){
		getList().add(ip);
	}

	public boolean shouldBlock(Request request) {
		return getList().contains(request.getIpAddress());
	}
	
	private List<String> getList() {
    	if(this.list == null && blackListProvider != null) {
			this.list = blackListProvider.getBlackList();
		}
		else if(this.list == null && blackListProvider == null){
    		list = new ArrayList<>();
		}
		
		return this.list;
	}
}
