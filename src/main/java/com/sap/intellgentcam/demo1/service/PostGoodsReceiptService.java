package com.sap.intellgentcam.demo1.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author : Jenson.Liu
 * @date : 2019/10/29  9:55 上午
 */
@Service
public interface PostGoodsReceiptService {

    /**
     * get CSRF token to call api
     * @return
     * @throws IOException
     */
    public String getCsrfToken() throws IOException;

    /**
     * put away
     * @param deliveryNum
     * @param token
     * @return
     * @throws IOException
     */
    public boolean putAway(String deliveryNum,String token) throws IOException;

    /**
     * get etag to call api
     * @param deliveryNum
     * @param token
     * @return
     * @throws IOException
     */
    public String getEtagMatch(String deliveryNum,String token) throws IOException;

    /**
     * post goods receipt
      * @param deliveryNum
     * @param token
     * @param match
     * @return
     */
    public String postGoodsReceipt(String deliveryNum,String token,String match);
}
