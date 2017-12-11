package com.luxoft.recruitment.cstr;

import com.luxoft.recruitment.cstr.datalayer.BlackListProvider;
import com.luxoft.recruitment.cstr.filter.BlackListFilter;
import com.luxoft.recruitment.cstr.service.HealthCheckService;

public class Context {

    private SuperApiController superApiController;

    private static Context instance;

    private Context() {}

    public static Context getInstance() {
        if(instance == null) {
            instance = new Context();
        }
        return instance;
    }

    public SuperApiController getSuperApiController() {
        if(superApiController == null) {
            HealthCheckService healthCheckService = new HealthCheckService();
            BlackListFilter blackListFilter = new BlackListFilter(BlackListProvider.getInstance());
            superApiController = new SuperApiController(healthCheckService, blackListFilter);
        }

        return superApiController;
    }
}
