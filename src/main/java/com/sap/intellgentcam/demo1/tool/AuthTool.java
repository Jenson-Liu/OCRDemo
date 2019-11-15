package com.sap.intellgentcam.demo1.tool;

import java.util.HashMap;

/**
 * @author : Jenson.Liu
 * @date : 2019/10/29  10:15 上午
 */
public class AuthTool {

    public static HashMap<String,String> getAuth(){
        String auth = System.getenv("destinations");
        HashMap<String,String> hashMap = getAccount(auth);
        return hashMap;
    }

    public static HashMap<String,String> getAccount(String auth) {
        int username = auth.indexOf("username");
        int middle = auth.indexOf("password");
        int password = auth.indexOf(",properties");
        HashMap<String,String> hashMap = new HashMap<>(2);
        hashMap.put("username",auth.substring(username+10,middle-2));
        hashMap.put("password",auth.substring(middle+10,password-2));
        return hashMap;
    }

}
