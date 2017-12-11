package com.luxoft.recruitment.cstr.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BlackListProperties {
    private static String pathName;
    private static String fileName;
    private static String fullFilePath;

    private static final String filename = "application.properties";
    private static final Properties properties = new Properties();
    private static BlackListProperties instance;

    private BlackListProperties() {}

    static {
        try(InputStream input = BlackListProperties.class.getClassLoader().getResourceAsStream(filename)) {
            properties.load(input);
            fullFilePath = properties.getProperty("firewall.blacklist.path");
            File file = new File(fullFilePath);
            fileName = file.getName();
            pathName = file.getParent();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BlackListProperties getInstance(){
        if(instance == null) {
            instance = new BlackListProperties();
        }
        return instance;
    }

    public String getPathName(){
        return this.pathName;
    }

    public String getFileName(){
        return this.fileName;
    }

    public String getFullFilePath(){
        return this.fullFilePath;
    }
}
