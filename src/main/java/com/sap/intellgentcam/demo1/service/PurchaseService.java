package com.sap.intellgentcam.demo1.service;

import com.sap.cloud.sdk.odatav2.connectivity.ODataException;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.purchaseorder.PurchaseOrder;
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
     * @throws ODataException
     */
    public PurchaseOrder getPurchase(String poNum) throws ODataException;

}
