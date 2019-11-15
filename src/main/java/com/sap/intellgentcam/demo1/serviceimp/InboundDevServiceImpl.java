package com.sap.intellgentcam.demo1.serviceimp;

import com.sap.cloud.sdk.odatav2.connectivity.ODataException;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.inbounddelivery.InbDeliveryItem;
import com.sap.cloud.sdk.s4hana.datamodel.odata.services.DefaultInboundDeliveryService;
import com.sap.intellgentcam.demo1.service.InboundDevService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author : Jenson.Liu
 * @date : 2019/10/25  2:11 下午
 */
@Service
public class InboundDevServiceImpl implements InboundDevService {

    private static final Logger logger = LoggerFactory.getLogger( InboundDevServiceImpl.class );

    @Override
    public InbDeliveryItem getInbDev() {
        InbDeliveryItem inbDeliveryItem;
        try{
                inbDeliveryItem = (InbDeliveryItem) new DefaultInboundDeliveryService().getAllInbDeliveryItem()
                        .select(InbDeliveryItem.REFERENCE_SD_DOCUMENT,
                                InbDeliveryItem.DELIVERY_DOCUMENT,
                                InbDeliveryItem.GOODS_MOVEMENT_STATUS
                        ).filter(InbDeliveryItem.DELIVERY_DOCUMENT.eq("180000021"))
                        .execute();
        }catch (ODataException e){
            logger.info("the purchaser order isn't existed or not right");
        }
        return null;
    }
}
