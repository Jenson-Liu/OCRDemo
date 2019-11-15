package com.sap.intellgentcam.demo1.serviceimp;

import com.sap.intellgentcam.demo1.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author : Jenson.Liu
 * @date : 2019/11/13  4:33 下午
 */
@Service
public class RedisServiceImpl implements RedisService {

    private static int exprietime = 60*30;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String setImgBase64(String imageBase64) {
        stringRedisTemplate.opsForValue().set("img",imageBase64);
        return imageBase64;
    }

    @Override
    public String getImageBase64() {
        String imageBase64 = stringRedisTemplate.opsForValue().get("img");
        return imageBase64;
    }

    @Override
    public String setMLToken(String token) {
        stringRedisTemplate.opsForValue().set("ml-token",token,exprietime, TimeUnit.SECONDS);
        return token;
    }

    @Override
    public String getcsrfToken() {
        return stringRedisTemplate.opsForValue().get("csrf-token");
    }

    @Override
    public void setcsrfToken(String token) {
        stringRedisTemplate.opsForValue().set("csrf-token",token);
    }

    @Override
    public String getMLToken() {
        String token = stringRedisTemplate.opsForValue().get("ml-token");
        return token;
    }

    @Override
    public void setString(String key, String value) {
        stringRedisTemplate.opsForValue().set(key,value);
    }

    @Override
    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get("poNum");
    }
}
