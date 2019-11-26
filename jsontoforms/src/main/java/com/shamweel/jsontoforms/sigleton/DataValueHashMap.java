package com.shamweel.jsontoforms.sigleton;

import com.shamweel.jsontoforms.FormConstants;

import java.util.HashMap;
import java.util.Map;


public class DataValueHashMap {

    public static HashMap<String, String> dataValueHashMap;

    public static boolean init(){
        dataValueHashMap = new HashMap<>();
        return true;
    }

    public static  boolean put(String _id, String value){
        dataValueHashMap.put(_id, value);
      //  Log.d("_id : "+ _id , "value : "+ value);
        return true;
    }


    public static String getValue(String _id){
        String value = FormConstants.EMPTY_STRING;
        if (dataValueHashMap == null){
            return value;
        }
        outer: for (Map.Entry<String, String> e : dataValueHashMap.entrySet()) {
            if (e.getKey().equalsIgnoreCase(_id)) {
                value = e.getValue();
                break outer;
            }
        }
        return  value;
    }

    public static boolean remove(String _id){
        dataValueHashMap.remove(_id);
        return true;
    }

    public static boolean clear(){
        dataValueHashMap.clear();
        return true;
    }

}
