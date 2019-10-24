package com.sap.intellgentcam.demo1.service;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @author : Jenson.Liu
 * @date : 2019/10/23  9:43 上午
 */
@Service
public interface PurchaseService {

    /**
     * find purchase order in system
     * @param poNum
     * @return
     */
    public String getPurchase(String poNum);

    String getPurchase(Logger logger);
}
