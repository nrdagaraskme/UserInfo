package com.liftoff.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by nrdagar on 08/10/17.
 */
public class UserCache {

    private static HashMap<String,String> userCache = new HashMap<String, String>();

    private static HashSet<String> userSession = new HashSet<String>();


    public static void addUser(String email , String token){

        userCache.put(email,token);
    }

    public static String  getToken(String email){

        return userCache.get(email);
    }

    public static HashSet<String> getUserSession() {
        return userSession;
    }

    public static boolean isValidUser(String token){
        return userSession.contains(token);
    }

    public static boolean addUserInSession(String token){
        return userSession.add(token);
    }

    public static String getUserIDFromToken(String token){

        for(Map.Entry<String,String> map:userCache.entrySet() ){

            if(map.getValue().equals(token))
                return map.getKey();

        }

        return null;
    }
}
