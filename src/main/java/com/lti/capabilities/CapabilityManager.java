package com.lti.capabilities;

import com.lti.utilities.ConfigFileManager;
import com.lti.utilities.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class CapabilityManager {

    private static CapabilityManager instance;
    private JSONObject capabilities;

    public CapabilityManager(){
        String capabilitiesFilePath=getCapabilityLocation();
        JSONParser jsonParser=new JSONParser(capabilitiesFilePath);
        capabilities=jsonParser.getObjectFronJSON();
    }

    public static CapabilityManager getInstance(){
        if(instance==null){
            instance= new CapabilityManager();
        }
        return instance;

    }

    private String getCapabilityLocation(){
        String path=System.getProperty("user.dir") + "/caps/"
                + "capabilities.json";
        String caps= ConfigFileManager.getInstance().getProperty("CAPS");
        if(caps !=null){
            Path userDefinedPath= FileSystems.getDefault().getPath(caps);
            if(!userDefinedPath.getParent().isAbsolute()){
                path=userDefinedPath.normalize().toAbsolutePath().toString();
            }
            else{
                path=userDefinedPath.toString();
            }
        }
        return path;
    }

    public JSONObject getCapabilityFromJsonKey(String key){
        boolean hasKey=capabilities.has(key);
        if(hasKey){
            return (JSONObject) capabilities.get(key);
        }
        else
            return null;
    }

    public JSONArray getCapabilitiesArraysFromKJsonKey(String key){
        return capabilities.getJSONArray(key);
    }


    public boolean getCapabilityBoolean(String key){
            if(capabilities.has(key)){
                return (Boolean) capabilities.get(key);
            }
            else
                return false;
    }

}
