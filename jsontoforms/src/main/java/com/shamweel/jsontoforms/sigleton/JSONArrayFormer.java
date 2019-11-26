package com.shamweel.jsontoforms.sigleton;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONArrayFormer {

    public  static  JSONArray jsonArray;


    public static void   initJSONArray(){
        jsonArray = new JSONArray();
    }


    public static void addToJSONArray(JSONObject jsonObject){
        jsonArray.put(jsonObject);
    }


    public static JSONArray getJsonArray(){
        return jsonArray;
    }



}
