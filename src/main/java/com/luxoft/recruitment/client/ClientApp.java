package com.luxoft.recruitment.client;

import com.luxoft.recruitment.cstr.SuperApiController;
import com.luxoft.recruitment.cstr.datalayer.BlackListProvider;
import com.luxoft.recruitment.cstr.http.Request;

public class ClientApp {

    private static SuperApiController controller = new SuperApiController();

    public static void main(String[] args){
        Request request = new Request("172.16.1.2");
        controller.healthCheck(request);
    }
}
