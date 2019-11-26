package com.sap.intellgentcam.demo1.pojo;

/**
 * @author : Jenson.Liu
 * @date : 2019/10/29  10:49 上午
 */
// useless
public class Destination {
    String name;
    String url;
    String username;
    String password;
    Object properties;

    public Destination(String name, String url, String username, String password, Object properties) {
        this.name = name;
        this.url = url;
        this.username = username;
        this.password = password;
        this.properties = properties;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object getProperties() {
        return properties;
    }

    public void setProperties(Object properties) {
        this.properties = properties;
    }
}
