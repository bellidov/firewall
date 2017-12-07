package com.luxoft.recruitment.cstr.datalayer;

import java.util.ArrayList;
import java.util.List;

public class BlackListProvider {
    
    private static BlackListProvider provider;
    
    private List<String> blackList;
    
    private BlackListProvider(){
        
    }
    
    static {
        //read ip from blacklist.txt
    }
    
    public static BlackListProvider getInstance(){
        if(provider == null) {
            provider = new BlackListProvider();
        }
        return provider;
    }
    
    public List<String> getBlackList() {
        if(blackList == null) {
            blackList = new ArrayList<>();
        }
        return blackList;
    }
}
