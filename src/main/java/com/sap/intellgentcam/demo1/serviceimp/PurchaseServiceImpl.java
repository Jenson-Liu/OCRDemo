package com.sap.intellgentcam.demo1.serviceimp;

import com.sap.cloud.sdk.odatav2.connectivity.ODataException;
import com.sap.cloud.sdk.s4hana.connectivity.ErpConfigContext;
import com.sap.cloud.sdk.s4hana.datamodel.odata.services.DefaultPurchaseOrderService;
import com.sap.intellgentcam.demo1.service.PurchaseService;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.purchaseorder.PurchaseOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Jenson.Liu
 * @date : 2019/10/23  9:45 上午
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {

    private static final Logger logger = LoggerFactory.getLogger( PurchaseServiceImpl.class );

    /**
     * check the Po
     * @param poNum
     * @return
     * @throws ODataException
     */
    @Override
    public PurchaseOrder getPurchase(String poNum) throws ODataException{
        logger.info("ALLOW_MOCKED_AUTH_HEADER: " + System.getenv("ALLOW_MOCKED_AUTH_HEADER"));
        logger.info("Destinations: " + System.getenv("Destinations"));

        String destinations = "ErpQueryEndpoint";
        ErpConfigContext erpConfigContext = new ErpConfigContext("ErpQueryEndpoint");
        List<PurchaseOrder> purchaseOrder = null;
        logger.info("erpConfigContext info:"+erpConfigContext.toString());
        purchaseOrder =
                new DefaultPurchaseOrderService().getAllPurchaseOrder()
                        .select( PurchaseOrder.PURCHASE_ORDER,
                                PurchaseOrder.COMPANY_CODE,
                                PurchaseOrder.PURCHASE_ORDER_SUBTYPE,
                                PurchaseOrder.PURCHASE_ORDER_TYPE,
                                PurchaseOrder.SUPPLIER,
                                PurchaseOrder.LANGUAGE,
                                PurchaseOrder.PURCHASING_ORGANIZATION,
                                PurchaseOrder.PURCHASING_GROUP,
                                PurchaseOrder.DOCUMENT_CURRENCY,
                                PurchaseOrder.PURCHASE_ORDER_DATE,
                                PurchaseOrder.PURCHASING_COMPLETENESS_STATUS,
                                PurchaseOrder.PURCHASING_PROCESSING_STATUS )
                        .filter(PurchaseOrder.PURCHASE_ORDER.eq(poNum))
                        .execute();

        if(purchaseOrder.size()>=1){
            System.out.println(purchaseOrder.get(0).toString());
            return purchaseOrder.get(0);
        }else {
            return null;
        }
    }


}
