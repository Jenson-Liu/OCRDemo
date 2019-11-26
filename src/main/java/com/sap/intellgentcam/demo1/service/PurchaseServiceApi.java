package com.sap.intellgentcam.demo1.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author : Jenson.Liu
 * @date : 2019/11/22  4:59 下午
 */
@Service
public interface PurchaseServiceApi {

    /**
     * get PurchaseOrder with poNum
     * @param poNum
     * @return
     * @throws IOException
     */
    HashMap<String,String> getPurchase(String poNum) throws IOException;
}
