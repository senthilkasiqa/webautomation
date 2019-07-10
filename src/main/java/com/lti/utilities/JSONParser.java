package com.lti.utilities;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

public class JSONParser {

    private String filepath;

    public JSONParser(String filepath){

        this.filepath=filepath;
    }


    public JSONArray getJsonParsedObjectAsJsonArray(){

        try{
                BufferedReader bufferedReader=new BufferedReader(new FileReader(new File(filepath)));
                String jsonContent= IOUtils.toString(bufferedReader);
                return new JSONArray(jsonContent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        return null;
    }


    public JSONObject getObjectFronJSON() {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filepath)));
            String jsonContent = IOUtils.toString(bufferedReader);
            return new JSONObject(jsonContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
