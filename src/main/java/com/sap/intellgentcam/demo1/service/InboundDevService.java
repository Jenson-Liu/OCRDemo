package com.sap.intellgentcam.demo1.service;

import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.inbounddelivery.InbDeliveryItem;
import org.springframework.stereotype.Service;

/**
 * @author : Jenson.Liu
 * @date : 2019/10/25  2:02 下午
 */
@Service
public interface InboundDevService {

    /**
     * get InbDeliveryItem
     * @return
     */
    public InbDeliveryItem getInbDev();
}
