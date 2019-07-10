package com.lti.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigFileManager {
    public static Map<String,String> configFileMap=new HashMap<>();
    private static Properties prop=new Properties();
    private static ConfigFileManager instance;

    private ConfigFileManager(String configFile)throws IOException {
        FileInputStream fileInputStream=new FileInputStream(configFile);
        prop.load(fileInputStream);
    }

    public static ConfigFileManager getInstance(){
        if(instance==null){
            String configFile="config.properties";
            try{
                if(System.getenv().containsKey("CONFIG_FILE")){
                    configFile=System.getenv().get("CONFI_FILE");
                    System.out.println("Using config file from "+ configFile);
                }
                instance=new ConfigFileManager(configFile);
                Enumeration keys =prop.propertyNames();
                while (keys.hasMoreElements()){
                    String key= (String) keys.nextElement();
                    String value= (null==System.getenv(key))? prop.getProperty(key): System.getenv(key);
                    configFileMap.put(key,value);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
        return instance;
    }

    public String getProperty(String value){
        return configFileMap.get(value);
    }

    public String getProperty(String key,String value){
        return configFileMap.getOrDefault(key,value);
    }
}
