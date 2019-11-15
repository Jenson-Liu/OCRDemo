package com.sap.intellgentcam.demo1.service;

import org.springframework.stereotype.Service;

/**
 * @author : Jenson.Liu
 * @date : 2019/11/13  4:29 下午
 */
@Service
public interface RedisService {
    /**
     * set imageBase64
     * @param imageBase64
     * @return
     */
    public String setImgBase64(String imageBase64);

    /**
     * get ImgBase64 from redis
     * @return
     */
    public String getImageBase64();

    /**
     * set token
     * @param token
     * @return
     */
    public String setMLToken(String token);

    /**
     * get token
     * @return
     */
    public String getcsrfToken();

    /**
     * set token
     * @param token
     * @return
     */
    public void setcsrfToken(String token);

    /**
     * get token
     * @return
     */
    public String getMLToken();

    /**
     * set String with key and value
     * @param key
     * @param value
     */
    public void setString(String key,String value);

    /**
     * get value with key
     * @param key
     * @return
     */
    public String getString(String key);
}
